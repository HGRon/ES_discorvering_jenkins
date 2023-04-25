package valueprojects.mock_na_pratica;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Resultado;
import dominio.Jogo;
import dominio.Participante;
import service.Juiz;
import br.com.value.jogo.builder.*;

import org.junit.Before;

public class JuizTest {
	
	private Juiz juiz;
	private Participante laura;
	private Participante leonardo;
	private Participante andreia;

	@Before
	public void criaJulgamento() {
		this.juiz = new Juiz();
		this.laura = new Participante("Laura");
		this.leonardo = new Participante("Leonardo");
		this.andreia = new Participante("Andreia");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveTerJogoSemNenhumResultado() {
		Jogo jogo = new CriadorDeJogo().para("Cata Moedas").constroi();
		
		juiz.avalia(jogo);
		
	}
	
     
    @Test
    public void deveTerJogoComApenasUmResultado() {
    	Participante leonardo = new Participante("Leonardo");
        Jogo jogo = new Jogo("Ca�a Moedas");
         
        jogo.anota(new Resultado(leonardo, 1000.0));
         
        juiz.avalia(jogo);
         
        assertEquals(1000.0, juiz.getMaiorResultado(), 0.00001);
        assertEquals(1000.0, juiz.getMenorResulatado(), 0.00001);
    }
     
       
}
