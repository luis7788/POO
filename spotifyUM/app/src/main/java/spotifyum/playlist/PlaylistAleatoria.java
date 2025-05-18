package spotifyum.playlist;

import java.util.Collections;
import java.util.List;
import spotifyum.musica.Musica;
import spotifyum.mvc.SpotifyumController;
import spotifyum.utilizador.Utilizador;

/** 
 * Classe que representa uma PlaylistAleatória.
 * Esta classe estende a classe Playlist e implementa métodos específicos para playlists do tipo PlaylistAleatória.
 */
public class PlaylistAleatoria extends Playlist {

    /**
     * Construtor vazio
     * 
     * Cria uma PlaylistAleatória com nome e autor vazios.
     * A lista de músicas é inicializada como vazia.
     * A visibilidade é definida como pública.
     * Inicializa a posição atual como 0.
     * 
     * @return A PlaylistAleatória criada
     */
    public PlaylistAleatoria() {
        super();
    }

    /**
     * Construtor parametrizado
     * 
     * Cria uma PlaylistAleatória com o nome, a lista de músicas, a visibilidade e o autor passados como parâmetros.
     * Inicializa a posição atual como 0.
     * 
     * @param nome Nome da playlist
     * @param musicas Lista de músicas da playlist
     * @param autor Autor da playlist
     * 
     * @return A PlaylistAleatória criada
     */
    public PlaylistAleatoria(String nome, List<Musica> musicas) {
        super(nome, musicas, true, "Sistema");
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova PlaylistAleatória com os mesmos dados do que a PlaylistAleatoria passada como parâmetro.
     * 
     * @param umaPlaylist PlaylistAleatória a copiar
     * 
     * @return A PlaylistAleatória criada
     */
    public PlaylistAleatoria(Playlist umaPlaylist) {
        super(umaPlaylist);
    }

    // Clone, toString, equals

    /**
     * Método que devolve uma cópia da PlaylistAleatória
     * 
     * @return Cópia da PlaylistAleatória
     */
    @Override
    public PlaylistAleatoria clone() {
        return new PlaylistAleatoria(this);
    }

    /**
     * Método que devolve uma representação textual das informações da PlaylistAleatória
     * 
     * @return String com os dados da PlaylistAleatória
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYLIST ALEATORIA]\n");
        sb.append("Nome: ");
        sb.append(this.getNome()).append("\n");
        sb.append("Musicas: [");
        List<Musica> musicas = this.getMusicas();
        for (int i = 0; i < musicas.size(); i++) {
            sb.append(musicas.get(i).getId());
            if (i < musicas.size() - 1) sb.append(";");
        }
        sb.append("]\n");
        sb.append("Publica: ");
        sb.append(this.getPublica()).append("\n");
        sb.append("Autor: ");
        sb.append(this.getAutor()).append("\n");
        return sb.toString();
    }
    
    /**
     * Método que reproduz todas as músicas da playlist.
     * 
     * As músicas são reproduzidas de forma aleatória.
     * 
     * @param u Utilizador que está a reproduzir a playlist
     * @param controller Controlador do SpotifyUM
     */
    @Override
    public void reproduzir(Utilizador u, SpotifyumController controller){
        Collections.shuffle(this.getMusicas());
        for (Musica m : this.getMusicas()){
            u.ouvirMusica(m);
        } 
    }
}