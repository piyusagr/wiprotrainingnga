public class armstrongnumberfrom1to10000 {
    public static void main(String[] args) {
        for (int i = 1; i <= 9999; i++) {
            int n = i;
            int digits = String.valueOf(i).length(); 
            int sum = 0;

            while (n != 0) {
                int digit = n % 10;
                sum += Math.pow(digit, digits); 
                n /= 10;
            }

            if (sum == i) {
                System.out.println(i);
            }
        }
    }
}
