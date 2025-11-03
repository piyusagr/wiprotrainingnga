import java.io.*;
public class file {
    public static void main(String[] args) throws IOException {
        File files = new File("./input.txt");
        files.createNewFile();
        FileWriter fw = new FileWriter(files);
        fw.write("jVA IS OOPS PROGRAMING LANGUGAGE");
        fw.close();
        FileReader fr = new FileReader(files);   
        int ch;
        System.out.print("Input received: ");
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch); // convert int to char
        }
        fr.close();
    }    
}
