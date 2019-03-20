package background;

import database.QuestionDAO;

import java.sql.SQLException;
import java.util.Calendar;

/**Class Question
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.4
 * @since 1.0
 */
public class Question {

    /**
     * Variable protégé d'id de la question.
     */
    protected int idQuestion=0;

    /**
     * Variable protégé contenant le contenu de la question.
     */
    protected String contenu="No Question";

    /**
     * Variable protégé, contenant le niveau d'année.
     */
    protected AnneeTC anneetc;// a revoir

    /**
     * Variable protégé, de l'annexe.
     */
    protected boolean annexe;

    /**
     * Variable protégé contenant les indices.
     */
    protected String indice="";

    /**
     * Variable protégé contenant la bonne réponse.
     */
    protected boolean bonneReponse;

    /** Constructeur de Question
     * <p>
     *
     * @param date (requis),
     * @param  questionDAO(requis,
     * @exception  SQLException, en cas d'erreur de la base de donnée
     */
    Question(Calendar date, QuestionDAO questionDAO) throws SQLException {

        //this.contenu=questionDAO.getQuestion();
        this.contenu=questionDAO.getQuestion();
        this.idQuestion=questionDAO.getId();
        this.indice=questionDAO.getIndice();
        this.anneetc=AnneeTC.getAnneeTc(questionDAO.getAnnee_tc());

    }

    /** Constructeur de Question
     * <p>
     *
     */
    public Question() {
        this.contenu="Il n'y a pas de question aujourd'hui";
        this.idQuestion=0;
        this.indice="Changez de jour pour avoir une question";
        this.anneetc=AnneeTC.TC1;
    }

    /**
     * Fonction pour recuperer le contenu de la question
     * @return retourne la chaine de caractere qui contient la question
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Fonction pour connaitre la presence d'une annexe ou non
     * @return retourne un booleen sur la presence d'une annexe
     */
    public boolean isAnnexe() {
        return annexe;
    }

    /**
     * Fonction pour recuperer le contenu de l'indice
     * @return
     */
    public String getIndice() {
        return indice;
    }

    /**
     * Fonction pour connaitre si la reponse est bonne ou non
     * @return retourne un booleen sur la veracite de la question
     */
    public boolean isBonneReponse() {
        return bonneReponse;
    }


}
