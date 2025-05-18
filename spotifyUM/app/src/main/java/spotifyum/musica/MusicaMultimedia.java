package spotifyum.musica;

import java.util.List;

/** Classe que representa uma música multimédia do SpotifyUM 
 * Esta classe estende a classe Música e implementa métodos específicos para músicas que contêm conteúdo multimédia.
 */
public class MusicaMultimedia extends Musica {

    private static final String VIDEO = 
        "╔════════════╗\n" +
        "║            ║\n" +
        "║  [ VÍDEO ] ║\n" +
        "║            ║\n" +
        "╚════════════╝";

    // Construtores
    /**
     * Construtor vazio
     * 
     * Cria uma MúsicaMultimédia com ID, nome, artista, editora, letra e gênero vazios.
     * Inicializa a partitura como uma lista vazia, a duração e o número de reproduções a 0.
     * 
     * @return A MúsicaMultimédia criada
     */
    public MusicaMultimedia() {
        super();
    }

    /**
     * Construtor parametrizado
     *
     * Cria uma MúsicaMultimédia com os dados fornecidos.
     * 
     * @param id ID da MúsicaMultimédia
     * @param nome Nome da MúsicaMultimédia
     * @param artista Artista da MúsicaMultimédia
     * @param editora Editora da MúsicaMultimédia
     * @param letra Letra da MúsicaMultimédia
     * @param partitura Partitura da MúsicaMultimédia
     * @param genero Gênero da MúsicaMultimédia
     * @param duracao Duração da MúsicaMultimédia em segundos
     * 
     * @return A MúsicaMultimédia criada
     */
    public MusicaMultimedia(String id, 
                           String nome, 
                           String artista, 
                           String editora,
                           String letra,
                           List<String> partitura, 
                           String genero, 
                           int duracao){
        super(id, nome, artista, editora,letra, partitura, genero, duracao);
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova MúsicaMultimédia com os mesmos dados da Música passada como parâmetro.
     * 
     * @param umaMusica A Música a ser copiada
     * 
     * @return A MúsicaMultimédia criada
     */
    public MusicaMultimedia(Musica umaMusica) {
        super(umaMusica);
    }

    /** 
     * Método que devolve uma cópia da MúsicaMultimédia.
     * 
     * @return Cópia da MúsicaMultimédia
     */
    @Override
    public MusicaMultimedia clone() {
        return new MusicaMultimedia(this);
    }

    /**
     * Método que devolve uma representação textual das informações da MúsicaMultimédia.
     * 
     * @return String com os dados da MúsicaMultimédia
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MÚSICA MULTIMÉDIA]\n");
        sb.append("ID: ");
        sb.append(this.getId()).append("\n");
        sb.append("Nome: ");
        sb.append(this.getNome()).append("\n");
        sb.append("Artista: ");
        sb.append(this.getArtista()).append("\n");
        sb.append("Editora: ");
        sb.append(this.getEditora()).append("\n");
        sb.append("Letra: ");
        sb.append(this.getLetra()).append("\n");
        sb.append("Partitura: ");
        sb.append(this.getPartitura().toString()).append("\n");
        sb.append("Genero: ");
        sb.append(this.getGenero()).append("\n");
        sb.append("Duracao: ");
        sb.append(this.getDuracao()).append("\n");
        sb.append("Reproducoes: ");
        sb.append(this.getReproducoes()).append("\n");
        return sb.toString();
    }

    /**
     * Método que reproduz a MúsicaMultimédia
     * 
     * Imprime "[MÚSICA MULTIMÉDIA]" antes de reproduzir a música, junto com o vídeo.
     */
    @Override
    public void reproduzir(){
        System.out.println();
        System.out.print("[MÚSICA MULTIMÉDIA]\n");
        System.out.println();
        System.out.println(VIDEO);
        super.reproduzir();
    }
}
