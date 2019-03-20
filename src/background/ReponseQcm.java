package background;

/**Class ReponseQcm
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.4
 * @since 1.0
 */
public class ReponseQcm {

    /**
     * Variable idReponse, contenant l'id de la reponse.
     */
    private int idReponse;

    /**
     * Variable réponse, contenant la réponse.
     */
    private String reponse;

    /**
     * Variable estChoixValide, permettant de savoir si les choix pris sont valides.
     */
    private boolean estChoixValide;

    /** Constructeur de ReponseQCM
     * <p>
     *
     * @param reponse (requis), contient la réponse.
     * @param choix (requis), permet de savoir si les choix sont valides.
     * @param i (requis), id de la réponse.
     */
    ReponseQcm(String reponse, boolean choix, int i){
        this.reponse = reponse;
        this.estChoixValide = choix;
        idReponse = i;
    }

    /**
     * Fonction pour recuperer le contenu de la reponse
     * @return retourne la chaine de caracteres qui contient la reponse.
     */
    public String getReponse() {
        return reponse;
    }

    /**
     * Fonction pour savoir la veracite de la reponse
     * @return retourne le boolean pour savoir si la reponse associee est vraie ou fausse pour les QCM.
     */
    public boolean isEstChoixValide() {
        return estChoixValide;
    }

    /** Retourne l'id réponse passé au constructeur.  */
    public int getIdReponse() {
        return idReponse;
    }
}
