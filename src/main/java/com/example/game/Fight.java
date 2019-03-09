package com.example.game;

import java.util.Random;
import java.util.stream.Stream;

/**
 * default health is 100 for players
 *
 * @author khosro.makari@gmail.com
 */
public class Fight {

    private static final int minumumActions = 10;

    public static void fight(GameCharacter fighter, GameCharacter river) {
        Random random = new Random();
        int numberOfActions = random.nextInt(10) + minumumActions;

        for (int i = 0; i <= numberOfActions; i++) {
            int existingActionsCount = CombatAction.values().length;
            int ordinal = random.nextInt(existingActionsCount);
            CombatAction combatAction = Stream.of(CombatAction.values()).filter(action -> action.ordinal() == ordinal).findFirst().get();

            switch (random.nextInt(2)) {
                case 0:
                    beat(fighter, river, combatAction);
                    break;
                case 1:
                    beat(river, fighter, combatAction);
                    break;
            }
        }

    }

    public static void beat(GameCharacter fighter, GameCharacter river, CombatAction action) {

        switch (action) {
            case KICK:
                kick(fighter, river);
                break;
            case PUNCH:
                punch(fighter, river);
                break;
            case KNOCKDOWN:
                knockDown(fighter, river);
                break;
            case KNOCKOUT:
                knockOut(fighter, river);
                break;
        }
    }

    public static void kick(GameCharacter fighter, GameCharacter river) {

    }

    public static void punch(GameCharacter fighter, GameCharacter river) {

    }

    public static void knockDown(GameCharacter fighter, GameCharacter river) {
    }

    public static void knockOut(GameCharacter fighter, GameCharacter river) {
    }
}
