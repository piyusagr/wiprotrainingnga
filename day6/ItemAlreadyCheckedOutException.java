package day5;

class ItemAlreadyCheckedOutException extends RuntimeException {
    public ItemAlreadyCheckedOutException(int itemId) {
        super("Item " + itemId + " is already checked out");
    }
}