class Person{
    String name;
    void display(){
        System.out.println("Name: "+name);
    }
}

class Student extends Person{
    int roll;
    void display(){
        super.display();
        System.out.println("Roll: "+roll);
    }
}

public class methodoveride {
    public static void main(String[] args) {
        Student s=new Student();
        s.name="Rahul";
        s.roll=10;
        s.display();
    }
}
