public class hashmap {

    public static void main(String[] args) {
        java.util.HashMap<Integer, String> map = new java.util.HashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");

        System.out.println("Initial HashMap: " + map);

        map.remove(2);
        System.out.println("After removing key 2: " + map);

        String value = map.get(3);
        System.out.println("Value for key 3: " + value);

        map.put(1, "Apricot");
        System.out.println("After updating key 1: " + map);

        boolean containsKey = map.containsKey(2);
        System.out.println("Contains key 2: " + containsKey);

        int size = map.size();
        System.out.println("Size of HashMap: " + size);

        map.clear();
        System.out.println("After clearing HashMap: " + map);
    }
}
