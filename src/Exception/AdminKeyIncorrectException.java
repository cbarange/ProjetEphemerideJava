package exception;

/**
 *  Class exception pour une clé admin incorrecte.
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.1
 *  @since       1.1
 */
public class AdminKeyIncorrectException extends Exception{

    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe utilisateur
     * <p>
     *
     */
    AdminKeyIncorrectException(){
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
    AdminKeyIncorrectException(String msg){
        super(msg);
    }
}
