package spotifyum.mvc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import spotifyum.musica.Musica;
import spotifyum.planos.PlanoFree;
import spotifyum.utilizador.Utilizador;
import spotifyum.playlist.Playlist;
import spotifyum.playlist.PlaylistAleatoria;
import spotifyum.album.Album;

/**
 * Classe para os testes do model.
 */
public class SpotifyumModelTest {

    /**
     * Teste para a adição de um utilizador.
     * Verifica se o utilizador é adicionado corretamente à coleção de utilizadores.
     */
    @Test
    public void testAdicionarUtilizador(){
        SpotifyumModel model = new SpotifyumModel();
        
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());

        try{
            model.adicionarUtilizador(user);
            assertTrue(model.getUtilizadores().contains(user));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a adição de uma música.
     * Verifica se a música é adicionada corretamente à coleção de músicas.
     */
    @Test
    public void testAdicionarMusica(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);

        try{
            model.adicionarMusica(music);
            assertTrue(model.getMusicas().contains(music));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a adição de uma playlist.
     * Verifica se a playlist é adicionada corretamente à coleção de playlists.
     */
    @Test
    public void testAdicionarPlaylist(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        
        List<Musica> musicas = new ArrayList<>();
        musicas.add(music);

        Playlist playlist = new PlaylistAleatoria("Playlist Teste", musicas);

        try{
            model.adicionarPlaylist(playlist);
            assertTrue(model.getPlaylists().contains(playlist));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a adição de um álbum.
     * Verifica se o álbum é adicionado corretamente à coleção de álbuns.
     */
    @Test
    public void testAdicionarAlbum(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        
        List<Musica> musicas = new ArrayList<>();
        musicas.add(music);

        Album album = new Album("Album Teste", musicas);

        try{
            model.adicionarAlbum(album);
            assertTrue(model.getAlbuns().contains(album));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a remoção de um utilizador.
     * Verifica se o utilizador é removido corretamente da coleção de utilizadores.
     */
    @Test
    public void testRemoverUtilizador(){
        SpotifyumModel model = new SpotifyumModel();
        
        Utilizador user = new Utilizador("U1", "João Jesus", "joao.jesus@exemplo.pt", "Braga", new PlanoFree());

        try{
            model.adicionarUtilizador(user);
            model.removerUtilizador("U1");
            assertFalse(model.getUtilizadores().contains(user));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a remoção de uma música.
     * Verifica se a música é removida corretamente da coleção de músicas.
     */
    @Test
    public void testRemoverMusica(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);

        try{
            model.adicionarMusica(music);
            model.removerMusica("M1");
            assertFalse(model.getMusicas().contains(music));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a remoção de uma playlist.
     * Verifica se a playlist é removida corretamente da coleção de playlists.
     */
    @Test
    public void testRemoverPlaylist(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        
        List<Musica> musicas = new ArrayList<>();
        musicas.add(music);

        Playlist playlist = new PlaylistAleatoria("Playlist Teste", musicas);

        try{
            model.adicionarPlaylist(playlist);
            model.removerPlaylist("Playlist Teste");
            assertFalse(model.getPlaylists().contains(playlist));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Teste para a remoção de um álbum.
     * Verifica se o álbum é removido corretamente da coleção de álbuns.
     */
    @Test
    public void testRemoverAlbum(){
        SpotifyumModel model = new SpotifyumModel();
        
        List<String> partitura = new ArrayList<>();
        partitura.add("Do");
        partitura.add("Re");

        Musica music = new Musica("M1", "Noite Dourado", "Ed Sheeran", "Def Jam", "Era só mais um verão...",partitura, "Classical", 130);
        
        List<Musica> musicas = new ArrayList<>();
        musicas.add(music);

        Album album = new Album("Album Teste", musicas);

        try{
            model.adicionarAlbum(album);
            model.removerAlbum("Album Teste");
            assertFalse(model.getAlbuns().contains(album));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
        

}
