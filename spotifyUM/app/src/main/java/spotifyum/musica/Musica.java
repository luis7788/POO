package spotifyum.musica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Classe que representa uma música do SpotifyUM */
public class Musica implements Serializable {

    /** ID único da Música */
    private String id;

    /** Nome da Música */
    private String nome;

    /** Artista da Música */
    private String artista;

    /** Editora da Música */
    private String editora;

    /** Letra da Música */
    private String letra;

    /** Partitura da Música */
    private List<String> partitura;//notas

    /** Género da Música */
    private String genero;

    /** Duração da Música em segundos */
    private int duracao;

    /** Número de reproduções da Música */
    private int reproducoes;

    /** 
     * Construtor vazio 
     * 
     * Cria uma Música com ID, nome, artista, editora, letra e gênero vazios.
     * Inicializa a partitura como uma lista vazia, a duração e o número de reproduções a 0.
     * 
     * @return A Música criada
     */
    public Musica(){
        this.id = "";
        this.nome = "";
        this.artista = "";
        this.editora = "";
        this.letra = "";
        this.partitura = new ArrayList<>();
        this.genero = "";
        this.duracao = 0;
        this.reproducoes = 0;
    }

    /** 
     * Construtor parametrizado
     *
     * Cria uma Música com os dados fornecidos.
     * 
     * @param id ID da Música
     * @param nome Nome da Música
     * @param artista Artista da Música
     * @param editora Editora da Música
     * @param letra Letra da Música
     * @param partitura Partitura da Música
     * @param genero Gênero da Música
     * @param duracao Duração da Música em segundos
     * 
     * @return A Música criada
     */
    public Musica(String id, 
                  String nome, 
                  String artista, 
                  String editora,
                  String letra, 
                  List<String> partitura, 
                  String genero, 
                  int duracao){

        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.editora = editora;
        this.letra = letra;
        this.partitura = new ArrayList<>(partitura);
        this.genero = genero;
        this.duracao = duracao;
        this.reproducoes = 0;
    }

    /** 
     * Construtor de cópia 
     * 
     * Cria uma nova Música com os mesmos dados que a Música passada como parâmetro.
     * 
     * @param umaMusica Música a copiar
     * @return A Música criada
     */
    public Musica(Musica umaMusica){
        this.id = umaMusica.getId();
        this.nome = umaMusica.getNome();
        this.artista = umaMusica.getArtista();
        this.editora = umaMusica.getEditora();
        this.letra = umaMusica.getLetra();
        this.partitura = umaMusica.getPartitura();
        this.genero = umaMusica.getGenero();
        this.duracao = umaMusica.getDuracao();
        this.reproducoes = umaMusica.getReproducoes();
    }

    /**
     * Método que devolve o ID da Música.
     * 
     * @return ID da Música
     */
    public String getId(){
        return this.id;
    }

    /**
     * Método que devolve o nome da Música.
     * 
     * @return Nome da Música
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Método que devolve o artista da Música.
     * 
     * @return Artista da Música
     */
    public String getArtista(){
        return this.artista;
    }

    /**
     * Método que devolve a editora da Música.
     * 
     * @return Editora da Música
     */
    public String getEditora(){
        return this.editora;
    }


    /**
     * Método que devolve a letra da Música.
     * 
     * @return Letra da Música
     */
    public String getLetra(){
        return this.letra;
    }

    /**
     * Método que devolve a partitura da Música.
     * 
     * @return Partitura da Música
     */
    public List<String> getPartitura(){
        return new ArrayList<>(this.partitura);
    }


    /**
     * Método que devolve o gênero da Música.
     * 
     * @return Gênero da Música
     */
    public String getGenero(){
        return this.genero;
    }

    /**
     * Método que devolve a duração da Música.
     * 
     * @return Duração da Música em segundos
     */
    public int getDuracao(){
        return this.duracao;
    }

    /**
     * Método que devolve o número de reproduções da Música.
     * 
     * @return Número de reproduções da Música
     */
    public int getReproducoes(){
        return this.reproducoes;
    }

    /**
     * Método que define o ID da Música.
     * 
     * @param novoId Novo ID da Música
     */
    public void setId(String novoId){
        this.id = novoId;
    }

    /**
     * Método que define o nome da Música.
     * 
     * @param novoNome Novo nome da Música
     */
    public void setNome(String novoNome){
        this.nome = novoNome;
    }

    /**
     * Método que define o artista da Música.
     * 
     * @param novoArtista Novo artista da Música
     */
    public void setArtista(String novoArtista){
        this.artista = novoArtista;
    }

    /**
     * Método que define a editora da Música.
     * 
     * @param novaEditora Nova editora da Música
     */
    public void setEditora(String novaEditora){
        this.editora = novaEditora;
    }

    /**
     * Método que define a letra da Música.
     * 
     * @param novaLetra Nova letra da Música
     */
    public void setLetra(String novaLetra){
        this.letra = novaLetra;
    }

    /**
     * Método que define a partitura da Música.
     * 
     * @param novaPartitura Nova partitura da Música
     */
    public void setPartitura(List<String> novaPartitura){
        this.partitura = new ArrayList<>(novaPartitura);
    }
    
    /**
     * Método que define o gênero da Música.
     * 
     * @param novoGenero Novo gênero da Música
     */
    public void setGenero(String novoGenero){
        this.genero = novoGenero;
    }

    /**
     * Método que define a duração da Música.
     * 
     * @param novaDuracao Nova duração da Música em segundos
     */
    public void setDuracao(int novaDuracao){
        this.duracao = novaDuracao;
    }

    /**
     * Método que define o número de reproduções da Música.
     * 
     * @param novasReproducoes Novo número de reproduções da Música
     */
    public void setReproducoes(int novasReproducoes){
        this.reproducoes = novasReproducoes;
    }

    /**
     * Método que devolve uma cópia da Música.
     * 
     * @return Cópia da Música
     */
    @Override
    public Musica clone(){
        return new Musica(this);
    }

    /**
     * Método que devolve uma representação textual das informações da Música.
     * 
     * @return String com os dados da Música
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Música]\n");
        sb.append("ID: ");
        sb.append(this.id).append("\n");
        sb.append("Nome: ");
        sb.append(this.nome).append("\n");
        sb.append("Artista: ");
        sb.append(this.artista).append("\n");
        sb.append("Editora: ");
        sb.append(this.editora).append("\n");
        sb.append("Letra: ");
        sb.append(this.letra).append("\n");
        sb.append("Partitura: ");
        sb.append(this.partitura.toString()).append("\n");
        sb.append("Genero: ");
        sb.append(this.genero).append("\n");
        sb.append("Duracao: ");
        sb.append(this.duracao).append("\n");
        sb.append("Reproducoes: ");
        sb.append(this.reproducoes).append("\n");
        return sb.toString();
    }

    @Override
    /**
     * Método que verifica se duas Músicas são iguais..
     * 
     * @param o Objeto a comparar
     * 
     * @return true se as Músicas forem iguais, false caso contrário
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        Musica m = (Musica) o;
        return this.id.equals(m.getId()) &&
               this.nome.equals(m.getNome()) &&
               this.artista.equals(m.getArtista()) &&
               this.editora.equals(m.getEditora()) &&
               this.letra.equals(m.getLetra()) &&
               this.partitura.equals(m.getPartitura()) &&
               this.genero.equals(m.getGenero()) &&
               this.duracao == m.getDuracao() &&
               this.reproducoes == m.getReproducoes();
    }

    /**
     * Método que reproduz a Música
     * 
     * Aumenta o número de reproduções, imprime a letra, o nome da música e o artista.
     */
    public void reproduzir() {
        this.reproducoes++;
        System.out.println();
        System.out.println("═════════════════════════════════════════════════");
        System.out.println("[▶] Música: " + this.nome + " - " + this.artista + "\n");
    
        System.out.println(this.letra);
        System.out.println();
    
        System.out.print("[");
        int totalSteps = 40;
        for (int i = 0; i < totalSteps; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nReprodução interrompida.");
                return;
            }
            System.out.print("=");
            System.out.flush();
        }
        System.out.println("] 100%");
    
        System.out.println("\n═════════════════════════════════════════════════");
        System.out.println();
    }
    
}
