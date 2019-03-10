package com.example.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.List;
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
        fight.start(fighter, rival);
        String winner = fighter.getHealth() > 0 ? fighter.getFullname() : rival.getFullname();
        System.out.println("Winner is " + winner);
        System.out.println("Dead may never die!");
    }

    public void saveGame() {

    }

    public void resumeGame() {

    }
}
