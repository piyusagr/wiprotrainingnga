class Animal{
    void display(){
        System.out.println("animal");
    }
}

class Domestic extends Animal{
    void displayInfos(){
        System.out.println("domestics");
    }
}

class Dog extends Domestic{
    void displayInfo(){
        System.out.println("dog");
    }
}

public class multilevelinheritance {
    public static void main(String[] args){
        Dog d = new Dog();
        d.display();
        d.displayInfos();
        d.displayInfo();
    }
}
