package background;

import database.ReponseOuverteDAO;
import foreground.DateStructureur;

/** Class Reponse Ouverte
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.0
 * @since 1.0
 */
public class ReponseOuverte {

    /**
     * Variable idReponse, contenant l'id de la reponse.
     */
    private int idReponse;

    /**
     * Variable contenu, contenant la réponse donné par l'élève.
     */
    private String contenu;

    /** Constructeur de ReponseOuverte
     * <p>
     *
     * @param  id_question (requis), contient l'id de la question
     */
    ReponseOuverte(int id_question){
        DateStructureur ds = new DateStructureur();
        idReponse = ds.getWeekDayNumber();
        contenu=new ReponseOuverteDAO("invite","patate","pts").getReponse(id_question);
    }

    ReponseOuverte(String contenu){
        this.contenu = contenu;
    }
    /**
     *  Fonction pour recuperer le contenu de la reponse
     * @return retourne la chaine de caracteres qui contient la reponse
     */
    public String getContenu() {
        return contenu;
    }

    /** Retourne l'id réponse passé au constructeur.  */
    public int getIdReponse(){
        return idReponse;
    }
}
