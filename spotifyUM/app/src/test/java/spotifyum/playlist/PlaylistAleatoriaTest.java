package spotifyum.playlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spotifyum.musica.Musica;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para os testes das Playlists Aleatórias.
 */
public class PlaylistAleatoriaTest {

    /**
     * Teste para o construtor parameterizado da classe PlaylistAleatoria.
     */
    @Test
    public void testPlaylistConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Playlist playlist = new PlaylistAleatoria("nome", musicas);
        
        assertNotNull(playlist);
        assertEquals("nome", playlist.getNome());
        assertEquals(musicas, playlist.getMusicas());
        assertTrue(playlist.getPublica());
        assertEquals("Sistema", playlist.getAutor());
    }

    /**
     * Teste para o construtor de cópia da classe PlaylistAleatoria.
     */
    @Test
    public void testPlaylistConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Playlist playlist = new PlaylistAleatoria("nome", musicas);

        Playlist playlistCopia = new PlaylistAleatoria(playlist);

        assertEquals(playlist, playlistCopia);
    }
    
    /**
     * Teste para o método clone da classe PlaylistAleatoria.
     */
    @Test
    public void testPlaylistClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Playlist playlist = new PlaylistAleatoria("nome", musicas);

        Playlist playlistClone = playlist.clone();

        assertEquals(playlist, playlistClone);
    }
}
