package foreground.popupMdp;

import foreground.Controller;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**Class PopupMdpController, extends Controller et implements Initializable, défini les controlle de PopupMdp.fxml
 * <p>
 *
 * @author Alexandre Maul <>
 * @version 2.0
 * @since 2.0
 */
public class PopupMdpController extends Controller implements Initializable {

    /**
     * Variable BooleanBinding.
     */
    private BooleanBinding bpCM;

    /**
     * Variable BooleanBinding.
     */
    private BooleanBinding bpAM;

    /**
     * Blocs TextField.
     */
    @FXML
    TextField fAM, fNM, fCM;

    /**
     * Blocs PasswordField.
     */
    @FXML
    PasswordField pAM, pNM, pCM;

    /**
     * Blocs CheckBox.
     */
    @FXML
    CheckBox cAM, cNM, cCM;

    /**
     * Blocs Text.
     */
    @FXML
    Text tAM, tCM;

    /**
     * Blocs Rectangle.
     */
    @FXML
    Rectangle layout, btnClose;

    /**
     * Blocs crossClose.
     */
    @FXML
    SVGPath crossClose;

    /**Méthode fermant une requête
     * <p>
     *
     */
    public void closeRequest(){
        primaryStage.close();
    }

    /**Méthode donnant une animationa au bouton fermer.
     * <p>
     *
     */
    public void btnCloseAnimEnter(){
        btnClose.setOpacity(1);
        crossClose.setFill(Paint.valueOf("#393B47"));
    }

    /**Méthode donnant une animationa au bouton fermer.
     * <p>
     *
     */
    public void btnCloseAnimExit(){
        btnClose.setOpacity(0);
        crossClose.setFill(Paint.valueOf("#eb5757"));
    }

    /**Méthode validant la requête de changement de mdp.
     * <p>
     *
     */
    public void valideRequest(){
        if(fNM.getText().equals(fCM.getText())){
            if(utilisateur.changePassWord(fNM.getText(),fAM.getText()))
                closeRequest();
        }
    }

    /**Méthode initialisant la popup.
     * <p>
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMoveWindows(layout);
    }

    /**Méthode setCase affichant la popup.
     * <p>
     *
     */
    public void setCase(){
        fAM.textProperty().bindBidirectional(pAM.textProperty());
        fNM.textProperty().bindBidirectional(pNM.textProperty());
        fCM.textProperty().bindBidirectional(pCM.textProperty());

        pAM.visibleProperty().bind(cAM.selectedProperty().not());
        pNM.visibleProperty().bind(cNM.selectedProperty().not());
        pCM.visibleProperty().bind(cCM.selectedProperty().not());

        BooleanProperty bp = new SimpleBooleanProperty();
        bp.set(fNM != fCM);

        bpCM = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                return fNM != fCM;
            }
        };

        bpAM = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                return !fAM.getText().equals(utilisateur.motDePasse);
            }
        };

        tCM.visibleProperty().bind(bp);
        tAM.visibleProperty().bind(bpAM);
    }


}
