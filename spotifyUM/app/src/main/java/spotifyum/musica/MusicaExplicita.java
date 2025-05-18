package spotifyum.musica;

import java.util.List;

/** Classe que representa uma MúsicaMultimédia do SpotifyUM 
 * Esta classe estende a classe Música e implementa métodos específicos para músicas que contêm conteúdo explícito.
 */
public class MusicaExplicita extends Musica {

    private static final String AVISO = 
        "╔══════════════╗\n" +
        "║              ║\n" +
        "║   CONTEÚDO   ║\n" +
        "║     +18      ║\n" +
        "║              ║\n" +
        "╚══════════════╝";

    /**
     * Construtor vazio
     * 
     * Cria uma MúsicaMultimédia com ID, nome, artista, editora, letra e gênero vazios.
     * Inicializa a partitura como uma lista vazia, a duração e o número de reproduções a 0.
     * 
     * @return A Música explícita criada
     */
    public MusicaExplicita() {
        super();
    }

    /**
     * Construtor parametrizado
     * 
     * Cria uma MúsicaMultimédia com os dados fornecidos.
     * 
     * @param id       ID da música
     * @param nome     Nome da música
     * @param artista  Artista da música
     * @param editora  Editora da música
     * @param letra    Letra da música
     * @param partitura Partitura da música
     * @param genero   Gênero da música
     * @param duracao  Duração da música em segundos
     * 
     * @return A Música explícita criada
     */
    public MusicaExplicita(String id, 
                           String nome, 
                           String artista, 
                           String editora,
                           String letra, 
                           List<String> partitura, 
                           String genero, 
                           int duracao){
        super(id, nome, artista, editora, letra, partitura, genero, duracao);
    }

    /**
     * Construtor de cópia
     * 
     * Cria uma nova MúsicaMultimédia com os mesmos dados da música passada como parâmetro.
     *
     * @param umaMusica A música a ser copiada
     *
     * @return A Música explícita criada
     */
    public MusicaExplicita(Musica umaMusica) {
        super(umaMusica);
    }

    /**
     * Método que devolve uma cópia da MúsicaMultimédia.
     *
     * @return Cópia da MúsicaMultimédia
     */
    @Override
    public MusicaExplicita clone() {
        return new MusicaExplicita(this);
    }

    /**
     * Método que devolve uma representação textual das informações da MúsicaMultimédia.
     *
     * @return String com os dados da MúsicaMultimédia
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MÚSICA EXPLÍCITA]\n");
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
     * Método que reproduz a MúsicaMultimédia.
     * 
     * Imprime "[MÚSICA EXPLÍCITA]" antes de reproduzir a música, juntamente com um aviso de conteúdo explícito.
     */
    @Override
    public void reproduzir(){
        System.out.println();
        System.out.print("[MÚSICA EXPLÍCITA]\n");
        System.out.println();
        System.out.println(AVISO);
        super.reproduzir();
    }

}
