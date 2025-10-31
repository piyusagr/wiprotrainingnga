import java.util.TreeSet;
public class treeset {
    
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(2);
        set.add(8);
        set.add(1);
        set.add(6);

        System.out.println("TreeSet (sorted): " + set);
    }
    
}
