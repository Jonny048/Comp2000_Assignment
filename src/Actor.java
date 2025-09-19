/*
 * An Abstract class representing the characters in the game.
 * It provides core functionalities like movement, drawing, and location management.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;


public abstract class Actor<T>{
  Color color; //Colour used fir drawing the actor
  Cell loc; //The current cell location of the actor on the grid
  List<Polygon> display; //A list of polygons that form the actor's shape
  int moves; //The number of moves the actor has available
  Botmover mover; //The object responsible for automated movement if the actor is a bot
  boolean isBot;// A flag to check if the actor is a bot

  //Constructs an acotr with a starting location, move count and bot status
  public Actor(Cell inLoc, int moves, boolean isBot){
    this.loc = inLoc; //Initial cell location
    this.moves = moves; //Number of moves
    this.isBot = isBot; //If actor is a bot
  }

  //Draws the actor by filling its polygons with a colour and outlining them
  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  //Checks if the actor can move one step to an adjacent cell
  public boolean canMoveTo(Cell target){
    int dx = Math.abs(loc.col - target.col);
    int dy = Math.abs(loc.row - target.row);
    return (dx+dy == 1);
  }

  //Updates the actor's location
  public void moveTo(Cell target){
    loc = target;
  }

  //Sets a new location and updates the actor's visual shape
  public void setLocation(Cell newLoc){
    this.loc = newLoc;
    setPoly();
  }

  //An alternative constructor setting only the move count
  public Actor(int moves){
    this.moves = moves;
  }

  //Assigns a bot mover to the actor
  public void setBotMover(Botmover mover){
    this.mover = mover;
  }

  //Checks if the actor is controlled by a bot
  public boolean isBot(){
    return mover != null;
  }

  //These methods are  for type checking and overriden by the subclasses
  public boolean isCat(){
    return false;
  }

  public boolean isDog(){
    return false;
  }

  public boolean isBird(){
    return false;
  }

  //Abstract Method to define the actor's visual shape
  protected abstract void setPoly();

  //Sets the number of moves
  public void setMoves(int moves) {
    this.moves = moves;
  }

  //Abstract method to determine if the actor can eat a given item
  public abstract boolean eats(T item);

}
