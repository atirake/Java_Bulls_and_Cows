package bullscows.bullscows;

import java.util.*;


public class Main {

    public static boolean continueGame(int size, int range) {
        if (range > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        } else if (size > range) {
            System.out.println("Error: it's not possible to generate a code with a length of "
                    + size
                    + " with "
                    + range
                    + " unique symbols.");
            return false;
        }
        return true;
    }

    public static void displaySecretCodeRanges(int size, int range) {
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < size; i++) {
            System.out.print("*");
        }
        System.out.print(" (");
        if (range <= 10) {
            if (range == 10) {
                System.out.println("0-9).");
            } else {
                System.out.println(size + ").");
            }
        } else {
            int lastAlphabet = 97 + (range - 10) - 1;
            System.out.print("0-9)");
            System.out.print(", ");
            System.out.print((char)97);
            System.out.print("-");
            System.out.print((char) lastAlphabet);
            System.out.println(").");
        }
    }
    public static List<Character> returnSecretCode(int size, int range) {
        Set<Character> secretCode = new HashSet<>(size);
        while (secretCode.size() < size) {
            if (range <= 10) {
                int temp = Math.toIntExact(Math.round(Math.floor(Math.random() * 10)));
                secretCode.add((char) ( temp + '0'));
            }
            else {
                List<Character> temp = new ArrayList<>(range);
                int lastAlphabet = 97 + (range - 10) - 1;
                for (int i = 0; i <= 9; i++) {
                    temp.add((char) (i + '0'));
                }
                for (int i = 97; i <= lastAlphabet; i++) {
                    temp.add((char)i);
                }
                Collections.shuffle(temp);
                secretCode.add(temp.get(0));
                temp.remove(0);
            }
        }
        displaySecretCodeRanges(size, range);
        List<Character> arr = new ArrayList<>(secretCode);
        System.out.println("Okay, let's start a game!");
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String lenInput = scanner.nextLine();
        if (!lenInput.matches("\\d+")) {
             System.out.println("Error: " + lenInput +" isn't a valid number.");
             System.exit(0);
        }
        int length = Integer.parseInt(lenInput);
        if (length <= 0 ) {
            System.out.println("Error: it's not possible to generate a code with a negative or zero length.");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        String symInput = scanner.nextLine();
        if (!symInput.matches("\\d+")) {
            System.out.println("Error: " + symInput +" isn't a valid number.");
            System.exit(0);
        }
        int symbols = Integer.parseInt(symInput);
        if (continueGame(length, symbols)) {
            new BullsAndCows(length, returnSecretCode(length, symbols)).gamePlay();
        }
    }
}
