package com.example.game;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class PlayerTests {

    private final Player player = new Player();

    @Test
    public void explorerChracters_ShouldReturnContent() throws IOException, URISyntaxException {

        //act
        List<String> characterList = player.explorer();

        //assert
        Assert.assertNotNull(characterList);
        Assert.assertNotEquals(characterList.size(), 0);

    }

    @Test
    public void createChracter_SampleCharacter_ShouldSucceed() throws IOException, URISyntaxException {
        //arrange
        GameCharacter character = new GameCharacter("Red woman", 1, 1, 0);

        //act
        List<String> characterList = player.explorer();
        player.createCharacter(character);
        List<String> newCharacterList = player.explorer();

        //assert
        Assert.assertEquals((characterList.size() + 1), (newCharacterList.size()));

    }

    @Test
    public void fight_TwoFighters_ShouldHaveAWinnerAndALooser() throws IOException, URISyntaxException {
        //arrange
        GameCharacter fighter = new GameCharacter("Jon snow", 2, 2, 10);
        GameCharacter rival = new GameCharacter("Joffery Lannister", 1, 1, 0);

        //act
        player.fight(fighter, rival);

        //asserts
        Assert.assertTrue((fighter.getHealth() <= 0 || rival.getHealth() <= 0));
    }

    @Test
    public void fight_TwoFighters_ShouldGainExperience() throws IOException, URISyntaxException {
        //arrange
        int fighterExperience = 10;
        int rivalExperience = 1;
        GameCharacter fighter = new GameCharacter("Jon snow", 2, 2, fighterExperience);
        GameCharacter rival = new GameCharacter("Joffery Lannister", 1, 1, rivalExperience);
        //act
        player.fight(fighter, rival);

        //asserts
        Assert.assertTrue(fighter.getExperience() > fighterExperience);
        Assert.assertTrue(rival.getExperience() > rivalExperience);
    }
}
