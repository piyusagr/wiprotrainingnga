// Non-generic Box class
class BoxNonGeneric {
    private Object item;

    public void setItem(Object item) {
        this.item = item;
    }

    public Object getItem() {
        return item;
    }
}

// Generic Box class
class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

public class generic {

    public static void main(String[] args) {
        // Testing non-generic Box
        System.out.println("Testing non-generic Box:");
        BoxNonGeneric box1 = new BoxNonGeneric();
        box1.setItem(100);
        System.out.println("Integer value: " + (Integer) box1.getItem());

        box1.setItem("Hello");
        System.out.println("String value: " + (String) box1.getItem());

        // Testing generic Box
        System.out.println("\nTesting generic Box:");
        Box<Integer> intBox = new Box<>();
        intBox.setItem(200);
        System.out.println("Integer value: " + intBox.getItem());

        Box<String> stringBox = new Box<>();
        stringBox.setItem("World");
        System.out.println("String value: " + stringBox.getItem());

    }
}
