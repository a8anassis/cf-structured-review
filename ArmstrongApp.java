package testbed.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Βρίσκει αν το άθροισμα των δυνάμεων
 * κάθε ψηφίου εις τη (πλήθος ψηφίων)
 * είναι ίσο με τον ίδιο τον αριθμό.
 */
public class ArmstrongApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int inputNumber = 0;
        int digitsCount = 0;
        int num = 0;
        int sum = 0;
        int digit = 0;
        boolean isArmstrong = false;
        List<Integer> digits = new ArrayList<>();

        System.out.println("Please insert a number (int)");
        inputNumber = in.nextInt();

        num = inputNumber;
        // Digits count
        do {
            digitsCount++;
            if (digitsCount > 5) break;
            digit = num % 10;
            digits.add(digit);
            num = num / 10;
        } while (num != 0);

        for (int item : digits) {
            sum += Math.pow(item, digitsCount);
        }

        isArmstrong = (sum == inputNumber);

        System.out.println("Number is: " + inputNumber);
        System.out.println("Sum is: " + sum);
        System.out.printf("%d is Armstrong: %s", inputNumber,  (isArmstrong) ? "YES" : "NO");
    }
}