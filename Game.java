import java.util.Scanner;

public class Game {

    public static Scanner kb = new Scanner(System.in);
    public static Scanner kb2 = new Scanner(System.in);

    public static void timeDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }

    public static void fight(String name, char diff) {

        Combat_Interactions move = new Combat_Interactions();
        Player p1 = new Player(3,22,7);

        // Changes difficulty and Enemy's name.
        move.changeDiff(diff);
        move.changeName(name);

        System.out.print("\nPlayer vs "+ move.p2.getName() +"!\n");
        timeDelay(800);
        do {
            char answer;

            // Shows both the player's and enemy's health, and the player's weapon durability.
            System.out.println("                             |   Durability:\n" +
            " --- You have " + move.p1.getHealth() + " health." +
            "      |    - Sword "+ move.p1.getSwordHealth() +"/"+ p1.getSwordHealth() +"\n" +
            "                             |    - Shield "+ move.p1.getShieldHealth() +"/"+ p1.getShieldHealth() +
            "\n --- "+ move.p2.getName() +" has " + move.p2.getHealth() + " health.\n");
            System.out.print("(A)ttack, (B)lock or (P)arry: ");
                answer = kb.next().toUpperCase().charAt(0);

            // If user chooses Attack when sword is broken
            if (answer == 'A' && move.p1.getSwordHealth() <= 0) {
                System.out.println("Your sword is broken, you can't attack!");
            }
            // If user chooses Block when shield is broken
            else if (answer == 'B' && move.p1.getShieldHealth() <= 0) {
                System.out.println("Your shield is broken, you can't block!");
            }
            // If user chooses Parry when sword is broken
            else if (answer == 'P' && move.p1.getSwordHealth() <= 0) {
                System.out.println("Your sword is broken, you can't parry!");
            }
            // If user chooses Attack
            else if (answer == 'A') {
                move.userAttack();
                timeDelay(800);
            }
            // If user chooses Block
            else if (answer == 'B') {
                move.userBlock();
                timeDelay(800);
            }
            // If user chooses Parry
            else if (answer == 'P') {
                move.userParry();
                timeDelay(800);
            }
            else {
                System.out.println("Enter a valid option.");
            }

            if (move.p2.getSwordHealth() <= 0) {
                System.out.println(" " + move.p2.getName() + "'s sword is broken.");
            }
            if (move.p2.getShieldHealth() <= 0) {
                System.out.println(" " + move.p2.getName() + "'s shield is broken.");
            }

            // Breaks out of loop if either player's sword AND shield are broken.
            if (move.p2.getShieldHealth() <= 0 && move.p2.getSwordHealth() <= 0) {
                break;
            }
            if (move.p1.getShieldHealth() <= 0 && move.p1.getSwordHealth() <= 0) {
                break;
            }

        } while (move.p1.getHealth() > 0 && move.p2.getHealth() > 0);

        // Game draws if both players reach 0 health or if both players sword and shield are broken.
        if (move.p2.getHealth() <= 0 && move.p1.getHealth() <= 0 ||
            move.p1.getSwordHealth() <=0 && move.p1.getShieldHealth() <=0 &&
            move.p2.getShieldHealth() <=0 && move.p2.getSwordHealth() <= 0) {
            System.out.println("\n   -= Draw =-");
            timeDelay(2000);
        }
        // Player loses if their health reaches 0
        else if (move.p1.getHealth() <= 0) {
            System.out.println("\n   -= You lose =-");
            timeDelay(2000);
        }
        // Player wins if the enemy reaches 0 health or if their sword and shield are broken.
        else if (move.p2.getHealth() <= 0 || move.p2.getShieldHealth() <= 0 && move.p2.getSwordHealth() <= 0) {
            System.out.println("\n   -= You win =-");
            timeDelay(2000);
        }
        // Player loses if their sword and shield are both broken.
        else {
            System.out.println("\nBoth your sword and shield are broken!\n\n -= You lose =-");
            timeDelay(2000);
        }
    }

    public static void information() {

        int selectionRules;

        System.out.print("\nThis game works on a round system," +
                " where both players choose a move at the same time.\n\n  It relies on 3 main systems:\n");
        do {
            System.out.print("\n    (1). Win/Lose conditions\n" +
                            "    (2). Player damage interactions\n" +
                            "    (3). Weapon durability interactions\n" +
                            "  \n    (R)eturn to main menu.\n\n  Selection: ");
                selectionRules = kb2.next().toUpperCase().charAt(0);

            if (selectionRules == '1') {
                System.out.print("\nWin/Lose conditions:" +
                        "\n  -=If both a player's sword and shield break, they lose the game.\n" +
                        "  -=If a player's health reaches 0, they lose the game.\n");
                timeDelay(500);
            }
            else if (selectionRules == '2') {
                System.out.print("\nPlayer damage interactions." +
                        "\n  -When both players attack, they will take 1 damage each.\n" +
                        "  -When both players block, neither player will take damage.\n" +
                        "  -An attacking player and a blocking player will result" +
                        " in neither taking damage.\n" +
                        "  -A parrying player and an attacking player will result" +
                        " in the attacking player taking damage.\n" +
                        "  -A blocking player and a parrying player will result" +
                        " in the parrying player taking damage.\n");
                timeDelay(500);
            }
            else if (selectionRules == '3') {
                System.out.print("\nWeapon durability interactions:" +
                        "\n  -If the shield or sword breaks, it becomes unusable.\n" +
                        "  ~When an attack hits a player, the sword loses 1 durability.\n" +
                        "  ~When an attack hits a shield, the sword loses 3 durability.\n" +
                        "  ~When an attack gets parried, the sword loses 1 durability.\n" +
                        "  ~When an attack parries another attack, the sword loses 5 durability.\n" +
                        "  ~When an attack parries the shield, the sword loses 3 durability.\n" +
                        "  ~When the shield blocks an attack, it loses 2 durability.\n" +
                        "  ~When the shield blocks a parry, it loses 1 durability.\n" +
                        "  ~When both shields block, both lose 1 durability.\n");
                timeDelay(500);
            }
            else if (selectionRules != 'R') {
                System.out.println("\nEnter a valid option.");
            }
        } while (selectionRules != 'R');
    }

    public static char changeDifficulty(int diff) {

        char newDiff;

        System.out.print("\nDifficulty:\n\n" +
                "            Enemy: Health| Sword Durability| Shield Durability|\n" +
                "   (N)ormal             3|   -   -   -   18|   -    -    -   5|\n" +
                "   (H)ard               5|   -   -   -   22|   -    -    -   7|\n" +
                "   (I)mpossible         7|   -   -   -   26|   -    -    -   9|\n\n" +
                " Selection: ");
            newDiff = kb2.next().toUpperCase().charAt(0);

        if (newDiff == diff) {
            System.out.println("\nDifficulty not changed.");
            timeDelay(800);
        }
        else if (newDiff == 'N' || newDiff == 'H' || newDiff == 'I') {
            System.out.print("\nDifficulty set to ");
                switch (newDiff) {
                    case 'N': System.out.println("Normal.");
                        break;
                    case 'H': System.out.println("Hard.");
                        break;
                    case 'I': System.out.println("Impossible.");
                        break;
                }
            timeDelay(800);
        }
        else {
            System.out.println("\nInvalid option - Difficulty not updated.");
            timeDelay(800);
        }

        return newDiff;
    }

    public static String changeName() {

        String newName;

        System.out.print("\nEnter a new name for Enemy: ");
            newName = kb2.nextLine().replace(' ', '_');

        return newName;
    }

    public static void quitGame() {
        System.out.print("\nQuitting game");
        timeDelay(500);
        System.out.print(".");
        timeDelay(500);
        System.out.print(".");
        timeDelay(500);
        System.out.print(".");
    }

    public static void main(String[] args) {

        String name = "Watson";
        char selection, selectionOptions, diff = 'H';

        do {
            System.out.print("  Welcome to Dungeon_Crawler!\n\n" +
                    "     (S)tart - Start game\n" +
                    "     (I)nfo - Game details\n" +
                    "     (O)ptions - Change settings\n\n" +
                    "     (Q)uit - Exit game.\n\n" +
                    "  Selection: ");
                selection = kb.next().toUpperCase().charAt(0);

            if (selection == 'S') {
                fight(name, diff);
                System.out.println();
            }
            else if (selection == 'I') {
                information();
                System.out.println();
            }
            else if (selection == 'O') {
                do {
                    System.out.print("\nSettings:\n\n" +
                            "    (D)ifficulty\n" +
                            "    (C)hange Enemy name\n\n" +
                            "    (R)eturn to main menu\n\n" +
                            "  Selection: ");
                        selectionOptions = kb.next().toUpperCase().charAt(0);

                    if (selectionOptions == 'D') {
                        diff = changeDifficulty(diff);
                    }
                    else if (selectionOptions == 'C') {
                        name = changeName();
                    }
                    else if (selectionOptions != 'R') {
                        System.out.println("\nEnter a valid option.");
                    }

                } while (selectionOptions != 'R');
                System.out.println();
            }
            else if (selection == 'Q') {
                quitGame();
            }
            else {
                System.out.println("\nEnter a valid option.\n");
            }

        } while (selection != 'Q');
    }

}
