package com.example.game;

import com.example.game.fight.Fight;
import com.example.game.fight.FightEvent;
import com.example.game.fight.FightObservable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.APPEND;
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

    //save it to file
    public void createCharacter(GameCharacter character) throws IOException, URISyntaxException {
        Files.write(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()), character.toString().getBytes(), APPEND);
    }

    //load characters from file
    public List<String> explorer() {
        //        return Files.readAllLines(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()));
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("characters.txt"))).lines();) {
            return stream.collect(Collectors.toList());
        }

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
            String key = scanner.nextLine();

            System.out.println("entered key is:" + key);
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
        System.out.println("fight is overrrrrrrrrrr in player");
        String winner = fighter.getHealth() > 0 ? fighter.getFullname() : rival.getFullname();
        String looser = fighter.getHealth() > 0 ? rival.getFullname() : fighter.getFullname();
        System.out.println("Winner is " + winner);
        System.out.println("Dead may never die!");
        raiseExperience(winner, looser);
    }

    /**
     * add 2 score for winner add 1 score for looser
     *
     * @param winner
     * @param looser
     */
    private void raiseExperience(String winner, String looser) throws IOException, URISyntaxException {
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("characters.txt"))).lines();) {
            List<String> gameCharacters = stream.map(str -> GameCharacter.of(str)).map(gameCharacter -> {
                if (gameCharacter.getFullname().equals(winner)) {
                    gameCharacter.setExperience(gameCharacter.getExperience() + 2);
                }
                if (gameCharacter.getFullname().equals(looser)) {
                    gameCharacter.setExperience(gameCharacter.getExperience() + 1);
                }
                return gameCharacter;
            }).map(gameCharacter -> gameCharacter.toString()).collect(Collectors.toList());
            Files.write(Paths.get(ClassLoader.getSystemResource("characters.txt").toURI()), gameCharacters, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

}
