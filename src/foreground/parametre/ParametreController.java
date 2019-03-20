package foreground.parametre;

import background.HashMD5;
import database.UpToAdminDAO;
import foreground.Controller;
import foreground.popupMdp.PopupMdpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ParametreController extends Controller{
    private boolean notif, lancement, mini, memo;

    @FXML
    TextField clefAdmin;

    @FXML
    public Button activerNotif;

    @FXML
    public Button activerDemarrageLancement;

    @FXML
    public Button activerMinimisation;

    @FXML
    public Button memoriserConnexion;

    public void btnClefAdmin(ActionEvent event) throws IOException {
        // need integration pls
        if(new UpToAdminDAO("invite","patate","pts").getAdminRight(utilisateur.pseudo,HashMD5.hash(utilisateur.motDePasse),HashMD5.hash(clefAdmin.getText())))
            System.out.println("Cle correcte");
            //return;
        /*
        ALRTE BOX CLEF ADMIN INCORECT
         */
    }

    public void btnActiverNotif(ActionEvent event) throws IOException{
        // need integration pls
        notif = !notif;
        if(notif){
            activerNotif.setText("Désactivé");
            activerNotif.getStyleClass().add("bouton2");
        }
        else{
            activerNotif.setText("Activé");
            activerNotif.getStyleClass().removeAll("bouton2");
            activerNotif.getStyleClass().add("bouton1");
        }
    }

    public void btnActiverDemarrageLancement(ActionEvent event) throws IOException{
        // need integration pls

        lancement = !lancement;
        if(lancement){
            if(utilisateur.disableAutoRun()){
                activerDemarrageLancement.setText("Désactivé");
                activerDemarrageLancement.getStyleClass().add("bouton2");
            }
        }
        else{
            if(utilisateur.enableAutoRun()){
                activerDemarrageLancement.setText("Activé");
                activerDemarrageLancement.getStyleClass().removeAll("bouton2");
                activerDemarrageLancement.getStyleClass().add("bouton1");
            }
        }
    }

    public void btnActiverMinimisation(ActionEvent event) throws IOException{
        // need integration pls

        mini = !mini;
        if(mini){
            activerMinimisation.setText("Activé");
            activerMinimisation.getStyleClass().removeAll("bouton2");
            activerMinimisation.getStyleClass().add("bouton1");
        }
        else{
            activerMinimisation.setText("Désactivé");
            activerMinimisation.getStyleClass().add("bouton2");
        }
    }

    public void btnChangePassword(ActionEvent event) throws IOException{
        Stage primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../popupMdp/PopupMdp.fxml").openStream());
        PopupMdpController controller = fxmlLoader.getController();
        root.getStylesheets().add(getClass().getResource("../Background.css").toExternalForm());
        controller.setUser(utilisateur);
        controller.setDependance(primaryStage, multiC);
        controller.setCase();

        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void btnMemoriserConnexion(ActionEvent event) throws IOException {
        // ajout du background
        memo = !memo;
        if(memo){
            utilisateur.enableAutoConnect();
            memoriserConnexion.setText("Activé");
            memoriserConnexion.getStyleClass().removeAll("bouton2");
            memoriserConnexion.getStyleClass().add("bouton1");
        }
        else{
            utilisateur.disableAutoConnect();
            memoriserConnexion.setText("Désactivé");
            memoriserConnexion.getStyleClass().add("bouton2");
        }
    }

    public void btnDeconnexion(ActionEvent event) throws IOException {
        utilisateur = null;
        multiC.setActionEvent(event);
        multiC.connexion();
    }


    public void getUserRunStatus(){
        if(utilisateur.getRunStatus()==1){
            activerDemarrageLancement.setText("Activé");
            activerDemarrageLancement.getStyleClass().removeAll("bouton2");
            activerDemarrageLancement.getStyleClass().add("bouton1");
        }else{
            activerDemarrageLancement.setText("Désactivé");
            activerDemarrageLancement.getStyleClass().add("bouton2");
        }
    }
    public void getUserConnectStatus(){
        if(utilisateur.getConnectStatus()==1){
            memoriserConnexion.setText("Activé");
            memoriserConnexion.getStyleClass().removeAll("bouton2");
            memoriserConnexion.getStyleClass().add("bouton1");
        }else{
            utilisateur.disableAutoConnect();
            memoriserConnexion.setText("Désactivé");
            memoriserConnexion.getStyleClass().add("bouton2");
        }
    }
}
