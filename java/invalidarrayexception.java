public class invalidarrayexception {
    public static void main(String[] args) {
        try {
            int arr[] = new int[5];
            arr = new int[]{1, 2, 3, 4, 5};
            arr[7] = 10; 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index is out of bounds");
        }
    }
}
