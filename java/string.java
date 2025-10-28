 public class string{
    public static void main(String[] args) {
        String str = "Hello, World!";
        int length = str.length();
        System.out.println("Length of the string: " + length);
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Substring (0-5): " + str.substring(0, 5));
        System.out.println("Character at index 7: " + str.charAt(7));
        System.out.println("Index of 'World': " + str.indexOf("World"));
        System.out.println("concatenated string: " + str.concat(" Welcome to Java."));
        System.out.println("search 'o' in string: " + str.contains("o"));
        
    }   
 }