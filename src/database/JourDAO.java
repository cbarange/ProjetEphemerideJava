package database;

import java.sql.*;

/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.2
 */
public class JourDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";

    public JourDAO(String utilisateur, String mdp, String database) {
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

    public boolean isActifDay(int i){
        boolean res=false;
        this.connect();
        try {
            sqlRequest = "SELECT * from configuration where id_numero_jour="+i+';';
            ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next()){
               res = resSelect.getBoolean(3);
            }
        } catch (SQLException e) {

        } finally {
            this.disConnect();
        }
        return res;
    }


}
