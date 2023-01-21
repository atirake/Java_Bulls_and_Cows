package bullscows.bullscows;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BullsAndCows {
    int length;
    List<Character> code;


    public BullsAndCows(int length, List<Character> code) {
        this.length = length;
        this.code = code;
    }

    protected void gamePlay() {
        Scanner scanner = new Scanner(System.in);
        int bulls = 0;
        int cows = 0;
        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn++ + ":");
            String inputLineStr = scanner.nextLine();
            for (int i = 0; i < inputLineStr.length(); i++) {
                char charInput = inputLineStr.charAt(i);
                if (Objects.equals(charInput, code.get(i))) {
                    bulls += 1;
                } else if (code.contains(charInput)) {
                    cows += 1;
                }
            }
            displayGrades(bulls, cows);
            if (bulls < this.length) {
                bulls = 0;
                cows = 0;
            } else {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    public void displayGrades(int bullsCount, int cowsCount) {
        System.out.print("Grade: ");
        if (bullsCount == 0 && cowsCount == 0) {
            System.out.println("None");
        }
        if (bullsCount >= 1) {
            System.out.print(bullsCount + " bull(s)");
            if (cowsCount >= 1) {
                System.out.print(" and ");
            } else {
                System.out.print(" ");
            }
        }
        if (cowsCount >= 1) {
            System.out.print(cowsCount + " cow(s)");
        }
        System.out.println();
    }
}
