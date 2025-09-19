import java.awt.Color;
import java.awt.Graphics;

/*
 * Represtns a Seeds item in the game. It is a subclass of Item and implements the Giveable interface
 * Seeds has a specific visual representation and can only be given to the Bird actor
 */
public class Seeds extends Item implements Giveable{
    
    //Constructs a Seeds object at a given location
    public Seeds(Cell loc){
        super(loc);
    }

    //Draws the Seeds on the grid
    @Override
    public void paint(Graphics g){
        //Calculate the center of the cell to position of the drawing
        int centerX = loc.x + loc.width/2;
        int centerY = loc.y + loc.height/2;

        //Draw the main body of the seed pile using a dark brown colour
        g.setColor(new Color(139,69,19));
        g.fillOval(centerX - 8, centerY - 10, 16, 20);

        //Draw a secondary part of the pile in a slightly lighter brown
        g.setColor(new Color(160, 82, 45));
        g.fillOval(centerX - 8, centerY - 12, 16, 6);

        //Draw individual seeds on top using a yellow colour
        g.setColor(Color.YELLOW);
        g.fillOval(centerX - 6, centerY - 11, 3, 3);
        g.fillOval(centerX - 2, centerY - 12, 3, 3);
        g.fillOval(centerX + 2, centerY - 10, 3, 3);
        g.fillOval(centerX + 5, centerY - 11, 2, 2);
        g.fillOval(centerX - 1, centerY - 9, 2, 2);

    }

    //Gets the type of this item
    @Override
    public String getItemType(){
        return "Seeds";
    }

    //Checks if teh item cna be given to a specific actor.Seeds can only be given to the Bird actor
    @Override
    public boolean canBeGivenTo(Actor actor){
        return actor.isBird();
    }

    //Checks if the item is a Bone
    @Override 
    public boolean isBone(){ 
        return false; 
    }

    //Checks if the item is a Fish
    @Override 
    public boolean isFish(){ 
        return false; 
    }

    //Checks if the item is Seeds
    @Override 
    public boolean isSeeds(){ 
        return true; 
    }
}
