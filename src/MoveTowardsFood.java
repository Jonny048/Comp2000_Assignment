import java.util.List;
import java.util.Random;

/**
 * A bot movement strategy where the bot's goal is to move towards its food
 * This class implements the `Botmover` interface and calculates the optimal move
 * to minimize the distance to the nearest food item of the correct type.
 */

public class MoveTowardsFood implements Botmover {
    //A random object used for making a random move of no food is found
    Random rand = new Random();

    /**
     * Chooses the best next location for the bot actor
     * The method first identifies the actor's food type, then searches for that food on the grid 
     * It calculates the distance to all possible next locations and chooses the one closest to the food
     * If no food is found, it returns a random possible location
     * @param possibleLocs A list of valid cells the actor can move to.
     * @param currActor The current bot actor
     * @param otherActors A list of all other actors
     * @param items A list of all items on the grid
     * @return The chosen Cell to move to
     */
    @Override
    public Cell chooseNextLoc(List<Cell> possibleLocs, Actor currActor, List<Actor> otherActors, List<Item> items) {
        String foodType = null;
        
        //Identify the type of food the current actor eats
        if(currActor.isDog()){
            foodType = "Bone";
        }else if(currActor.isBird()){
            foodType = "Seeds";
        }

        Cell targetFoodLoc = null;
        //Search for the first item that macthes the required food type
        if(foodType != null){
            for(Item item : items){
                if(foodType.equals(item.getItemType())){
                    targetFoodLoc = item.getLoc();
                    break;
                }
            }
        }

        //If no food of the correct type is found, return a random move
        if(targetFoodLoc == null){
            return possibleLocs.get(rand.nextInt(possibleLocs.size()));
        }

        //Initialise the best move with the first possible location
        Cell bestMove = possibleLocs.get(0);
        int minDist = calDistance(bestMove, targetFoodLoc);

        //Iterate through all possible locations to find the one closet to the food
        for(Cell c: possibleLocs){
            int dist = calDistance(c, targetFoodLoc);
            if(dist < minDist){
                minDist = dist;
                bestMove = c;
            }
        }
        return bestMove;
    }

    /**
     * Calculates the Manhattan distance between two cells
     * The Manhattan distance is the sum of the absolute differences of their coordinates
     * @param c1 The first cell
     * @param c2 The second cell
     * @return The Manhattan disatcen between the two cells
     */
    public int calDistance(Cell c1, Cell c2){
        return Math.abs(c1.col - c2.col) + Math.abs(c1.row - c2.row);
    }
}
