package com.example.game;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The Runner class of the game
 *
 * @author khosro.makari@gmail.com
 */
public class GameRunner {

    //valid menu choices which is from 1 to 4
    private static final String MENU_CHOICES = "1234";
    private final Player player = new Player();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Runs the game in a loop until the user quits the game via exit key(4 in
     * the menu)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        GameRunner application = new GameRunner();
        displayMenu();
        boolean exit = false;
        while (!exit) {
            System.out.println("#######################################################################################");
            String input = application.scanner.nextLine();
            try {
                exit = application.handleSelectMenu(input);
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input.");
                displayMenu();
            }

        }
    }

    /**
     * Shows the main menu
     */
    private static void displayMenu() {
        System.out.println("######################################################################################");
        System.out.println("please select a number:");
        System.out.println("1- Create a character");
        System.out.println("2- Explorer Characters");
        System.out.println("3- Fight");
        System.out.println("4- Exit");
    }

    /**
     * Handles the menu selection and executes the relevant action based on the
     * user input * The key 'P' pauses the game. The key 'R' resumes the game
     *
     *
     * @param choice the selected number
     * @param scanner the scanner to get inputs
     * @return
     * @throws IOException
     */
    private boolean handleSelectMenu(String choice) throws IOException {
        if (choice.isEmpty() || choice.length() != 1 || !MENU_CHOICES.contains(choice)) {
            throw new InputMismatchException("Incorrect weight!");
        }
        switch (choice) {
            case "1":
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter kick weight(between 1 to 3): ");
                int kickWeight = readKickOrPunchWeight();
                scanner.nextLine();
                System.out.println("Enter punch weight(between 1 to 3): ");
                int punchWeight = readKickOrPunchWeight();
                scanner.nextLine();
                player.createCharacter(new GameCharacter(name, kickWeight, punchWeight, 0));
                System.out.println("Game Character is created successfully!");
                break;
            case "2":
                System.out.println("## NAME ## KICK WEIGHT  ## PUNCH WEIGHT ## EXPERIENCE");
                player.explorer().forEach(System.out::println);
                break;
            case "3": // A fight can also be saved, and resumed
                List<String> fighters = player.explorer();
                for (int i = 0; i < fighters.size(); i++) {
                    System.out.println(i + 1 + "- " + fighters.get(i).substring(0, fighters.get(i).indexOf(',')));
                }
                System.out.println("#######################################################################################");
                System.out.println("Choose two of your favourite characters by their number and start the fight!");
                int fighterNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Now select the rival!");
                int rivalNumber = scanner.nextInt();
                scanner.nextLine();
                if (fighterNumber < 1 || fighterNumber > fighters.size() || rivalNumber < 1 || rivalNumber > fighters.size()) {
                    throw new InputMismatchException();
                }
                player.fight(GameCharacter.of(fighters.get(fighterNumber - 1)), GameCharacter.of(fighters.get(rivalNumber - 1)));
                break;
            case "4":
                return true;

        }
        return false;
    }

    private int readKickOrPunchWeight() {
        try {
            int weight = scanner.nextInt();
            if (weight < 0 || weight > 3) {
                throw new InputMismatchException();
            }
            return weight;
        } catch (InputMismatchException ex) {
            throw ex;
        }
    }

}
