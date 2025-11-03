import java.util.HashMap;


public class countwordtimesinstring {
 
    public static void main(String[] args) {
        String str = "This is a test string. This string contains the word 'test'.";
        
        HashMap<String, Integer> wordCount = new HashMap<>();
        String[] words = str.split("\\W+");
        for (String word : words) {
            word = word.toLowerCase();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word counts: " + wordCount);
    }
}

