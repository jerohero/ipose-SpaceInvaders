package sample.enemy;

import javafx.scene.image.Image;
import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.Runner;
import sample.bullet.EnemyBullet;
import sample.gameBehaviours.TimedBehaviour;
import sample.player.Player;
import sample.player.Player2;
import sample.tiles.EnemyBorder;
import sample.tiles.WallLeft;
import sample.tiles.WallRight;

public class Enemy extends Element implements TimedBehaviour, Collidable  {

    private double speed = 0.8;
    private int shootTimer = 1;
    private int bounceTimer = 1;
    private boolean recentlyFired = false;
    private boolean recentlyBounced = false;
    private boolean setSprites = false;
    boolean reachedBorder = false;
    Player player = new Player();
    Player2 player2 = new Player2();

    public Enemy() {
        super("/resources/textures/enemy1.png");
        super.setFitHeight(50);
        super.setFitWidth(64);
        setSprites();
    }

    @Override
    public void handleTimeTick() {
        if (!setSprites) {
            setSprites();
            setSprites = true;
        }
        moveX();
        shoot();
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable instanceof WallRight) {
            bounce();
        }
        else if (collidable instanceof WallLeft) {
            bounce();
        }
        else if (collidable instanceof EnemyBorder) {
            enemiesWin();
        }
    }

    public void moveX(){
        super.setX(super.getX() - speed);
    }

    public void bounce(){
        if (!recentlyBounced) {
            double speedMultiplier = 1.3;
            speed = speed * -speedMultiplier;
            super.setY(super.getY() + getFitHeight());
            recentlyBounced = true;
        }
        else{
            if (bounceTimer < 10) {
                bounceTimer++;
            } else {
                bounceTimer = 1;
                recentlyBounced = false;
            }
        }
    }

    public void enemiesWin(){
        if (!reachedBorder) {
            player.killPlayer(); player2.killPlayer();
            reachedBorder = true;
        }
        Game game = Engine.getGameGlobaly();
        game.getActiveLevel().getElements().clear();
    }

    public void shoot() {
        if (!recentlyFired) {
            double shootMultiplier = 0.04;
            if(Math.random() < shootMultiplier) {
                Game game = Engine.getGameGlobaly();
                EnemyBullet b = new EnemyBullet();
                b.setSpeedY(2);
                b.setY(super.getY() + getFitHeight());
                b.setX(super.getX() + getFitWidth() / 2.5);
                game.getActiveLevel().getElements().add(b);
                recentlyFired = true;
            }
            else {
                recentlyFired = true;
            }
        }
        else {
            if (shootTimer < Math.random() * 1000) {  shootTimer++; }
            else {
                shootTimer = 1;
                recentlyFired = false;
            }
        }
    }

    public void killEnemy(){
        System.out.println("Killed an enemy");
        Game game = Engine.getGameGlobaly();
        game.getActiveLevel().getElements().remove(this);

        System.out.println("Enemy Count: " + enemyCount());
        if (enemyCount() == 0) {
            Runner.nextLevel();
        }

        Runner.playSound("src/main/java/resources/sounds/killed_enemy.wav", 0.3);
    }

    private static int enemyCount() {
        int enemyCount = 0;
        Game game = Engine.getGameGlobaly();
        for (Element element : game.getActiveLevel().getElements()) {
            if (element instanceof Enemy) {
                enemyCount++;
            }
        }
        return enemyCount;
    }

    private void setSprites() {
        int activeLevel = Runner.getActiveLevelInt();
        if(activeLevel == 1) {
            Image sprite = new Image("/resources/textures/enemy5.png");
            super.setImage(sprite);
        }
        else if(activeLevel == 2) {
            Image sprite = new Image("/resources/textures/enemy7.png");
            super.setImage(sprite);
        }
        else if(activeLevel == 3) {
            Image sprite = new Image("/resources/textures/enemy8.png");
            super.setImage(sprite);
        }
    }

}

