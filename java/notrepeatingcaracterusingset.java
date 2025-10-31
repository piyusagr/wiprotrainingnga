public class notrepeatingcaracterusingset {
    public static void main(String[] args) {
        String input = "swiss";
        StringBuilder result = new StringBuilder();
        java.util.Set<Character> charSet = new java.util.HashSet<>();

        for (char c : input.toCharArray()) {
            if(charSet.contains(c)){
                // charSet.remove(c);
                result.deleteCharAt(result.lastIndexOf(String.valueOf(c)));
            }
            else if (!charSet.contains(c)) {
                charSet.add(c);
                result.append(c);
            }
        }

        System.out.println("String 1st non repeating character: " + result.toString().charAt(0));
    }
}
