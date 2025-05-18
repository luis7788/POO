package spotifyum.playlist;

import java.util.List;

import spotifyum.musica.Musica;

/**
 * Classe que representa uma lista de músicas favoritas de um utilizador.
 * Esta classe estende a classe Playlist e implementa métodos específicos para playlists do tipo ListaFavoritos.
 */
public class ListaFavoritos extends Playlist {

    /** ID do utilizador dono da Lista de Favoritos */
    private String utilizador;

    /**
     * Construtor vazio
     * 
     * Cria uma playlist com nome, autor e utilizador vazios.
     * A lista de músicas é inicializada como vazia.
     * A visibilidade é definida como privada.
     * Inicializa a posição atual como 0.
     * 
     * @return A Playlist criada
     */
    public ListaFavoritos() {
        super();
        this.utilizador = "";
    }

    /**
     * Construtor parametrizado
     * 
     * Cria uma ListaFavoritos com o nome, a lista de músicas, a visibilidade, o autor e o utilizador passados como parâmetros.
     * Inicializa a posição atual como 0.
     * 
     * @param nome Nome da playlist
     * @param ms Lista de músicas da playlist
     * @param utilizador ID do utilizador dono da Lista de Favoritos
     * 
     * @return A Playlist criada
     */
    public ListaFavoritos(String nome, List<Musica> ms, String utilizador) {
        super(nome, ms, false, "Sistema");
        this.utilizador = utilizador;
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova ListaFavoritos com os mesmos dados do que a ListaFavoritos passada como parâmetro.
     * 
     * @param umaLista ListaFavoritos a copiar
     * 
     * @return A ListaFavoritos criada
     */
    public ListaFavoritos(ListaFavoritos umaLista) {
        super(umaLista);
        this.utilizador = umaLista.getUtilizador();
    }

    /**
     * Método que retorna o ID do utilizador dono da Lista de Favoritos.
     * 
     * @return ID do utilizador
     */
    public String getUtilizador() {
        return utilizador;
    }
    
    /**
     * Método que define o ID do utilizador dono da Lista de Favoritos.
     * 
     * @param utilizador ID do utilizador
     */
    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    /** 
     * Método que devolve uma cópia da ListaFavoritos.
     * 
     * @return Cópia da ListaFavoritos
     * 
     */
    @Override
    public ListaFavoritos clone() {
        return new ListaFavoritos(this);
    }

    /**
     * Método que devolve uma representação textual das informações da ListaFavoritos.
     * 
     * @return String com os dados da ListaFavoritos
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[LISTA FAVORITOS]\n");
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
        sb.append("Utilizador: ");
        sb.append(this.utilizador).append("\n");
        return sb.toString();
    }
    

    /**
     * Método que verifica se duas ListasFavoritos são iguais
     * 
     * @param o Objeto a comparar
     * 
     * @return true se as ListasFavoritos forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        ListaFavoritos l = (ListaFavoritos) o;
        return super.equals(l) && 
               this.utilizador.equals(l.getUtilizador());
    }
}
