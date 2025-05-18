package spotifyum.menu;

/**
 * Classe MenuOpcao
 * Esta classe representa uma opção de menu.
 */
public class MenuOpcao {

    /** Nome da opção */
    private String nomeOpcao;

    /** Pre-condição da opção */
    private PreCondicao preCondicao;

    /** Método a ser executado quando a opção é escolhida */
    private Handler metodo;

    /**
     * Construtor parameterizado
     * 
     * Cria uma opção de menu com o nome, pre-condição e método a ser executado.
     * @param nomeOpcao Nome da opção
     * @param preCondicao Pré-condição a ser validada
     * @param metodo Método a ser executado
     * 
     * @return A opção de menu criada
     */
    public MenuOpcao(String nomeOpcao, PreCondicao preCondicao, Handler metodo) {
        this.nomeOpcao = nomeOpcao;
        this.preCondicao = preCondicao;
        this.metodo = metodo;
    }

    /**
     * Construtor parameterizado
     * 
     * Cria uma opção de menu com o nome e método.
     * Neste construtor, a pre-condição é sempre verdadeira.
     * @param nomeOpcao Nome da opção
     * @param metodo Método a ser executado
     * 
     * @return A opção de menu criada
     */
    public MenuOpcao(String nomeOpcao, Handler metodo){
        this.nomeOpcao = nomeOpcao;
        this.preCondicao = () -> true;
        this.metodo = metodo;
    }

    /**
     * Método que devolve o nome da opção.
     * 
     * @return Nome da opção
     */
    public String getNomeOpcao() {
        return nomeOpcao;
    }

    /**
     * Método que executa o método associado à opção, se a pre-condição for válida.
     */
    public void executa(){
        if(preCondicao.valida()){
            metodo.executa();
        } else {
            System.out.println("Pre-condição não satisfeita.");
        }
    }

    /**
     * Método que verifica se a pre-condição é válida.
     * 
     * @return true se a pre-condição for válida, false caso contrário.
     */
    public boolean isPreCondicaoValida() {
        return preCondicao.valida();
    }
}
