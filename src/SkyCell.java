import java.awt.Color;

/**
 * Represents a SkyCell, a specific type of cell on the game grid
 * It is a subclass of the abstract Cell class and provides the
 * characteristic light blue color for a sky tile
 */
public class SkyCell extends Cell{

    /**
     * Constructs a SkyCell
     * @param col The column of the cell
     * @param row The row of the cell
     * @param x The x-coordinates on the screen
     * @param y The y-coordinates on the screen
     */
    public SkyCell(char col, int row, int x, int y){
        //Calls teh constructor of teh parent Cell class to set up its position and size
        super(col,row,x,y);
    }

    /*
     * returns the colour of the SkyCell
     */
    @Override
    public Color getColor() {
        return new Color(172,216,230);
    }
}