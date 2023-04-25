package br.com.value.jogo.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dominio.Resultado;
import dominio.Jogo;
import dominio.Participante;

public class CriadorDeJogo {

	private String descricao;
	private Calendar data;
	private List<Resultado> resultados;
	private boolean finalizado;

	public CriadorDeJogo() {
		this.data = Calendar.getInstance();
		resultados = new ArrayList<Resultado>();
	}
	
	public CriadorDeJogo para(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public CriadorDeJogo naData(Calendar data) {
		this.data = data;
		return this;
	}

	public CriadorDeJogo resultado(Participante participante, double metrica) {
		resultados.add(new Resultado(participante, metrica));
		return this;
	}

	public CriadorDeJogo finalizado() {
		this.finalizado = true;
		return this;
	}

	public Jogo constroi() {
		Jogo jogo = new Jogo(descricao, data);
		for(Resultado resultadoDado : resultados) jogo.anota(resultadoDado);
		if(finalizado) jogo.finaliza();
				
		return jogo;
	}

}
