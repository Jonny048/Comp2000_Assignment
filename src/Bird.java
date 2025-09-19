import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
/*
 * Respresents a Bird actor in the game 
 * It defines the bird's appearance, movement behaviour, and what it can eat
 */

public class Bird extends Actor<Seeds> {

  //Constructs a Bird
  public Bird(Cell inLoc, boolean isBot) {
    super(inLoc,3,isBot);
    color = Color.GREEN;
    //If it's a bot, it will be moved by a Botmover that seeks out food
    if(isBot){
      mover = new MoveTowardsFood();
    }
    //Set the visual shape of the bird
    setPoly();
  }

  //Defines the visual shape of the bird using polygons for its body and wings
  //This method is called to draw the bird on the grid
  @Override
  protected void setPoly(){
    display = new ArrayList<Polygon>();

    Polygon wing1 = new Polygon();
    wing1.addPoint(loc.x + 5, loc.y + 5);
    wing1.addPoint(loc.x + 15, loc.y + 17);
    wing1.addPoint(loc.x + 5, loc.y + 17);

    Polygon wing2 = new Polygon();
    wing2.addPoint(loc.x + 30, loc.y + 5);
    wing2.addPoint(loc.x + 20, loc.y + 17);
    wing2.addPoint(loc.x + 30, loc.y + 17);

    Polygon body = new Polygon();
    body.addPoint(loc.x + 15, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 10);
    body.addPoint(loc.x + 20, loc.y + 25);
    body.addPoint(loc.x + 15, loc.y + 25);

    display.add(body);
    display.add(wing1);
    display.add(wing2);
  }

  /*
   * Overrides the isBird method from the Actor class to return true,
   * allowing for type checking
   */
  @Override
  public boolean isBird(){
    return true;
  }

  /*
   * Determines if the bord can eat a specific item. Birds eats Seeds
   * item the Item to check.
   * returns true id the item is an instance of Seeds, false otherwise
   */
  @Override
  public boolean eats(Seeds item) {
    return item.isSeeds();
  }

}
