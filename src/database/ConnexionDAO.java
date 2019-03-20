package database;

import java.sql.*;
/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.1
 */
public class ConnexionDAO{
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    String pseudo;
    String mail;
    int nbPoint;
    int theme;
    int mileStone;
    boolean admin;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public ConnexionDAO(String utilisateur, String mdp,String database) {
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

    public String getPseudo() {
        return pseudo;
    }

    public String getMail() {
        return mail;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public int getTheme() {
        return theme;
    }

    public int getMileStone() {
        return mileStone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean userExite(String user, String mdp) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "SELECT * from utilisateur where login='"+user+"' and mot_de_passe='"+mdp+"';";
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees

            if(resSelect.next()){
                this.pseudo=user;
                this.mail=resSelect.getString("mail");
                this.nbPoint=resSelect.getInt("points");
                this.mileStone=Integer.parseInt(resSelect.getString("millestone"));
                this.theme=Integer.parseInt(resSelect.getString("theme"));
                this.admin=resSelect.getBoolean("admin");
                res = true;
            }

        } catch (SQLException e) {

        } finally {
            this.disConnect();
        }
        return res;
    }
}
