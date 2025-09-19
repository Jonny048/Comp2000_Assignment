import java.awt.Color;
import java.awt.Graphics;

/*
 * Represtns a Fish item in the game. It is a subclass of Item
 * A Cat has a specific visual representation and can only be given to the Cat actor
 */
public class Fish extends Item {
    
    //Constructs a Fish object at a given location
    public Fish(Cell loc){
        super(loc);
    }

    //Draws the fish on the game panel
    @Override
    public void paint(Graphics g){
        int fishWidth = 25;
        int fishHeight = 15;

        //Calculates the coordinates to center the fish within the cell
        int centerX = loc.x + (loc.width - fishWidth) / 2;
        int centerY = loc.y + (loc.height - fishHeight) / 2;

        //Draws the main body of the fish
        g.setColor(Color.ORANGE);
        g.fillOval(centerX, centerY, fishWidth, fishHeight);

        //Draws the tail of the fish
        g.setColor(Color.YELLOW);
        int[] tailX = {centerX + fishWidth, centerX + fishWidth - 5, centerX + fishWidth};
        int[] tailY = {centerY, centerY, centerY + fishHeight};
        g.fillPolygon(tailX, tailY, 3);

        //Draw the eye of the fish with a with backgrounf and a black pupil
        g.setColor(Color.WHITE);
        g.fillOval(centerX + 5, centerY + 4, 4, 4);
        g.setColor(Color.BLACK);
        g.fillOval(centerX + 6, centerY + 5, 2, 2);
    }

    //Gets the type of this item
    @Override
    public String getItemType(){
        return "Fish";
    }

    //Checks if this item is a bone
    @Override 
    public boolean isBone(){ 
        return false; 
    }

    //Checks if the item is a fish
    @Override 
    public boolean isFish(){ 
        return true; 
    }

    //Checks if this item is seeds
    @Override 
    public boolean isSeeds(){ 
        return false; 
    }
}
