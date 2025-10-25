import java.util.Scanner;

public class reverse {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        input.close();
        System.out.println("Reversed Number: " + reversed);
    }
}