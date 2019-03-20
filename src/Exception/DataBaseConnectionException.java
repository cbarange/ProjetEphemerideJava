package exception;


/**
 *  Class exception pour un problème de connection à la data base.
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.1
 *  @since       1.1
 */
public class DataBaseConnectionException extends Exception {

    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe SQL exception.
     * <p>
     *
     */
    DataBaseConnectionException(){
        super();
    }

    /**
     * Constructeur
     * <p>
     * utilise les paramètres de la classe SQL exception.
     * <p>
     *
     * @param msg (requis) Message d'erreur.
     */
    DataBaseConnectionException(String msg){
        super(msg);
    }
}
