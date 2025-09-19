import java.awt.Color;
import java.awt.Graphics;
/*
 * Represtns a Bone item in the game. It is a subclass of Item and implements the Giveable interface
 * A Bone has a specific visual representation and can only be given to the Dog actor
 */

public class Bone extends Item implements Giveable {
    
    //Constructs a Bone object at a given location
    public Bone(Cell loc){
        super(loc);// Where the bone will be placed
    }

    //Draws the Bone on the Grid
    @Override
    public void paint(Graphics g){
        Cell loc = getLoc();
        if(loc != null){
            g.setColor(Color.WHITE);
            g.fillRect(loc.x+8,loc.y+12,16,6);
            g.fillOval(loc.x+4,loc.y+8,8,12);
            g.fillOval(loc.x+20,loc.y+8,8,12);

            
        }
    }

    //Gets the type of this item
    @Override
    public String getItemType(){
        return "Bone";
    }

    //Checks if the Bone can be given to a specific actor. A bone can only be given to the Dog actor
    @Override
    public boolean canBeGivenTo(Actor actor){
        return actor.isDog();
    }

    
    //Check if the item is a Bone
    @Override 
    public boolean isBone(){ 
        return true; 
    }

    //Checks if the item is a Fish
    @Override 
    public boolean isFish(){
        return false;
    }

    //Checks if the item is Seeds
    @Override 
    public boolean isSeeds(){
        return false;
    }
}
