
class Animal {
    void makeSound() {
        System.out.println("Animal sound");
    }
}
class Dog extends Animal {
    void makeSound() {
        super.makeSound();
        System.out.println("Woof");
    }
    void fetch() {
        System.out.println("Fetching the ball!");
    }
}

public class upcastdowncast {
    public static void main(String[] args) {
        // Upcasting (implicit)
        Animal myAnimal = new Dog(); 
        myAnimal.makeSound(); 

        // Downcasting (explicit)
            Dog myDog = (Dog) myAnimal; 
            myDog.fetch(); 
    }
}
