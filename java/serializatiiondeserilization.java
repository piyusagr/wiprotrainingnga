/*
 * Q3: Write a Java program that demonstrates the Serialization and Deserialization process. The program should:
	- Define a Person class with two fields: name (String) and age (int).
	- Serialize an object of the Person class to a file (person.ser).
	- Deserialize the object from the file (person.ser) and print its data (name and age) to the console.
 */
import java.io.*;
class person implements Serializable{
    String name;
    int age;
}
public class serializatiiondeserilization {
    public static void main(String[] args) {
        person p1 = new person();
        p1.name = "John Doe";
        p1.age = 30;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            oos.writeObject(p1);
            System.out.println("Serialization successful!");
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
            person p2 = (person) ois.readObject();
            System.out.println("Deserialization successful!");
            System.out.println("Name: " + p2.name);
            System.out.println("Age: " + p2.age);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
    }
}
