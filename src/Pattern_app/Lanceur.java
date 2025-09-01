/* ----------------------------------------------------------------
* Lanceur.java
* 20250826
* @Zlatko
* ----------------------------------------------------------------- */
// ----------------------------------------------------------------
// paquet
package Pattern_app;
// ----------------------------------------------------------------
// modules importés
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.Duration;
// ----------------------------------------------------------------
// Classe principale
public class Lanceur extends Application {
    // ----------------------------------------------------------------
    // Attributs / variables
    // pour le splash
    public static final String SPLASH_IMAGE =
            "ressources/splashscreen.png";
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage splashStage;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;
    // pour la fenetre principale
    public static final String APPLICATION_ICON =
            "ressources/ico64.png";
    private StackPane principaleLayout;
    private Stage principaleStage;
    /* ----------------------------------------------------------------
    *
    * main
    *
    * ----------------------------------------------------------------- */
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    /* ----------------------------------------------------------------
    *
    * surcharge init, start et lance le thread splash
    *
    * ----------------------------------------------------------------- */
    // ----------------------------------------------------------------
    // Surcharge de la classe Application init()
    // Les composants
    @Override
    // prépare les formes
    public void init() {
        //initialise le splash avec ses composants et leur style
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH + 42);
        loadProgress.setPrefHeight(10);
        progressText = new Label("Vérification de l'intégrité du programme ... ");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        progressText.setStyle(
                "-fx-font-size: 10;" +
                "-fx-font-style: italic;" + 
                "-fx-text-fill: white;"
        );
        splashLayout.setStyle(
                "-fx-padding: 2; " +
                "-fx-background-color: black; " +
                "-fx-border-width:2; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "darkslategray, " +
                        "derive(darkslategray, 50%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());

        // initialise la fenetre principale avec ses composants et leur style
        principaleLayout = new StackPane();
        // Create a label
        Label label = new Label("Chargement de l'application");
        // Set a larger font size
        label.setFont(Font.font(24));
        // Add the label to the parent StackPane
        principaleLayout.getChildren().add(label);

        Pane pane = new Pane();
        // pane.setPrefSize(1024, 768);
        pane.setStyle("-fx-background-color: #1e1e1e;");
        principaleLayout.getChildren().add(pane);
        Button element1 = new Button("Click Me");
        element1.setLayoutX(62.57);
        element1.setLayoutY(59.19);
        element1.setPrefWidth(105.81);
        element1.setPrefHeight(29.00);
        element1.setDisable(false);
        element1.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #D9D9D9; -fx-border-color: #979797; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        // element1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
        // element1.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
        principaleLayout.getChildren().add(element1);

        principaleLayout.setStyle(
                "-fx-padding: 2; " +
                "-fx-background-color: black; " +
                "-fx-border-width:2; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "darkslategray, " +
                        "derive(darkslategray, 50%)" +
                    ");"
        );
    }
    // ----------------------------------------------------------------
    // Surcharge de la classe Application start()
    // Insérer ici la nature de la vérification
    @Override
    //lance le traitement de l'appli
    public void start(final Stage initStage) throws Exception {
        //construit un thread
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {

            @Override
            //construit le thread prévu pendant le splash
            protected ObservableList<String> call() throws InterruptedException {                
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(
                                "Fili", "Kili", "Oin", "Gloin", "Thorin",
                                "Dwalin", "Balin", "Bifur", "Bofur",
                                "Bombur", "Dori", "Nori", "Ori"
                        );
                updateMessage("Composant vérifié ... ");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("Composant vérifié ... " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("Tous les composants ont été vérifiés.");
                return foundFriends;
            }
        };

        // appelle l'affichage du splash et le lance
        showSplash(
                initStage,
                friendTask,
                () -> showResultverifStage(friendTask.valueProperty())
        );
        new Thread(friendTask).start();

        // appelle l'affichage de la fenètre principale
        showFenetrePrincipale();
    }
    /* ----------------------------------------------------------------
    *
    * Affiche Splashscreen
    *
    * ----------------------------------------------------------------- */
    // ----------------------------------------------------------------
    // les fonctions séparées
    // Affichage, travail et disparition du splash
    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }
    public interface InitCompletionHandler {
        void complete();
    }
    // ----------------------------------------------------------------
    // Affiche la fenètre du résultat thread
    private void showResultverifStage(
            ReadOnlyObjectProperty<ObservableList<String>> friends
    ) {
        splashStage = new Stage(StageStyle.DECORATED);
        splashStage.setTitle("Vérification des composants");
        splashStage.getIcons().add(new Image(
                APPLICATION_ICON
        ));

        final ListView<String> peopleView = new ListView<>();
        peopleView.itemsProperty().bind(friends);

        splashStage.setScene(new Scene((peopleView), 600, 400));
        splashStage.show();
    }
    /* ----------------------------------------------------------------
    *
    * Affiche Application principale
    *
    * ----------------------------------------------------------------- */
    // ----------------------------------------------------------------
    // Affiche la fenètre principale
    private void showFenetrePrincipale() {  
        // pour la fenètre principale, prépare le stage
        principaleStage = new Stage(StageStyle.DECORATED);
        principaleStage.setTitle("Pattern_app");
        principaleStage.getIcons().add(new Image(
                APPLICATION_ICON
        ));
        // affecte le stage à une scène
        Scene principaleScene = new Scene(principaleLayout, 1324, 768);
        principaleStage.setScene(principaleScene);
        principaleStage.centerOnScreen();
        principaleStage.show();
    }
}
