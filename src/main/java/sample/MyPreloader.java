package sample;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MyPreloader extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        VBox loading = new VBox(20);
        loading.setMaxWidth(Region.USE_PREF_SIZE);
        loading.setMaxHeight(Region.USE_PREF_SIZE);

        Label label = new Label("Please wait...");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label.setTextFill(Color.web("WHITE"));
        loading.getChildren().add(label);
        loading.getChildren().add(new ProgressBar());
        loading.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(loading);
        root.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        Scene scene = new Scene(root);

        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if(stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
