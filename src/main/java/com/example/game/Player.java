package com.example.game;

import com.example.game.fight.Fight;
import com.example.game.fight.FightEvent;
import com.example.game.fight.FightObservable;
import com.example.game.utils.Constant;
import com.example.game.utils.FileUtil;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class contains features which a player can use them to control the game
 *
 * @author khosro.makari@gmail.com
 */
public class Player {

    /**
     * In order to handle the pause and resume feature('P' and 'R' as input), a
     * new thread is needed. Using executersService will handle the lifecycles
     * of threads.
     */
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Fight fight = new Fight();

    /**
     * Saves the game character to the file
     *
     * @param character the game character to be created
     * @throws IOException
     */
    public void createCharacter(GameCharacter character) throws IOException {
        FileUtil.appendToFile(Constant.GAME_CHARACTERS_FILE_NAME, character.toString());
    }

    /**
     * Loads all the game characters
     *
     * @return list of game characters
     * @throws IOException
     */
    public List<String> explorer() throws IOException {
        return FileUtil.readLines(Constant.GAME_CHARACTERS_FILE_NAME);
    }

    /**
     * Handles the fight between two game characters. This method uses
     * multi-threading in order to create a seperate thread to handle the PAUSE
     * and RESUME featrues of the fight. It also uses Observer design pattern to
     * publish the change to FightEvent property of the Observable to the
     * observer classes.
     *
     * When a fight is paused, observable will publish this status, Fight class
     * will be informed and will pause the fight via exiting the method. When a
     * fight is resumed, observable will publish this status, Fight class will
     * be informed and will resume the game again. It also sets the Health of
     * the fighters, based on the Default Health property in the GameCharacter
     * class and based on the experience of each fighter.
     *
     * The key 'P' pauses the game. The key 'R' resumes the game
     *
     * @param fighter to fight to the rival
     * @param rival to fight to the fighter
     * @throws IOException
     */
    public void fight(GameCharacter fighter, GameCharacter rival) throws IOException {
        FightObservable observable = new FightObservable();
        observable.addPropertyChangeListener(fight);
        fighter.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + fighter.getExperience() * 10);
        rival.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + rival.getExperience() * 10);
        Scanner scanner = new Scanner(System.in);

        Runnable task = () -> fight.start(fighter, rival);
        executorService.execute(task);

        while (fight.getFightEvent() != FightEvent.OVER) {
            System.out.println("#######################################################################################");
            String key = scanner.nextLine();

            if (key.equals("P")) {
                observable.setFightEvent(FightEvent.PAUSED);
            }

            if (key.equals("R")) {
                observable.setFightEvent(FightEvent.RESUMED);
                System.out.println("Game is resumed");
                executorService.execute(task);
            }
        }
        String winner = fighter.getHealth() > 0 ? fighter.getFullname() : rival.getFullname();
        String looser = fighter.getHealth() > 0 ? rival.getFullname() : fighter.getFullname();
        System.out.println("Winner is " + winner);
        System.out.println("Dead may never die!");
        raiseExperience(winner, looser);
    }

    /**
     * Raises the experiences of the given fighters. The winner should be
     * awarded score 2 as the experience and the looser should be awarded score
     * 1 as the experience.
     *
     * @param winner the winner fighter
     * @param looser the looser fighter
     */
    private void raiseExperience(String winner, String looser) throws IOException {
        try (Stream<String> stream = FileUtil.readLines(Constant.GAME_CHARACTERS_FILE_NAME).stream();) {
            List<String> gameCharacters = stream.map(str -> GameCharacter.of(str)).map(gameCharacter -> {
                if (gameCharacter.getFullname().equals(winner)) {
                    gameCharacter.setExperience(gameCharacter.getExperience() + 2);
                }
                if (gameCharacter.getFullname().equals(looser)) {
                    gameCharacter.setExperience(gameCharacter.getExperience() + 1);
                }
                return gameCharacter;
            }).map(gameCharacter -> gameCharacter.toString()).collect(Collectors.toList());
            FileUtil.updateFile(Constant.GAME_CHARACTERS_FILE_NAME, gameCharacters);
        }
    }

}
