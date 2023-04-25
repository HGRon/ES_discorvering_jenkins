package service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import dominio.Jogo;
import dominio.Participante;
import dominio.Resultado;
import infra.JogoDao;

public class FinalizaJogo {

	private int total = 0;
	private final JogoDao dao;
	private final EnviaSms enviaSms;

	public FinalizaJogo(JogoDao dao, EnviaSms enviaSms) {
		this.dao = dao;
		this.enviaSms = enviaSms;
	}

	public void finaliza() {
		List<Jogo> todosJogosEmAndamento = dao.emAndamento();

		for (Jogo jogo : todosJogosEmAndamento) {
			if (iniciouSemanaAnterior(jogo)) {
				jogo.finaliza();
				jogo.setVencedor(getVencedor(jogo.getResultados()));
				total++;
				dao.atualiza(jogo);
			}
		}
		
		
		for (Jogo jogo : todosJogosEmAndamento) {
			if (jogo.isFinalizado()) {
				Participante vencedor = jogo.getVencedor();
				
				if (vencedor != null) {
					this.enviaSms.enviarSmsVitoria(vencedor);
				}
			}
		}
	}

	private boolean iniciouSemanaAnterior(Jogo jogo) {
		return diasEntre(jogo.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}
	
	private Participante getVencedor(List<Resultado> resultados) {
		HashMap<Participante, Double> metricas = new HashMap<Participante, Double>();
		Participante vencedor = null;
		double maiorMetrica = 0;
		
		for (Resultado resultado : resultados) {
			Participante participante = resultado.getParticipante();
			Double totalMetrica = metricas.get(participante);
			
			if (totalMetrica == null) {
				totalMetrica = 0.0;
			}
			
			totalMetrica += resultado.getMetrica();
			
			metricas.put(participante, totalMetrica);
			
			if (maiorMetrica < totalMetrica) {
				maiorMetrica = totalMetrica;
				vencedor = participante;
			}
		}
		
		return vencedor;
	}

	public int getTotalFinalizados() {
		return total;
	}
}
