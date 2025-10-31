import java.util.*;
public class merge2list {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, 6, 8));
        
        List<Integer> mergedList = mergeLists(list1, list2);
        
        System.out.println("Merged List: " + mergedList);
    }
    
    public static List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j) && !merged.contains(list1.get(i))) {
                merged.add(list1.get(i));
                i++;
            } else if (!merged.contains(list2.get(j))) {
                merged.add(list2.get(j));
                j++;
            }
            else {
                i++;
                j++;
            }
        }
        
        while (i < list1.size() ) {
             if (!merged.contains(list1.get(i))) {
            merged.add(list1.get(i));
            i++;
             } else {
                 i++;
             }
        }
        
        while (j < list2.size()) {
            if (!merged.contains(list2.get(j))) {
                merged.add(list2.get(j));
            }
            j++;
        }
        Collections.sort(merged);
        return merged;
    }
}
