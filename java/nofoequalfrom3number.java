public class nofoequalfrom3number {

    public static void main(String[] args) {
        int a = 20;
        int b = 20;
        int c = 20;
        if (a != b && b != c && a != c) {
            System.out.println("All numbers are different");
        } else if (a == b && b == c) {
            System.out.println("All same");
        } else if (a == b || b == c || a == c) {
            System.out.println("Two numbers are equal");
        }
    }
}