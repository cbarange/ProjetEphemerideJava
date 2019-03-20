package exception;


/**
 *  Class exception pour un manque de token.
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.1
 *  @since       1.1
 */
public class NotEnoughTokensException extends Exception{

    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe utilisateur
     * <p>
     *
     */
    NotEnoughTokensException(){
        super();
    }

    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe utilisateur
     * <p>
     *
     * @param msg (requis) Message d'erreur.
     */
    NotEnoughTokensException(String msg){
        super(msg);
    }
}
