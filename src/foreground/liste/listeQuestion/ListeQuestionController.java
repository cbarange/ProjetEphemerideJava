package foreground.liste.listeQuestion;

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

public class ListeQuestionController extends Controller implements Initializable {
    private ArrayList<CaseEvenementQuestionController> lqcCtrl;

    @FXML
    public GridPane gpq;

    public void btnAjouterQuestion(ActionEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.ajoutObjetEnseignantQuestion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lqcCtrl = new ArrayList<>();

        for(int i = 0; i < 1; i++){
            Pane pane = new Pane();
            gpq.add(pane, 0, i);

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
            lqcCtrl.add(controller);
        }
    }
}
