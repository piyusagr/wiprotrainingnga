import java.util.*;
 class listt {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(54);
        list.add(44);

        list.remove(1);
       System.out.println(list.get(1)); 
        System.out.println(list.size());
        list.set(0,66 );
        System.out.println(list.contains(66));
        System.out.println(list.indexOf(66));
        list.clear();
        System.out.println(list);
    }
    
}