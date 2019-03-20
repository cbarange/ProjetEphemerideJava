package database;

import background.Evenement;
import background.TypeEvent;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;

public class EvenementDAO {
    String url;
    String utilisateur;
    String motDePasse;
    Connection connection;
    Statement st;
    String sqlRequest;

    final String ip="90.78.143.166";
    //final String ip="109.11.132.182";
    //final String ip="localhost";

    public EvenementDAO(String utilisateur, String mdp, String database) {
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


    public boolean exist(Calendar date) {
        String day=Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String month=Integer.toString(date.get(Calendar.MONTH)+1);

        int annee;
        if(date.get(Calendar.DAY_OF_MONTH)<10)
            day="0"+date.get(Calendar.DAY_OF_MONTH);
        if((date.get(Calendar.MONTH)+1)<10)
            month="0"+(date.get(Calendar.MONTH)+1);
        annee=date.get(Calendar.YEAR);
        String dateJour=day+'/'+month+'/'+annee;

        boolean res =false;
        this.connect();

        try {
            sqlRequest=null;
            sqlRequest="select e.id from evenement e,jour j where j.this_date=STR_TO_DATE('"+dateJour+"','%d/%m/%Y') and j.id=e.id_jour;";
            ResultSet resSelect=null;
            resSelect = st.executeQuery(sqlRequest);//On utilise executeQuery pour les requetes que n'ecrive pas dans la base de donnees
            if(resSelect.next()){
                res=true;
                resSelect.close();
               }
        } catch (SQLException e) {
            res = false;
        }finally {
            this.disConnect();
        }
        return res;
    }

    public Evenement get(Calendar date) {
        return new Evenement("Plage",LocalDate.now(),TypeEvent.Cours);
    }
}
