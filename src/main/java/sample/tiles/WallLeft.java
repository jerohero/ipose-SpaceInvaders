package sample.tiles;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.game.Tile;

public class WallLeft extends Tile implements Collidable{

    public WallLeft(){
        super("/resources/WallTile.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
