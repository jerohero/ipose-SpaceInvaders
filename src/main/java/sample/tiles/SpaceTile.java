package sample.tiles;

import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.game.Tile;

public class SpaceTile extends Tile implements Collidable {

	public SpaceTile(){
		super("/resources/blackTile.png");
	}

	@Override
	public void handleCollision(Collidable collidable) {

	}
}
