// ----------------------------------------------------------------
// Lanceur.java
// 20250825
// @Zlatko
// ----------------------------------------------------------------
package Pattern_app;
// ----------------------------------------------------------------
// modules importés
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
// ----------------------------------------------------------------
// Classe Lanceur
public class Main extends Application {
    // Le parent layout manager
    private final StackPane parent = new StackPane();
    // ----------------------------------------------------------------
    // Surcharge init()
    @Override
    public void init() throws Exception {
        super.init();
        // Simule le chargement des tasks
        simulateLoadingTasks();
        buildUI();
    }
    // ----------------------------------------------------------------
    // Simule le chargement des tasks
    // A modifier avec la vérif de l'appli pendant le splash
    private void simulateLoadingTasks() throws InterruptedException {
        // Simule loading progress
        for (int i = 0; i < 100; i++) {
            double progress = (i + 1) / 100.0;
            notifyPreloader(new Preloader.ProgressNotification(progress));
            // Simulate loading delay
            try
            {
            Thread.sleep(10);
            } catch (InterruptedException ex) { }
        }
    }
    // ----------------------------------------------------------------
    // Construit l'IHM
    private void buildUI() {
        // Create a label to indicate application loading
        Label label = new Label("Chargement de l'application");
        // Set a larger font size
        label.setFont(Font.font(24));
        // Add the label to the parent StackPane
        this.parent.getChildren().add(label);



        Pane pane = new Pane();
        // pane.setPrefSize(1024, 768);
        pane.setStyle("-fx-background-color: #1e1e1e;");
        this.parent.getChildren().add(pane);
        Button element1 = new Button("Click Me");
        element1.setLayoutX(62.57);
        element1.setLayoutY(59.19);
        element1.setPrefWidth(105.81);
        element1.setPrefHeight(29.00);
        element1.setDisable(false);
        element1.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #D9D9D9; -fx-border-color: #979797; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        // element1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
        // element1.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
        this.parent.getChildren().add(element1);




    }
    // ----------------------------------------------------------------
    // Surcharge start()
    @Override
    public void start(Stage stage) throws Exception {
        // Create a scene with the StackPane as the root
        Scene scene = new Scene(parent, 1024, 768);
        // Set the stage title
        stage.setTitle("Pattern_app");
        // Set the scene for the stage
        stage.setScene(scene);
        // Center the stage on the screen
        stage.centerOnScreen();
        // Display the stage
        stage.show();
    }

    // @Override
    // public void start(Stage primaryStage) throws Exception{
    //     Pane pane = new Pane();
    //     pane.setPrefSize(1024, 512);
    //     pane.setStyle("-fx-background-color: #1e1e1e;");

    //     Button element1 = new Button("Click Me");
    //     element1.setLayoutX(62.57);
    //     element1.setLayoutY(59.19);
    //     element1.setPrefWidth(105.81);
    //     element1.setPrefHeight(29.00);
    //     element1.setDisable(false);
    //     element1.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #D9D9D9; -fx-border-color: #979797; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
    //     element1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
    //     element1.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#fc0404ff"), new CornerRadii(4.00), null))); });
    //     pane.getChildren().add(element1);

    //     Scene sceneappli = new Scene(pane, 1024, 512);
    //     primaryStage.setTitle("Pattern_app");
    //     primaryStage.setScene(sceneappli);
    //     primaryStage.show();
    // }

    // ----------------------------------------------------------------
    // Lance les routines
    public static void main(String[] args) {
        // Specify the custom preloader class
        System.setProperty("javafx.preloader", SplashScreen.class.getName());
        launch(args);
    }
}