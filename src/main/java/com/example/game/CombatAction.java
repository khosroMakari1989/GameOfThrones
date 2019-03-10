package com.example.game;

/**
 * weight and score for the actions
 *
 * @author khosro.makari@gmail.com
 */
public enum CombatAction {
    KICK(10), PUNCH(10), KNOCKDOWN(30), KNOCKOUT(-1);//KNOCKOUT means that the game is finished

    private int weight;

    private CombatAction(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }
}
