/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import background.Mois;
import background.Utilisateur;
import foreground.MultiController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *  Class Ephemeride.
 *  <p>
 *
 *  @author
 *  @version     1.
 *  @since       1.0
 */
public class Ephemeride extends Application {

    /**
     * Variable ListMois, est une list des Mois de l'année.
     */
    static List<Mois> listMois = new ArrayList<>();

    /** Retourne la liste de mois.  */
    public static List<Mois> getListMois(){
        return new ArrayList<>(listMois);
    }


    /**
     * Méthode start qui créer et remplis le calendrier avec la liste de mois.
     * <p>
     *
     * @param primaryStage (requis),
     * @exception IOException, renvoie une erreur d'IO.
     * @exception SQLException, renvoit une erreur de base de donnée.
     */
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {

        //--- DEBUG ---
        System.out.println("Application lancer Utilisateur cree");
        //=============


        for (int i = 0; i < 12; i++) {
            //--- DEBUG ---
            System.out.println("Creation du Mois : " + i);
            //=============
            Mois mois = new Mois();
            listMois.add(mois);
        }

        Calendar calendar=new GregorianCalendar();
        listMois.set(calendar.get(Calendar.MONTH),new Mois(calendar.get(Calendar.MONTH)));

        for (int i = 0; i < 12; i++) {
            if(i==calendar.get(Calendar.MONTH) && i<11)
                i++;
            int finalI = i;
            new Thread("" + finalI) {
                public void run() {
                    //--- DEBUG ---
                    //System.out.println("Creation du Mois : " + finalI);
                    //=============

                    Mois mois = null;
                    try {
                        mois = new Mois(finalI);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    listMois.set(finalI,mois);

                }
            }.start();
        }

    /*
        for (int i = 0; i < 12; i++) {
            //--- DEBUG ---
            System.out.println("Creation du Mois : " + i);
            //=============
            Mois mois = new Mois(i);
            listMois.add(mois);
        }
    */

        Utilisateur utilisateur = new Utilisateur();
        MultiController multiC = new MultiController(utilisateur);
    }

    /**
     * Méthode main.
     * <p>
     *
     * @param args .
     */
    public static void main(String[] args) {

        File file = new File(System.getenv("APPDATA") + "\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");
        if ((file.exists() & args.length > 0)) {
            try {
                BufferedReader br;
                br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                char autoRun = line.charAt(10);
                if (autoRun == '1') {
                    launch(args);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            launch(args);
        }

    }

}