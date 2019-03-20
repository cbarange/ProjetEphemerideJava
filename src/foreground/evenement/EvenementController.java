package foreground.evenement;

import foreground.Controller;
import foreground.evenement.CaseEvenementController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 *  class EvenementController, permettant d'afficher la page evenement.
 *
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.4
 *  @since       1.4
 */
public class EvenementController extends Controller implements Initializable {
    private ArrayList<CaseEvenementController> ecCtrl;

    /**
     * Variable FXML GridPane, gpq, du fichier fxml evenement.
     */
    @FXML
    public GridPane gpq;

    /**Méthode d'initialisation
     * <p>
     *
     * @param location (requis),
     * @param resources (requis), donne le mois.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**
         * Variable ecCtrl, ArrayList.
         */
        ecCtrl = new ArrayList<>();

    }

    /**Méthode affichant les évènements
     * <p>
     *
     */
    public void setCase(){
        for (int i = 0; i < 2; i++) {
            Pane pane = new Pane();
            gpq.add(pane, 0, i);

            Pane newLoadedPane = null;
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            try {
                newLoadedPane = fxmlLoader2.load(getClass().getResource("CaseEvenement.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CaseEvenementController controller = fxmlLoader2.getController();
            controller.setDependance(primaryStage, multiC);
            pane.getChildren().add(newLoadedPane);
            ecCtrl.add(controller);
        }
    }
}