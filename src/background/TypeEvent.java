package background;

import java.awt.*;

/**
 *  Class enum de l'année dans laquel ce trouve les étudiants.
 *  <li>{@link #Loisir}</li>
 *  <li>{@link #Cours}</li>
 *  <li>{@link #Conference}</li>
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.0
 *  @since       1.0
 */

public enum TypeEvent {

    /**
     * Loisir
     */
    Loisir("Loisir", Color.GREEN),

    /**
     * Cours
     */
    Cours("Cours", Color.RED),

    /**
     * Conference
     */
    Conference("Conference", Color.ORANGE);

    /**
     * Variable String des intitulé de l'enum.
     */
    private String intitule;

    /**
     * Variable color des types d'évènement de l'enum.
     */
    private Color typeEvent;

    /**
     * Constructeur
     * <p>
     *
     * @param intitule (requis) intitulé du type d'évènement.
     * @param typeEvent (requis) couleur par défaut du type d'évènement.
     */
    TypeEvent(String intitule, Color typeEvent){
        this.intitule=intitule;
        this.typeEvent=typeEvent;
    }

    /** Retourne l'intitule passé au constructeur.  */
    public String getIntitule() {
        return intitule;
    }

    /** Retourne la couleur passé au constructeur.  */
    public  Color getTypeEvent(){
        return typeEvent;
    }
}

