import java.util.Arrays;

public class secondlargest {
    public static void main(String[] args) {
        int[] arr = {10};

        Arrays.sort(arr);
        int n = arr.length;
        if (n<2) {
            System.out.println("There is no second largest element.");
        } else {
            System.out.println("The second largest element is: " + arr[n-2]);
        }
    }
}