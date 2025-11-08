public class max3nousingternary {
    
    public static void main(String[] args) {
        int a = 30;
        int b = 20;
        int c = 85;

        int max = (a > b) ? (a > c ? a : c) : (b > c ? b : c);

        System.out.println("The maximum number is: " + max);
    }
}
