package database;

import java.sql.*;

public class ReponseOuverteDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";

    public ReponseOuverteDAO(String utilisateur, String mdp, String database) {
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

    public String getReponse(int id_question) {
        String res="";
        this.connect();
        try {
            sqlRequest = "SELECT contenu from reponse where id_question="+id_question+"";;
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next()){
                res = resSelect.getString(1);
            }
        } catch (SQLException e) {

        } finally {
            this.disConnect();
        }
        return res;
    }
}
