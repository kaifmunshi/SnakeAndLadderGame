import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadderGame {

    private static final int WIN_POSITION = 100;
    private static Map<Integer, Integer> snakes = new HashMap<>();
    private static Map<Integer, Integer> ladders = new HashMap<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("Welcome to Snake and Ladder Game!");
        while (true) {
            System.out.println("Choose mode: 1. Player 2. Admin 3. Exit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                playGame();
            } else if (choice == 2) {
                adminMode();
            } else {
                System.out.println("Exiting game.");
                break;
            }
        }
    }

    private static void initializeBoard() {
        // Pre-defined snakes and ladders
        snakes.put(14, 7);
        snakes.put(31, 26);
        snakes.put(77, 35);
        snakes.put(99, 63);
        
        ladders.put(3, 22);
        ladders.put(8, 29);
        ladders.put(28, 84);
        ladders.put(58, 77);
    }

    private static void playGame() {
        int position = 1;
        while (position < WIN_POSITION) {
            System.out.println("Press any key to roll the dice.");
            scanner.nextLine();  // Wait for user input
            int diceValue = rollDice();
            position = movePlayer(position, diceValue);
            if (position >= WIN_POSITION) {
                System.out.println("Congratulations! You have won the game!");
                break;
            }
        }
    }

    private static int rollDice() {
        return random.nextInt(6) + 1;
    }

    private static int movePlayer(int position, int diceValue) {
        position += diceValue;
        System.out.println("Dice rolled: " + diceValue + ". You moved to: " + position);
        if (snakes.containsKey(position)) {
            position = snakes.get(position);
            System.out.println("Oops! You got bitten by a snake. Move down to: " + position);
        } else if (ladders.containsKey(position)) {
            position = ladders.get(position);
            System.out.println("Great! You climbed a ladder. Move up to: " + position);
        }
        return position;
    }

    private static void adminMode() {
        System.out.println("Admin Mode:");
        while (true) {
            System.out.println("Choose option: 1. Add/Update/Delete Snakes and Ladders 2. Play as Admin 3. Exit Admin Mode");
            int choice = scanner.nextInt();
            if (choice == 1) {
                modifyBoard();
            } else if (choice == 2) {
                playGame();
            } else {
                System.out.println("Exiting Admin Mode.");
                break;
            }
        }
    }

    private static void modifyBoard() {
        System.out.println("Choose option: 1. Add/Update Snake 2. Add/Update Ladder 3. Delete Snake 4. Delete Ladder");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Enter snake start and end positions:");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            snakes.put(start, end);
            System.out.println("Snake updated. Start: " + start + ", End: " + end);
        } else if (choice == 2) {
            System.out.println("Enter ladder start and end positions:");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            ladders.put(start, end);
            System.out.println("Ladder updated. Start: " + start + ", End: " + end);
        } else if (choice == 3) {
            System.out.println("Enter snake start position to delete:");
            int start = scanner.nextInt();
            snakes.remove(start);
            System.out.println("Snake deleted.");
        } else if (choice == 4) {
            System.out.println("Enter ladder start position to delete:");
            int start = scanner.nextInt();
            ladders.remove(start);
            System.out.println("Ladder deleted.");
        }
    }
}
