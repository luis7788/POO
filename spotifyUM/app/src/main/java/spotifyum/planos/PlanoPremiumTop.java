package spotifyum.planos;

import java.util.List;

import spotifyum.album.Album;
import spotifyum.exceptions.*;
import spotifyum.mvc.SpotifyumController;
import spotifyum.playlist.*;
import spotifyum.utilizador.Utilizador;

/**
 * Classe que representa o plano Premium Top do SpotifyUM
 * 
 * Este plano permite ao utilizador ouvir playlists aleatórias, tempo-genero, personalizadas e Listas de favoritas.
 * 
 */
public class PlanoPremiumTop extends PlanoPremiumBase {

    /**
     * Construtor vazio para o plano Premium Top.
     */
    public PlanoPremiumTop(){
        super();
    }

    /**
     * Construtor parameterizado que recebe listas de playlists e álbuns.
     * 
     * @param mp Lista de playlists do utilizador
     * @param al Lista de álbuns do utilizador
     */
    public PlanoPremiumTop(List<Playlist> mp, List<Album> al){
        super(mp, al);
    }

    /**
     * Construtor de cópia que recebe um objeto PlanoPremiumBase.
     * 
     * @param umPlano Objeto PlanoPremiumBase a ser copiado
     */
    public PlanoPremiumTop(PlanoPremiumBase umPlano){
        super(umPlano);
    }

    /**
     * Método toString que devolve uma representação em string do plano Premium Top.
     * 
     * @return String com a representação do plano
     */
    @Override
    public String toString() {
        return "Plano Premium Top";
    }

    /**
     * Método que devolve o número de pontos que um user do Plano Premium Top recebe por música ouvida.
     * 
     * @param u User
     * 
     * @return Pontos por música do plano Premium Top
     */ 
    @Override
    public double calcularPontos(Utilizador u) {
        return  u.getPontos() * 0.025;
    }
    
    /**
     * Método que reproduz uma playlist.
     * 
     * @param p Playlist a reproduzir
     * @param u Utilizador que está a reproduzir a playlist
     * @param c Controller do SpotifyUM
     */
    @Override
    public void reproduzPlaylist(Playlist p, Utilizador u, SpotifyumController c) throws PlanoException, PublicException{
        if(p instanceof ListaFavoritos &&  ((ListaFavoritos)p).getUtilizador().equals(u.getId())){
            p.reproduzir(u,c);
            return;
        }
        if(p instanceof PlaylistPersonalizada){
            if(p.getAutor().equals(u.getId())){
                p.reproduzir(u, c);
                return;
            }
        }
        
        if (p.getPublica() == false) {
            throw new PublicException("Playlist não é pública");
        }
        p.reproduzir(u,c);
    }
}
