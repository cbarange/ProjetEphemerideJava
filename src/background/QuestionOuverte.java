package background;

import database.AjoutQuestionDAO;
import database.AjouteReponseDAO;
import database.QuestionDAO;


import java.sql.SQLException;
import java.util.Calendar;

/**Class Question Ouvertes, extends Question
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.0
 */
public class QuestionOuverte extends Question{

    /**
     * Variable contenant la réponse.
     */
    private ReponseOuverte reponse;

    /** Constructeur de QuestionOuverte
     * <p>
     *
     * @param date, contenant la date de la question ouverte
     * @param questionDAO
     * @exception SQLException, en cas d'erreur de la base de donnée.
     */
    public QuestionOuverte(Calendar date, QuestionDAO questionDAO) throws SQLException {
        super(date,questionDAO);
        reponse=new ReponseOuverte(this.idQuestion);
    }

    public QuestionOuverte(String contenu, String indice, String reponse, AnneeTC uneAnne){
        this.contenu = contenu;
        this.indice = indice;
        this.anneetc = uneAnne;
        this.reponse = new ReponseOuverte(contenu);
    }

    /** Retourne la réponse passé au constructeur.  */
    public ReponseOuverte getReponse(){
        return reponse;
    }

    /**
     * Méthode insertQuestionOuverte, servant à insérer une question ouverte.
     * <p>
     *
     * @param anneeTC (requis)
     */
    public void insertQuestionOuverte(AnneeTC anneeTC){
        AjoutQuestionDAO ajoutQuestionDAO = new AjoutQuestionDAO("invite", "patate", "pts");
        int id = ajoutQuestionDAO.determineLastDay();
        if(id != 0){
            System.out.println(this.contenu+this.indice+anneeTC+id);
            if(ajoutQuestionDAO.insertQuestion(this.contenu,this.indice,anneeTC,id)){
                AjouteReponseDAO ajouteReponseDAO = new AjouteReponseDAO("invite","patate","pts");
                int numeroQuestion = ajoutQuestionDAO.determineIdQuestion(id);
                if(ajouteReponseDAO.isQuestion(numeroQuestion)){
                    ajouteReponseDAO.insertReponse(reponse,numeroQuestion);
                }
            }

        }
    }
}
