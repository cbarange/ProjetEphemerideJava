package foreground;

import background.Mois;
import background.Utilisateur;
import foreground.menu.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @author Alexandre Maul
 * @version 2.1
 * @since 1.2
 */
public class Controller {
    protected Stage primaryStage;
    protected MultiController multiC;
    protected Utilisateur utilisateur;
    protected List<Mois> listMois;
    private double x, y;

    @FXML
    Pane barreOpenPane;

    @FXML
    Pane barrePane;

    /**
     * Permet d'ajouter une utilisateur
     * @param utilisateur
     */
    public void setUser(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }

    /**
     * Permet d'ajouter un Stage et un Multicontroller
     * @param primaryStage
     * @param multiC
     */
    public void setDependance(Stage primaryStage, MultiController multiC) {
        this.multiC = multiC;
        this.primaryStage = primaryStage;
    }

    /**
     * Permet de gerer le mouvement de la fenetre lors du clique
     * @param layout
     */
    protected void setMoveWindows(Rectangle layout){
        WindowsBox window = new WindowsBox();

        layout.setOnMousePressed(mouseEvent -> {
            x = primaryStage.getX() - mouseEvent.getScreenX();
            y = primaryStage.getY() - mouseEvent.getScreenY();
        });
        layout.setOnMouseDragged(mouseEvent -> {
            double readyX = mouseEvent.getScreenX() + x;
            double readyY = mouseEvent.getScreenY() + y;

            if (window.yValue(readyY)){
                primaryStage.setX(readyX);
                primaryStage.setY(readyY);
            }
        });
    }

    /**
     * Permet de mettre en place les menus
     */
    public void setPane(){
        Pane newLoadedPane1 = null;
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        try {
            //newLoadedPane1 = fxmlLoader1.load(getClass().getResource("..//menu//Barre.fxml").openStream());
            newLoadedPane1 = fxmlLoader1.load(getClass().getResource("../menu/Barre.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController controller1 = fxmlLoader1.getController();
        controller1.setDependance(primaryStage, multiC);
        controller1.setUser(utilisateur);
        controller1.setCtrl(this);
        barrePane.getChildren().add(newLoadedPane1);

        Pane newLoadedPane2 = null;
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        try {
            newLoadedPane2 = fxmlLoader2.load(getClass().getResource("../menu/BarreOpen.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController controller2 = fxmlLoader2.getController();
        controller2.setDependance(primaryStage, multiC);
        controller2.setUser(utilisateur);
        controller2.setCtrl(this);
        barreOpenPane.getChildren().add(newLoadedPane2);
    }

    /**
     * Permet de mettre en place les menus avec une redirection des chemins
     * @param path1
     * @param path2
     */
    public void setPane(String path1, String path2){
        Pane newLoadedPane1 = null;
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        try {
            newLoadedPane1 = fxmlLoader1.load(getClass().getResource(path1).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController controller1 = fxmlLoader1.getController();
        controller1.setDependance(primaryStage, multiC);
        controller1.setUser(utilisateur);
        controller1.setCtrl(this);
        barrePane.getChildren().add(newLoadedPane1);

        Pane newLoadedPane2 = null;
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        try {
            newLoadedPane2 = fxmlLoader2.load(getClass().getResource(path2).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController controller2 = fxmlLoader2.getController();
        controller2.setDependance(primaryStage, multiC);
        controller2.setUser(utilisateur);
        controller2.setCtrl(this);
        barreOpenPane.getChildren().add(newLoadedPane2);
    }

    /**
     * Permet d'actualiser la page a la sortie de la minimisation
     * previens d'un bug
     */
    public void deminiDebug(){
        primaryStage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                barrePane.getChildren().remove(0);
                barreOpenPane.getChildren().remove(0);
                setPane();
            }
        });
    }

    /**
     * Permet d'actualiser la page a la sortie de la minimisation
     * previens d'un bug
     * avec une redirection des chemins
     * @param path1
     * @param path2
     */
    public void deminiDebug(String path1, String path2){
        primaryStage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                barrePane.getChildren().remove(0);
                barreOpenPane.getChildren().remove(0);
                setPane(path1, path2);
            }
        });
    }

    /**
     * Inverse la visibilite des menus
     */
    public void revertMenu(){
        barreOpenPane.setVisible(!barreOpenPane.isVisible());
    }
}
