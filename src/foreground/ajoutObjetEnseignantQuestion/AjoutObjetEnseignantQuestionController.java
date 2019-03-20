package foreground.ajoutObjetEnseignantQuestion;

import background.AnneeTC;
import background.QuestionOuverte;
import foreground.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutObjetEnseignantQuestionController extends Controller implements Initializable {
    private boolean ver1, ver2, ver3, ver4;

    @FXML
    TextField indiceChamp, champQu, champRep;
    @FXML
    CheckBox indiceCheck;
    @FXML
    Text indiceText;
    @FXML
    ChoiceBox<AnneeTC> typeAnnee;
    @FXML
    ChoiceBox<String> typeSelect;
    @FXML
    Pane paneType;

    @FXML
    Button btnTrue1, btnTrue2, btnTrue3, btnTrue4, btn8;


    @FXML
    public void btnTrueO1(){
        ver1 = !ver1;
        if (ver1)
            btnTrue1.setText("Vrai");
        else
            btnTrue1.setText("Faux");
    }

    public void btnTrueO2(){
        ver2 = !ver2;
        if (ver2)
            btnTrue2.setText("Vrai");
        else
            btnTrue2.setText("Faux");
    }

    public void btnTrueO3(){
        ver3 = !ver3;
        if (ver3)
            btnTrue3.setText("Vrai");
        else
            btnTrue3.setText("Faux");
    }

    public void btnTrueO4(){
        ver4 = !ver4;
        if (ver4)
            btnTrue4.setText("Vrai");
        else
            btnTrue4.setText("Faux");
    }

    @FXML
    public void btnTerminer(){
        if (typeSelect.getValue() == "Ouvert"){
            String indice = " ";
            if(indiceCheck.isSelected()){
                indice = indiceChamp.getText();
            }
            String contenu = " ";
            contenu = champQu.getText();
            String reponse = " ";
            reponse = champRep.getText();
            AnneeTC anneeTC = typeAnnee.getValue();
            QuestionOuverte uneQuestion = new QuestionOuverte(contenu,indice,reponse,anneeTC);
            uneQuestion.insertQuestionOuverte(anneeTC);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indiceChamp.visibleProperty().bind(indiceCheck.selectedProperty());
        indiceText.visibleProperty().bind(indiceCheck.selectedProperty());

        typeAnnee.getItems().addAll(AnneeTC.TC1, AnneeTC.TC2);
        typeAnnee.getSelectionModel().selectFirst();
        typeSelect.getItems().addAll("Ouvert", "QCM");
        typeSelect.getSelectionModel().selectFirst();

        typeSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("QCM"))
                paneType.setVisible(true);
            else
                paneType.setVisible(false);
        });
    }
}
