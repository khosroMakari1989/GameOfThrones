package com.example.game;

import java.io.IOException;
import java.net.URISyntaxException;
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

    public static void main(String[] args) throws IOException, URISyntaxException {
        Application application = new Application();
        System.out.println("please select:");
        System.out.println("1- Create a character");
        System.out.println("2- Explorer Characters");
        System.out.println("3- Fight");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        application.handleSelectMenu(input, scanner);
    }

    private void handleSelectMenu(String choice, Scanner scanner) throws IOException, URISyntaxException {
        if (choice.isEmpty() || choice.length() != 1 || !MENU_CHOICES.contains(choice)) {
            System.out.println("Wrong input.");
            return;
        }
        switch (choice) {
            case "1":
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter height: ");
                int height = scanner.nextInt();
                scanner.nextLine();
                player.createCharacter(new GameCharacter(name, age, height, 0));
                System.out.println("Game Character is created successfully!");
                break;
            case "2":
                player.explorer().forEach(System.out::println);
                break;
            case "3":
            case "4":
        }
    }
}
