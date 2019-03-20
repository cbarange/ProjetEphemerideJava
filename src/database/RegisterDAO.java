package database;

import java.sql.*;
/**
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.3
 * @since 1.2
 */
public class RegisterDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public RegisterDAO(String utilisateur, String mdp,String database) {
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
            System.out.println("connexion impossible");
            System.out.println(e.getMessage());
        }
    }

    public void disConnect() {
        try {
            connection.close();
            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean register(String login, String mdp, String mail) {
        boolean res=false;
        this.connect();
        try {
            sqlRequest = "INSERT INTO utilisateur(mail,login,mot_de_passe) VALUES('"+mail+"','"+login+"','"+mdp+"');";
            st.executeUpdate(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            res= true;

        } catch (SQLException e) {
            res=false;
        }finally {
            this.disConnect();
        }
        return res;
    }

    public boolean register(String login, String mdp, String mail, String adminKey) {
        boolean res=false;
        this.connect();
        try {
            if(adminKey.equals(new UpToAdminDAO("invite","patate","pts").getAdminKey())){
                sqlRequest = "INSERT INTO utilisateur(mail,login,mot_de_passe) VALUES('"+mail+"','"+login+"','"+mdp+"');";
                st.executeUpdate(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
                if(new UpToAdminDAO("invite","patate","pts").getAdminRight(login,mdp,adminKey))
                    res= true;
            }

        } catch (SQLException e) {
            res=false;
        }finally {
            this.disConnect();
        }
        return res;
    }
}
