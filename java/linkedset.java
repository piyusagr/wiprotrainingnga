import java.util.LinkedHashSet;
public class linkedset {

    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>();

        // Adding elements to the LinkedHashSet
        set.add(null);
        set.add("null");
        set.add("Orange");
        set.add("Mango");

        // Displaying the LinkedHashSet
        System.out.println("LinkedHashSet: " + set);

        // Checking if an element exists
        if (set.contains("Banana")) {
            System.out.println("Banana is in the LinkedHashSet.");
        }

        // Removing an element
        set.remove("Apple");
        System.out.println("LinkedHashSet after removing Apple: " + set);
    }
}
