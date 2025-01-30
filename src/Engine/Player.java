package Engine;

import java.util.Random;

public class Player {
    private double balance;
    private double bet;
    private int[] cards;
    private Random random = new Random();

    public Player() {
        balance = 3500;
    }

    public Player(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    private int sum_deck(int[] cards) {
        int sum = 0;

        for (int i = 0; i < cards.length; i++) {
            sum += cards[i];
        }

        return sum;
    }

    public int game_start() {
        cards = new int[2];

        cards[0] = random.nextInt(1, 11);
        cards[1] = random.nextInt(1, 11);

        return sum_deck(cards);
    }

    public int add_card() {
        int[] temp;
        temp = cards;
        cards = new int[temp.length + 1];

        System.arraycopy(temp, 0, cards, 0, temp.length);
        cards[temp.length] = random.nextInt(1, 11);

        return sum_deck(cards);
    }

    public int checker() {
        int cards_sum = sum_deck(cards);
        cards = null;
        int cpu_points = random.nextInt(1, 11) + random.nextInt(1, 11);

        while (true) {
            if (cpu_points < 17) {
                cpu_points += random.nextInt(1, 11);
            }
            else {
                break;
            }
        }
        System.out.println("CPU: " + cpu_points);

        if (cards_sum > cpu_points && cards_sum < 22) {
            setBalance(getBalance() + getBet());
            return 0;
        } else if (cpu_points > cards_sum && cpu_points < 22 || cards_sum > 21 && cpu_points < 22) {
            setBalance(getBalance() - getBet());
            return 1;
        } else {
            return 2;
        }
    }
}
