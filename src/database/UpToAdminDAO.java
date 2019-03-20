package database;

import java.sql.*;

/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.1
 */

public class UpToAdminDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public UpToAdminDAO(String utilisateur, String mdp,String database) {
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
            connection.close();
            st.close();
        } catch (SQLException e) {

        }

    }
    public String getAdminKey(){
        this.connect();
        try {
            sqlRequest = "SELECT clee from admin_key where id=1;";
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees

            if(resSelect.next() && !resSelect.getString("clee").isEmpty()){
                return resSelect.getString("clee");
            }
        }catch (SQLException e){

        }finally {
            this.disConnect();
        }
        return null;

    }

    public boolean getAdminRight(String user,String mdp,String key) {
        this.connect();
        boolean res = false;

        try {
            sqlRequest = "SELECT clee from admin_key where id=1;";
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next() && resSelect.getString("clee").equals(key)){

                sqlRequest = "update utilisateur set admin=1 where login='"+user+"' and mot_de_passe='"+mdp+"';";
                st.executeUpdate(sqlRequest);//On utilise executeUpdate pour les requetes qui ecrive dans la base de donnees

                this.disConnect();
                return true;
            }
        } catch (SQLException e) {
            this.disConnect();
            return false;
        }
        this.disConnect();
        return false;
    }
}
