package valueprojects.mock_na_pratica;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.value.jogo.builder.CriadorDeJogo;
import dominio.Jogo;
import dominio.Participante;
import infra.JogoDao;
import service.EnviaSms;
import service.FinalizaJogo;
import static org.mockito.Mockito.*;

public class FinalizaJogoTest {
	
	 @Test
	    public void deveFinalizarJogosDaSemanaAnterior() {

	        Calendar antiga = Calendar.getInstance();
	        antiga.set(1999, 1, 20);

	        Jogo jogo1 = new CriadorDeJogo().para("Ca�a moedas")
	            .naData(antiga).constroi();
	        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras")
	            .naData(antiga).constroi();

	        // mock no lugar de dao falso
	        
	        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

	        JogoDao daoFalso = mock(JogoDao.class);
	        EnviaSms enviaSms = mock(EnviaSms.class);

	        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

	        FinalizaJogo finalizador = new FinalizaJogo(daoFalso, enviaSms);
	        finalizador.finaliza();

	        assertTrue(jogo1.isFinalizado());
	        assertTrue(jogo2.isFinalizado());
	        assertEquals(2, finalizador.getTotalFinalizados());
	        Mockito.verifyNoInteractions(enviaSms);
	    }
	 
	 @Test
		public void deveVerificarSeMetodoAtualizaFoiInvocado() {

			Calendar antiga = Calendar.getInstance();
			antiga.set(1999, 1, 20);

			Jogo jogo1 = new CriadorDeJogo().para("Cata moedas").naData(antiga).constroi();
			Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras").naData(antiga).constroi();

			// mock no lugar de dao falso

			List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

			JogoDao daoFalso = mock(JogoDao.class);
	        EnviaSms enviaSms = mock(EnviaSms.class);

			when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

			FinalizaJogo finalizador = new FinalizaJogo(daoFalso, enviaSms);
			finalizador.finaliza();

			verify(daoFalso, times(1)).atualiza(jogo1);
			verify(daoFalso, times(1)).atualiza(jogo2);
	        Mockito.verifyNoInteractions(enviaSms);
		}
		
		@Test
		public void deveEnviarSmsAposSalvarOsDados() {
			Calendar antiga = Calendar.getInstance();
	        antiga.set(1999, 1, 20);
	        
	        Participante participante = new Participante("Gilberto");

	        Jogo jogo1 = new CriadorDeJogo().para("Ca�a moedas")
	            .naData(antiga).resultado(participante, 10).constroi();
	        Jogo jogo2 = new CriadorDeJogo().para("Derruba barreiras")
	            .naData(antiga).resultado(participante, 2).constroi();

	        // mock no lugar de dao falso
	        
	        List<Jogo> jogosAnteriores = Arrays.asList(jogo1, jogo2);

	        JogoDao daoFalso = mock(JogoDao.class);
	        EnviaSms enviaSms = mock(EnviaSms.class);

	        when(daoFalso.emAndamento()).thenReturn(jogosAnteriores);

	        FinalizaJogo finalizador = new FinalizaJogo(daoFalso, enviaSms);
	        finalizador.finaliza();

	        assertTrue(jogo1.isFinalizado());
	        assertTrue(jogo2.isFinalizado());
	        verify(enviaSms, times(2)).enviarSmsVitoria(participante);
		}
	 
	 
		 
	}

 

	
	

	
