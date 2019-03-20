package foreground.evenement;

        import foreground.Controller;
        import javafx.fxml.FXML;
        import javafx.scene.control.Label;
        import javafx.scene.paint.Paint;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.shape.SVGPath;

/**
 *  Class enum de l'année dans laquel ce trouve les étudiants.
 *
 *
 *  @author      Fournier Alexandre <alexandre.fournier@etudiant.univ-lr.fr>
 *  @version     1.4
 *  @since       1.4
 */
public class CaseEvenementController extends Controller {

    /**
     * Variable Label date evenement donnant la date d'un évènement pour le fichier fxml CaseEvenement
     */
    @FXML
    public Label dateEvenement;

    /**
     * Variable Label detailEvenement donnant le détails d'un évènement pour le fichier fxml CaseEvenement
     */
    @FXML
    public Label detailEvenement;

    /**
     * Variable SVGPath, symbol, donnant le type d'évènement pas une couleur sur un symbole de cloche d'un évènement pour le fichier fxml CaseEvenement
     */
    @FXML
    SVGPath symbol;

    /** Dessine le symbol de cloche en rouge par défaut.  */
    public void cloche(){
        symbol.setFill(Paint.valueOf("#e87575"));
    }
}
