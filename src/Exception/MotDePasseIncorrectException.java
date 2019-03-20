package exception;

/**
 *  Class exception pour un problème de mot de passe incorrecte.
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.1
 *  @since       1.1
 */
public class MotDePasseIncorrectException extends Exception {


    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe utilisateur
     * <p>
     *
     *
     */
    MotDePasseIncorrectException(){
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
    MotDePasseIncorrectException(String msg){
        super(msg);
    }
}
