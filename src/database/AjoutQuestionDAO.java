package database;

import background.AnneeTC;
import background.TypeEvent;

import java.sql.*;

/**
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.4
 * @since 1.4
 */
public class AjoutQuestionDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;


    final String ip="90.78.143.166";

    public AjoutQuestionDAO(String utilisateur, String mdp,String database) {
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
            if(connection != null) {
                connection.close();
            }
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
        }
    }

    public boolean insertQuestion(String contenu, String indice, AnneeTC anneeTC, int idJour){
        boolean res = true;
        this.connect();
        try{
            sqlRequest = "INSERT INTO QUESTION(question,indice,annee_tc,id_jour) VALUES("+contenu+","+indice+","+anneeTC+","+idJour+";";
            st.executeUpdate(sqlRequest);
        } catch (SQLException e){
            res = false;
        } finally {
            this.disConnect();
        }
        return res;
    }

    public int determineLastDay(){
        int res = 0;
        this.connect();
        try{
            sqlRequest = "SELECT j.id FROM jour j WHERE NOT exists(SELECT j.id FROM question q, configuration c WHERE j.id = q.id_jour AND c.id_numero_jour = j.numero_jour AND c.etat=true order by j.id);";
            ResultSet resultSet = st.executeQuery(sqlRequest);
            if(resultSet.last()) {
                res = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e){

        } finally {
            this.disConnect();
        }
        return res;
    }

    public int determineIdQuestion(int idJour){
        this.connect();
        int res = 0;
        try{
            sqlRequest = "SELECT id FROM question WHERE id_jour="+idJour+";";
            ResultSet resultSet = st.executeQuery(sqlRequest);
            res = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e){

        } finally {
            this.disConnect();
        }
        return res;
    }
}
