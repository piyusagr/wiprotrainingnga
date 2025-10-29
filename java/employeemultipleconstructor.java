public class employeemultipleconstructor{
    String name;
    String department;
    double salary=30000.59;

    employeemultipleconstructor(String name, String department, double salary){
        this.name=name;
        this.department=department;
        this.salary=salary;
        System.out.println("all  parameter by user");
    }

    employeemultipleconstructor(String name, String Department){
        this.name=name;
        this.department=Department;
        System.out.println("only name and department provided by user");
    }

    void display(){
        System.out.println(name);
        System.out.println(department);
        System.out.println(salary);
    }
    public static void main(String[] agrs){
        employeemultipleconstructor emc= new employeemultipleconstructor("piyush","it",70000.09);
        emc.display();
        employeemultipleconstructor emc1= new employeemultipleconstructor("abccc","sales");
        emc1.display();
    }
}