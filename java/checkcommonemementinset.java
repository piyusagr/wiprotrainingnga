import java.util.HashSet;
public class checkcommonemementinset {
 
    public static void main(String[] args) {
        int arr1[]={2,3,4};
        int arr2[]={5,6,7};
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : arr1)
            set1.add(i);
        for (int i : arr2)
            set2.add(i);
        set1.retainAll(set2);
        if(set1.isEmpty()){
            System.out.println("True");
        }else{System.out.println("False");}
    }
}
