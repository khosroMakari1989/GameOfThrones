package com.example.game;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * default health is 100 for players
 *
 * @author khosro.makari@gmail.com
 */
public class Fight {

    private static final int minumumActions = 10;

    public void start(GameCharacter fighter, GameCharacter rival) {
        Random random = new Random();
        //int numberOfActions = random.nextInt(10) + minumumActions;
        fighter.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + fighter.getExperience() * 10);
        rival.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + rival.getExperience() * 10);
        while (fighter.getHealth() > 0 && rival.getHealth() > 0) {
            try {
                TimeUnit.SECONDS.sleep(3);
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
            case KNOCKOUT:
                knockOut(fighter, rival);
                rival.setHealth(-1);
                break;
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
}
