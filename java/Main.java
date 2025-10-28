import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = "";
        if (s.hasNextLine()) {
            line = s.nextLine();
        }
        s.close();

        String[] words = line.split(" ", -1);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            out.append(new StringBuilder(words[i]).reverse());
            if (i < words.length - 1) out.append(' ');
        }

        System.out.println(out.toString());
    }
}
