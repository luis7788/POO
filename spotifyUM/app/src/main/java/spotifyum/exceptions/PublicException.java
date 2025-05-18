package spotifyum.exceptions;

/**
 * Exceção lançada quando há problemas com a visibilidade de uma playlist.
 */
public class PublicException extends Exception {
    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem de erro a ser exibida
     */
    public PublicException(String mensagem) {
        super(mensagem);
    }
}