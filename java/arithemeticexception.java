public class arithemeticexception {
    public static void main(String[] args) {
        try {
            int data = 100 / 0;
            System.out.println(data);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }
}
