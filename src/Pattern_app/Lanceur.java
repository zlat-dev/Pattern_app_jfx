/* ----------------------------------------------------------------
Lanceur.java
20250826
@Zlatko
------------------------------------------------------------------- */
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
import javafx.stage.*;
import javafx.util.Duration;

// ----------------------------------------------------------------
// Classe principale
public class Lanceur extends Application {
    // ----------------------------------------------------------------
    // Attributs
    public static final String APPLICATION_ICON =
            "ressources/ico64.png";
    public static final String SPLASH_IMAGE =
            "ressources/splashscreen.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;

    /* ----------------------------------------------------------------
    
    main

    ------------------------------------------------------------------- */
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    /* ----------------------------------------------------------------
    
    Splashscreen

    ------------------------------------------------------------------- */

    // ----------------------------------------------------------------
    // Surcharge de la classe Application init()
    // Les composants
    @Override
    public void init() {
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
    }

    // ----------------------------------------------------------------
    // Surcharge de la classe Application start()
    // Insérer ici la nature de la vérification
    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
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

        showSplash(
                initStage,
                friendTask,
                () -> showMainStage(friendTask.valueProperty())
        );
        new Thread(friendTask).start();
    }

    // ----------------------------------------------------------------
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

    /* ----------------------------------------------------------------
    
    Application

    ------------------------------------------------------------------- */

    // ----------------------------------------------------------------
    // Affiche la fenètre principale
    private void showMainStage(
            ReadOnlyObjectProperty<ObservableList<String>> friends
    ) {
        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.setTitle("Mes composants");
        mainStage.getIcons().add(new Image(
                APPLICATION_ICON
        ));

        final ListView<String> peopleView = new ListView<>();
        peopleView.itemsProperty().bind(friends);

        mainStage.setScene(new Scene(peopleView));
        mainStage.show();
    }
}
