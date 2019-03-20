package background;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**Class BaseDeDonnes, permettant de gérer le liens entre la base de donnée et le java.
 * <p>
 *
 */
public class BaseDeDonnees {
	/**
	 * Variable String des url
	 */
	String url;

	/**
	 * Variable String du pseudo utilisateur
	 */
	String utilisateur;

	/**
	 * Variable String du mot de passe de l'utilisateur
	 */
	String motDePasse;

	/**
	 * Variable Connection permettant la connection
	 */
	Connection connection;

	/**
	 * Variable Statement, st.
	 */
	Statement st;

	/**
	 * Variable String des requête sql
	 */
	String sqlRequest;

	/**
	 * Variable String de l'ip.
	 */
	final String ip="90.78.143.166";
	//final String ip="109.11.132.182";
	//final String ip="localhost";

	/**Méthode base de donnée
	 * <p>
	 *
	 * @param utilisateur (requis)
	 * @param mdp (requis)
	 * @param database (requis)
	 */
	public BaseDeDonnees(String utilisateur, String mdp,String database) {
		//url = "jdbc:mariadb://" + ip + ":3307/"+database+"?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=invite&password=patate";
		url = "jdbc:mariadb://" + ip + ":3306/"+database+"?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		this.utilisateur = utilisateur;
		motDePasse = mdp;
		connection = null;
		st = null;
	}

	/**Méthode connection, permettant de se connecter.
	 * <p>
	 *
	 */
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

	/**Methode insertRequest, permettant d'insérer une requête.
	 * <p>
	 *
	 * @param  requete (requis)
	 */
	public void insertRequest(String requete) {
		try {
			//sqlRequest = "INSERT INTO tabletestv1 (valeur) VALUES('valeur');";
			//sqlRequest = "SELECT * from tabletestv1 where valeur='valeur10';";
			sqlRequest=requete;
			st.executeUpdate(sqlRequest);// on utilise executeUpdate pour des requetes qui ne revoie rien
			connection.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}/*finally{
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					
				}
			}
		}*/
	}

	/**Méthode selectrequest permettant de sélectionner une requête.
	 * <p>
	 *
	 * @param requete
	 */
	public void selectRequest(String requete) {
		try {
			//sqlRequest = "INSERT INTO tabletestv1 (valeur) VALUES('valeur');";
			//sqlRequest = "SELECT * from tabletestv1 where valeur='valeur10';";
			sqlRequest=requete;
			ResultSet resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
			while (resSelect.next()) {
				System.out.println(resSelect.getInt("id") + resSelect.getString("valeur"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/*Quelque commande mysql
	 * show tables
	 * 
	 */

	/**Méthode disconnect permettant de déconnecté.
	 * <p>
	 *
	 */
	public void disConnect() {
		try {
			connection.close();
			st.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
