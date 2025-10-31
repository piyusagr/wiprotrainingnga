package day5;
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