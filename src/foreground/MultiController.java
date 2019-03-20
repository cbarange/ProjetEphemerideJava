package foreground;

import background.Installeur;
import background.Utilisateur;
import foreground.ajoutObjetEnseignantEvenement.AjoutObjetEnseignantEvenementController;
import foreground.ajoutObjetEnseignantQuestion.AjoutObjetEnseignantQuestionController;
import foreground.calendrier.CalendrierController;
import foreground.connexion.ConnexionController;
import foreground.dimanche.DimancheController;
import foreground.evenement.EvenementController;
import foreground.inscription.InscriptionController;
import foreground.jourQuestion.JourQuestionController;
import foreground.lanceur.LanceurController;
import foreground.liste.listeEvenement.ListeEvenementController;
import foreground.liste.listeQuestion.ListeQuestionController;
import foreground.magasin.MagasinController;
import foreground.parametre.ParametreController;
import foreground.parametreEnseignant.ParametreEnseignantController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MultiController {
    private Event event;
    private Utilisateur utilisateur;

    public void appelDuBackgroundSpe(Parent root){
        root.getStylesheets().add(getClass().getResource("Foreground.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("Background.css").toExternalForm());
    }

    public void appelDuBackground(Parent root){
        root.getStylesheets().add(getClass().getResource("Foreground.css").toExternalForm());
        if(utilisateur.theme==2){
            root.getStylesheets().add(getClass().getResource("Background2.css").toExternalForm());
        }
        else if(utilisateur.theme==3){
            root.getStylesheets().add(getClass().getResource("Background3.css").toExternalForm());
        }
        else if(utilisateur.theme==4){
            root.getStylesheets().add(getClass().getResource("Background4.css").toExternalForm());
        }
        else if(utilisateur.theme==5){
            root.getStylesheets().add(getClass().getResource("Background5.css").toExternalForm());
        }
        else{
            root.getStylesheets().add(getClass().getResource("Background.css").toExternalForm());
        }
    }

    /**
     * Constructeur pour permettre un lancement de la partie IHM
     * @throws IOException
     * @param utilisateur
     */
    public MultiController(Utilisateur utilisateur) throws IOException {
        this.utilisateur = utilisateur;
        if (!Installeur.isExisteStatic())
            lanceur();
        else if (utilisateur.getConnectStatus() == 1)
            if (!utilisateur.connexion(utilisateur.pseudo, utilisateur.motDePasse)){
                connexion();
            }
            else
                startAccueil();

        else
            connexion();
    }

    /**
     * Fonction de selection de l'IHM a lancer apres une connexion de l'utilisateur
     * @throws IOException
     */
    public void startAccueil() throws IOException {
        Calendar calendar = new GregorianCalendar();
        if (utilisateur.getMail() == null)
            return;

        if (utilisateur.getStatus()){
            calendrier();
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            dimanche();
        }
        else {
            jourQuestion();
        }
    }

    /**
     * Fonction de selection de l'IHM a lancer apres une connexion de l'utilisateur
     * @param event
     */
    public void setActionEvent(ActionEvent event){
        this.event = event;
    }

    /**
     * Fonction de selection de l'IHM a lancer apres une connexion de l'utilisateur
     * @param event
     */
    public void setActionEvent(MouseEvent event){
        this.event = event;
    }

    /**
     * Fonction de selection de l'IHM a lancer apres une connexion de l'utilisateur
     * @param event
     */
    public void setActionEvent(Event event){
        this.event = event;
    }

    /**
     * Lancer l'IHM lanceur
     * @throws IOException
     */
    public void lanceur() throws IOException {
        Stage primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("lanceur/Lanceur.fxml").openStream());
        LanceurController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);

        primaryStage.setScene(new Scene(root));
        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }
        primaryStage.show();
    }

    /**
     * Lancer l'IHM inscription
     * @throws IOException
     */
    public void inscription() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("inscription/Inscription.fxml").openStream());
        InscriptionController controller = fxmlLoader.getController();
        appelDuBackgroundSpe(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM connexion
     * @throws IOException
     */
    public void connexion() throws IOException {
        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("connexion/Connexion.fxml").openStream());
        ConnexionController controller = fxmlLoader.getController();
        appelDuBackgroundSpe(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);

        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM jourQuestion
     * @throws IOException
     */
    public void jourQuestion() throws IOException {
        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("jourQuestion/JourQuestion.fxml").openStream());
        JourQuestionController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        System.out.println(controller.toString());
        controller.setPane();
        controller.setDateShow();
        controller.deminiDebug();

        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM jourQuestion avec une date precise
     * @throws IOException
     * @param day
     * @param month
     * @param year
     */
    public void jourQuestionWithDate(int day, int month, int year) throws IOException {
        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("jourQuestion/JourQuestion.fxml").openStream());
        JourQuestionController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane();
        controller.setDate(day, month, year);
        controller.setDateShow();
        controller.deminiDebug();

        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM dimanche
     * @throws IOException
     */
    public void dimanche() throws IOException {
        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("dimanche/Dimanche.fxml").openStream());
        DimancheController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane();
        controller.deminiDebug();

        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM calendrier
     * @throws IOException
     */
    public void calendrier() throws IOException {
        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("calendrier/Calendrier.fxml").openStream());
        CalendrierController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setCase();
        controller.setPane();
        controller.deminiDebug();

        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM parametre
     * @throws IOException
     */
    public void parametre() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("parametre/Parametre.fxml").openStream());
        ParametreController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.getUserConnectStatus();
        controller.getUserRunStatus();
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM parametreEnseignant
     * @throws IOException
     */
    public void parametreEnseignant() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("parametreEnseignant/ParametreEnseignant.fxml").openStream());
        ParametreEnseignantController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.getUserRunStatus();
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM ajoutObjetEnseignantQuestion
     * @throws IOException
     */
    public void ajoutObjetEnseignantQuestion() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("ajoutObjetEnseignantQuestion/AjoutObjetEnseignantQuestion.fxml").openStream());
        AjoutObjetEnseignantQuestionController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM ajoutObjetEnseignantEvenement
     * @throws IOException
     */
    public void ajoutObjetEnseignantEvenement() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("ajoutObjetEnseignantEvenement/AjoutObjetEnseignantEvenement.fxml").openStream());
        AjoutObjetEnseignantEvenementController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM listeEvenement
     * @throws IOException
     */
    public void listeEvenement() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("liste/listeEvenement/ListeEvenement.fxml").openStream());
        ListeEvenementController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane("../../menu/Barre.fxml", "../../menu/BarreOpen.fxml");
        controller.deminiDebug("../../menu/Barre.fxml", "../../menu/BarreOpen.fxml");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM listeQuestion
     * @throws IOException
     */
    public void listeQuestion() throws IOException {
        //Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage primaryStage = new Stage();
        if (event != null){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("liste/listeQuestion/ListeQuestion.fxml").openStream());
        ListeQuestionController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane("../../menu/Barre.fxml", "../../menu/BarreOpen.fxml");
        controller.deminiDebug("../../menu/Barre.fxml", "../../menu/BarreOpen.fxml");

        primaryStage.setScene(new Scene(root));
        if (primaryStage.getStyle() != StageStyle.UNDECORATED){
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }
        primaryStage.show();
    }

    /**
     * Lancer l'IHM evenement
     * @throws IOException
     */
    public void evenement() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("evenement/Evenement.fxml").openStream());
        EvenementController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setCase();
        controller.setUser(utilisateur);
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Lancer l'IHM magasin
     * @throws IOException
     */
    public void magasin() throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("magasin/Magasin.fxml").openStream());
        MagasinController controller = fxmlLoader.getController();
        appelDuBackground(root);
        controller.setDependance(primaryStage, this);
        controller.setUser(utilisateur);
        controller.setPane();
        controller.deminiDebug();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
