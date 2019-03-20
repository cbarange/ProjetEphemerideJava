package foreground.magasin;

import foreground.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MagasinController extends Controller {
    private boolean s1, s2, s3, s4;

    @FXML
    public Button style1;

    @FXML
    public Button style2;

    @FXML
    public Button style3;

    @FXML
    public Button style4;


    public void resetStyle(){
        utilisateur.setTheme(1);
    }

    public void btnStyle1(ActionEvent event) throws IOException {
        if(utilisateur.useTokens(25) || utilisateur.getStatus()){
            utilisateur.setTheme(2);
            s1 = !s1;
            if(s1){
                style1.setText("Obtenu");
                style2.setText("Acheter [50]");
                style3.setText("Acheter [75]");
                style4.setText("Acheter [100]");
            }
        }
    }

    public void btnStyle2(ActionEvent event) throws IOException {
        if(utilisateur.useTokens(50) || utilisateur.getStatus()){
            utilisateur.setTheme(3);
            s2 = !s2;
            if(s2){
                style1.setText("Acheter [25]");
                style2.setText("Obtenu");
                style3.setText("Acheter [75]");
                style4.setText("Acheter [100]");
            }
        }
    }

    public void btnStyle3(ActionEvent event) throws IOException {
        if(utilisateur.useTokens(75) || utilisateur.getStatus()) {
            utilisateur.setTheme(4);
            s3 = !s3;
            if (s3) {
                style1.setText("Acheter [25]");
                style2.setText("Acheter [50]");
                style3.setText("Obtenu");
                style4.setText("Acheter [100]");
            }
        }
    }

    public void btnStyle4(ActionEvent event) throws IOException {
        if(utilisateur.useTokens(100) || utilisateur.getStatus()) {
            utilisateur.setTheme(5);
            s4 = !s4;
            if (s4) {
                style1.setText("Acheter [25]");
                style2.setText("Acheter [50]");
                style3.setText("Acheter [75]");
                style4.setText("Obtenu");
            }
        }
    }
}
