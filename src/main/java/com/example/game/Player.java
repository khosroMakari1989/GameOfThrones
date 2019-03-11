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

    public void fight(GameCharacter fighter, GameCharacter rival) {
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
        System.out.println("Winner is " + winner);
        System.out.println("Dead may never die!");

    }

    public void saveGame() {

    }

    public void resumeGame() {

    }
}
