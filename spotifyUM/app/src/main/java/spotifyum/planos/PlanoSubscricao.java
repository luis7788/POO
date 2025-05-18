package spotifyum.planos;

import java.io.Serializable;

import spotifyum.exceptions.*;
import spotifyum.mvc.SpotifyumController;
import spotifyum.playlist.*;
import spotifyum.utilizador.*;

/**
 * Classe abstrata que representa um plano de subscrição do SpotifyUM.
 * 
 * Esta classe define os métodos que devem ser implementados por todos os planos de subscrição.
 */
public abstract class PlanoSubscricao implements Serializable {
    /**
     * Método abstrato que calcula os pontos que um utilizador ganha por música ouvida
     * 
     * @param u Utilizador cujos pontos a adicionar vão ser calculados
     * 
     * @return Pontos a adicionar ao utilizador
     */
    public abstract double calcularPontos(Utilizador u);

    /**
     * Método abstrato que reproduz uma playlist.
     * 
     * @param p Playlist a reproduzir
     * @param u Utilizador que está a reproduzir a playlist
     * @param c Controller do SpotifyUM
     * 
     * @throws PlanoException Se o plano não permitir a reprodução da playlist.
     * @throws PublicException Se a playlist não for pública.
     */
    public abstract void reproduzPlaylist(Playlist p, Utilizador u, SpotifyumController c) throws PlanoException, PublicException;

}
