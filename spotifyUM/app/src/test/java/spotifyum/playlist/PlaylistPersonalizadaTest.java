package spotifyum.playlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spotifyum.musica.Musica;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para os testes das Playlists Personalizadas.
 */
public class PlaylistPersonalizadaTest {

    /**
     * Teste para o construtor parameterizado da classe PlaylistPersonalizada.
     */
    @Test
    public void testPlaylistPersonalizadaConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistPersonalizada playlist = new PlaylistPersonalizada("nome", musicas, true, "Autor");
        
        assertNotNull(playlist);
        assertEquals("nome", playlist.getNome());
        assertEquals(musicas, playlist.getMusicas());
        assertTrue(playlist.getPublica());
        assertEquals("Autor", playlist.getAutor());
    }

    /**
     * Teste para o construtor de cópia da classe PlaylistPersonalizada.
     */
    @Test
    public void testPlaylistPersonalizadaConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistPersonalizada playlist = new PlaylistPersonalizada("nome", musicas, true, "Autor");

        PlaylistPersonalizada playlistCopia = new PlaylistPersonalizada(playlist);

        assertEquals(playlist, playlistCopia);
    }

    /**
     * Teste para o método clone da classe PlaylistPersonalizada.
     */
    @Test
    public void testPlaylistPersonalizadaClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistPersonalizada playlist = new PlaylistPersonalizada("nome", musicas, true, "Autor");

        PlaylistPersonalizada playlistClone = playlist.clone();

        assertEquals(playlist, playlistClone);
    }
}
