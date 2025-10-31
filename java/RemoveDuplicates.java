import java.util.*;
public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();
        System.out.println("Enter the elements:");
        for(int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println("Original list: " + list);
        List<Integer> result = removeDuplicates(list);
        System.out.println("List after removing duplicates: " + result);
        sc.close();
    }
    
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for(Integer num : list) {
            if(!result.contains(num)) {
                result.add(num);
            }
        }
        Collections.sort(result);
        return result;
    }
}