public class capitalize1stletter {
    public static void main(String[] args) {
        String str = "hello world";
        String capitalizedStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        System.out.println(capitalizedStr);
    }
}