package background;

/**
 *  Class enum du niveau de l'année des étudiants.
 *  <li>{@link #TC1}</li>
 *  <li>{@link #TC2}</li>
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.0
 *  @since       1.0
 */

public enum AnneeTC {

    /**
     * TC1
     */
    TC1("TC1"),

    /**
     * TC2
     */
    TC2("TC2");

    /**
     * Variable String des intitulé de l'enum.
     */
    private String intitule;

    /**
     * Constructeur
     * <p>
     *
     * @param intitule (requis) nom de l'année d'un étudiant.
     */
    AnneeTC(String intitule){
        this.intitule=intitule;
    }

    public static AnneeTC getAnneeTc(String annee_tc) {
        switch(annee_tc){
            case "TC1":return AnneeTC.TC1;

            case "TC2":return AnneeTC.TC2;

            default:
                return AnneeTC.TC1;
        }

    }

    /** Retourne l'intitule passé au constructeur.  */
    public String getIntitule() {
        return intitule;
    }
}
