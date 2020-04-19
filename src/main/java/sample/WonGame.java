package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WonGame{
    private static Stage wonGameStage;

    public WonGame(Stage primaryStage){
        primaryStage.close();
        Stage wonGameStage = new Stage();
        wonGameStage(wonGameStage);

        BorderPane root = createRoot();
        Scene gameOverScene = new Scene(root, 500, 500, Color.WHITE);
        wonGameStage.setScene(gameOverScene);
        wonGameStage.show();
        Runner.stopBGM();
        Runner.playSound("src/main/java/resources/music/victorymusic.wav", 0.6);
    }

    private static BorderPane createRoot(){
        Label label = new Label("You're a Legend - You Won");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label.setTextFill(Color.web("WHITE"));

        Button backToMenu = new Button();
        backToMenu.setText("Press the button to exit the game");

        backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(label, backToMenu);

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        borderPane.setCenter(box);

        return borderPane;
    }

    private static Stage getWonGameStage() {
        return wonGameStage;
    }

    private void wonGameStage(Stage wonGameStage) {
        this.wonGameStage = wonGameStage;
    }
}
