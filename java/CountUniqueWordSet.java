import java.util.*;

public class CountUniqueWordSet {
    public static void main(String[] args) {
        String sentence = "java is a fun and java is powerful";
        String[] words = sentence.split(" ");

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        System.out.println("Number of unique words: " + uniqueWords.size());
        System.out.println("Unique words: " + uniqueWords);
    }
}