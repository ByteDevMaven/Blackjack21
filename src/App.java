import java.util.Scanner;

import Engine.Player;

public class App {
    static Scanner key = new Scanner(System.in);

    static void menu() {
        System.out.print("""
                --------- PLAY ---------
                1. Add Card
                2. New Bet
                3. Finish
                4. Exit
                ------------------------
                Input: """);
    }

    public static void main(String[] args) throws Exception {
        Player newPlayer;
        boolean run = true;
        double balance;
        double bet = 0.0;
        int cards;
        int input;
        int winner;

        System.out.print("Welcome! Please type your balance: $");
        balance = key.nextDouble();
        if (balance < 3500) {
            newPlayer = new Player();
        } else {
            newPlayer = new Player(balance);
        }
        newPlayer.setBet(bet);
        cards = newPlayer.game_start();

        while (run) {
            System.out.println("----- BLACKJACK 21 -----");
            System.out.println("Balance: $" + newPlayer.getBalance());
            System.out.println("Bet: $" + newPlayer.getBet());
            System.out.println("Cards: " + cards);
            menu();
            input = key.nextInt();

            switch (input) {
                case 1:
                    cards = newPlayer.add_card();
                    break;

                case 2:
                    System.out.print("Please type your bet: ");
                    bet = key.nextDouble();
                    if (bet > newPlayer.getBalance()) {
                        System.out.println("Invalid Bet!");
                    } else {
                        newPlayer.setBet(bet);
                    }
                    break;

                case 3:
                    winner = newPlayer.checker();
                    if (winner == 0) {
                        System.out.println("YOU WIN!");
                    } else if (winner == 1) {
                        System.out.println("LOSER!");
                    } else {
                        System.out.println("NO WINNERS");
                    }
                    cards = newPlayer.game_start();
                    break;

                case 4:
                    System.out.println("Thanks for playing!");
                    run = false;
                    break;

                default:
                    System.out.println("Wrong input.");
                    break;
            }
        }

    }
}
