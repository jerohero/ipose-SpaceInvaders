package sample.player;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.behaviour.behaviours.KeyBehaviour;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.Runner;
import sample.bullet.PlayerBullet;
import sample.bullet.EnemyBullet;
import sample.enemy.Enemy;
import sample.gameBehaviours.TimedBehaviour;
import sample.tiles.SpaceTile;
import sample.tiles.WallLeft;
import sample.tiles.WallRight;

import java.io.File;
import java.util.Set;

public class Player2 extends Element implements KeyBehaviour, Collidable, TimedBehaviour {

    private int speedLeft = 3;
    private int speedRight = 3;
    private double deltaY;
    private double deltaX;
    private int shootDelayCounter = 0;
    private int shootDelay = 100;
    private boolean recentlyFired = false;
    private Enemy enemy;


    public Player2(){
        super("/resources/textures/player2.png");
        this.setFitHeight(110);
        this.setFitWidth(110);
    }

    /*op elke keyboard input wordt deze methode aangeroepen en wordt de positie van de hero veranderd */
    @Override
    public void handleKeyPresses(Set<String> set) {
        if (set.contains("W") && !recentlyFired) {
            shoot();
        } else if (set.contains("A")){
            super.setX(super.getX()-speedLeft);
        } else if (set.contains("D")){
            super.setX(super.getX()+speedRight);
        }
    }

    @Override
    public void handleCollision(Collidable collidable){
        if (collidable instanceof SpaceTile) {
            speedLeft = 3;
            speedRight = 3;
        }
        if (collidable instanceof WallRight) {
            speedRight = 0;
        }
        if (collidable instanceof WallLeft) {
            speedLeft = 0;
        }
        if(collidable instanceof EnemyBullet){
            killPlayer();
        }
    }

    public void killPlayer(){
        System.out.println("Killed a player");
        Game game = Engine.getGameGlobaly();
        game.getActiveLevel().getElements().remove(this);

        Runner.removePlayer();

        Media sound = new Media(new File("src/main/java/resources/sounds/killed_player.wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }

    public void shoot(){
        Game game = Engine.getGameGlobaly();
        PlayerBullet b = new PlayerBullet(this);
        b.setSpeedY(5);
        b.setY(super.getY() - 50);
        b.setX(super.getX() + 47);
        game.getActiveLevel().getElements().add(b);
        recentlyFired = true;
    }

    public void setRecentlyFired(Boolean bool) {
        this.recentlyFired = bool;
    }

    @Override
    public void handleTimeTick() {
        if (recentlyFired) {
            shootDelayCounter++;
        }  if (shootDelayCounter > shootDelay) {
            setRecentlyFired(false);
            shootDelayCounter = 0;
        }
    }
}
