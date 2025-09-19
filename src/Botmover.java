import java.util.List;
/*
 * An interface for classes that determine the movement of a bot-controlled actor.
 * Classes implementing this interface provide a strategy for choosing the next
 * location based on the current game envirnoment.
 */

public interface Botmover {
    /**
     * @param possibleLocs A list of valid cells the actor can move to.
     * @param currActor The current bot actor that needs to make a move
     * @param otherActors A list of all the other actors currently in the game
     * @param items A list of all items currently on the game grid
     * @return The chosen cell for the actor to move to
     */
    Cell chooseNextLoc(List<Cell> possibleLocs, Actor currActor, List<Actor> otherActors, List<Item> items);
}
