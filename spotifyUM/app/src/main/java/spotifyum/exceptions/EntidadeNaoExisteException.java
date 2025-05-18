package spotifyum.exceptions;

/**
 * Exceção lançada quando uma entidade não existe.
 */
public class EntidadeNaoExisteException extends Exception {
    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem de erro a ser exibida
     */
    public EntidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
}