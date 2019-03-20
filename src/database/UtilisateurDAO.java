package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;


    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public UtilisateurDAO(String utilisateur, String mdp, String database) {
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

    public boolean useTokens(String user, String mdp, int nbPoint) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "UPDATE utilisateur set points="+nbPoint+" where login='"+user+"' and mot_de_passe='"+mdp+"';";
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


    public boolean earnTokens(String userEarnToken, String mdp, int nbPoint, int mileStoneEarnTokens) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "UPDATE utilisateur set points="+nbPoint+" and set millestone='"+mileStoneEarnTokens+"'where login='"+userEarnToken+"' and mot_de_passe='"+mdp+"';";
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

    public boolean setTheme(String pseudoSetTheme, String mdpSetTheme, int settheme) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "UPDATE utilisateur set theme='"+Integer.toString(settheme)+"' where login='"+pseudoSetTheme+"' and mot_de_passe='"+mdpSetTheme+"';";
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
