package com.example.game;

/**
 * characters of Game of Thrones
 *
 * numbers 0-9 for player 1 keys for player 2
 *
 * @author khosro.makari@gmail.com
 */
public class GameCharacter {

    // by default every fighter holds 100 score as the health.
    // This number will be increased by gaining experience through each fight
    public static final int BASIC_FIGHTER_HEALTH = 100;
    private String fullname;
    private int age;
    private int height;
    //numberof fights will increase the fighter's experience
    private int experience;
    private transient int health;

    public GameCharacter(String fullname, int age, int height, int experience) {
        this.fullname = fullname;
        this.age = age;
        this.height = height;
        this.experience = experience;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    @Override
    public String toString() {
        return (this.fullname + ", " + this.age + ", " + this.height + ", " + this.experience);
    }
}
