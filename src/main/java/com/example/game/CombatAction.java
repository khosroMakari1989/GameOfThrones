package com.example.game;

/**
 * The default actions that fighter can do in a fight. Each action has a weight
 * which will affect the health of the fighter while fighting. Also, the
 * kickWeight and punchWeight properties of the GameCharacter will be multiplied
 * on these actions to make the game more dynamic and more real.
 *
 * @author khosro.makari@gmail.com
 */
public enum CombatAction {
    KICK(10), PUNCH(10), KNOCKDOWN(30);//, KNOCKOUT(-1);

    private final int weight;

    private CombatAction(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return weight;
    }
}
