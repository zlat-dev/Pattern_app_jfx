package Pattern_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new Pane();
        pane.setPrefSize(864, 432);
        pane.setStyle("-fx-background-color: #1e1e1e;");

        Button element1 = new Button("Click Me");
        element1.setLayoutX(62.57);
        element1.setLayoutY(59.19);
        element1.setPrefWidth(105.81);
        element1.setPrefHeight(29.00);
        element1.setDisable(false);
        element1.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #D9D9D9; -fx-border-color: #979797; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        element1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#232323"), new CornerRadii(4.00), null))); });
        element1.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#2e2e2e"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(element1);

        Scene scene = new Scene(pane, 864, 432);

        primaryStage.setTitle("Pattern_app");

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}