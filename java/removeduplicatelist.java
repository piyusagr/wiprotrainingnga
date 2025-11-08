// remove duplicate while presiving insertion order

import java.util.*;
public class removeduplicatelist {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 2, 5, 2, 3, 4, 1, 5);
        System.out.println("Original list: " + list);
        List<Integer> result = removeDuplicates(list);
        System.out.println("List after removing duplicates: " + result);
    }

   public static List<Integer> removeDuplicates(List<Integer> list) {
       Set<Integer> set = new LinkedHashSet<>(list);
       return new ArrayList<>(set);
   }
}

