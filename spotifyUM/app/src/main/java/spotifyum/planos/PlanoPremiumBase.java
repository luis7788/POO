package spotifyum.planos;

import java.util.*;
import spotifyum.album.Album;
import spotifyum.exceptions.*;
import spotifyum.mvc.SpotifyumController;
import spotifyum.playlist.*;
import spotifyum.utilizador.Utilizador;

/**
 * Classe que representa o plano Premium Base do SpotifyUM
 * 
 * Este plano permite ao utilizador ouvir playlists aleatórias, tempo-genero e personalizadas.
 */
public class PlanoPremiumBase extends PlanoSubscricao{
    
    /** Lista de playlists do utilizador */
    private List<Playlist> minhasPlaylist;

    /** Lista de álbuns do utilizador*/
    private List<Album> bibliotecaAlbuns;

    /**
     * Construtor vazio para o plano Premium Base.
     */
    public PlanoPremiumBase(){
        this.minhasPlaylist = new ArrayList<>();
        this.bibliotecaAlbuns = new ArrayList<>();
    }

    /**
     * Construtor parameterizado que recebe  as listas de playlists e álbuns.
     * 
     * @param mp Lista de playlists do utilizador
     * @param al Lista de álbuns do utilizador
     */
    public PlanoPremiumBase(List<Playlist> mp, List<Album> al){
        this.minhasPlaylist = new ArrayList<>(mp);
        this.bibliotecaAlbuns = new ArrayList<>(al);
    }
    
    /**
     * Construtor de cópia que recebe um objeto PlanoPremiumBase.
     * 
     * @param umPlano Objeto PlanoPremiumBase a ser copiado
     */
    public PlanoPremiumBase(PlanoPremiumBase umPlano){
        this.minhasPlaylist = getMinhasPlaylist();
        this.bibliotecaAlbuns = getBibliotecaAlbuns();
    }

    /**
     * Método que devolve a lista de playlists do utilizador.
     * 
     * @return Lista de playlists do utilizador
     */
    public List<Playlist> getMinhasPlaylist(){
        return new ArrayList<>(this.minhasPlaylist);
    }

    /**
     * Método que devolve a lista de álbuns do utilizador.
     * 
     * @return Lista de álbuns do utilizador
     */
    public List<Album> getBibliotecaAlbuns(){
        return new ArrayList<>(this.bibliotecaAlbuns);
    }

    /**
     * Método que define a lista de playlists do utilizador.
     * 
     * @param playlist Lista de playlists do utilizador
     */
    public void setMinhasPlaylist(List<Playlist> playlist){
        this.minhasPlaylist = new ArrayList<>(playlist);
    }

    /**
     * Método que define a lista de álbuns do utilizador.
     * 
     * @param albuns Lista de álbuns do utilizador
     */
    public void setBibliotecaAlbuns(List<Album> albuns){
        this.bibliotecaAlbuns = new ArrayList<>(albuns);
    }

    /**
     * Método toString que devolve uma representação em string do plano Premium Base.
     * 
     * @return String com a representação do plano
     */
    @Override
    public String toString() {
        return "Plano Premium Base";
    }

    /**
     * Método que devolve uma representação em string das playlists do utilizador.
     * 
     * @return String que representa as playlists do utilizador
     */
    public String toStringMinhasPlaylists(){
        StringBuilder sb = new StringBuilder();
        sb.append("[MINHAS PLAYLISTS]:\n");
        sb.append("\n");
        for (Playlist p : this.minhasPlaylist) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Método que devolve uma representação em string dos álbuns na biblioteca do utilizador.
     * 
     * @return String que representa os álbuns do utilizador
     */
    public String toStringAlbuns(){
        StringBuilder sb = new StringBuilder();
        sb.append("[BIBLIOTECA DE ALBUNS]:\n");
        sb.append("\n");
        for (Album a : this.bibliotecaAlbuns) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Método que adiciona um álbum à biblioteca do utilizador.
     * 
     * @param album Album a ser adicionado
     * @throws EntidadeExisteException Se o álbum já existir na biblioteca
     */ 
    public void adicionarAlbumBiblioteca(Album album) throws EntidadeExisteException{
        if(!this.bibliotecaAlbuns.contains(album)){
            this.bibliotecaAlbuns.add(album);
        }
        else{
            throw new EntidadeExisteException("Album já existe na biblioteca");
        }
    }

    /**
     * Método que adiciona uma playlist à biblioteca do utilizador.
     * 
     * @param playlist Playlist a ser adicionada
     * @throws EntidadeExisteException Se a playlist já existir na biblioteca
     */
    public void adicionarPlaylistBiblioteca(Playlist playlist) throws EntidadeExisteException{
        if(!this.minhasPlaylist.contains(playlist)){
            this.minhasPlaylist.add(playlist);
        }
        else{
            throw new EntidadeExisteException("Playlist já existe na biblioteca");
        }
    }

    /**
     * Método que retorna o número de pontos que um user do Plano Premium Base recebe por música ouvida.
     * 
     * @param u User
     * 
     * @return Pontos por música do plano premium base
     */
    @Override
    public double calcularPontos(Utilizador u) {
        return 10.0;
    }
    
    /**
     * Método que reproduz uma playlist.
     * 
     * @param p Playlist a reproduzir
     * @param u Utilizador que está a reproduzir a playlist
     * @param c Controller do SpotifyUM
     * 
     * @throws PlanoException Se a playlist não for do tipo permitido.
     * @throws PublicException Se a playlist não for pública.
     */
    @Override   
    public void reproduzPlaylist(Playlist p, Utilizador u, SpotifyumController c) throws PlanoException, PublicException{
        if(p instanceof PlaylistPersonalizada){
            if(p.getAutor().equals(u.getId())){
                p.reproduzir(u, c);
                return;
            }
        }
        if (p.getPublica() == false) {
            throw new PublicException("Playlist não é pública");
        }
        if (p instanceof PlaylistAleatoria || p instanceof PlaylistTempoGenero || p instanceof PlaylistPersonalizada) {
            p.reproduzir(u,c);
        } else {
            throw new PlanoException("Plano Premium Base só pode ouvir playlists aleatórias, playlists tempo-genero ou personalizadas!");
        }
    }
}
