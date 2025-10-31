public class array{
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Sum of array elements: " + sum);
    }
}

// public class multidimensionalusingscaanner {
//     public static void main(String[] args) {
//         java.util.Scanner scanner = new java.util.Scanner(System.in);
//         System.out.print("Enter number of rows: ");
//         int rows = scanner.nextInt();
//         System.out.print("Enter number of columns: ");
//         int cols = scanner.nextInt();

//         int[][] matrix = new int[rows][cols];

//         System.out.println("Enter elements of the matrix:");
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 matrix[i][j] = scanner.nextInt();
//             }
//         }

//         System.out.println("The matrix is:");
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 System.out.print(matrix[i][j] + " ");
//             }
//             System.out.println();
//         }
//         scanner.close();
//     }
// }