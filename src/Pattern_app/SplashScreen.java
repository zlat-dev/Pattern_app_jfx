// ----------------------------------------------------------------
// SplashScreen.java
// 20250825
// @Zlatko
// ----------------------------------------------------------------
package Pattern_app;
// ----------------------------------------------------------------
// modules importés
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
// ----------------------------------------------------------------
// Classe SplashScreen
public class SplashScreen extends Preloader {
    // ----------------------------------------------------------------
    // Déclaration des modules
    // Création splash screen layout
    private final StackPane parent = new StackPane();
    // Le preloader
    private Stage preloaderStage;
    // L'indicateur de progression à l'intérieur
    private ProgressIndicator progressIndicator;
    // ----------------------------------------------------------------
    // Surcharge init()
    @Override
    public void init() throws Exception {
        // Image en fond du splash screen (un imageview aurait été possible mais sans progressbar)
        // background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("/Pattern_app/ressources/desticraft.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.parent.setBackground(new Background(backgroundImage));
        // ProgressIndicator
        this.progressIndicator = new ProgressIndicator(0.0);
        this.progressIndicator.setMinSize(85, 85);
        this.parent.getChildren().add(this.progressIndicator);
    }
    // ----------------------------------------------------------------
    // Surcharge start()
    @Override
    public void start(Stage stage) throws Exception {
        //Chargement du preloader
        this.preloaderStage = stage;
        // Create a scene with the StackPane as the root
        Scene scene = new Scene(parent, 640, 480);
        // Set the scene for the stage
        stage.setScene(scene);
        // Remove window decorations to create an undecorated window
        stage.initStyle(StageStyle.UNDECORATED);
        // Center the SplashScreen on the screen
        stage.centerOnScreen();
        // Display the SplashScreen
        stage.show();        
    }
    // ----------------------------------------------------------------
    // Surcharge handleApplicationNotification()
    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            // progress updates pour l'application
            double progress = ((ProgressNotification) info).getProgress();
            this.progressIndicator.setProgress(progress);
        }
    }
    // ----------------------------------------------------------------
    // Surcharge handleStateChangeNotification()
    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            // Ferme le splash screen quand l'application est prête à démarrer
            this.preloaderStage.close();
        }
    }
}
