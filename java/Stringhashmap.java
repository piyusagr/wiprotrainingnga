import java.util.HashMap;

public class Stringhashmap {
    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();
        String str="i am a boy and a java programming language.";
        String words[]=str.split(" ");
        for(String word:words){
            if(map.containsKey(word)){
                map.put(word,map.get(word)+1);
            }else{
                map.put(word,1);
            }
        }
        System.out.println(map);
    }
}
