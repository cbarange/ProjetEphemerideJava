package foreground.calendrier;

import application.Ephemeride;
import foreground.Controller;
import foreground.DateStructureur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendrierController extends Controller implements Initializable {

    private ArrayList<CaseJourController> csjCtrl;
    private DateStructureur ds;

    @FXML
    Label datePrincipal, dateSec1, dateSec2, dateSec3, dateSec4;
    @FXML
    GridPane gp;
    @FXML
    SVGPath backArrow, foreArrow;

    public void backTemp(){
        ds.setDecalMonth(-1);
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour() == null){
            ds.setDecal(1);
            return;
        }
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour().size() < 30){
            ds.setDecal(1);
            return;
        }
        setDateShow();
    }

    public void foreTemp(){
        ds.setDecalMonth(1);
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour() == null){
            ds.setDecal(-1);
            return;
        }
        if (Ephemeride.getListMois().get(ds.getMonth()).getListeJour().size() < 30){
            ds.setDecal(-1);
            return;
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        csjCtrl = new ArrayList<>();

        ds = new DateStructureur();
        ds.setfirst();
    }

    public void setCase(){
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                Pane pane = new Pane();
                gp.add(pane, j, i);

                Pane newLoadedPane = null;
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                try {
                    newLoadedPane = fxmlLoader2.load(getClass().getResource("CaseJour.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CaseJourController controller = fxmlLoader2.getController();
                controller.setDependance(primaryStage, multiC);
                pane.getChildren().add(newLoadedPane);

                csjCtrl.add(controller);
            }
        }
        setDateShow();
    }

    public void setDateShow(){
        datePrincipal.setText(ds.getActuelMonth());
        dateSec1.setText(ds.getDecalMonth(-2));
        dateSec2.setText(ds.getDecalMonth(-1));
        dateSec3.setText(ds.getDecalMonth(1));
        dateSec4.setText(ds.getDecalMonth(2));

        for (CaseJourController csj: csjCtrl) {
            csj.nbDay.setText("");
            //csj.svgEvent.setVisible(listMois.get(ds.getMonth()).getJour(ds.getDay()).getEvenement() != null);
        }

        for (int i = ds.getWeekDayNumber(), x=1; i < ds.getMonthDayNumber()+ds.getWeekDayNumber(); i++, x++){
            csjCtrl.get(i).nbDay.setText(String.valueOf(x));
            csjCtrl.get(i).day = x;
            csjCtrl.get(i).month = ds.getMonth();
        }
    }


}
