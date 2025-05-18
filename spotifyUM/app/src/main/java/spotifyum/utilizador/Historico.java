package spotifyum.utilizador;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa o histórico de reprodução de músicas.
 * Cada objeto contém o ID da música e a data em que foi reproduzida.
 */
public class Historico implements Serializable{

    /** Id da música reproduzida */
    private String idMusica;

    /** Data em que a música foi reproduzida */
    private LocalDate dataReproducao;

    /**
     * Construtor vazio que inicializa o id da música como uma string vazia
     * e a data de reprodução como a data atual.
     */
    public Historico(){
        this.idMusica = "";
        this.dataReproducao = LocalDate.now();
    }

    /**
     * Construtor parameterizado que inicializa o id da música como sendo
     * o idMusica passado como argumento.
     * 
     * A data de reprodução é inicializada como a data atual.
     * 
     * @param idMusica ID da música reproduzida
     */
    public Historico(String idMusica) {
        this.idMusica = idMusica;
        this.dataReproducao = LocalDate.now();
    }
    
    /**
     * Construtor de cópia que inicializa as variáveis de instância
     * com os valores do objeto passado como argumento.
     * 
     * @param umUistorico Historico a ser copiado
     */
    public Historico (Historico umUistorico){
        this.idMusica = umUistorico.getIdMusica();
        this.dataReproducao = umUistorico.getDataReproducao();
    }

    /**
     * Método que devolve o id da música.
     * 
     * @return Id da música
     */
    public String getIdMusica() {
        return idMusica;
    }

    /**
     * Método que devolve a data de reprodução da música.
     * 
     * @return Data de reprodução
     */
    public LocalDate getDataReproducao(){
        return this.dataReproducao;
    }

    /**
     * Método que dfine o id da música com o idMusica passado como argumento.
     * 
     * @param idMusica Id da música
     */
    public void setIdMusica (String idMusica) {
        this.idMusica = idMusica;
    }

    /**
     * Método que define a data de reprodução com a dataReproducao passada como argumento.
     * 
     * @param dataReproducao Data de reprodução
     */
    public void setDataReproducao (LocalDate dataReproducao) {
        this.dataReproducao = dataReproducao;
    }

    /**
     * Método que devolve uma cópia do objeto atual.
     * 
     * @return Cópia do objeto atual
     */
    public Historico clone(){
        return new Historico(this);
    }

    /**
     * Método que devolve uma representação textual do objeto atual.
     * 
     * @return Representação textual do objeto atual
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Música: ").append(this.idMusica).append(" -> ");
        sb.append("Data: ").append(this.dataReproducao).append("\n");
        return sb.toString();
    }

    /**
     * Método que verifica se dois objetos são iguais.
     * Dois objetos são considerados iguais se os seus IDs de música e datas de reprodução
     * forem iguais.
     * 
     * @param o Objeto a ser comparado
     * @return true se os objetos forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        Historico h = (Historico) o;
        return this.idMusica.equals(h.getIdMusica()) &&
               this.dataReproducao.equals(h.getDataReproducao());
    }
}
