import java.util.HashSet;
public class hashset {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();

        // Adding elements to the HashSet
        set.add(null);
        set.add("Banana");
        set.add("Apple");
        set.add("Mango");

        // Displaying the HashSet
        System.out.println("HashSet: " + set);

        // Checking if an element exists
        if (set.contains("Banana")) {
            System.out.println("Banana is in the HashSet.");
        }

        // Removing an element
        set.remove("Apple");
        System.out.println("HashSet after removing Apple: " + set);
    }
}
