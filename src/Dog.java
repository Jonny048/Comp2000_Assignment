import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

/*
 * Represents a Dog actor in the game. It defines the dog's visual apperance,
 * movement behaviour, and what it can eat
 */
public class Dog extends Actor<Bone>{

  //Constructs a Dog actor
  public Dog(Cell inLoc, boolean isBot) {
    super(inLoc,3,isBot);
    color = Color.YELLOW;
    //If it's a bot, it will be moved by a Botmover that seeks out food
    if(isBot){
      mover = new MoveTowardsFood();
    }
    //Sets the visual shape of the dog
    setPoly();
  }
  
  /* 
  * Defines the visual shape of the Dog using polygons
  * This method is called to draw the Dog on the grid
  */
  @Override  
  protected void setPoly(){
    display = new ArrayList<Polygon>();

    Polygon ear1 = new Polygon();
    ear1.addPoint(loc.x + 5, loc.y + 5);
    ear1.addPoint(loc.x + 15, loc.y + 5);
    ear1.addPoint(loc.x + 5, loc.y + 15);

    Polygon ear2 = new Polygon();
    ear2.addPoint(loc.x + 20, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 5);
    ear2.addPoint(loc.x + 30, loc.y + 15);

    Polygon face = new Polygon();
    face.addPoint(loc.x + 8, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 7);
    face.addPoint(loc.x + 27, loc.y + 25);
    face.addPoint(loc.x + 8, loc.y + 25);

    display.add(face);
    display.add(ear1);
    display.add(ear2);
  }

  /*
   * Overrides the isDog methof from the actor class to enable type checking
   */
  @Override
  public boolean isDog(){
    return true;
  }

  //Determines if the dog can eat a specific item. Dog eats bones.
  @Override
  public boolean eats(Bone item) {
      return item.isBone();
  }
}
