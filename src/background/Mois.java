package background;

import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.*;

/** Class Mois qui implements comparable
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class Mois implements Comparable{

    /**
     * Variable d'année.
     */
    private int annee;

    /**
     * Variable Liste, d'une liste de jour.
     */
    private List<Jour> listeJour;

    /**
     * Variable contenant le nom des mois.
     */
    private NomMois nomMois;

    /**
     * Constructeur.
     * <p>
     *
     */
    public Mois(){

    }

    /** Constructeur de Mois
     * <p>
     *
     * @param month (requis),
     * @exception  SQLException, en cas d'erreur de la base de donnée
     */
    public Mois(int month) throws SQLException {
        listeJour=new ArrayList<>();
        Calendar calendar=new GregorianCalendar();
        calendar.set(Calendar.MONTH,month);
        for(int i=1;i<calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+1;i++){
            Jour jour =new Jour(i,month);

            //--- DEBUG ---
            DateFormatSymbols symbols=new DateFormatSymbols(Locale.FRANCE);
            System.out.println("Creation du Jour : "+i+ " Du Mois : "+symbols.getMonths()[calendar.get(Calendar.MONTH)].toUpperCase()+ " : "+jour.getQuestion().getContenu());
            //=============

            listeJour.add(jour);
        }
        annee=calendar.get(Calendar.YEAR);
    }

    /** Retourne la liste des jours.  */
    public List<Jour> getListeJour() {
        return listeJour;
    }

    /** Retourne un jour en particulier*/
    public Jour getJour(int numeroJour){
        for(Jour j : listeJour){
            if(j.getNumero()==numeroJour){
                return j;
            }
        }
        return listeJour.get(0);
    }

    /** Retourne l'année.*/
    public int getAnnee() {
        return annee;
    }

    /** Retourn le nom du mois.*/
    public NomMois getNomMois() {
        return nomMois;
    }

    /** Retourne l'object o, */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
