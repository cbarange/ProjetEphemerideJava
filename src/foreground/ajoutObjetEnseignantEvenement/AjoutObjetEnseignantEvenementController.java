package foreground.ajoutObjetEnseignantEvenement;

import background.AnneeTC;
import background.TypeEvent;
import foreground.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutObjetEnseignantEvenementController extends Controller implements Initializable {
    @FXML
    ChoiceBox<AnneeTC> typeAnnee;
    @FXML
    ChoiceBox<TypeEvent> typeSelect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        typeAnnee.getItems().addAll(AnneeTC.TC1, AnneeTC.TC2);
        typeAnnee.getSelectionModel().selectFirst();
        typeSelect.getItems().addAll(TypeEvent.Conference, TypeEvent.Cours, TypeEvent.Loisir);
        typeSelect.getSelectionModel().selectFirst();
    }
}
