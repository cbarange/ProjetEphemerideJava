package foreground.inscription;

import foreground.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InscriptionController extends Controller implements Initializable {

    @FXML
    Rectangle layout;

    @FXML
    Rectangle btnClose;

    @FXML
    SVGPath crossClose;

    @FXML
    TextField champsLogin;

    @FXML
    TextField afficherPassword;

    @FXML
    PasswordField champsPassword;

    @FXML
    TextField afficherPasswordConf;

    @FXML
    PasswordField champsPasswordConf;

    @FXML
    CheckBox checkPassword;

    @FXML
    CheckBox checkPasswordConf;

    @FXML
    TextField champsEmail;

    @FXML
    TextField champsEmailConf;

    @FXML
    TextField clefAdmin;

    @FXML
    CheckBox checkAdmin;

    @FXML
    Label labelLogin, labelMdp, labelEmail, labelChamp;

    //Fonction pour la croix de fermeture
    public void closeRequest(){
        Platform.exit();
    }

    public void btnCloseAnimEnter(){
        btnClose.setOpacity(1);
        crossClose.setFill(Paint.valueOf("#393B47"));
    }

    public void btnCloseAnimExit(){
        btnClose.setOpacity(0);
        crossClose.setFill(Paint.valueOf("#eb5757"));
    }

    public void btnRegister(ActionEvent event) throws IOException{
        boolean checkChamp = champsPassword.getText().equals(champsPasswordConf.getText()) && champsEmail.getText().equals(champsEmailConf.getText());
        boolean checkMail = ((champsEmail.getText()).matches("^(\\w{1,40}\\.\\w{1,40}@univ-lr\\.fr)$") | (champsEmail.getText()).matches("^(\\w{1,40}\\.\\w{1,40}@etudiant\\.univ-lr\\.fr)$"));
        if(champsLogin.getText().equals("") || champsPassword.getText().equals("") || champsPasswordConf.getText().equals("") || champsEmail.getText().equals("") || champsEmailConf.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Vous n'avez pas rempli tous les champs !");
            alert.setContentText("Merci de renseigner toute les informations demandés afin de vous enregistrer correctement dans notre base de donnée.");

            alert.showAndWait();

        }
        else if (checkChamp && checkMail){
            if(!checkAdmin.isSelected()){
                if(!utilisateur.register(champsLogin.getText(),champsPassword.getText(),champsEmail.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention !");
                    alert.setHeaderText("Cette utilisateur exsite deja");
                    alert.setContentText("Merci de renseigner votre login et votre mot de passe afin de pouvoir vous connecter");

                    alert.showAndWait();
                    return;// utilisateur existe deja
                }

            }else{
                if(!utilisateur.register(champsLogin.getText(),champsPassword.getText(),champsEmail.getText(),clefAdmin.getText()))
                    return;// utilisateur existe deja
            }
            multiC.setActionEvent(event);
            multiC.connexion();
        }
    }

    public void btnConnexion(ActionEvent event) throws IOException{
        multiC.setActionEvent(event);
        multiC.connexion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMoveWindows(layout);
        afficherPassword.visibleProperty().bind(checkPassword.selectedProperty());
        afficherPasswordConf.visibleProperty().bind(checkPasswordConf.selectedProperty());

        champsPassword.visibleProperty().bind(checkPassword.selectedProperty().not());
        champsPasswordConf.visibleProperty().bind(checkPasswordConf.selectedProperty().not());

        afficherPassword.textProperty().bindBidirectional(champsPassword.textProperty());
        afficherPasswordConf.textProperty().bindBidirectional(champsPasswordConf.textProperty());

        clefAdmin.editableProperty().bind(checkAdmin.selectedProperty());

        champsPassword.textProperty().addListener((observable, oldValue, newValue) -> labelMdp.setVisible(!newValue.equals(champsPasswordConf.getText())));

        champsPasswordConf.textProperty().addListener((observable, oldValue, newValue) -> labelMdp.setVisible(!newValue.equals(champsPassword.getText())));

        champsEmail.textProperty().addListener((observable, oldValue, newValue) -> labelEmail.setVisible(!newValue.equals(champsEmailConf.getText())));

        champsEmailConf.textProperty().addListener((observable, oldValue, newValue) -> labelEmail.setVisible(!newValue.equals(champsEmail.getText())));

    }
}
