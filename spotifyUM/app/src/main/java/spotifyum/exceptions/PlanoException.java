package spotifyum.exceptions;

/**
 * Exceção lançada quando há problemas relacionados com os planos.
 */
public class PlanoException extends Exception{
    /**
     * Construtor da exceção.
     *
     * @param mensagem Mensagem de erro a ser exibida
     */
    public PlanoException (String mensagem){
        super(mensagem);
    }
}