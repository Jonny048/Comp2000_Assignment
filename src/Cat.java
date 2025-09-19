import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
/*
 * Represents a Cat acotr in the game. It defines the cat's visual apperance,
 * movement behaviour, and what it can eat.
 */

public class Cat extends Actor {

  //Constructs a Cat actor
  public Cat(Cell inLoc,boolean isBot) {
    //A Cat has 3 moves per turn
    super(inLoc,3,isBot);
    color = Color.BLUE;
    //Sets the visual shape of the Cat
    setPoly();
  }

  /* 
  * Defines the visual shape of the Cat using polygons
  * This method is called to draw the Cat on the grid
  */
  @Override
  protected void setPoly(){
    display = new ArrayList<Polygon>();

    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 11, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 15);
    ear1.addPoint(loc.x + 7, loc.y + 15);

    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 22, loc.y + 5);
    ear2.addPoint(loc.x + 26, loc.y + 15);
    ear2.addPoint(loc.x + 18, loc.y + 15);

    Polygon face = new Polygon();
    face.addPoint(loc.x + 5, loc.y + 15);
    face.addPoint(loc.x + 29, loc.y + 15);
    face.addPoint(loc.x + 17, loc.y + 30);

    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }

  //Overrides the isCat method from the Actor class to enable type checking
  @Override
  public boolean isCat(){
    return true;
  }

  //Determines if the cat can eat a specific item. Cats eat Fish
  @Override
  public boolean eats(Item item) {
        return item.isFish();
  }
}
