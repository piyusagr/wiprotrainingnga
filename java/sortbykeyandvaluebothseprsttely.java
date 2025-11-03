import java.util.*;
public class sortbykeyandvaluebothseprsttely {
    
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();
        map.put(3,"C");
        map.put(1,"D");
        map.put(2,"B");
        map.put(5,"E");

        // Sorting by keys
        Map<Integer, String> sortedByKey = new TreeMap<>(map);
        System.out.println("Sorted by keys: " + sortedByKey);

        // Sorting by values
        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        System.out.println("Sorted by values: " + entryList);
    }}
