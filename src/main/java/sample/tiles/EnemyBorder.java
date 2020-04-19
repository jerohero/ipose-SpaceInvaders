package sample.tiles;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.game.Tile;

public class EnemyBorder extends Tile implements Collidable {

    public EnemyBorder(){
        super("/resources/blackTile.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {
    }
}

