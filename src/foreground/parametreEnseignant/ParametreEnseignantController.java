package foreground.parametreEnseignant;

import application.Ephemeride;
//import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;

import com.sun.javafx.application.HostServicesDelegate;
import database.ConfigDayDAO;
import foreground.Controller;
import foreground.popupMdp.PopupMdpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParametreEnseignantController extends Controller implements Initializable {
    private boolean notif, lancement, mini, lundi, mardi, mercredi, jeudi, vendredi, samedi;

    @FXML
    public Button activerNotif;

    @FXML
    public Button activerDemarrageLancement;

    @FXML
    public Button activerMinimisation;

    @FXML
    public Button lundiB;

    @FXML
    public Button mardiB;

    @FXML
    public Button mercrediB;

    @FXML
    public Button jeudiB;

    @FXML
    public Button vendrediB;

    @FXML
    public Button samediB;

    public void btnNotice(ActionEvent event) throws IOException {
        String link="https://docs.google.com/document/d/1wAKCeOhFooy5YvZfadcUOf9brX0WfxyiJ3Tqmo6D8II/edit";
       // HostServicesDelegate hostServices = HostServicesFactory.getInstance(new Ephemeride());
       // hostServices.showDocument(link);
    }

    public void btnDeconnexion(ActionEvent event) throws IOException {
        utilisateur = null;
        multiC.setActionEvent(event);
        multiC.connexion();
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

    public void btnLundi(ActionEvent event) throws IOException{
        lundi = !lundi;
        if(lundi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,1);
            lundiB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,1);
            lundiB.getStyleClass().removeAll("bouton2");
            lundiB.getStyleClass().add("bouton1");
        }
    }

    public void btnMardi(ActionEvent event) throws IOException{
        mardi = !mardi;
        if(mardi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,2);
            mardiB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,2);
            mardiB.getStyleClass().removeAll("bouton2");
            mardiB.getStyleClass().add("bouton1");
        }
    }

    public void btnMercredi(ActionEvent event) throws IOException{
        mercredi = !mercredi;
        if(mercredi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,3);
            mercrediB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,3);
            mercrediB.getStyleClass().removeAll("bouton2");
            mercrediB.getStyleClass().add("bouton1");
        }
    }

    public void btnJeudi(ActionEvent event) throws IOException{
        jeudi = !jeudi;
        if(jeudi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,4);
            jeudiB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,4);
            jeudiB.getStyleClass().removeAll("bouton2");
            jeudiB.getStyleClass().add("bouton1");
        }
    }

    public void btnVendredi(ActionEvent event) throws IOException{
        vendredi = !vendredi;
        if(vendredi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,5);
            vendrediB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,5);
            vendrediB.getStyleClass().removeAll("bouton2");
            vendrediB.getStyleClass().add("bouton1");
        }
    }

    public void btnSamedi(ActionEvent event) throws IOException{
        samedi = !samedi;
        if(samedi){
            new ConfigDayDAO("invite","patate","pts").changeActifDay(false,6);
            samediB.getStyleClass().add("bouton2");
        }
        else{
            new ConfigDayDAO("invite","patate","pts").changeActifDay(true,6);
            samediB.getStyleClass().removeAll("bouton2");
            samediB.getStyleClass().add("bouton1");
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
