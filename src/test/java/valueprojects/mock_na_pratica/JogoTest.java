package valueprojects.mock_na_pratica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Jogo;
import dominio.Participante;
import dominio.Resultado;

public class JogoTest
{

	@Test
	public void deveTerUmResultado() {
		Jogo jogo = new Jogo("Caça medalhas");
		assertEquals(0, jogo.getResultados().size());
		
		jogo.anota(new Resultado(new Participante("Leonardo"), 2000));
		
		assertEquals(1, jogo.getResultados().size());
		assertEquals(2000.0, jogo.getResultados().get(0).getMetrica(), 0.00001);
	}
	
	@Test
	public void deveTerVariosResultados() {
		Jogo jogo = new Jogo("Caça Medalhas");
		jogo.anota(new Resultado(new Participante("Steve Jobs"), 2000));
		jogo.anota(new Resultado(new Participante("Steve Wozniak"), 3000));
		
		assertEquals(2, jogo.getResultados().size());
		assertEquals(2000.0, jogo.getResultados().get(0).getMetrica(), 0.00001);
		assertEquals(3000.0, jogo.getResultados().get(1).getMetrica(), 0.00001);
	}
	
	@Test
	public void naoDevePermitirDoisResultadosMesmoParticipante() {
		Participante leonardo = new Participante("Leonardo");
		Jogo jogo = new Jogo("Caça Moedas");
		jogo.anota(new Resultado(leonardo, 2000));
		jogo.anota(new Resultado(leonardo, 3000));
		
		assertEquals(1, jogo.getResultados().size());
		assertEquals(2000.0, jogo.getResultados().get(0).getMetrica(), 0.00001);
	}
	
	@Test
	public void naoDevePerimitirMaisQueCincoResultados() {
		Participante leonardo = new Participante("Leonardo");
		Participante laura = new Participante("Laura");

		Jogo jogo = new Jogo("cata moedas");
		jogo.anota(new Resultado(leonardo, 300));
		jogo.anota(new Resultado(laura, 400));
		jogo.anota(new Resultado(leonardo, 500));
		jogo.anota(new Resultado(laura, 600));
		jogo.anota(new Resultado(leonardo, 700));
		jogo.anota(new Resultado(laura, 800));
		jogo.anota(new Resultado(leonardo, 900));
		jogo.anota(new Resultado(laura, 1000));
		jogo.anota(new Resultado(leonardo, 1100));
		jogo.anota(new Resultado(laura, 1200));
		jogo.anota(new Resultado(leonardo, 1300));
		
		assertEquals(10, jogo.getResultados().size());
		assertEquals(1200.0, jogo.getResultados().get(jogo.getResultados().size()-1).getMetrica(), 0.00001);
	}	
}


