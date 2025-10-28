// factorial

import java.util.Scanner;

public class factorialfunction{
    static void factorial(int n){
        int prod=1;
        for(int i=n;i>=1;i--){
            prod*=i;
        }
        System.out.println(prod);
    }
    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        factorial(sc.nextInt());
        sc.close();
    }
}