package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import nl.hsleiden.engine.GameLoader;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import nl.hsleiden.game.Tile;
import sample.bullet.PlayerBullet;
import sample.enemy.Boss;
import sample.enemy.Enemy;
import sample.gameBehaviours.TimedBehaviour;
import sample.gameBehaviours.TimedBehaviourManager;
import sample.player.Player;
import sample.player.Player2;
import sample.tiles.EnemyBorder;
import sample.tiles.SpaceTile;
import sample.tiles.WallLeft;
import sample.tiles.WallRight;

import java.io.File;
import java.util.HashMap;

public class Runner extends Application {
    private static int activeLevel = 0;
    private static int playersAlive = 2;
    private static Stage primaryStage;
    private static Scene startGameScene;
    private static AudioClip bgm;

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5, scene6;

    // Just a counter to create some delay while showing preloader.
    private static final int COUNT_LIMIT = 50000;

    private static int stepCount = 1;

    // Used to demonstrate step couns.
    public static String STEP() {
        return stepCount++ + ". ";
    }

    @Override
    public void init() throws Exception {
        System.out.println(Runner.STEP() + "MyApplication#init (doing some heavy lifting), thread: " + Thread.currentThread().getName());

        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (100 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        Runner.primaryStage = primaryStage;
        /*Standaard javafx applicatie opbouw*/
        window = primaryStage;
        primaryStage.setTitle("1920.IPOSE Space Invaders - Groep 5");
        Button startGameButton = new Button("Start Game");
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20, 10, 20, 10));

        // Setting the buttons
        Button player2Button = new Button("2 Players");
        Button nextNameButton = new Button("Next playername");
        Button controllerButton2 = new Button("See Contoller");
        Button startGameButton2 = new Button("Start Game");
        Button sluitGameButton = new Button("Close Game");
        Button sluitGameButton2 = new Button("Close Game");

        player2Button.setOnAction(e -> window.setScene(scene2));

        // Setting text box player 2
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setVgap(5);
        grid2.setHgap(5);

        Label label2 = new Label("Name: ");
        label2.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label2.setTextFill(Color.web("WHITE"));

        TextField name2 = new TextField();
        name2.setPromptText("Enter your name");
        name2.setPrefColumnCount(10);
        name2.getText();
        grid2.getChildren().add(name2);

        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setPadding(new Insets(10, 10, 10, 10));
        grid3.setVgap(5);
        grid3.setHgap(5);

        Label label6 = new Label("Name: ");
        label6.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label6.setTextFill(Color.web("WHITE"));

        TextField name3 = new TextField();
        name3.setPromptText("Enter your name");
        name3.setPrefColumnCount(10);
        name3.getText();
        grid3.getChildren().add(name3);

        // Checkbox voor kiezen van controller
        Label label4 = new Label("Controls Player 2:");
        label4.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label4.setTextFill(Color.web("WHITE"));

        Label label5 = new Label("Controls Player 1: ");
        label5.setFont(Font.font("Cambria", FontWeight.BOLD, 35));
        label5.setTextFill(Color.web("WHITE"));

        // Buttons voor het aangeven welke speler welke controls heeft
        RadioButton rb1 = new RadioButton("A, D & W to SHOOT");
        rb1.setFont(Font.font("Cambria", FontWeight.BOLD, 25));
        rb1.setTextFill(Color.web("WHITE"));
        rb1.setSelected(true);

        RadioButton rb3 = new RadioButton("Arrow's & UP to SHOOT");
        rb3.setFont(Font.font("Cambria", FontWeight.BOLD, 25));
        rb3.setTextFill(Color.web("WHITE"));
        rb3.setSelected(true);

        nextNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Starten van de game
                if ((name2.getText() != null && !name2.getText().isEmpty())) {
                    nextNameButton.setOnAction(e -> window.setScene(scene6));
                } else {
                    label2.setText("You have not left your name.");
                }
            }
        });

        controllerButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Starten van de game
                if ((name3.getText() != null && !name3.getText().isEmpty())) {
                    controllerButton2.setOnAction(e -> window.setScene(scene5));
                } else {
                    label6.setText("You have not left your name.");
                }
            }
        });

        startGameButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Als er op start wordt gedrukt automatisch opslaan van naam??

                //Starten van de game
                startGame(primaryStage);
            }
        });

        sluitGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        sluitGameButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        // Layout van scene 1 (players kiezen)
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        layout1.getChildren().addAll(player2Button, sluitGameButton);

        // Layout van scene 2 (player 2)
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        layout2.getChildren().addAll(label2, grid2, nextNameButton);

        // Layout van scene 6 (player 2)
        VBox layout6 = new VBox(20);
        layout6.setAlignment(Pos.CENTER);
        layout6.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        layout6.getChildren().addAll(label6, grid3, controllerButton2);

        // Layout van scene 5 (kies controller)
        VBox layout5 = new VBox(20);
        layout5.setAlignment(Pos.CENTER);
        layout5.setBackground(new Background(new BackgroundImage(new
                Image("/resources/MicrosoftTeams-image.png"), null, null, null, new BackgroundSize(45,
                45, true, true, true, true))));
        layout5.getChildren().addAll(label5, rb3, label4, rb1, startGameButton2, sluitGameButton2);

        // Setting the scene
        scene1 = new Scene(layout1, 800, 800, Color.WHITE);
        scene2 = new Scene(layout2,800, 800, Color.WHITE);
        scene5 = new Scene(layout5, 800, 800, Color.WHITE);
        scene6 = new Scene(layout6, 800, 800, Color.WHITE);

//        pane.setCenter(startGameButton);
//        Scene startGameScene = new Scene(pane, 500, 500, Color.WHITE );
//        setStartGameScene(startGameScene);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private void startGame(Stage primaryStage){
        GameLoader gameLoader = new GameLoader(40);

        BorderPane root = new BorderPane();
        primaryStage.getScene().setRoot(root);

        /*aanmaken van de hashmaps om de tiles van een level en de elementen van een level te mappen*/
        HashMap<Integer, Class<? extends Tile>> tileHashMap = new HashMap<>();
        tileHashMap.put(3, EnemyBorder.class);
        tileHashMap.put(2, WallRight.class);
        tileHashMap.put(1, WallLeft.class);
        tileHashMap.put(0, SpaceTile.class);
        gameLoader.addTileConfiguration(tileHashMap);

        HashMap<Integer, Class<? extends Element>> elementHashMap = new HashMap<>();
        elementHashMap.put(0, Player.class);
        elementHashMap.put(2, PlayerBullet.class);
        elementHashMap.put(3, Enemy.class);
        elementHashMap.put(4, sample.player.Meteorite.class);
        elementHashMap.put(5, Player2.class);
        elementHashMap.put(6, Boss.class);
        gameLoader.addElementsConfiguration(elementHashMap);

        /* toevoegen aan de gameloader van de levels van de game in dit geval alleen level 1, hier gaat als eerste
        een pad naar de tiles txt bestand en als tweede een pad naar de elementen. */
        gameLoader.addLevel(1,"/resources/level1Tiles.txt","/resources/level1Elements.txt");
        gameLoader.addLevel(2,"/resources/level2Tiles.txt","/resources/level2Elements.txt");
        gameLoader.addLevel(3,"/resources/level2Tiles.txt","/resources/level3Elements.txt");
        gameLoader.addLevel(4,"/resources/level2Tiles.txt","/resources/level4Elements.txt");
        gameLoader.addLevel(5,"/resources/level2Tiles.txt","/resources/level5Elements.txt");

        //aanmaken van het game object
        Game game = gameLoader.load();

        game.setActiveLevel(game.getLevels().get(0));

        /*aanmaken van de engine*/
        Engine engine = new Engine(game);
        engine.addBehavior(TimedBehaviour.class, new TimedBehaviourManager());
        engine.start(primaryStage);

        startBGM();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Runner.class, MyPreloader.class, args);
    }

    public static void playSound(String path, double vol) {
        Media sound = new Media(new File(path).toURI().toString());
        AudioClip audioClip = new AudioClip(sound.getSource());
        audioClip.setVolume(vol);
        audioClip.play();
    }

    public static void startBGM() {
        Media sound = new Media(new File("src/main/java/resources/music/to_the_next_destination.wav").toURI().toString());
        bgm = new AudioClip(sound.getSource());
        bgm.setVolume(0.4);
        bgm.play();
    }

    public static void stopBGM() {
        bgm.stop();
    }

    public static void nextLevel() {
        Game game = Engine.getGameGlobaly();
        game.setActiveLevel(game.getLevels().get(activeLevel + 1));
        activeLevel++;
        playersAlive = 2;
    }

    public static void wonGame() {
        WonGame wonGame = new WonGame(getPrimaryStage());
    }

    public static void removePlayer() {
        playersAlive--;
        if (playersAlive <= 0) {         // playersAlive reset niet na het resetten van het spel
            System.out.println("No players alive");
            GameOver gameOver = new GameOver(getPrimaryStage());
        } else {
            System.out.println(playersAlive + " players alive.");
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage pStage) {
        Runner.primaryStage = pStage;
    }

    public static Scene getStartGameScene() {
        return startGameScene;
    }

    public void setStartGameScene(Scene startGameScene) {
        Runner.startGameScene = startGameScene;
    }

    public static int getActiveLevelInt() {
        return activeLevel;
    }
}
