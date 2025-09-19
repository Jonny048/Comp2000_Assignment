/**
 * An interface for items that can be given to a specific actor.
 * It provides a contract for checking if an item is compatible with an actor.
 */

public interface Giveable{
    /**
     * Determines if the item can be given to particular actor
     * Implementhing classes will define the specific criteria
     * @param actor The actor to check for compatibility
     * @return True if the item can be given to the actor, false otherwise
     */
    boolean canBeGivenTo(Actor actor);
}