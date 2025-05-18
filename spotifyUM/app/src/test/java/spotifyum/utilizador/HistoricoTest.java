package spotifyum.utilizador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Classe para os testes do histórico de reprodução.
 */
public class HistoricoTest {
    
    /**
     * Teste para o construtor parameterizado da classe Historico.
     */
    @Test
    public void testHistoricoConstrutorParameterizado(){
        Historico historico = new Historico("M1");

        assertNotNull(historico);
        assertEquals("M1", historico.getIdMusica());
        assertNotNull(historico.getDataReproducao());
    }

    /**
     * Teste para o construtor de cópia da classe Historico.
     */
    @Test
    public void testHistoricoConstrutorCopia(){
        Historico historicoOriginal = new Historico("M1");
        Historico historicoCopia = new Historico(historicoOriginal);

        assertNotNull(historicoCopia);
        assertEquals(historicoOriginal.getIdMusica(), historicoCopia.getIdMusica());
        assertEquals(historicoOriginal.getDataReproducao(), historicoCopia.getDataReproducao());
    }

    /**
     * Teste para o método clone da classe Historico.
     */
    @Test
    public void testClone(){
        Historico historicoOriginal = new Historico("M1");
        Historico historicoClone = historicoOriginal.clone();

        assertNotNull(historicoClone);
        assertEquals(historicoOriginal.getIdMusica(), historicoClone.getIdMusica());
        assertEquals(historicoOriginal.getDataReproducao(), historicoClone.getDataReproducao());
    }
}
