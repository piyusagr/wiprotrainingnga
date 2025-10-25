public class countoddeven{
    public static void main(String[] args) {
        int evenCount = 0;
        int oddCount = 0;

        int[] num={2,5,8,9,12,15,20};
        for (int i = 0;i<num.length; i++) {
            if (num[i] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println("Count of even numbers  " + evenCount);
        System.out.println("Count of odd numbers " + oddCount);
    }
}