package background;

import java.time.LocalDate;

/**Class evenement, permettant de gérer les évènements.
 *
 * @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 * @version     1.0
 * @since       1.0
 */

public class Evenement {

    /**
     * Variable d'intitulé des évènements.
     */
    private String intitule;

    /**
     * Variable de date.
     */
    private LocalDate date;

    /**
     * Variable de l'énumération du type d'évènement.
     */
    private TypeEvent typeEvent;

    /**
     * Constructeur, vide.
     * <p>
     *
     */
    public Evenement(String intitule, LocalDate date, TypeEvent typeEvent){
        this.intitule=intitule;
        this.date=date;
        this.typeEvent=typeEvent;
    }

    /** Retourne l'idJour passée au constructeur.  */
    public String getIntitule() {
        return intitule;
    }

    /** Retourne l'idJour passée au constructeur.  */
    public LocalDate getDate() {
        return date;
    }

    /** Retourne le typeEvent passée au constructeur.  */
    public TypeEvent getTypeEvent() {
        return typeEvent;
    }
}
