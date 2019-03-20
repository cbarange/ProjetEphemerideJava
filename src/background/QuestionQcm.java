package background;

import database.AjoutQuestionDAO;
import database.AjouteReponseDAO;
import database.QuestionDAO;
import database.ReponseQcmDAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Class QuestionQCM
 * <p>
 *
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.4
 * @since 1.0
 */
public class QuestionQcm extends Question{

    /**
     * Variable listeReponse, contenant la liste de réponse.
     */
    private List<ReponseQcm> listeReponse;

    public String getReponse() {
        return reponse;
    }

    /**
     * Variable réponse, contenant la réponse.
     */
    private String reponse;

    /** Constructeur de QuestionQCM
     * <p>
     *
     * @param  date (requis), contient la date de la question
     * @param questionDAO (requis),
     * @exception SQLException, renvoi un message en cas d'erreur liée à la base de donnée.
     */
    QuestionQcm(Calendar date, QuestionDAO questionDAO) throws SQLException {
        super(date,questionDAO);
        listeReponse=new ArrayList<>();
        ReponseQcmDAO reponseQcmDao = new ReponseQcmDAO("invite","patate","pts");
        int j = reponseQcmDao.setId(this.idQuestion);

        for(int i = 0; i < j; i++){
            reponseQcmDao.setQuestion(this.idQuestion, i);
            if(reponseQcmDao.isEstBon())
               reponse= reponseQcmDao.getReponse();
            listeReponse.add(new ReponseQcm(reponseQcmDao.getReponse(),reponseQcmDao.isEstBon(),i));

        }
    }
    /** Constructeur de QuestionQCM
     * <p>
     *
     * @param  contenu (requis), contient le contenu de la question.
     * @param indice (requis), contient l'indice lié à la question.
     * @param anneeTC (requis), contient l'année pour laquel cette question est destiné
     * @param listeReponse (requis), contient la liste des réponse
     */
    QuestionQcm(String contenu,String indice, AnneeTC anneeTC, List<ReponseQcm> listeReponse){
        this.contenu = contenu;
        this.indice = indice;
        this.anneetc = anneeTC;
        this.listeReponse = listeReponse;
    }

    /**
     * Méthode parseIt, .
     * <p>
     *
     */
    public String parseIt(){
        String res ="";
        for(int i = 0; i < this.listeReponse.size(); i++){
            res+=" " + listeReponse.get(i).getReponse() + "\n";
        }
        return res;
    }

    /** Retourne la liste des réponse passé au constructeur.  */
    public List<ReponseQcm> getChoix() {
        return listeReponse;
    }

    /**
     * Méthode insertQuestionQCM, servant à insérer une question de type qcù.
     * <p>
     *
     * @param anneeTC (requis)
     */
    public void insertQuestionQcm(AnneeTC anneeTC){
        AjoutQuestionDAO ajoutQuestionDAO = new AjoutQuestionDAO("invite", "patate", "pts");
        int id = ajoutQuestionDAO.determineLastDay();
        if(id != 0){
            ajoutQuestionDAO.insertQuestion(this.contenu,this.indice,anneeTC,id);
            AjouteReponseDAO ajouteReponseDAO = new AjouteReponseDAO("invite","patate","pts");
            int numeroQuestion = ajoutQuestionDAO.determineIdQuestion(id);
            if(ajouteReponseDAO.isQuestion(numeroQuestion)){
                for(int i = 0; i<listeReponse.size(); i++){
                    ajouteReponseDAO.insertReponse(listeReponse.get(i), numeroQuestion);
                }
            }
        }
    }

}
