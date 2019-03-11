package com.example.game.fight;

import com.example.game.CombatAction;
import com.example.game.GameCharacter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * default health is 100 for players
 *
 * @author khosro.makari@gmail.com
 */
public class Fight implements PropertyChangeListener {

    private FightEvent fightEvent = FightEvent.STARTED;
    private final Random random = new Random();

    public void start(GameCharacter fighter, GameCharacter rival) {

        while (fighter.getHealth() > 0 && rival.getHealth() > 0) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Fight event after 3 seconds sleep:" + fightEvent.name());
                if (fightEvent == FightEvent.PAUSED) {
                    System.out.println("Game is paused!");
                    return;

                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Failed to make delay between actions!");
            }
            int existingActionsCount = CombatAction.values().length;
            int ordinal = random.nextInt(existingActionsCount);
            CombatAction combatAction = Stream.of(CombatAction.values())
                    .filter(action -> action.ordinal() == ordinal)
                    .findFirst()
                    .get();

            switch (random.nextInt(2)) {
                case 0:
                    beat(fighter, rival, combatAction);
                    break;
                case 1:
                    beat(rival, fighter, combatAction);
                    break;
            }
        }
        System.out.println("Fight is over!");
        setFightEvent(FightEvent.OVER);
    }

    private void beat(GameCharacter fighter, GameCharacter rival, CombatAction action) {
        rival.setHealth(rival.getHealth() - CombatAction.KICK.weight());
        switch (action) {
            case KICK:
                kick(fighter, rival);
                break;
            case PUNCH:
                punch(fighter, rival);
                break;
            case KNOCKDOWN:
                knockDown(fighter, rival);
                break;
//            case KNOCKOUT:
//                knockOut(fighter, rival);
//                rival.setHealth(-1);
//                break;
        }
    }

    private static void kick(GameCharacter fighter, GameCharacter rival) {
        System.out.println(fighter.getFullname() + " is Kicking " + rival.getFullname());

    }

    private void punch(GameCharacter fighter, GameCharacter rival) {
        System.out.println(fighter.getFullname() + " is Punching " + rival.getFullname());

    }

    private void knockDown(GameCharacter fighter, GameCharacter rival) {
        System.out.println(fighter.getFullname() + " is Knocking down " + rival.getFullname());

    }

    private void knockOut(GameCharacter fighter, GameCharacter rival) {
        System.out.println(fighter.getFullname() + " is Knocking out " + rival.getFullname());

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        this.fightEvent = (FightEvent) event.getNewValue();
        System.out.println("Observable is changed to: " + fightEvent.name());
    }

    public FightEvent getFightEvent() {
        return fightEvent;
    }

    public void setFightEvent(FightEvent fightEvent) {
        this.fightEvent = fightEvent;
        System.out.println("Game is " + fightEvent.name());
    }

}
