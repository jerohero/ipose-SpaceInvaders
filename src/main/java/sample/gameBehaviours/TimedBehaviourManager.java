package sample.gameBehaviours;

import nl.hsleiden.behaviour.BehaviourManager;
import nl.hsleiden.game.Element;

public class TimedBehaviourManager implements BehaviourManager {

    @Override
    public void handle(Element element) {
        TimedBehaviour timedBehaviour = (TimedBehaviour) element;
        timedBehaviour.handleTimeTick();
    }
}
