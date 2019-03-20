package background;

import java.util.Calendar;

/**
 *  Class enum des Mois
 *  <li>{@link #Janvier}</li>
 *  <li>{@link #Fevrier}</li>
 *  <li>{@link #Mars}</li>
 *  <li>{@link #Avril}</li>
 *  <li>{@link #Mai}</li>
 *  <li>{@link #Juin}</li>
 *  <li>{@link #Juillet}</li>
 *  <li>{@link #Aout}</li>
 *  <li>{@link #Septembre}</li>
 *  <li>{@link #Octobre}</li>
 *  <li>{@link #Novembre}</li>
 *  <li>{@link #Decembre}</li>
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.0
 *  @since       1.0
 */

public enum NomMois {
    /**
     * Janvier
     */
    Janvier(1, "Janvier"),

    /**
     * Fevrier
     */
    Fevrier(2, "Fevrier"),

    /**
     * Mars
     */
    Mars(3, "Mars"),

    /**
     * Avril
     */
    Avril(4, "Avril"),

    /**
     * Mai
     */
    Mai(5, "Mai"),

    /**
     * Juin
     */
    Juin(6, "Juin"),

    /**
     * Juillet
     */
    Juillet(7, "Juillet"),

    /**
     * Aout
     */
    Aout(8, "Aout"),

    /**
     * Septembre
     */
    Septembre(9, "Septembre"),

    /**
     * Octobre
     */
    Octobre(10, "Octobre"),

    /**
     * Novembre
     */
    Novembre(11, "Novembre"),

    /**
     * Decembre
     */
    Decembre(12, "Decembre");

    /**
     * Variable d'id des Mois
     */
    private int idMois;

    /**
     * Intitule string des énumérations des jours de la semaine
     */
    private String intitule;

    /**
     * Constructeur
     * <p>
     *
     * @param  idMois (requis) id d'un mois de l'année, doit être compris entre <tt>1..12</tt>.
     * @param intitule (requis) nom du mois.
     */
    NomMois(int idMois,String intitule){
        this.idMois=idMois;
        this.intitule=intitule;
    }

    /** Retourne l'idMois passé au constructeur.  */
    public int getIdMois() {
        return idMois;
    }

    /** Retourne l'intitule passé au constructeur.  */
    public String getIntitule() {
        return intitule;
    }
}