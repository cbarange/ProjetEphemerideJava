package background;

import database.EvenementDAO;
import database.JourDAO;
import database.QuestionDAO;

import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**Class Jour, implements la classe comparable et sert à associée les jours au questions et évènements.
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class Jour implements Comparable {

    /**
     * Variable d'id d'une journée.
     */
    private int idJour;

    /**
     * Variable actif, savoir si un jour à une question
     */
    private boolean actif=false;

    /**
     * Variable date, de calendrier.
     */
    private Calendar date;

    /**
     * Variable de numéro de la journée.
     */
    private int numeroJour;

    /**
     * Variable Question, de question.
     */
    private Question question;

    /**
     * Variable Evenement, d'évenement.
     */
    private Evenement evenement;

    /**
     * Variable de nom du jour.
     */
    private NomJour nomJour;


    /**
     * constructeur
     * <p>
     *
     * @param i (requis),
     * @param month (requis), donne le mois.
     * @exception  SQLException, en cas d'erreurs de base de donnée
     */
    public Jour(int i, int month) throws SQLException {
        this.numeroJour=i;
        date=new GregorianCalendar();
        date.set(Calendar.DAY_OF_MONTH,i);
        date.set(Calendar.MONTH,month);

        /*MANQUE PAS DES TRUC ICI ?*/

        DateFormatSymbols symbols=new DateFormatSymbols(Locale.FRANCE);
        if(new EvenementDAO("invite","patate","pts").exist(date)){
            evenement=new EvenementDAO("invite","patate","pts").get(date);
        }



        JourDAO jourDAO=new JourDAO("invite","patate","pts");

        actif=jourDAO.isActifDay(NomJour.getNomJour(symbols.getWeekdays()[date.get(Calendar.DAY_OF_WEEK)].toUpperCase()).getIdJour());
        QuestionDAO questionDAO=new QuestionDAO("invite","patate","pts");

        if(actif && questionDAO.setQuestion(date)){
            if(questionDAO.isOpenQuestion()){
                question=new QuestionOuverte(date,questionDAO);
            }else if(questionDAO.isQCMQuestion()){
                question=new QuestionQcm(date,questionDAO);
            }
        }else{
            question = new Question();
        }
    }

    /** Retourn la date du jour passée au constructeur.  */
    public Calendar getDate() {
        return date;
    }

    /** Retourne l'actif passée au constructeur.  */
    public boolean isActif() {
        return actif;
    }

    /** Retourne la question passée au constructeur.  */
    public Question getQuestion() {
        return question;
    }

    /** Retourne l'évènement passée au constructeur.  */
    public Evenement getEvenement() {
        return evenement;
    }

    /** Retourne le nom du jour passée au constructeur.  */
    public NomJour getNomJour() {
        return nomJour;
    }

    /** Retourne l'object o,  */
    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /** Retourne le numero du jour passée au constructeur.  */
    public int getNumero() {
        return numeroJour;
    }
}
