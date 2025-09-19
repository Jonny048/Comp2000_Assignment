import java.awt.Graphics;

/**
 * An abstract base class for all collectible items on the game board
 * It provides core functionality for managing an item's location and serves
 * as a blueprint for drawing the item and identifying its type
 */
public abstract class Item {
    
    //The current cell location of the item on the grid
    public Cell loc;

    /**
     * Constructs an Item with a specified starting location
     * @param start The initial Cell where the item is placed
     */
    public Item(Cell start){
        this.loc = start;
    }

    //Retrieves the current location of the item
    public Cell getLoc(){
        return loc;
    }

    //Stes a new location for the item
    public void setLoc(Cell c){
        this.loc = c;
    }

    //An abstract method that subclasses must implement to deine how the item
    //is drawn in the screen
    public abstract void paint(Graphics g);

    /*
     * An abstract method to get the type of the item as a string
     * Subclasses will return a string
     */
    public abstract String getItemType();

    //Abstract method to check oif the item is a bone
    public abstract boolean isBone();

    //Abstract method to check oif the item is a fish
    public abstract boolean isFish();

    //Abstract method to check oif the item is seeds
    public abstract boolean isSeeds();
}
