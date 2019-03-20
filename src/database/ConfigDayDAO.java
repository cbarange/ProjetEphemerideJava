package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDayDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public ConfigDayDAO(String utilisateur, String mdp, String database) {
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
            if(connection != null){
                connection.close();
            }
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
        }
    }

    public boolean changeActifDay(boolean actif,int id) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "UPDATE configuration set etat="+actif+" where id_numero_jour="+id+";";
            st.executeUpdate(sqlRequest);//On utilise executeUpdate pour les requetes qui ecrive dans la base de donnees
            res=true;
        } catch (SQLException e) {
            this.disConnect();
            res = false;
        }finally {
            this.disConnect();
        }
        return res;
    }
}
