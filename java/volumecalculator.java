public class volumecalculator{
    int volume(int l){
        return l*l*l;
    }
    double volume(double r){
        return (4.0/3)*3.14*r*r*r;
    }
    double volume(double r, double h){
        return 3.14*r*r*h;
    }
    public static void main(String[] agrs){
        volumecalculator v= new volumecalculator();
        System.out.println("cube: "+v.volume(3));
        System.out.println("SphereL "+v.volume(4.14));
        System.out.println("Cylinder: "+v.volume(4.14,5.15));
    }
}