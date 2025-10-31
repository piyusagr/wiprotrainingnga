public class studentclass{
    String name;
    char grade;
    int age;

    studentclass(String name, char grade, int age){
        this.name=name;
        this.age=age;
        this.grade=grade;
    }

    void Display(){
        System.out.println(name);
        System.out.println(grade);
        System.out.println(age);
    }
    public static void main(String[] agrs){
        // studentclass sc=new studentclass();
        studentclass scs=new studentclass("Piyush",'A',34);
        // sc.name="piyush";
        // sc.grade="A";
        // sc.age=22;
        // System.out.println("without constructor");
        // sc.Display();
        System.out.println("with constructor");
        scs.Display();
    }
}