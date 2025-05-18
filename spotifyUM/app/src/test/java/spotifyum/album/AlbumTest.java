package spotifyum.album;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import spotifyum.musica.Musica;

/**
 * Classe para os testes dos albuns.
 */
public class AlbumTest {

    /**
     * Teste para o construtor parameterizado da classe Album.
     */
    @Test
    public void testAlbumConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Album album = new Album("Album Teste", musicas);

        assertNotNull(album);
        assertEquals("Album Teste", album.getTitulo());
        assertEquals(2, album.getMusicas().size());
        assertEquals(m1, album.getMusicas().get(0));
        assertEquals(m2, album.getMusicas().get(1));
    }

    /**
     * Teste para o construtor de cópia da classe Album.
     */
    @Test
    public void testAlbumConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Album albumOriginal = new Album("Album Teste", musicas);
        Album albumCopia = new Album(albumOriginal);

        assertNotNull(albumCopia);
        assertEquals(albumOriginal.getTitulo(), albumCopia.getTitulo());
        assertEquals(albumOriginal.getMusicas().size(), albumCopia.getMusicas().size());
    }

    /**
     * Teste para o método clone da classe Album.
     */
    @Test
    public void testClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica m1 = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica m2 = new Musica("M2", "Sol Frio", "Ed Sheeran", "RCA", "Caminhei sem direção...", partitura, "Jazz", 137);

        List<Musica> musicas = new ArrayList<>();
        musicas.add(m1);
        musicas.add(m2);

        Album albumOriginal = new Album("Album Teste", musicas);
        Album albumClone = albumOriginal.clone();

        assertNotNull(albumClone);
        assertEquals(albumOriginal.getTitulo(), albumClone.getTitulo());
        assertEquals(albumOriginal.getMusicas().size(), albumClone.getMusicas().size());
    }
}
