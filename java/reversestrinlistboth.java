import java.util.*;
public class reversestrinlistboth {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        for (int i = 0; i < list.size(); i++) {
            String reversed = new StringBuilder(list.get(i)).reverse().toString();
            list.set(i, reversed);
        }

        Collections.reverse(list);
        System.out.println("Reversed strings: " + list);
    }
}
