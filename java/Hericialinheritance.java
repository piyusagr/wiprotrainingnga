class Animal{
    void display(){
        System.out.println("animal");
    }
}

class Domestics extends Animal{
    void displayInfo(){
        System.out.println("domestics");
    }
}

class Wild extends Animal{
    void displayInfo(){
        System.out.println("wild");
    }
}
public class Hericialinheritance{
	public static void main(String[] args){
		Wild a = new Wild();
		a.display();
		a.displayInfo();
	}
}