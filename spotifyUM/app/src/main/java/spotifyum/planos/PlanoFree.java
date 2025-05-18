package spotifyum.planos;

import spotifyum.exceptions.*;
import spotifyum.mvc.SpotifyumController;
import spotifyum.playlist.*;
import spotifyum.utilizador.Utilizador;

/**
 * Classe que representa o plano Free do SpotifyUM
 * 
 * Este plano permite ao utilizador ouvir apenas playlists aleatórias.
 * 
 */
public class PlanoFree extends PlanoSubscricao {

    /**
     * Construtor vazio para o plano Free.
     */
    public PlanoFree(){}

    /**
     * Método toString que retorna uma representação em string do plano Free.
     */
    @Override
    public String toString() {
        return "Plano Free";
    }

    /**
     * Método que retorna o número de pontos que um user do Plano Free recebe por música ouvida.
     * 
     * @param u User
     * 
     * @return Pontos por música do Plano Free
     */
    @Override
    public double calcularPontos(Utilizador u) {
        return 5.0;
    }

    /**
     * Método que reproduz uma playlist.
     * 
     * @param p Playlist a reproduzir
     * @param u Utilizador que está a reproduzir a playlist
     * @param c Controller do SpotifyUM
     */
    @Override
    public void reproduzPlaylist(Playlist p, Utilizador u, SpotifyumController c) throws PlanoException, PublicException {
        if (p instanceof PlaylistAleatoria) {
            p.reproduzir(u,c);
        } else  {
            throw new PlanoException("Plano Free só pode ouvir playlists aleatórias!");
        }
    }

}
