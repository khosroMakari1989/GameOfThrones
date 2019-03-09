package com.example.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class Player {

    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());

    private int exprience;
    private int score;

    //save it to file
    public void createCharacter() {

    }

    //load characters from file
    public void explorer() {
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("characters.txt"))).lines();) {
            stream.forEach(LOGGER::info);
        }
    }

    public void fight() {

    }

    public void saveGame() {

    }

    public void resumeGame() {

    }
}
