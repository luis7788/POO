package spotifyum.playlist;

import java.util.List;

import spotifyum.musica.Musica;

/**
 * Classe que representa uma lista de músicas favoritas de um utilizador.
 * Esta classe estende a classe Playlist e implementa métodos específicos para playlists do tipo TempoGênero.
 */
public class PlaylistTempoGenero extends Playlist {

    /** Tempo de duração da PlaylistTempoGênero */
    private int tempo;

    /** Gênero da PlaylistTempoGenero */
    private String genero;

    /**
     * Construtor vazio
     * 
     * Cria uma playlist com nome e autor vazios.
     * A lista de músicas é inicializada como vazia.
     * A visibilidade é definida como pública.
     * O tempo e o gênero são inicializados como 0 e vazio, respetivamente.
     * 
     * @return A PlaylistTempoGênero criada
     */
    public PlaylistTempoGenero() {
        super();
        this.tempo = 0;
        this.genero = "";
    }

    /**
     * Construtor parametrizado
     * 
     * Cria uma playlist com o nome, a lista de músicas, a visibilidade, o autor, o tempo e o gênero passados como parâmetros.
     * 
     * @param nome Nome da playlist
     * @param ms Lista de músicas da playlist
     * @param publica Visibilidade da playlist
     * @param autor Autor da playlist
     * @param tempo Tempo de duração da playlist
     * @param genero Gênero da playlist
     * 
     * @return A PlaylistTempoGênero criada
     */
    public PlaylistTempoGenero(String nome, List<Musica> ms, int tempo, String genero) {
        super(nome, ms, true, "Sistema");
        this.tempo = tempo;
        this.genero = genero;
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova PlaylistTempoGênero com os mesmos dados do que a PlaylistTempoGênero passada como parâmetro.
     * 
     * @param umaPlaylistTempoGenero PlaylistTempoGênero a copiar
     * 
     * @return A PlaylistTempoGênero criada
     */
    public PlaylistTempoGenero(PlaylistTempoGenero umaPlaylistTempoGenero) {
        super(umaPlaylistTempoGenero);
        this.tempo = umaPlaylistTempoGenero.getTempo();
        this.genero = umaPlaylistTempoGenero.getGenero();
    }

    /**
     * Método que devolve o tempo da PlaylistTempoGênero
     * 
     * @return Tempo da PlaylistTempoGênero
     */
    public int getTempo() {
        return tempo;
    }
    
    /**
     * Método que devolve o gênero da PlaylistTempoGênero
     * 
     * @return Gênero da PlaylistTempoGênero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método que define o tempo da PlaylistTempoGênero
     * 
     * @param novoTempo Novo tempo da PlaylistTempoGênero
     */
    public void setTempo(int novoTempo) {
        this.tempo = novoTempo;
    }

    /**
     * Método que define o gênero da PlaylistTempoGênero
     * 
     * @param novoGenero Novo gênero da PlaylistTempoGênero
     */
    public void setGenero(String novoGenero) {
        this.genero = novoGenero;
    }

    // clone, toString, equals
    /**
     * Método que devolve uma cópia da PlaylistTempoGênero
     * 
     * @return Cópia da PlaylistTempoGênero
     */
    @Override
    public PlaylistTempoGenero clone() {
        return new PlaylistTempoGenero(this);
    }

    /**
     * Método que devolve uma representação textual das informações da PlaylistTempoGênero
     * 
     * @return String com os dados da PlaylistTempoGênero
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[PLAYLIST TEMPO GENERO]\n");
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
        sb.append("Tempo: ");
        sb.append(this.getTempo()).append("\n");
        sb.append("Genero: ");
        sb.append(this.getGenero()).append("\n");
        return sb.toString();
    }
    

    /**
     * Método que verifica se duas PlaylistTempoGênero são iguais
     * 
     * @param o Objeto a comparar
     * 
     * @return true se as PlaylistTempoGênero forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        PlaylistTempoGenero p = (PlaylistTempoGenero) o;
        return super.equals(p) &&
               this.tempo == p.getTempo() &&
               this.genero.equals(p.getGenero());
    }
}
