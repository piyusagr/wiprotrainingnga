import java.util.HashMap;
public class firstnonrepeartingcharacterhashmap {
    public static void main(String[] args) {
        String str = "geeksforgeeks";
        int n=str.length();
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<n;i++){
            if(hm.containsKey(str.charAt(i))){
                hm.put(str.charAt(i),hm.get(str.charAt(i))+1);
            }
            else{
                hm.put(str.charAt(i),1);
            }
        }
        for(int i=0;i<n;i++){
            if(hm.get(str.charAt(i))==1){
                System.out.println("First non-repeating character is: "+str.charAt(i));
                break;
            }
        }
    }
}
