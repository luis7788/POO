package spotifyum.playlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spotifyum.musica.Musica;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para os testes das Listas de Favoritos.
 */
public class ListaFavoritosTest {

    /**
     * Teste para o construtor parameterizado da classe ListaFavoritos.
     */
    @Test
    public void testListaFavoritosConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        ListaFavoritos lista = new ListaFavoritos("nome", musicas, "utilizador");
        
        assertNotNull(lista);
        assertEquals("nome", lista.getNome());
        assertEquals(musicas, lista.getMusicas());
        assertFalse(lista.getPublica());
        assertEquals("Sistema", lista.getAutor());
        assertEquals("utilizador", lista.getUtilizador());
    }

    /**
     * Teste para o construtor de cópia da classe ListaFavoritos.
     */
    @Test
    public void testListaFavoritosConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        ListaFavoritos lista = new ListaFavoritos("nome", musicas, "utilizador");

        ListaFavoritos listaCopia = new ListaFavoritos(lista);

        assertEquals(lista, listaCopia);
        assertEquals(lista.getUtilizador(), listaCopia.getUtilizador());
    }

    /**
     * Teste para o método clone da classe ListaFavoritos.
     */
    @Test
    public void testListaFavoritosClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        ListaFavoritos lista = new ListaFavoritos("nome", musicas, "utilizador");

        ListaFavoritos listaClone = lista.clone();

        assertEquals(lista, listaClone);
        assertEquals(lista.getUtilizador(), listaClone.getUtilizador());
    }
}
