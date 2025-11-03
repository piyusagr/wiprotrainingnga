import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class bufferwriterreader {
    
    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter("file.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("jVA IS OOPS PROGRAMING LANGUGAGE");
        bw.newLine();
        bw.append("my name is piyush");
        bw.close();

        FileReader fr = new FileReader("file.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Output: " + line);
        }
        br.close();

        //rename
        File oldFile = new File("file_moves.txt");
        File newFile = new File("file.txt");
        if (oldFile.renameTo(newFile)) {
            System.out.println("File renamed successfully to " + newFile.getName());
        } else {
            System.out.println("Failed to rename file.");
        }
        
        // copy
        Files.copy(
            Paths.get("file_move.txt"),       
            Paths.get("file_copy.txt"),    
            StandardCopyOption.REPLACE_EXISTING 
        );  
        
        //move
        Files.move(
            Paths.get("file_copy.txt"),       
            Paths.get("../file_move.txt"),    
            StandardCopyOption.REPLACE_EXISTING 
        ); 
    }
}
