package foreground.liste.listeEvenement;

import background.Evenement;
import foreground.Controller;
import foreground.liste.CaseEvenementQuestionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeEvenementController extends Controller implements Initializable {
    private ArrayList<CaseEvenementQuestionController> lecCtrl;
    private ArrayList<Evenement> listEvent=new ArrayList<>();
    @FXML
    public GridPane gpe;

    public void btnAjouterEvenement(ActionEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.ajoutObjetEnseignantEvenement();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lecCtrl = new ArrayList<>();
        /*
        for(Jour j : Ephemeride.getListMois().get(5).getListeJour()){
            if(j.getEvenement().getIntitule()!=null){
                listEvent.add(j.getEvenement());
            }
        }
        */





        for(int i = 0; i < 1; i++){
            Pane pane = new Pane();
            gpe.add(pane, 0, i);

            Pane newLoadedPane = null;
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            try {
                newLoadedPane = fxmlLoader2.load(getClass().getResource("../CaseEvenementQuestion.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CaseEvenementQuestionController controller = fxmlLoader2.getController();
            controller.setDependance(primaryStage, multiC);
            pane.getChildren().add(newLoadedPane);
            lecCtrl.add(controller);
        }
    }
}
