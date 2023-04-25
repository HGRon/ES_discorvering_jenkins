package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import 
dominio.Resultado;
import dominio.Jogo;

public class Juiz {

	private double maiorResultado = Double.NEGATIVE_INFINITY;
	private double menorResultado = Double.POSITIVE_INFINITY;
	private List<Resultado> maiores;

	public void avalia(Jogo jogo) {
		
		if(jogo.getResultados().size() == 0) {
			throw new RuntimeException("N�o � poss�vel jugar sem Resultados dos jogos!");
		}
		
		for(Resultado resultado : jogo.getResultados()) {
			if(resultado.getMetrica() > maiorResultado) maiorResultado = resultado.getMetrica();
			if (resultado.getMetrica() < menorResultado) menorResultado = resultado.getMetrica();
		}
		
		tresMaiores(jogo);
	}

	private void tresMaiores(Jogo jogo) {
		maiores = new ArrayList<Resultado>(jogo.getResultados());
		Collections.sort(maiores, new Comparator<Resultado>() {

			public int compare(Resultado o1, Resultado o2) {
				if(o1.getMetrica() < o2.getMetrica()) return 1;
				if(o1.getMetrica() > o2.getMetrica()) return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public List<Resultado> getTresMaiores() {
		return maiores;
	}
	
	public double getMaiorResultado() {
		return maiorResultado;
	}
	
	public double getMenorResulatado() {
		return menorResultado;
	}
}
