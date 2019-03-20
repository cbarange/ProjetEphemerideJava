package foreground.jourQuestion;

import application.Ephemeride;
import background.QuestionOuverte;
import background.QuestionQcm;
import background.ReponseQcm;
import foreground.Controller;
import foreground.DateStructureur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.*;

public class JourQuestionController extends Controller implements Initializable {
    private DateStructureur ds;

    @FXML
    Label datePrincipal;

    @FXML
    Label dateSec1;

    @FXML
    Label dateSec2;

    @FXML
    Label dateSec3;

    @FXML
    Label dateSec4;

    @FXML
    Label labelQuestion, labelRep;

    @FXML
    TextField champReponse,tfRep;

    @FXML
    CheckBox ch1,ch2,ch3,ch4;

    @FXML
    Button btnShow;

    @FXML
    SVGPath backArrow;

    @FXML
    SVGPath foreArrow;

    @FXML
    Pane paneDispo;

    @FXML
    Label labelIndice;

    @FXML
    Button btnIndice;

    public void btnShowIndice(){
        if(labelRep.getText().length()==0){
            if(utilisateur.useTokens(10)){
                labelIndice.setText(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion().getIndice());
                btnIndice.setOpacity(0);
            }
        }
    }

    public void backTemp(){
        ds.setDecal(-1);
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour() == null){
            ds.setDecal(1);
            return;
        }
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour().size() < 30){
            ds.setDecal(1);
            return;
        }
        if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionOuverte) {
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
        }
        else if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionQcm){
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
            List<ReponseQcm> listRep=new ArrayList(((QuestionQcm) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion()).getChoix());
            for(int i=0;i<listRep.size();i++){
                switch(i){
                    case 0:
                        ch1.setVisible(true);
                        ch1.setText(listRep.get(0).getReponse());
                        break;

                    case 1:
                        ch2.setVisible(true);
                        ch2.setText(listRep.get(1).getReponse());
                        break;
                    case 2:
                        ch3.setVisible(true);
                        ch3.setText(listRep.get(2).getReponse());
                        break;
                    case 3:
                        ch4.setVisible(true);
                        ch4.setText(listRep.get(3).getReponse());
                        break;
                }
            }
        }else{
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
        }
        paneDispo.setVisible(ds.getDate().after(new Date()));
        btnShow.setOpacity(1);
        labelRep.setText("");
        labelIndice.setOpacity(1);
        btnIndice.setOpacity(1);
        setDateShow();
    }

    public void foreTemp(){
        ds.setDecal(1);
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour() == null){
            ds.setDecal(-1);
            return;
        }
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour().size() < 30){
            ds.setDecal(-1);
            return;
        }
        if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionOuverte) {
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
        }
        else if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionQcm){
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
            List<ReponseQcm> listRep=new ArrayList(((QuestionQcm) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion()).getChoix());
            for(int i=0;i<listRep.size();i++){
                switch(i){
                    case 0:
                        ch1.setVisible(true);
                        ch1.setText(listRep.get(0).getReponse());
                        break;

                    case 1:
                        ch2.setVisible(true);
                        ch2.setText(listRep.get(1).getReponse());
                        break;
                    case 2:
                        ch3.setVisible(true);
                        ch3.setText(listRep.get(2).getReponse());
                        break;
                    case 3:
                        ch4.setVisible(true);
                        ch4.setText(listRep.get(3).getReponse());
                        break;
                }
            }




        }else{
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
        }
        paneDispo.setVisible(ds.getDate().after(new Date()));
        btnShow.setOpacity(1);
        labelRep.setText("");
        labelIndice.setOpacity(1);
        btnIndice.setOpacity(1);
        setDateShow();
    }

    public void backTempEnterAnim(){
        backArrow.setFill(Paint.valueOf("#56ccf2"));
    }

    public void backTempExitAnim(){
        backArrow.setFill(Paint.valueOf("#ffffff"));
    }

    public void foreTempEnterAnim(){
        foreArrow.setFill(Paint.valueOf("#56ccf2"));
    }

    public void foreTempExitAnim(){
        foreArrow.setFill(Paint.valueOf("#ffffff"));
    }

    public void btnShow()  {
        if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionOuverte) {
            btnShow.setOpacity(0);
            QuestionOuverte questionOuverte = (QuestionOuverte) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion();
            labelRep.setOpacity(1);
            labelRep.setText(questionOuverte.getReponse().getContenu());
            labelIndice.setOpacity(0);
            btnIndice.setOpacity(0);
        }
        else if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionQcm){
            btnShow.setOpacity(0);
            if((((QuestionQcm) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion()).getReponse()==chSelected()))
                utilisateur.earnTokens(1000);
            QuestionQcm questionQcm = (QuestionQcm) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion();
            labelRep.setText(questionQcm.parseIt());
        }
    }

    private String chSelected() {
        if(ch1.isSelected())
            return ch1.getText();
        if(ch2.isArmed())
            return ch1.getText();
        if(ch3.isArmed())
            return ch1.getText();
        if(ch4.isArmed())
            return ch1.getText();
        return "error";
    }

    public void keyPess(KeyEvent event){
        if (event.getCode() == KeyCode.LEFT){
            backTemp();
        }
        else if (event.getCode() == KeyCode.RIGHT){
            foreTemp();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ds = new DateStructureur();
        if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionOuverte) {
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
        }
        else if(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion() instanceof QuestionQcm){
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
            List<ReponseQcm> listRep=new ArrayList(((QuestionQcm) Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion()).getChoix());
            for(int i=0;i<listRep.size();i++){
                switch(i){
                    case 0:
                        ch1.setVisible(true);
                        ch1.setText(listRep.get(0).getReponse());
                        break;

                    case 1:
                        ch2.setVisible(true);
                        ch2.setText(listRep.get(1).getReponse());
                        break;
                    case 2:
                        ch3.setVisible(true);
                        ch3.setText(listRep.get(2).getReponse());
                        break;
                    case 3:
                        ch4.setVisible(true);
                        ch4.setText(listRep.get(3).getReponse());
                        break;
                }
            }
        }else{
            ch1.setVisible(false);
            ch2.setVisible(false);
            ch3.setVisible(false);
            ch4.setVisible(false);
            tfRep.setVisible(false);
        }
    }

    public void setDateShow(){
        datePrincipal.setText(ds.getActuelDay());
        dateSec1.setText(ds.getDecalDay(-2));
        dateSec2.setText(ds.getDecalDay(-1));
        dateSec3.setText(ds.getDecalDay(1));
        dateSec4.setText(ds.getDecalDay(2));

        paneDispo.setVisible(ds.getDate().after(new Date()));

        labelQuestion.setText(Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion().getContenu());
        if(!Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion().getIndice().equals("") && !Ephemeride.getListMois().get(ds.getMonth()).getJour(ds.getDay()).getQuestion().getIndice().equals("Changez de jour pour avoir une question")){
            labelIndice.setText("Un Indice est disponible contre 10 Points");
        }else{
            labelIndice.setText("Il n'y  pas d'indice pour cette question");
        }
        //labelQuestion.setText(listMois.get(2).getJour(2).getQuestion().getContenu());
       //labelQuestion.setText(listMois().get(5).getJour(20).getQuestion().getContenu()+"DEBUG JOURQUESTIONCONTROLER");
    }


    public void setDate(int day, int month, int year){
        ds.setDate(day, month, year);
    }
}
