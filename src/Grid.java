import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Manages the game board, which is a 2D array of Cell objects
 * This class provides methods for initialising the grid, painting it,
 * and retrieving specific cells based on coordinates or position
 */
public class Grid {
  //A 2D array to hold all the cells of the grid
  Cell[][] cells = new Cell[20][20];
  
 //Constructs the grid and initialises each cell
  public Grid() {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        if(j >= 15){
          //Creates a GrassCell for the bottom rows
          cells[i][j] = new GrassCell(colToLabel(i), j, 10+Cell.size*i, 10+Cell.size*j);
        }else{
          //Creats a SkyCell for the upper rows
          cells[i][j] = new SkyCell(colToLabel(i), j, 10+Cell.size*i, 10+Cell.size*j);
        }
      }
    }
  }

  //Converts a column index to tis corresponding character label
  private char colToLabel(int col) {
    return (char) (col + Character.valueOf('A'));
  }

  //Convberts a column label to its corresponding interger index
  private int labelToCol(char col) {
    return (int) (col - Character.valueOf('A'));
  }

  //Provides the entire grid on the screen
  public void paint(Graphics g, Point mousePos) {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j].paint(g, mousePos);
      }
    }
  }

  /**
   * Safely retrieves a Cell at a given column and row index
   * It returns an Optional to handle cases where the coordinares are out of bounds
   * preventing a NullPointerException
   * @param c The column index
   * @param r The row index
   * @return An Optional containing the Cell if it exists, otehrwise Optional.empty()
   */
  public Optional<Cell> cellAtColRow(int c, int r) {
    if(c >= 0 && c < cells.length && r >=0 && r < cells[c].length) {
      return Optional.of(cells[c][r]);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Retrives a Cell object using a column character label and a row index
   * @param c The column label
   * @param r The row index
   * @return An Optional containign the cell if it exists
   */  
  public Optional<Cell> cellAtColRow(char c, int r) {
    return cellAtColRow(labelToCol(c), r);
  }

  /**
   * Finds the cell at a specific point on the screen. This is used ti determine 
   * which cell the mouse is currently over
   * @param p The point on the screen
   * @return An optional containing the cell at the point, or Optional.empty()
   */
  public Optional<Cell> cellAtPoint(Point p) {
    for(int i=0; i < cells.length; i++) {
      for(int j=0; j < cells[i].length; j++) {
        if(cells[i][j].contains(p)) {
          return Optional.of(cells[i][j]);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Finds all cells within a given radius from a starting cell
   * This method recursively finds adjacent cells and adds them to a set to avoid duplicates
   * @param from The starting cell
   * @param size The radius(number of steps away)
   * @return A list of cells within the specificed radius
   */
  public List<Cell> getRadius(Cell from, int size) {
    int i = labelToCol(from.col);
    int j = from.row;
    Set<Cell> inRadius = new HashSet<Cell>();

    //Base case: for a size > 0, find direct neighbours
    if (size > 0) {
        cellAtColRow(colToLabel(i), j - 1).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i), j + 1).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i - 1), j).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i + 1), j).ifPresent(inRadius::add);
    }

    //Recursive step: find the neighbours of the neighbours
    for(Cell c: inRadius.toArray(new Cell[0])) {
        inRadius.addAll(getRadius(c, size - 1));
    }
    return new ArrayList<Cell>(inRadius);
  }

}
