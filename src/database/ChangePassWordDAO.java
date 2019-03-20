package database;

import java.sql.*;
/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.1
 */
public class ChangePassWordDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public ChangePassWordDAO(String utilisateur, String mdp,String database) {
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

    public boolean changePassWord(String user, String mdp,String nouveauMdp) {
        this.connect();
        boolean res = false;
        try {
            sqlRequest = "UPDATE utilisateur set mot_de_passe='"+nouveauMdp+"' where login='"+user+"' and mot_de_passe='"+mdp+"';";
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
