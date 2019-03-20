package foreground.calendrier;

import foreground.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.io.IOException;

public class CaseJourController extends Controller{
    public int day, month, year;

    @FXML
    public Label nbDay;

    @FXML
    SVGPath svgEvent;

    @FXML
    Circle circlePasti;

    public void btnClick(MouseEvent event) throws IOException {
        multiC.setActionEvent(event);
        multiC.jourQuestionWithDate(day, month, year);
    }
}
