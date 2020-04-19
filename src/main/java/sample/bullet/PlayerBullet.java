package sample.bullet;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.enemy.Enemy;
import sample.gameBehaviours.TimedBehaviour;
import sample.player.Player;
import sample.player.Player2;

public class PlayerBullet extends Element implements Collidable, TimedBehaviour {
    private Player player;
    private Player2 player2;
    private int speedY;

    public PlayerBullet(Player player) {
        super("/resources/textures/bullet_player1.png");
        super.setFitHeight(50);
        super.setFitWidth(15);
        this.speedY = 0;
        this.player = player;
    }

    public PlayerBullet(Player2 player2) {
        super("/resources/textures/bullet_player2.png");
        super.setFitHeight(50);
        super.setFitWidth(15);
        this.speedY = 0;
        this.player2 = player2;
    }


    @Override
    public void handleTimeTick() {
        super.setY(super.getY() - speedY);
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable instanceof Enemy) {
            ((Enemy) collidable).killEnemy();
            removeBullet();
            player.setRecentlyFired(false);
            player2.setRecentlyFired(false);
        }
    }

    private void removeBullet() {
        Game game = Engine.getGameGlobaly();
        game.getActiveLevel().getElements().remove(this);
    }
}
