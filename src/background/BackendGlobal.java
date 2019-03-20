package background;

import java.util.List;

/**
 * Class mettant en liens les données et le multicontroller
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class BackendGlobal {

    /**
     * Variable liste des mois.
     */
    private List<Mois> listeMois;

    /**
     * Variable d'utilisateur.
     */
    private Utilisateur utilisateur;

    /**
     * Constructeur, vide.
     * <p>
     *
     */
    BackendGlobal(){
    }

    /** Retourne la liste de mois.  */
    public List<Mois> getListeMois() {
        return listeMois;
    }

    /** Retourne l'utilisateur.  */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
