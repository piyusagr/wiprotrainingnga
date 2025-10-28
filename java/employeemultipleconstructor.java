public class employeemultipleconstructor{
    String name;
    String department;
    double salary=30000.59;

    employeemultipleconstructor(String name, String department, double salary){
        this.name=name;
        this.department=department;
        this.salary=salary;
    }

    employeemultipleconstructor(String name, String Department){
        this.name=name;
        this.department=Department;
    }

    void display(){
        System.out.println(name);
        System.out.println(department);
        System.out.println(salary);
    }
    public static void main(String[] agrs){
        employeemultipleconstructor emc= new employeemultipleconstructor("piyush","it",70000.09);
        employeemultipleconstructor emc1= new employeemultipleconstructor("abccc","sales");
        emc.display();
        emc1.display();
    }
}