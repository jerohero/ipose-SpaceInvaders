package sample.bullet;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.gameBehaviours.TimedBehaviour;

public class EnemyBullet extends Element implements Collidable, TimedBehaviour {

    private int speedY;

    public EnemyBullet() {
        super("/resources/textures/bullet_enemy.png");
        super.setFitHeight(50);
        super.setFitWidth(25);
        this.speedY = 0;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable instanceof PlayerBullet) {
            System.out.println("Bullets collided");
            Game game = Engine.getGameGlobaly();
            game.getActiveLevel().getElements().remove(this);
            game.getActiveLevel().getElements().remove(collidable);
        }
    }

    @Override
    public void handleTimeTick() {
        super.setY(super.getY() + speedY);
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }
}
