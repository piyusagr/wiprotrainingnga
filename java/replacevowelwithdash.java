import java.util.*;

public class replacevowelwithdash {
    public static void main(String[] args) {
        String str="Beautiful Day";
        String result = str.replaceAll("[AEIOUaeiou]", "-");
        System.out.println(result);
    }
}