package database;

import java.sql.*;
import java.util.Calendar;
/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.2
 */
public class QuestionDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    int id;
    String question;
    String indice;
    String annee_tc;
    int nb_bonnes_reponses;
    int nb_mauvaises_reponses;
    int id_jour;



    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public QuestionDAO(String utilisateur, String mdp,String database) {
        url = "jdbc:mariadb://" + ip + ":3306/"+database+"?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.utilisateur = utilisateur;
        motDePasse = mdp;
        connection = null;
        st = null;
    }


    public void connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url+"&user="+utilisateur+"&password="+motDePasse);
            st = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            this.disConnect();
        }
    }

    public void disConnect() {
        try {
            if(!(connection ==null))
                connection.close();
            if(!(st==null))
                st.close();
        } catch (SQLException e) {

        }

    }



    public boolean setQuestion(Calendar date) {
        boolean res = false;

        String day=Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String month=Integer.toString(date.get(Calendar.MONTH)+1);

        int annee;
        if(date.get(Calendar.DAY_OF_MONTH)<10)
            day="0"+date.get(Calendar.DAY_OF_MONTH);
        if((date.get(Calendar.MONTH)+1)<10)
            month="0"+(date.get(Calendar.MONTH)+1);
        annee=date.get(Calendar.YEAR);
        String dateJour=day+'/'+month+'/'+annee;


        this.connect();

        try {
            /*
            sqlRequest = "SELECT q.id,q.question,q.indice,q.annee_tc,q.nb_bonnes_reponses,q.nb_mauvaises_reponses" +
                    " FROM question q, jour j " +
                    "WHERE q.id_jour=j.id" +
                    "AND j.this_date=STR_TO_DATE('"+dateJour+"','%d/%m/%Y');";
            */
            sqlRequest=null;
            sqlRequest="SELECT q.id,question,indice,annee_tc,nb_bonnes_reponses,nb_mauvaises_reponses FROM question q, jour j WHERE q.id_jour=j.id AND j.this_date=STR_TO_DATE('"+dateJour+"','%d/%m/%Y');";

            ResultSet resSelect=null;
            resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees

            if(resSelect.next()){
                this.id=resSelect.getInt("id");
                this.question=resSelect.getString("question");
                this.indice=resSelect.getString("indice");
                this.annee_tc=resSelect.getString("annee_tc");
                this.nb_bonnes_reponses=resSelect.getInt("nb_bonnes_reponses");
                this.nb_mauvaises_reponses=resSelect.getInt("nb_mauvaises_reponses");
                this.id_jour=resSelect.getInt("id");
                res=true;
                resSelect.close();

            }
        } catch (SQLException e) {
            res = false;
        }finally {

            this.disConnect();
        }
        return res;
    }

    public boolean isQCMQuestion(){
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "SELECT COUNT(id) FROM reponse WHERE id_question="+this.id+";";
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next()){
                if(resSelect.getInt(1)>1){
                    res = true;
                }
            }
        } catch (SQLException e) {
            res = false;
        }finally {
            this.disConnect();
        }
        return res;
    }

    public boolean isOpenQuestion(){
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "SELECT COUNT(id) FROM reponse WHERE id_question="+this.id+";";
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next()){
                if(resSelect.getInt(1)<=1) {
                    res = true;
                }
            }
        } catch (SQLException e){
            res = false;
        }finally {
            this.disConnect();
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getIndice() {
        return indice;
    }

    public String getAnnee_tc() {
        return annee_tc;
    }

    public int getNb_bonnes_reponses() {
        return nb_bonnes_reponses;
    }

    public int getNb_mauvaises_reponses() {
        return nb_mauvaises_reponses;
    }

    public int getId_jour() {
        return id_jour;
    }


}
