class Student{
    private String name;
    private String age;
    private String roll;

    private String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setAge(String age){
        this.age=age;
    }

    private String getAge(){
        return age;
    }

    public void setRoll(String roll){
        this.roll=roll;
    }   
    private String getRoll(){
        return roll;
    }
    public void display(){
        System.out.println("Name: "+getName());
        System.out.println("Age: "+getAge());
        System.out.println("Roll: "+getRoll());
    }
}
public class studentgettersetter{
    public static void main(String[] agrs){
        Student s1=new Student();
        s1.setName("John");
        s1.setAge("20");
        s1.setRoll("101");
        s1.display();

    }
}