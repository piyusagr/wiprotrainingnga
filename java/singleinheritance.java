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

public class singleinheritance{
    public static void main(String[] args){
        Domestic a=new Domestic();
        a.display();
        a.displayInfo();
    }
}