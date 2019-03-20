package foreground.connexion;

import foreground.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnexionController extends Controller implements Initializable {

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
    CheckBox checkPassword;


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

    public void btnRegister(ActionEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.inscription();
    }

    public void btnConnexion(Event event) throws IOException{
        if (champsLogin.getText().equals("") || champsPassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Vous n'avez pas rempli tous les champs !");
            alert.setContentText("Merci de renseigner votre login et votre mot de passe afin de pouvoir vous connecter");
            alert.showAndWait();
            return;
        }
        else{
            if (!utilisateur.connexion(champsLogin.getText(), champsPassword.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention !");
                alert.setHeaderText("Identifiant ou Mot De Passe incorrecte");
                alert.setContentText("Merci de renseigner votre login et votre mot de passe afin de pouvoir vous connecter");

                alert.showAndWait();
                return;
            }

            multiC.setActionEvent(event);
            multiC.startAccueil();
        }
    }

    public void enterPress(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            try {
                btnConnexion(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMoveWindows(layout);
        afficherPassword.visibleProperty().bind(checkPassword.selectedProperty());

        champsPassword.visibleProperty().bind(checkPassword.selectedProperty().not());

        afficherPassword.textProperty().bindBidirectional(champsPassword.textProperty());

    }

}
