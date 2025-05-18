package spotifyum.album;

import java.io.Serializable;
import java.util.*;
import spotifyum.musica.*;
import spotifyum.utilizador.*;

/** Um Álbum do SpotifyUM */
public class Album implements Serializable {

    /** Titulo do Álbum */
    private String titulo;

    /** Lista de músicas do Álbum */
    private List<Musica> musicas;

    /**
     * Construtor vazio
     * 
     * Cria um álbum com título vazio e uma lista de músicas vazia.
     * 
     * @return O Álbum criado
     */
    public Album(){
        this.titulo = "";
        this.musicas = new ArrayList<>();
    }

    /** 
     * Construtor parametrizado 
     * Cria um álbum com o título e a lista de músicas passados como parâmetros.
     * 
     * @param titulo Título do álbum
     * @param ms Lista de músicas do álbum
     * 
     * @return O Álbum criado
     */
    public Album(String titulo, List<Musica> ms){
        this.titulo = titulo;
        this.musicas = new ArrayList<>(ms);
    }

    /**
     * Construtor de cópia
     * Cria um novo Álbum com os mesmos dados do que o Álbum passado como parâmetro.
     * 
     * @param umAlbum Álbum a copiar
     * 
     * @return O Álbum criado
     */
    public Album(Album umAlbum){
        this.titulo = umAlbum.getTitulo();
        this.musicas = umAlbum.getMusicas();
    }

    /**
     * Método que devolve o título do álbum.
     * 
     * @return Título do álbum
     */
    public String getTitulo(){
        return this.titulo;
    }

    /**
     * Método que devolve a lista de músicas do álbum.
     * 
     * @return Lista de músicas do álbum
     */
    public List<Musica> getMusicas(){
        return new ArrayList<>(this.musicas);
    }
    
    /**
     * Método que define o título do álbum.
     * 
     * @param novoTitulo Novo título do álbum
     */
    public void setTitulo(String novoTitulo){
        this.titulo = novoTitulo;
    }

    /**
     * Método que define a lista de músicas do álbum.
     * 
     * @param novasMusicas Lista de músicas a definir
     */
    public void setMusicas(List<Musica> novasMusicas){
        this.musicas = new ArrayList<>(novasMusicas);
    }

    /**
     * Método que devolve uma cópia do álbum.
     * 
     * @return Cópia do álbum
     */
    @Override
    public Album clone(){
        return new Album(this);
    }

    /**
     * Método que devolve uma representação textual do álbum com as suas informações.
     * 
     * @return String com os dados do álbum
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ALBUM]\n");
        sb.append("Titulo: ");
        sb.append(this.titulo).append("\n");
        sb.append("Musicas: [");

        for (int i = 0; i < this.musicas.size(); i++) {
            sb.append(this.musicas.get(i).getId());
            if (i < this.musicas.size() - 1) {
                sb.append(";");
            }
        }

        sb.append("]\n");
        return sb.toString();
    }


    /**
     * Método que verifica se dois álbuns são iguais.
     * 
     * @param o Objeto a comparar com o álbum
     * @return true se os álbuns forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(this == null || this.getClass() != o.getClass()){
            return false;
        }
        Album a = (Album) o;
        return this.titulo.equals(a.getTitulo()) &&
               this.musicas.equals(a.getMusicas());
    }

    /**
     * Método que reproduz um álbum.
     * 
     * As músicas são reproduzidas de forma sequencial.
     * 
     * @param u Utilizador que está a reproduzir o álbum
     */
    public void reproduzir(Utilizador u){
        for(Musica m : this.musicas){
            u.ouvirMusica(m);
        }
    }
}
