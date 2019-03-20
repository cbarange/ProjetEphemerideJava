package foreground.liste;

import foreground.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class CaseEvenementQuestionController extends Controller {
    @FXML
    public Label question;

    @FXML
    Rectangle btnEdit;

    @FXML
    SVGPath crayon;

    @FXML
    Rectangle btnDelete;

    @FXML
    SVGPath gomme;

    public void btnEditAnimEnter(){
        crayon.setFill(Paint.valueOf("#87DE9F"));
    }

    public void btnEditAnimExit(){
        crayon.setFill(Paint.valueOf("#FFFFFF"));
    }

    public void btnDeleteAnimEnter(){
        gomme.setFill(Paint.valueOf("#eb5757"));
    }

    public void btnDeleteAnimExit(){
        gomme.setFill(Paint.valueOf("#FFFFFF"));
    }
}
