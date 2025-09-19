import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
/*
 * An abstract class representing a single cell or tile on the game grid.
 * It extends `java.awt.Rectangle` to define its position and size.
 * This class handles the basic visual rendering of a cell, including drawing
 * its background and border, and highlighting it on mouse hover.
 * Subclasses must define the specific color of the cell.
 */

public abstract class Cell extends Rectangle {
  static int size = 35; //The fixed size of a cell
  char col; //The column coordinates of the cell
  int row; //The row coordinates of the cell

  /**
   *Constructs a Cell at a specified position on the grid
   * @param inCol The column of the cell
   * @param inRow The row of the cell
   * @param x The x-coordinate of the cell's top left corner of the screen
   * @param y The y-coordinate of the cell's top-left corner of the screen
   */
  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

  /**
   * Provides the cell on the game panel. It fills the cell with its defined colour,
   * draws a balck border, and highlights the cell in white if the mouse is hovering over it
   * @param g The graphics object used for drawing
   * @param mousePos The current position of the mouse
   */
  public void paint(Graphics g, Point mousePos) {
    //Set the cell's backgroung color and fill the rectangle
    g.setColor(getColor());
    g.fillRect(x, y, size, size);

    //Draws a black border around the cell
    g.setColor(Color.BLACK);
    g.drawRect(x, y, size, size);

    //Checks if the mouse is inside the cell's boundaries
    if(contains(mousePos)) {
      //if the mouse is over the cell, draws a white rectangle
      g.setColor(Color.WHITE);
      g.fillRect(x+1,y+1,size-2,size-2);
    } else {
      //if not use a default colour(doesnt affect the drawing)
      g.setColor(Color.GRAY);
    }
    
  }

  /*
   * A safe way to check if a given point is inside the cell's boundaries
   * It handles the case where the point might be null, preventing a NullPointerException.
   */
  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }

  /*
   * An abstract method that subclasses must implement to provide the specific 
   * colour of the cell. This allows differnt types of cell
   */
  public abstract Color getColor();
}
