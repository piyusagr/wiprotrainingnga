import java.util.*;
class Student {
    private String name;
    private int rollNo;
    private double marks;
    //Getters and Setters for name, rollNo, marks.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public double getMarks() {
        return marks;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    // Default Constructor
    Student() {
        System.out.println("Default Constructor Called");
    }
    // Parameterized Constructor
    Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.setMarks(marks);
    }
    void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Marks: " + marks);
    }
}
public class StudentManagement {
  
   
    void menu() {
        System.out.println("Menu");
        System.out.println("1. Add Student");
        System.out.println("2. Display Student");
        System.out.println("3. Search");
        System.out.println("4. Delete Student");
        System.out.println("5. Modify Student Details");
        System.out.println("0. Exit");
    }

    void modifyMenu() {
        System.out.println("\nModify Menu");
        System.out.println("1. Modify Name");
        System.out.println("2. Modify Roll No");
        System.out.println("3. Modify Marks");
        System.out.println("0. Back to Main Menu");
    }
    public static void main(String[] args) {
        StudentManagement obj = new StudentManagement();
        Student[] students = new Student[3]; 
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            obj.menu();
            System.out.print("Enter your choice: ");   
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    for(int i=0;i<students.length;i++){
                        if(students[i]==null){
                            System.out.print("Enter Name: ");                  
                            String name = sc.next();
                            System.out.print("Enter Roll No: ");
                            int rollNo = sc.nextInt();
                            System.out.print("Enter Marks: ");                  
                            double marks = sc.nextDouble();
                            students[i]= new Student(name,rollNo,marks);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Student Details:");
                    for(Student s : students){
                        if(s!=null){
                            s.displayInfo();
                            System.out.println("-----");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Roll No to search: ");
                    int searchRollNo = sc.nextInt();
                    boolean found = false;
                    for(Student s : students){
                        if(s!=null && s.getRollNo() == searchRollNo){
                            s.displayInfo();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    int deleteRollNo = sc.nextInt();
                    found = false;
                    // Find and delete
                    for(int i = 0; i < students.length; i++) {
                        if(students[i] != null && students[i].getRollNo() == deleteRollNo) {
                            // Move elements up
                            for(int j = i; j < students.length - 1; j++) {
                                students[j] = students[j + 1];
                            }
                            students[students.length - 1] = null;
                            System.out.println("Student deleted successfully!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Roll No to modify: ");
                    int modifyRollNo = sc.nextInt();
                    found = false;
                    for(int i = 0; i < students.length; i++) {
                        if(students[i] != null && students[i].getRollNo() == modifyRollNo) {
                            found = true;
                            int modifyChoice;
                            do {
                                obj.modifyMenu();
                                System.out.print("Enter your choice: ");
                                modifyChoice = sc.nextInt();
                                switch(modifyChoice) {
                                    case 1:
                                        System.out.print("Enter new name: ");
                                        String newName = sc.next();
                                        students[i].setName(newName);
                                        System.out.println("Name updated successfully!");
                                        break;
                                    case 2:
                                        System.out.print("Enter new Roll No: ");
                                        int newRollNo = sc.nextInt();
                                        students[i].setRollNo(newRollNo);
                                        System.out.println("Roll No updated successfully!");
                                        break;
                                    case 3:
                                        System.out.print("Enter new Marks: ");
                                        double newMarks = sc.nextDouble();
                                        students[i].setMarks(newMarks);
                                        System.out.println("Marks updated successfully!");
                                        break;
                                    case 0:
                                        System.out.println("Returning to main menu...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice!");
                                }
                            } while(modifyChoice != 0);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student not found!");
                    }
                    break;
                case 6:
                    sortByMarks(students);
                    System.out.println("Students sorted by marks:");
                    for(Student s : students) {
                        if(s != null) {
                            s.displayInfo();
                            System.out.println("-----");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
    }
    while (choice != 0);
        sc.close();
    }
}