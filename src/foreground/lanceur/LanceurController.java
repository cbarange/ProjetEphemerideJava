package foreground.lanceur;

import background.Installeur;
import foreground.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LanceurController extends Controller implements Initializable {

    Installeur installeur;
    @FXML
    Rectangle layout;

    @FXML
    Rectangle btnClose;

    @FXML
    SVGPath crossClose;

    public void closeRequest(){
        Platform.exit();
    }

    public void btnInstall(ActionEvent event) throws IOException {
    	installeur.install();
        multiC.setActionEvent(event);
        multiC.connexion();
    }

    public void btnCloseAnimEnter(){
        btnClose.setOpacity(1);
        crossClose.setFill(Paint.valueOf("#393B47"));
    }

    public void btnCloseAnimExit(){
        btnClose.setOpacity(0);
        crossClose.setFill(Paint.valueOf("#eb5757"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMoveWindows(layout);
        installeur=new Installeur();
    }
}