// 
import java.util.*;

public class evenoddfunvtion{
    void evenodd(int n){
        if (n%2==0){
            System.out.println("even");
        }
        else{
            System.out.println("odd");
        }
    }
    public static void main(String[] agrs){
        evenoddfunvtion eo=new evenoddfunvtion();
        Scanner sc=new Scanner(System.in);
        eo.evenodd(sc.nextInt());
        sc.close();
    }
}