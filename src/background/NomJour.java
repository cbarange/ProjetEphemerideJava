package background;

/**
 *  Class enum des Jour de la semaine
 *  <li>{@link #Lundi}</li>
 *  <li>{@link #Mardi}</li>
 *  <li>{@link #Mercredi}</li>
 *  <li>{@link #Jeudi}</li>
 *  <li>{@link #Vendredi}</li>
 *  <li>{@link #Samedi}</li>
 *  <li>{@link #Dimanche}</li>
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.0
 *  @since       1.0
 */

public enum NomJour {
    /**
     * Lundi
     */
    Lundi(1, "Lundi"),
    /**
     * Mardi
     */
    Mardi(2, "Mardi"),
    /**
     * Mercredi
     */
    Mercredi(3, "Mercredi"),
    /**
     * Jeudi
     */
    Jeudi(4, "Jeudi"),
    /**
     * Vendredi
     */
    Vendredi(5, "Vendredi"),
    /**
     * Samedi
     */
    Samedi(6, "Samedi"),
    /**
     * Dimanche
     */
    Dimanche(7, "Dimanche");

    /**
     * Variable d'id des jours de la semaine
     */
    private int idJour;

    /**
     * Intitule string des énumérations des jours de la semaine
     */
    private String intitule;


    /**
     * Constructeur
     * <p>
     *
     * @param  idJour (requis) id d'un jour de la semaine, doit être compris entre <tt>1..7</tt>.
     * @param intitule (requis) nom du jour.
     */
    NomJour(int idJour, String intitule){
        this.idJour=idJour;
        this.intitule=intitule;
    }

    /** Retourne l'idJour passée au constructeur.  */
    public int getIdJour() {
        return idJour;
    }
    /** Retourne l'intitule passé au constructeur.  */
    public String getIntitule() {
        return intitule;
    }

    /** Value - {@value}, méthode pour savoir en ihm avec le calendrier quel jour à qu'elle valeur d'id..*/
    public static NomJour getNomJour(String jour){
        switch(jour){
            case "Lundi":return NomJour.Lundi;
            case "Mardi":return NomJour.Mardi;
            case "Mercredi":return NomJour.Mercredi;
            case "Jeudi":return NomJour.Jeudi;
            case "Vendredi":return NomJour.Vendredi;
            case "Samedi":return NomJour.Samedi;
            case "Dimanche":return NomJour.Dimanche;
            case "LUNDI":return NomJour.Lundi;
            case "MARDI":return NomJour.Mardi;
            case "MERCREDI":return NomJour.Mercredi;
            case "JEUDI":return NomJour.Jeudi;
            case "VENDREDI":return NomJour.Vendredi;
            case "SAMEDI":return NomJour.Samedi;
            case "DIMANCHE":return NomJour.Dimanche;
            default:
                return null;


        }

    }


}
