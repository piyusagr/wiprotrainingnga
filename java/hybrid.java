class Animal{
    void display(){
        System.out.println("animal");
    }
}
class Domestic extends Animal{
    void displayInfo(){
        System.out.println("domestics");
    }
}
class Wild extends Animal{
    void displayInfo(){
        System.out.println("wild");
    }
}
class Dog extends Domestic{
    void displayDog(){
        System.out.println("dog");
    }
}


class Tiger extends Wild{
    void displayTiger(){
        System.out.println("tiger");
    }
}


public class hybrid {
    public static void main(String[] args) {

        Dog d = new Dog();
        d.display();
        d.displayInfo();
        d.displayDog();

        Tiger t = new Tiger();
        t.display();
        t.displayInfo();
        t.displayTiger();
    }
}
