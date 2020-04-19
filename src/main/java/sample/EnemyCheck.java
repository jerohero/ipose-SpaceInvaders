package sample;

import nl.hsleiden.engine.Engine;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import sample.enemy.Enemy;


public class EnemyCheck implements Runnable {
    int currentLevel = 0;


    @Override
    public void run() {
        while (true) {
            System.out.println("Enemy Count: " + enemyCount());
            Game game = Engine.getGameGlobaly();
            if (enemyCount() == 0 && currentLevel < 5) {
                game.setActiveLevel(game.getLevels().get(currentLevel++));
            } else if (enemyCount() == 0 && currentLevel == 5) {
                System.out.println("Game Over");
                game.getActiveLevel().getElements().clear();
            }
        }
    }

    private static int enemyCount() {
        int enemyCount = 0;
        Game game = Engine.getGameGlobaly();
        for (Element e : game.getActiveLevel().getElements()) {
            if (e instanceof Enemy) {
                enemyCount++;
            }
        }
        return enemyCount;
    }
}

