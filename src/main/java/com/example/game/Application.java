package com.example.game;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class Application {

    private static final String MENU_CHOICES = "1234";
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private final Player player = new Player();

    public static void main(String[] args) {
        Application application = new Application();
        System.out.println("please select:");
        System.out.println("1- Create a character");
        System.out.println("2- Explorer Characters");
        System.out.println("3- Fight");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        application.handleSelectMenu(input);
    }

    private void handleSelectMenu(String choice) {
        if (choice.isEmpty() || choice.length() != 1 || !MENU_CHOICES.contains(choice)) {
            System.out.println("Wrong input.");
            return;
        }
        switch (choice) {
            case "1":

                break;
            case "2":
                player.explorer();
                break;
            case "3":
            case "4":
        }
    }
}
