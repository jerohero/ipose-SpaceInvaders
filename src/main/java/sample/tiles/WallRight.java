package sample.tiles;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.game.Tile;

public class WallRight extends Tile implements Collidable{

    public WallRight(){
        super("/resources/WallTile.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
