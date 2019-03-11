package com.example.game;

/**
 * A game character entity.
 *
 * @author khosro.makari@gmail.com
 */
public class GameCharacter {

    /**
     * by default every fighter holds 100 score as the health. This number will
     * be increased by gaining experience through each fight.
     *
     */
    public static final int BASIC_FIGHTER_HEALTH = 100;
    private String fullname;
    private int kickWeight; // a number between 1 to 3
    private int punchWeight;// a number between 1 to 3
    /**
     * numbe rof fights will increase the fighter's experience. Each win brings
     * 2 score as experience. Each loose brings 1 score as experience*
     */
    private int experience;
    private transient int health;

    public GameCharacter(String fullname, int kickWeight, int height, int experience) {
        this.fullname = fullname;
        this.kickWeight = kickWeight;
        this.punchWeight = height;
        this.experience = experience;
    }

    public int getKickWeight() {
        return kickWeight;
    }

    public void setKickWeight(int kickWeight) {
        this.kickWeight = kickWeight;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPunchWeight() {
        return punchWeight;
    }

    public void setPunchWeight(int punchWeight) {
        this.punchWeight = punchWeight;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Converts String to the Game Character
     *
     * @param characterInfo information of the game character as a String
     * @return the GameCharacter object
     */
    public static GameCharacter of(String characterInfo) {
        String[] info = characterInfo.split(", ");
        return new GameCharacter(info[0], Integer.valueOf(info[1]), Integer.valueOf(info[2]), Integer.valueOf(info[3]));
    }

    /**
     * Represents the game character information as a single String
     *
     * @return the information of the game character
     */
    @Override
    public String toString() {
        return (this.fullname + ", " + this.kickWeight + ", " + this.punchWeight + ", " + this.experience);
    }
}
