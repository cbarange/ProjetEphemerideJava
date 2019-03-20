package database;

import background.QuestionQcm;
import background.ReponseQcm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pierre Lemaigre pierre.lemaigre@etudiant.univ-lr.fr
 * @version 1.4
 * @since 1.4
 */
public class ReponseQcmDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    boolean estBon;
    String reponse;
    final String ip = "90.78.143.166";

    public ReponseQcmDAO(String utilisateur, String mdp, String database) {
        url = "jdbc:mariadb://" + ip + ":3306/" + database + "?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.utilisateur = utilisateur;
        motDePasse = mdp;
        connection = null;
        st = null;
    }


    public void connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url + "&user=" + utilisateur + "&password=" + motDePasse);
            st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
        } catch (ClassNotFoundException | SQLException e) {
            this.disConnect();

        }
    }

    public void disConnect() {
        try {
            if (!(connection == null))
                connection.close();
            if (!(st == null))
                st.close();
        } catch (SQLException e) {

        }
    }

    public int setId(int id_question) {
        this.connect();
        int res = 0;
        try {
            sqlRequest = "SELECT * from reponse where id_question='" + id_question + "' order by id;";
            ResultSet resultSet = st.executeQuery(sqlRequest);
            resultSet.last();
            res = resultSet.getRow();
            resultSet.close();
        } catch (SQLException e) {
        } finally {
            this.disConnect();
        }
        return res;
    }

    public void setQuestion(int id_question, int row) {
        this.connect();
        try {
            sqlRequest = "SELECT * from reponse where id_question='" + id_question + "' order by id;";
            ResultSet resultSet = st.executeQuery(sqlRequest);
            if(resultSet.next()){

            }

            if (resultSet.first()) {

                for (int i = 0; i < row; i++){
                    resultSet.next();
                }
                reponse = resultSet.getString("contenu");
                estBon = resultSet.getBoolean("est_bonne");

            }
            resultSet.close();
        } catch (SQLException e) {
        } finally {
            this.disConnect();
        }
    }

    public boolean isEstBon() {
        return estBon;
    }

    public String getReponse() {
        return reponse;
    }
}
