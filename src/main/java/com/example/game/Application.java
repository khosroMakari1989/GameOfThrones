package com.example.game;

import com.example.game.utils.FileUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class Application {

    private static final String MENU_CHOICES = "123456";
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private final Player player = new Player();

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("BathPath is: " + FileUtil.BASE_PATH);
        Application application = new Application();
        System.out.println("please select a number:");
        System.out.println("1- Create a character");
        System.out.println("2- Explorer Characters");
        System.out.println("3- Fight");
        System.out.println("4- Pause");
        System.out.println("5- Resume");
        System.out.println("6- Exit");
        boolean exit = false;
        while (!exit) {
            System.out.println("#######################################################################################");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            exit = application.handleSelectMenu(input, scanner);

        }
    }

    private boolean handleSelectMenu(String choice, Scanner scanner) throws IOException, URISyntaxException {
        if (choice.isEmpty() || choice.length() != 1 || !MENU_CHOICES.contains(choice)) {
            System.out.println("Wrong input.");
            return true;
        }
        switch (choice) {
            case "1":
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter kick weight(between 1 to 3): ");
                int kickWeight = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter punch weight(between 1 to 3): ");
                int punchWeight = scanner.nextInt();
                scanner.nextLine();
                player.createCharacter(new GameCharacter(name, kickWeight, punchWeight, 0));
                System.out.println("Game Character is created successfully!");
                break;
            case "2":
                player.explorer().forEach(System.out::println);
                break;
            case "3":
                List<String> fighters = player.explorer();
                for (int i = 0; i < fighters.size(); i++) {
                    System.out.println(i + 1 + "- " + fighters.get(i).substring(0, fighters.get(i).indexOf(',')));
                }
                System.out.println("#######################################################################################");
                System.out.println("Choose two of your favorite characters by their number and start the fight!");
                int fighterNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Now select the rival!");
                int rivalNumber = scanner.nextInt();
                scanner.nextLine();
                String[] fighterInfo = fighters.get(fighterNumber - 1).split(", ");
                String[] rivalInfo = fighters.get(rivalNumber - 1).split(", ");
                player.fight(extractGameCharacterInfo(fighterInfo), extractGameCharacterInfo(rivalInfo));

            //player.fight();
            case "4":
                break;
            case "5":
                break;
            case "6":
                return true;

        }
        return false;
    }

    private GameCharacter extractGameCharacterInfo(String[] info) {
        return new GameCharacter(info[0], Integer.valueOf(info[1]), Integer.valueOf(info[2]), Integer.valueOf(info[3]));
    }
}
