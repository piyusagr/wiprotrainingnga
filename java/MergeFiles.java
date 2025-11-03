import java.io.*;

public class MergeFiles {
    public static void main(String[] args) {
        try {
            FileWriter f1 = new FileWriter("file1.txt");
            f1.write("Line 1 from file1\n");
            f1.write("Line 2 from file1\n");
            f1.write("Line 3 from file1\n");
            f1.close();

            FileWriter f2 = new FileWriter("file2.txt");
            f2.write("Line 1 from file2\n");
            f2.write("Line 2 from file2\n");
            f2.close();
            
            BufferedReader reader1 = new BufferedReader(new FileReader("file1.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("file2.txt"));
            FileWriter merged = new FileWriter("merged.txt");

            String line1, line2;
            while ((line1 = reader1.readLine()) != null | (line2 = reader2.readLine()) != null) {
                if (line1 != null) merged.write(line1 + "\n");
                if (line2 != null) merged.write(line2 + "\n");
            }

            reader1.close();
            reader2.close();
            merged.close();

            System.out.println("Merge completed successfully!");
        } 
        catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
