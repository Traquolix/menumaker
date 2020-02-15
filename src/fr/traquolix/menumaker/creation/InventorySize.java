package fr.traquolix.menumaker.creation;

public enum InventorySize {
    ONE_LINE(9, 1),
    TWO_LINES(18, 2),
    THREE_LINES(27, 3),
    FOUR_LINES(36, 4),
    FIVE_LINES(45, 5),
    SIX_LINES(54, 6);

    public int size;
    private int numberOfLines;

    InventorySize(int size, int numberOfLines) {
        this.size = size;
        this.numberOfLines = numberOfLines;
    }
}