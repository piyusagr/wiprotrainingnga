/*
 * Question 2
Multiple Catch Blocks
You are required to catch Arithmetic or number format exceptions if present using multiple catch blocks in the code for finding of 99 with the number entered by user, else print "n is a factor of 99" or "n is not a factor of 99".
Sample Input
5
Output Format
5 is a not a factor of 99
Input
hi
Output
Number Format Exception java.lang.NumberFormatException: For input string: "hi"
 */

package day5;

import java.util.Scanner;

class Q2Multiple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String input = sc.nextLine();  // input can be string or number
            int n = Integer.parseInt(input); // may throw NumberFormatException

            if (99 % n == 0) { // may throw ArithmeticException if n == 0
                System.out.println(n + " is a factor of 99");
            } else {
                System.out.println(n + " is a not a factor of 99");
            }

        } catch (NumberFormatException e) {
            System.err.println(e);
        } catch (ArithmeticException e) {
            System.err.println("Cannot divide by zero.");
        }

        sc.close();
    }
}
