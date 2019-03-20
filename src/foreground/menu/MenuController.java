package foreground.menu;

import foreground.Controller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {
    private Controller ctrl;

    @FXML
    Rectangle layout;

    @FXML
    Rectangle btnClose;

    @FXML
    Rectangle btnMini;

    @FXML
    SVGPath crossClose;

    @FXML
    SVGPath mini;

    @FXML
    Label labelCredit;

    @FXML
    Label labelHeure;

    @FXML
    Rectangle rectAccueil;

    @FXML
    Rectangle rectCalendrier;

    @FXML
    Rectangle rectEvenement;

    @FXML
    Rectangle rectOption;

    @FXML
    Rectangle rectMagasin;

    @FXML
    Rectangle rectDeco;

    @FXML
    Rectangle rectGQ;

    @FXML
    Pane paneAdmin;

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

    public void miniRequest(){
        primaryStage.setIconified(true);
    }

    public void btnMiniAnimEnter(){
        btnMini.setOpacity(1);
        mini.setFill(Paint.valueOf("#393B47"));
    }

    public void btnMiniAnimExit(){
        btnMini.setOpacity(0);
        mini.setFill(Paint.valueOf("#87de9f"));
    }

    public void menuOpenEnter(){
        ctrl.revertMenu();
    }

    public void btnAccueilEnterAnim(){
        rectAccueil.setOpacity(0.3);
    }

    public void btnAccueilExitAnim(){
        rectAccueil.setOpacity(0);
    }

    public void btnCalendrierEnterAnim(){
        rectCalendrier.setOpacity(0.3);
    }

    public void btnCalendrierExitAnim(){
        rectCalendrier.setOpacity(0);
    }

    public void btnEvenementEnterAnim(){
        rectEvenement.setOpacity(0.3);
    }

    public void btnEvenementExitAnim(){
        rectEvenement.setOpacity(0);
    }

    public void btnOptionEnterAnim(){
        rectOption.setOpacity(0.3);
    }

    public void btnOptionExitAnim(){
        rectOption.setOpacity(0);
    }

    public void btnMagasinEnterAnim(){
        rectMagasin.setOpacity(0.3);
    }

    public void btnMagasinExitAnim(){
        rectMagasin.setOpacity(0);
    }

    public void btnDecoEnterAnim(){
        rectDeco.setOpacity(0.3);
    }

    public void btnDecoExitAnim(){
        rectDeco.setOpacity(0);
    }

    public void btnGQEnterAnim(){
        rectGQ.setOpacity(0.3);
    }

    public void btnGQExitAnim(){
        rectGQ.setOpacity(0);
    }

    public void btnAccueil(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.startAccueil();
    }

    public void btnCalendrier(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.calendrier();
    }

    public void btnEvent(MouseEvent event) throws IOException{
        multiC.setActionEvent(event);
        if (utilisateur.getStatus())
            multiC.listeEvenement();
        else
            multiC.evenement();
    }

    public void btnParam(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        if (utilisateur.getStatus())
            multiC.parametreEnseignant();
        else
            multiC.parametre();
    }

    public void btnMag(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.magasin();
    }

    public void btnDeco(MouseEvent event) throws IOException {
        utilisateur = null;
        multiC.setActionEvent(event);
        multiC.connexion();
    }

    public void btnGestionQ(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.listeQuestion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMoveWindows(layout);

        Task dynamicTimeTask = new Task<Void>() {
            @Override
            protected Void call() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                while (true) {
                    updateMessage(sdf.format(new Date()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
        labelHeure.textProperty().bind(dynamicTimeTask.messageProperty());
        Thread t2 = new Thread(dynamicTimeTask);
        t2.setName("Affichage de l'heure");
        t2.setDaemon(true);
        t2.start();

        }

        public void setCtrl(Controller ctrl){
        this.ctrl = ctrl;
        labelCredit.setText(String.valueOf(utilisateur.nbPoint));
        if (paneAdmin != null){
            paneAdmin.setVisible(utilisateur.getStatus());
        }
    }

}
