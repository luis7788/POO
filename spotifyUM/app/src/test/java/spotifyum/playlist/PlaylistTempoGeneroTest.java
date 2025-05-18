package spotifyum.playlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spotifyum.musica.Musica;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para os testes das Playlists Tempo Género.
 */
public class PlaylistTempoGeneroTest {

    /**
     * Teste para o construtor parameterizado da classe PlaylistTempoGenero.
     */
    @Test
    public void testPlaylistTempoGeneroConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistTempoGenero playlist = new PlaylistTempoGenero("nome", musicas, 120, "Jazz");
        assertNotNull(playlist);
        assertEquals("nome", playlist.getNome());
        assertEquals(musicas, playlist.getMusicas());
        assertTrue(playlist.getPublica());
        assertEquals("Sistema", playlist.getAutor());
        assertEquals(120, playlist.getTempo());
        assertEquals("Jazz", playlist.getGenero());
    }

    /**
     * Teste para o construtor de cópia da classe PlaylistTempoGenero.
     */
    @Test
    public void testPlaylistTempoGeneroConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistTempoGenero playlist = new PlaylistTempoGenero("nome", musicas, 120, "Jazz");

        PlaylistTempoGenero playlistCopia = new PlaylistTempoGenero(playlist);

        assertEquals(playlist, playlistCopia);
        assertEquals(playlist.getTempo(), playlistCopia.getTempo());
        assertEquals(playlist.getGenero(), playlistCopia.getGenero());
    }
    /**
     * Teste para o método clone da classe PlaylistTempoGenero.
     */
    @Test
    public void testPlaylistTempoGeneroClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Jazz", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        PlaylistTempoGenero playlist = new PlaylistTempoGenero("nome", musicas, 120, "Jazz");

        PlaylistTempoGenero playlistClone = playlist.clone();

        assertEquals(playlist, playlistClone);
        assertEquals(playlist.getTempo(), playlistClone.getTempo());
        assertEquals(playlist.getGenero(), playlistClone.getGenero());
    }
}
