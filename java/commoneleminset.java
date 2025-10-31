import java.util.HashSet;
public class commoneleminset {
    public static void main(String[] args) {
        int a[]={1,2,5};
        int b[]={4,5,6};
        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        for (int num : a) {
            setA.add(num);
        }
        for (int num : b) {
            setB.add(num);
        }

        // Find common elements
        setA.retainAll(setB);
        System.out.println("Common elements: " + setA);
    }
}
