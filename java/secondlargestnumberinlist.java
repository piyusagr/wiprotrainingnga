import java.util.*;
public class secondlargestnumberinlist {
    public static void main(String[] agrs){
        List<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(30);
        list.add(20);
        Collections.sort(list);
        System.out.println("Second largest number is: " + list.get(list.size()-2));   
    }
}
