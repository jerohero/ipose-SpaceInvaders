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


public class GameOver{
    private static Stage gameOverStage;

    public GameOver(Stage primaryStage){
        primaryStage.close();
        Stage gameOverStage = new Stage();
        setGameOverStage(gameOverStage);

        BorderPane root = createRoot();
        Scene gameOverScene = new Scene(root, 500, 500, Color.WHITE);
        gameOverStage.setScene(gameOverScene);
        gameOverStage.show();
        Runner.stopBGM();
    }

    private static BorderPane createRoot(){
        Label label = new Label("Game Over - You Lost");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label.setTextFill(Color.web("WHITE"));

        Button backToMenu = new Button();
        backToMenu.setText("Press the button to exit the game");

        backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();                                              // zo lang het resetten niet lukt moet het maar zo
                System.exit(0);                                        // zou leuk zijn als de hele applicatie opnieuw zou opstarten
//                Game game = Engine.getGameGlobaly();                        // zet game naar level 1, maar reset het niet
//                game.setActiveLevel(game.getLevels().get(0));
//                game.getActiveLevel().refreshElements();
//                getGameOverStage().close();
//                Runner.getPrimaryStage().show();
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

    private static Stage getGameOverStage() {
        return gameOverStage;
    }

    private void setGameOverStage(Stage gameOverStage) {
        this.gameOverStage = gameOverStage;
    }
}
