package database;


import background.ReponseOuverte;
import background.ReponseQcm;
import background.TypeEvent;

import java.sql.*;

/**
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 2.0
 * @since 2.0
 */
public class AjouteReponseDAO {
    AjouteReponseDAO(){

    }
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;


    final String ip="90.78.143.166";

    public AjouteReponseDAO(String utilisateur, String mdp,String database) {
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

    public boolean insertReponse(ReponseQcm reponse, int numeroJour){
        boolean res = true;
        this.connect();
        try{
            sqlRequest = "INSERT INTO reponse(est_bonne,contenu,id_question) VALUES("+reponse.isEstChoixValide()+","+reponse.getReponse()+","+numeroJour+")";
            st.executeUpdate(sqlRequest);
        } catch (SQLException e){
            res = false;
        } finally {
            this.disConnect();
        }
        return res;
    }

    public boolean insertReponse(ReponseOuverte reponse, int numeroJour){
        boolean res = true;
        this.connect();
        try{
            sqlRequest = "INSERT INTO reponse(contenu,id_question) VALUES("+reponse.getContenu()+","+numeroJour+")";
            st.executeUpdate(sqlRequest);
        } catch (SQLException e){
            res = false;
        } finally {
            this.disConnect();
        }
        return res;
    }

    public boolean isQuestion(int idQuestion){
        boolean res = false;
        try{
            sqlRequest = "SELECT id FROM question WHERE id="+idQuestion+";";
            ResultSet resultSet = st.executeQuery(sqlRequest);

        } catch (SQLException e){

        } finally {
            this.disConnect();
        }
        return res;
    }
}
