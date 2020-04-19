package sample.player;

import javafx.scene.image.Image;
import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.Runner;
import sample.bullet.EnemyBullet;

public class Meteorite extends Element implements Collidable {
	
	int i = 1;
	
	public Meteorite() {
		super("/resources/textures/shield.png");
		this.setFitHeight(60);
		this.setFitWidth(110);
	}
	
	@Override
	public void handleCollision(Collidable collidable) {
		if(collidable instanceof EnemyBullet) {
			if(i == 1) {
				Game game = Engine.getGameGlobaly();
				if(Runner.getActiveLevelInt() == 4) { 	//level 5
					game.getActiveLevel().getElements().remove(collidable);
					game.getActiveLevel().getElements().remove(this);
				}
				else {
					Image sprite = new Image("/resources/textures/shield2.png");
					super.setImage(sprite);
					game.getActiveLevel().getElements().remove(collidable);
				}
			}
			if(i == 2) {
				Image sprite = new Image("/resources/textures/shield3.png");
				super.setImage(sprite);
				Game game = Engine.getGameGlobaly();
				game.getActiveLevel().getElements().remove(collidable);
			}
			if(i == 3) {
				Game game = Engine.getGameGlobaly();
				game.getActiveLevel().getElements().remove(this);
				game.getActiveLevel().getElements().remove(collidable);
			}
			i++;
		}
	}
	
}
