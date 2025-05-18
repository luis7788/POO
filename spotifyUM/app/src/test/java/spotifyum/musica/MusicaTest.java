package spotifyum.musica;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para os testes das músicas.
 */
public class MusicaTest {

    /**
     * Teste para o construtor parameterizado da classe Musica.
     */
    @Test
    public void testMusicaConstrutorParameterizado(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica musica = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);

        assertNotNull(musica);
        assertEquals("M1", musica.getId());
        assertEquals("Noite Dourado", musica.getNome());
        assertEquals("Ed Sheeran", musica.getArtista());
        assertEquals("Def Jam", musica.getEditora());
        assertEquals("Era só mais um verão...", musica.getLetra());
        assertEquals(partitura, musica.getPartitura());
        assertEquals("Classical", musica.getGenero());
        assertEquals(130, musica.getDuracao());
    }

    /**
     * Teste para o construtor de cópia da classe Musica.
     */
    @Test
    public void testMusicaConstrutorCopia(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica musicaOriginal = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica musicaCopia = new Musica(musicaOriginal);

        assertNotNull(musicaCopia);
        assertEquals(musicaOriginal.getId(), musicaCopia.getId());
        assertEquals(musicaOriginal.getNome(), musicaCopia.getNome());
        assertEquals(musicaOriginal.getArtista(), musicaCopia.getArtista());
        assertEquals(musicaOriginal.getEditora(), musicaCopia.getEditora());
        assertEquals(musicaOriginal.getLetra(), musicaCopia.getLetra());
        assertEquals(musicaOriginal.getPartitura(), musicaCopia.getPartitura());
        assertEquals(musicaOriginal.getGenero(), musicaCopia.getGenero());
        assertEquals(musicaOriginal.getDuracao(), musicaCopia.getDuracao());
    }

    /**
     * Teste para o método clone da classe Musica.
     */
    @Test
    public void testClone(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica musicaOriginal = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        Musica musicaClone = musicaOriginal.clone();

        assertNotNull(musicaClone);
        assertEquals(musicaOriginal.getId(), musicaClone.getId());
        assertEquals(musicaOriginal.getNome(), musicaClone.getNome());
        assertEquals(musicaOriginal.getArtista(), musicaClone.getArtista());
        assertEquals(musicaOriginal.getEditora(), musicaClone.getEditora());
        assertEquals(musicaOriginal.getLetra(), musicaClone.getLetra());
        assertEquals(musicaOriginal.getPartitura(), musicaClone.getPartitura());
        assertEquals(musicaOriginal.getGenero(), musicaClone.getGenero());
        assertEquals(musicaOriginal.getDuracao(), musicaClone.getDuracao());
    }

    /**
     * Teste para o método reproduzir da classe Musica.
     * Verifica se o número de reproduções aumenta após chamar o método.
     */
    @Test 
    public void testReproduzir(){
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica musica = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        
        musica.reproduzir();
        
        assertEquals(musica.getReproducoes(), 1);;
    }
}
