package day5;
import java.util.*;

public class HashMaps {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
                HashMap<Integer, String> map = new HashMap<>();
        for(int i = 1; i <= 4; i++) {
            String name = s.next();
            map.put(i, name);
        }
        
        System.out.println("Mappings of HashMap are : " + map);
        
        int keyToRemove = s.nextInt();
        
        map.remove(keyToRemove);
        
        System.out.println("Mappings after removal are : " + map);
        
        s.close();
    }
}
