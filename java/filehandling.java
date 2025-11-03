import java.io.*;
public class filehandling {
    
    public static void main(String[] args) {
    
        File file = new File("essay.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try{    
            FileWriter essayWriter= new FileWriter(file);
            essayWriter.write("Java is a popular programming language.\n");
            essayWriter.write("It is object-oriented and platform-independent.\n");
            essayWriter.write("Developers use Java to build robust applications.\n");
            essayWriter.close();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            int wordCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                String[] words = line.trim().split("\\s+");
                wordCount += words.length;
            }
            reader.close();

            FileWriter reportWriter = new FileWriter("report.txt");
            reportWriter.write("Total number of lines: " + lineCount + "\n");
            reportWriter.write("Total number of words: " + wordCount + "\n");
            reportWriter.close();

            System.out.println("Report generated successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}