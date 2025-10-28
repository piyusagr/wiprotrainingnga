import java.util.*;

public class stringbuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        System.out.println(sb.toString());
        System.out.println("Length: " + sb.length());
        System.out.println("Reversed: " + sb.reverse().toString());
        System.out.println("Character at index 2: " + sb.charAt(2));
        System.out.println("replace index 0-4 with 'Hi': " + sb.replace(0, 5, "Hi"));
        System.out.println("insert Java at index 3: " + sb.insert(3, " Java"));
        System.out.println("delete index 0-2: " + sb.delete(0, 3));
        
    }
}