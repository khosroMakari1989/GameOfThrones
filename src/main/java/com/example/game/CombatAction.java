package com.example.game;

/**
 * weight and score for the actions
 *
 * @author khosro.makari@gmail.com
 */
public enum CombatAction {
    KICK(10), PUNCH(20), KNOCKDOWN(100), KNOCKOUT(50);

    private int weight;

    private CombatAction(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }
}
