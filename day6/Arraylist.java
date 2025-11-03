// Question 1
// Sort the Collection
// students are provided with a challenge to sort the given arraylist in the lesser lines of code. Student with minimum lines will win , try to write a code for it in less lines and win

// Input

// An arraylist of integers

// Output

// Sorted list

// Sample input

// 1
// 4
// 5
// 0

// output

// List before sort: [1, 4, 5, 0]
// List after sort: [0, 1, 4, 5]

// code:
package day6;
import java.util.*;
public class Arraylist {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) list.add(sc.nextInt());
        System.out.println("List before sort: " + list);
        Collections.sort(list);
        System.out.println("List after sort: " + list);
        sc.close();
    }
}