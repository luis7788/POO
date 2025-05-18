package spotifyum.exceptions;

/**
 * Exceção lançada quando uma entidade já existe.
 */
public class EntidadeExisteException extends Exception {
    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem de erro a ser exibida
     */
    public EntidadeExisteException(String mensagem) {
        super(mensagem);
    }
}