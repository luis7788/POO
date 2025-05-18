package spotifyum.playlist;

import java.util.Collections;
import java.util.List;
import spotifyum.musica.Musica;
import spotifyum.mvc.SpotifyumController;
import spotifyum.utilizador.Utilizador;

/**
 * Classe que representa uma playlist personalizada.
 * Esta classe estende a classe Playlist e implementa métodos específicos para playlists do tipo Personalizada.
 */
public class PlaylistPersonalizada extends Playlist {

    /**
     * Construtor vazio
     * 
     * Cria uma playlist personalizada com nome e autor vazios.
     * A lista de músicas é inicializada como vazia.
     * A visibilidade é definida como pública.
     * Inicializa a posição atual como 0.
     * 
     * @return A PlaylistPersonalizada criada
     */
    public PlaylistPersonalizada() {
        super();
    }

    /**
     * Construtor parametrizado
     * 
     * Cria uma PlaylistPersonalizada com o nome, a lista de músicas, a visibilidade e o autor passados como parâmetros.
     * Inicializa a posição atual como 0.
     * 
     * @param nome Nome da playlist
     * @param musicas Lista de músicas da playlist
     * @param publica Visibilidade da playlist
     * @param autor Autor da playlist
     * 
     * @return A PlaylistPersonalizada criada
     */
    public PlaylistPersonalizada(String nome, List<Musica> musicas, boolean publica, String autor) {
        super(nome, musicas, publica, autor);
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova PlaylistPersonalizada com os mesmos dados do que a PlaylistPersonalizada passada como parâmetro.
     * 
     * @param umaPlaylist PlaylistPersonalizada a copiar
     * 
     * @return A PlaylistPersonalizada criada
     */
    public PlaylistPersonalizada(Playlist umaPlaylist) {
        super(umaPlaylist);
    }

    /**
     * Método que devolve uma cópia da PlaylistPersonalizada.
     * 
     * @return Cópia da PlaylistAleatoria
     */
    @Override
    public PlaylistPersonalizada clone() {
        return new PlaylistPersonalizada(this);
    }

    /**
     * Método que devolve uma representação textual das informações da PlaylistPersonalizada.
     * 
     * @return String com os dados da PlaylistPersonalizada
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYLIST PERSONALIZADA]\n");
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
     * Ao longo da reprodução, o utilizador pode escolher entre reproduzir a música atual, avançar para a próxima, voltar para a anterior.
     * 
     * @param u Utilizador que está a reproduzir a playlist
     * @param controller Controlador do SpotifyUM
     */
    @Override
    public void reproduzir(Utilizador u, SpotifyumController controller){
        List<Musica> musicas = this.getMusicas();
        int random = controller.pedirAleatorio();

        if(random == 1){
            Collections.shuffle(musicas);
            for(Musica m : musicas){
                u.ouvirMusica(m);
            }
        }else{
            int posicao = 0;
            while(posicao >= 0 && posicao < musicas.size()){
                Musica mAtual = musicas.get(posicao);
                int acao = controller.pedirAcao(mAtual);
                switch(acao){
                    case 1 -> {
                        u.ouvirMusica(mAtual);
                        posicao++;
                    }
                    case 2 -> posicao++;
                    case 3 -> posicao--;
                    default -> System.out.println("\nAção inválida. Tente novamente.\n");
                }
            }
        }
    }
}
