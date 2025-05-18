package spotifyum.exceptions;

/**
 * Exceção lançada quando uma opção é inválida.
 */
public class OpcaoException extends Exception {
    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem de erro a ser exibida
     */
    public OpcaoException (String mensagem) {
        super(mensagem);
    }
}