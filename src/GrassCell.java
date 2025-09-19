import java.awt.Color;
/**
 * Represents a GrassCell, a specific type of cell on the game grid
 * It is a subclass of the abstract Cell class and provides the
 * characteristic green color for a grass tile
 */

public class GrassCell extends Cell {
    /**
     * Construts a GrassCell
     * @param col The column of the cell
     * @param row The row of the cell
     * @param x The x-coordinates on the screen
     * @param y The y-coordinates on the screen
     */
    public GrassCell(char col, int row, int x, int y){
        //Calls the constructor of the parent Cell class to set up its position and size
        super(col,row,x,y);
    }

    //Returns the colour of the grass cell
    @Override
    public Color getColor(){
        return Color.GREEN;
    }

    
}
