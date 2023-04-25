package service;

import dominio.Participante;

public class EnviaSms {
	
	public void enviarSmsVitoria(Participante participante) {
		System.out.println("SMS enviado para " + participante.getNome());
	}
	
}
