package day5;

public class ItemNotCheckedOutException extends RuntimeException {
    public ItemNotCheckedOutException(int itemId) {
        super("Item " + itemId + " is not checked out");
    }
}