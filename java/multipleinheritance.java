interface phone{
    default void makeCall(){
        System.out.println("making call");
    }
}

interface camera{
    default void takeSnap(){
        System.out.println("taking snap");
    }
}

public class multipleinheritance implements phone, camera{
    public static void main(String[] args){
        multipleinheritance mi = new multipleinheritance();
        mi.makeCall();
        mi.takeSnap();
    }
}