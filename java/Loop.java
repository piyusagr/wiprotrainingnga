import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        int a=0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            a+=input.nextInt();
        }
        input.close();
        System.out.println("Total: " + a);
    }
}
