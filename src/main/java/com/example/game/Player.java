package com.example.game;

import com.example.game.fight.Fight;
import com.example.game.fight.FightEvent;
import com.example.game.fight.FightObservable;
import com.example.game.utils.Constant;
import com.example.game.utils.FileUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class Player {

    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    Fight fight = new Fight();

    /**
     * Saves the game character to the file
     *
     * @param character the game character to be created
     * @throws IOException
     */
    public void createCharacter(GameCharacter character) throws IOException {
        //    Files.write(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()), character.toString().getBytes(), APPEND);
        FileUtil.appendToFile(Constant.GAME_CHARACTERS_FILE_NAME, character.toString());
    }

    /**
     * Loads all the game characters
     *
     * @return list of game characters
     * @throws IOException
     */
    public List<String> explorer() throws IOException {
        //        return Files.readAllLines(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()));
//        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("characters.txt"))).lines();) {
//            return stream.collect(Collectors.toList());
//        }
        return FileUtil.readLines(Constant.GAME_CHARACTERS_FILE_NAME);
    }

    public void fight(GameCharacter fighter, GameCharacter rival) throws IOException, URISyntaxException {
        FightObservable observable = new FightObservable();
        observable.addPropertyChangeListener(fight);
        fighter.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + fighter.getExperience() * 10);
        rival.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + rival.getExperience() * 10);

        Scanner scanner = new Scanner(System.in);

        Runnable task = () -> fight.start(fighter, rival);
        new Thread(task).start();

        while (fight.getFightEvent() != FightEvent.OVER) {
            System.out.println("#######################################################################################");
            String key = scanner.nextLine();

            if (key.equals("P")) {
                observable.setFightEvent(FightEvent.PAUSED);
            }

            if (key.equals("R")) {
                observable.setFightEvent(FightEvent.RESUMED);
                System.out.println("game is resumed");
                new Thread(task).start();
            }
            //key = scanner.nextLine();

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
    private void raiseExperience(String winner, String looser) throws IOException, URISyntaxException {
//        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("characters.txt"))).lines();) {
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
//            Files.write(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()), gameCharacters, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

}
