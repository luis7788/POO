package spotifyum.playlist;

import java.io.Serializable;
import java.util.*;
import spotifyum.musica.Musica;
import spotifyum.mvc.SpotifyumController;
import spotifyum.utilizador.Utilizador;

/** Classe que representa uma playlist do SpotifyUM */
public abstract class Playlist implements Serializable {

    /** Nome da playlist */
    private String nome;

    /** Lista de músicas da playlist */
    private List<Musica> musicas;

    /** Indica se a playlist é pública ou privada */
    private boolean publica;

    /** Autor da playlist */
    private String autor;

    /**
     * Construtor vazio
     * 
     * Cria uma playlist com nome e autor vazios.
     * A lista de músicas é inicializada como vazia.
     * A visibilidade é definida como privada.
     * Inicializa a posição atual como 0.
     * 
     * @return A Playlist criada
     */
    public Playlist(){
        this.nome = "";
        this.musicas = new ArrayList<>();
        this.publica = false;
        this.autor = "";
    }

    /** 
     * Construtor parametrizado
     * 
     * Cria uma playlist com o nome, a lista de músicas, a visibilidade e o autor passados como parâmetros.
     * Inicializa a posição atual como 0.
     * 
     * @param nome Nome da playlist
     * @param ms Lista de músicas da playlist
     * @param publica Indica se a playlist é pública ou privada
     * @param autor Autor da playlist
     * 
     * @return A Playlist criada
     */
    public Playlist(String nome, List<Musica> ms, boolean publica, String autor){
        this.nome = nome;
        this.musicas = new ArrayList<>(ms);
        this.publica = publica;
        this.autor = autor;
    }

    /**
     * Construtor de cópia
     * Cria uma nova Playlist com os mesmos dados do que a Playlist passada como parâmetro.
     * 
     * @param umaPlaylist Playlist a copiar
     * 
     * @return A Playlist criada
     */
    public Playlist(Playlist umaPlaylist){
        this.nome = umaPlaylist.getNome();
        this.musicas = umaPlaylist.getMusicas();
        this.publica = umaPlaylist.getPublica();
        this.autor = umaPlaylist.getAutor();
    }

    /**
     * Método que devolve o nome da playlist.
     * 
     * @return Nome da playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que devolve a lista de músicas da playlist.
     * 
     * @return Lista de músicas
     */
    public List<Musica> getMusicas() {
        return new ArrayList<>(this.musicas);
    }

    /**
     * Método que devolve a visibilidade da playlist.
     * 
     * @return Indica se a playlist é pública ou privada
     */
    public boolean getPublica() {
        return publica;
    }

    /**
     * Método que devolve o autor da playlist.
     * 
     * @return Autor da playlist
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Método que define o nome da playlist.
     * 
     * @param novoNome Novo nome da playlist
     */
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    /** 
     * Método que define a lista de músicas da playlist.
     * 
     * @param novasMusicas Nova lista de músicas
     */
    public void setMusicas(List<Musica> novasMusicas) {
        this.musicas = new ArrayList<>(novasMusicas);
    }

    /**
     * Método que define a visibilidade da playlist.
     * 
     * @param novaPublica Nova visibilidade da playlist
     */
    public void setPublica(boolean novaPublica) {
        this.publica = novaPublica;
    }

    /**
     * Método que define o autor da playlist
     * 
     * @param novoAutor Novo autor da playlist
     */
    public void setAutor(String novoAutor) {
        this.autor = novoAutor;
    }

    /** 
     * Método abstrato que devolve uma cópia da Playlist (depende da subclasse/tipo de Playlist).
     * 
     * @return Cópia da Playlist
     */
    public abstract Playlist clone();

    /**
     * Método que devolve uma representação textual das informações da Playlist.
     * 
     * @return String com os dados da Playlist
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYLIST]\n");
        sb.append("Nome: ");
        sb.append(this.nome).append("\n");
        sb.append("Musicas: [");
        for (int i = 0; i < this.musicas.size(); i++) {
            sb.append(this.musicas.get(i).getId());
            if (i < this.musicas.size() - 1) sb.append(";");
        }
        sb.append("]\n");
        sb.append("Publica: ");
        sb.append(this.publica).append("\n");
        sb.append("Autor: ");
        sb.append(this.autor).append("\n");
        return sb.toString();
    }


    /**
     * Método que verifica se duas Playlists são iguais.
     * 
     * @param o Objeto a comparar
     * 
     * @return true se as Playlists forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        Playlist p = (Playlist) o;
        return this.nome.equals(p.getNome()) &&
               this.musicas.equals(p.getMusicas()) &&
               this.publica == p.getPublica() &&
               this.autor.equals(p.getAutor());
    }

    /**
     * Método que reproduz todas as músicas da playlist.
     * 
     * As músicas são reproduzidas de forma sequencial.
     * 
     * @param u Utilizador que está a reproduzir a playlist
     * @param controller Controlador do SpotifyUM
     */
    public void reproduzir(Utilizador u, SpotifyumController controller){;
        for (Musica m : this.getMusicas()){
            u.ouvirMusica(m);
        } 
    }
}
