package com.example.game;

import com.example.game.fight.Fight;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class FighterTest {

    private final Fight fight = new Fight();

    @Test
    public void fight_TwoFighters_ShouldHaveAWinnerAndALooser() throws IOException, URISyntaxException {
        //arrange
        GameCharacter fighter = new GameCharacter("Jon snow", 3, 3, 1);
        GameCharacter rival = new GameCharacter("Joffery Lannister", 3, 3, 0);
        fighter.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + 1 * 10);
        rival.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH);
        //act
        fight.start(fighter, rival);

        //asserts
        Assert.assertTrue((fighter.getHealth() <= 0 || rival.getHealth() <= 0));
    }

    @Test
    public void fight_TwoFighters_ShouldGainExperience() throws IOException, URISyntaxException {
        //arrange
        int fighterExperience = 2;
        int rivalExperience = 1;
        GameCharacter fighter = new GameCharacter("Jon snow", 3, 3, fighterExperience);
        GameCharacter rival = new GameCharacter("Joffery Lannister", 3, 3, rivalExperience);
        fighter.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + fighterExperience * 10);
        rival.setHealth(GameCharacter.BASIC_FIGHTER_HEALTH + rivalExperience * 10);

        //act
        fight.start(fighter, rival);

        //asserts
        Assert.assertTrue(fighter.getExperience() > fighterExperience);
        Assert.assertTrue(rival.getExperience() > rivalExperience);
    }
}
