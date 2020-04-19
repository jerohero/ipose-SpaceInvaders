package sample.enemy;

import javafx.scene.image.Image;
import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.Runner;
import sample.bullet.PlayerBullet;
import sample.bullet.EnemyBullet;
import sample.gameBehaviours.TimedBehaviour;
import sample.tiles.WallLeft;
import sample.tiles.WallRight;

public class Boss extends Element implements TimedBehaviour, Collidable {

    private double speed = 5;
    private int shootTimer = 1;
    private int bounceTimer = 1;
    private boolean recentlyFired = false;
    private boolean recentlyBounced = false;
    private double shootMultiplier = 0.5;
    private int totalHits = 0;
    private boolean introSpeech = false;

    public Boss(){
        super("/resources/textures/jameswalls.png");
        super.setFitHeight(216);
        super.setFitWidth(189);
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable instanceof WallRight) {
            bounce();
        }
        else if (collidable instanceof WallLeft) {
            bounce();
        }
        else if (collidable instanceof PlayerBullet) {
            if(totalHits == 1) {
                Image sprite = new Image("/resources/textures/jameswalls_angry.png");
                super.setImage(sprite);

                Game game = Engine.getGameGlobaly();
                game.getActiveLevel().getElements().remove(collidable);
            }
            if(totalHits == 2) {
                Image sprite = new Image("/resources/textures/jameswalls_angrier.png");
                super.setImage(sprite);
                speed = speed * 1.1;
                Game game = Engine.getGameGlobaly();
                game.getActiveLevel().getElements().remove(collidable);
            }
            if(totalHits == 3) {
                Image sprite = new Image("/resources/textures/jameswalls_realshit.png");
                super.setFitHeight(getFitHeight()*0.97);
                super.setFitWidth(getFitWidth()*0.97);
                super.setImage(sprite);
                speed = speed * 1.2;
                Game game = Engine.getGameGlobaly();
                game.getActiveLevel().getElements().remove(collidable);
                Runner.playSound("src/main/java/resources/sounds/jameswalls_voice2.wav", 0.5);
            }
            if(totalHits == 4) {
                Image sprite = new Image("/resources/textures/jameswalls_actuallymalding.png");
                super.setFitHeight(getFitHeight()*0.97);
                super.setFitWidth(getFitWidth()*0.97);
                super.setImage(sprite);
                speed = speed * 1.3;
                Game game = Engine.getGameGlobaly();
                game.getActiveLevel().getElements().remove(collidable);
            }
            if(totalHits > 4) {
                killBoss();
            }
            totalHits++;
        }
    }

    @Override
    public void handleTimeTick() {
        moveX();
        shoot();
        if(!introSpeech){
            Runner.playSound("src/main/java/resources/sounds/jameswalls_voice3.wav", 0.3);
            introSpeech = true; }
    }

    public void killBoss(){
        System.out.println("Completed the game");
        Game game = Engine.getGameGlobaly();
        game.getActiveLevel().getElements().remove(this);

        Runner.wonGame();
    }

    private void moveX(){
        super.setX(super.getX() - speed);
    }

    private void shoot() {
        if (!recentlyFired) {
            if(Math.random() < shootMultiplier) {
                Game game = Engine.getGameGlobaly();
                EnemyBullet b = new EnemyBullet();
                b.setSpeedY(2);
                b.setY(super.getY() + getFitHeight());
                b.setX(super.getX() + getFitWidth() / 2.5);
                game.getActiveLevel().getElements().add(b);
            }
            recentlyFired = true;
        }
        else {
            if (shootTimer < Math.random() * 1000) {  shootTimer++; }
            else {
                shootTimer = 1;
                recentlyFired = false;
            }
        }
    }

    private void bounce(){
        if (!recentlyBounced) {
            speed = speed * - 1;
            recentlyBounced = true;
        }
        else{
            if (bounceTimer < 40) {
                bounceTimer++;
            } else {
                bounceTimer = 1;
                recentlyBounced = false;
            }
        }
    }
}
