package spotifyum.utilizador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spotifyum.planos.PlanoFree;

/**
 * Classe para os testes dos utilizadores.
 */
public class UtilizadorTest {

    /**
     * Teste para o construtor parameterizado da classe Utilizador.
     */
    @Test
    public void testUtilizadorConstrutorParameterizado(){
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());

        assertNotNull(user);
        assertEquals("U1", user.getId());
        assertEquals("João Jesus", user.getNome());
        assertEquals("joao.jesus@exemplo.pt", user.getEmail());
        assertEquals("Braga", user.getMorada());
        assertTrue(user.getPlano() instanceof PlanoFree);
    }

    /**
     * Teste para o construtor de cópia da classe Utilizador.
     */
    @Test
    public void testUtilizadorConstrutorCopia(){
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());

        Utilizador userCopia = new Utilizador(user);

        assertNotNull(userCopia);
        assertEquals(user.getId(), userCopia.getId());
        assertEquals(user.getNome(), userCopia.getNome());
        assertEquals(user.getEmail(), userCopia.getEmail());
        assertEquals(user.getMorada(), userCopia.getMorada());
        assertEquals(user.getPlano(), userCopia.getPlano());
    }

    /**
     * Teste para o método clone da classe Utilizador.
     */
    @Test
    public void testClone(){
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());
        Utilizador userClone = user.clone();

        assertNotNull(userClone);
        assertEquals(user.getId(), userClone.getId());
        assertEquals(user.getNome(), userClone.getNome());
        assertEquals(user.getEmail(), userClone.getEmail());
        assertEquals(user.getMorada(), userClone.getMorada());
        assertEquals(user.getPlano(), userClone.getPlano());
    }

    /**
     * Teste para o método atualizarPontos da classe Utilizador.
     * Verifica se os pontos são atualizados corretamente.
     */
    @Test
    public void testAtualizarPontos(){
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());

        user.atualizarPontos();

        assertEquals(user.getPontos(), 5);
    }
}
