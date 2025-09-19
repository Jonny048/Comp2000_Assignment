import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Manages the game's state, including the grid, actors, and items
 * This class handles game logic such as actor selection, movement,
 * and the collection of items. It also manages the visual representation
 * of all game elements.
 */
public class Stage {
  Grid grid; //The game grid
  List<Actor> actors; //A list of all the actors in the game
  Optional<Actor> selectedActor = Optional.empty(); //The currently selected actor, if any. Optional is used to prevent NullPointerException
  List<Cell> highlightedCells = new ArrayList<>();//A list of cells to be highlighted for a potienal move
  //The single instance of each item type on the board
  Bone bone;
  Fish fish;
  Seeds seeds;
  Random rand = new Random();//A random number generator
  //Counter for number of items collected by their respective actors
  int seedsCount = 0;
  int boneCount = 0;
  int fishCount = 0;
  
  //An enumeration to define the different states of game's turn
  enum State{ChoosingActor, SelectingNewLocation}
  State currentState = State.ChoosingActor;

  //Consructs the stage, initialising the grid an spawnign the intital terms
  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    //Spawn the initial set of items on the grid
    spawnBone();
    spawnFish();
    spawnSeeds();
  }

  //Spawns a new bone at a random, unoccpied cell on the grid
  public void spawnBone(){
    int col;
    int row;
    Optional<Cell> cell = Optional.empty();
    while(!cell.isPresent() || occupied(cell.get())){
      col = rand.nextInt(20);
      row = rand.nextInt(20);
      cell = grid.cellAtColRow(col,row);
    }
    
    bone = new Bone(cell.get());
  }

  //Spawns a new fish at a random, unoccupied cell on the grid
  public void spawnFish(){
    int col;
    int row;
    Optional<Cell> cell = Optional.empty();
    while (!cell.isPresent() || occupied(cell.get())){
      col = rand.nextInt(20);
      row = rand.nextInt(20);
      cell = grid.cellAtColRow(col,row);
    }

    fish = new Fish(cell.get());
  }

  //Spawns new Seeds at a random, unoccupied cell on the grid
  public void spawnSeeds(){
    int col;
    int row;
    Optional<Cell> cell = Optional.empty();
    while(!cell.isPresent() || occupied(cell.get())){
      col = rand.nextInt(20);
      row = rand.nextInt(20);
      cell = grid.cellAtColRow(col,row);
    }
    
    seeds = new Seeds(cell.get());
  }

  /**
   * Checks if a given cell is occupied by any actor
   * @param c The cell to check
   * @return True if an actor is at the cell, false otherwise
   */
  public boolean occupied(Cell c){
    for(Actor a: actors){
      if(a.loc == c){
        return true;
      }
    }
    return false;
  }

  /**
   * Adds an actor to the game
   * @param a the actor to add
   */
  public void addActor(Actor a){
    actors.add(a);
  }

  /*
   * Commands all bot controlled actors to make their moves
   * It gets a list of possible moves for each bot and then
   * calls the bot's mover to choose the next location
   */
  public void moveBots(){
      for(Actor a: actors){
          if(a.isBot()){
              List<Cell> possible = getClearRadius(a.loc, a.moves);
              if(!possible.isEmpty()){
                  a.setLocation(a.mover.chooseNextLoc(possible, a, actors,getAllItems()));
                  checkAndCollectItem(a);
              }
          }
      }
  }

  /**
   * Gathers all items currently on the board into a single list
   * @return A lisr of all available items
   */
  public List<Item> getAllItems() {
    List<Item> itemsOnBoard = new ArrayList<>();
    if (bone != null) {
        itemsOnBoard.add(bone);
    }
    if (fish != null) {
        itemsOnBoard.add(fish);
    }
    if (seeds != null) {
        itemsOnBoard.add(seeds);
    }
    return itemsOnBoard;
  }

  /**
   * Renders all the game elements on the screen
   * @param g The graphics object for drawing
   * @param mouseLoc The current mouse position to highlight the cell underneath it
   */
  public void paint(Graphics g, Point mouseLoc) {
    //Draw the grid
    grid.paint(g, mouseLoc);

    //Draw highlighted cells for potenial moves
    g.setColor(new Color(0f,0f,1f,0.5f));
    for(Cell c: highlightedCells){
      g.fillRect(c.x+2,c.y+2,c.width-4,c.height-4);
    }

    //Draw all actors
    for(Actor a: actors) {
      a.paint(g);
    }

    //Draw all items
    if(bone != null){
      bone.paint(g);
    }

    if(fish != null){
      fish.paint(g);
    }

    if(seeds != null){
      seeds.paint(g);
    }

    //Draw the sidebar with game information
    int panelX = 740; 
    int y = 100;             
    int lineHeight = 15;
    int labelIndent = 10;
    g.setColor(Color.DARK_GRAY);
    for(Actor a: actors){
      g.drawString(a.getClass().getSimpleName(), panelX,y);
      y += lineHeight;

      g.drawString("Location: ", panelX + labelIndent, y);
      g.drawString(a.loc.col + "" + a.loc.row, panelX + 100, y); 
      y += lineHeight;

      if(a.isDog()) {
            g.drawString("Bones:", panelX + labelIndent, y);
            g.drawString(String.valueOf(boneCount), panelX + 100, y);

            y += lineHeight;
        } else if(a.isCat()) {
            g.drawString("Fish:", panelX + labelIndent, y);
            g.drawString(String.valueOf(fishCount), panelX + 100, y);
            y += lineHeight;
        } else if(a.isBird()) {
            g.drawString("Seeds:", panelX + labelIndent, y);
            g.drawString(String.valueOf(seedsCount), panelX + 100, y);
            y += lineHeight;
        }
    }
    
    //Display the hovered cell's coordinates
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }

  /**
   * Get a list of cells witha specified radius that are not occupied by other actors
   * @param from The starting cell
   * @param size The radius
   * @return A list of valid, unoccupied cells
   */
  public List<Cell> getClearRadius(Cell from, int size){
    List<Cell> cellsInRadius = grid.getRadius(from,size);
    for(Actor a:actors){
      cellsInRadius.remove(a.loc);
    }
    return cellsInRadius;
  }

  /**
   * Handles mouse click events to manage the game's turn-based logic
   * @param x The x-coordinates of the mouse click
   * @param y The y-coordinates of the mouse click
   */
  public void mouseClicked(int x, int y){
    switch(currentState){
      case ChoosingActor:
        //Find and select an actor at the clicked location
        for(Actor a: actors){
          if(a.loc.contains(x,y)){
            selectedActor = Optional.of(a);
            //Highlight all possible moves for the selected actor
            highlightedCells = getClearRadius(a.loc, a.moves);
            currentState = State.SelectingNewLocation;
            break;
          }
        }
        break;
    
      case SelectingNewLocation:
      //Get the cell that was clicked on
        Optional<Cell> clickedCell = grid.cellAtPoint(new Point(x,y));
        //Check if the clicked cell is a valid move
        if(clickedCell.isPresent()&&highlightedCells.contains(clickedCell.get())){
        selectedActor.ifPresent(a -> {
          a.setLocation(clickedCell.get());
          checkAndCollectItem(a);
        // if(a.isCat() && a.loc == fish.getLoc()){
        //   fishCount++;
        //   spawnFish();
        // }

       });
      
        highlightedCells.clear();
        selectedActor = Optional.empty();
        currentState = State.ChoosingActor;

        moveBots();
        break;
      }
    }
  }

  public Optional<Item> getGivableItemAt(Cell cell) {
    if (bone != null && bone.loc == cell) return Optional.of(bone);
    if (seeds != null && seeds.loc == cell) return Optional.of(seeds);
    return Optional.empty();
  }

  public void checkAndCollectItem(Actor a){
    if (a.isDog() && a.loc == bone.getLoc()) {
        boneCount++;
        spawnBone();
    }
    if (a.isCat() && a.loc == fish.getLoc()) {
        fishCount++;
        spawnFish();
    }
    if (a.isBird() && a.loc == seeds.getLoc()) {
        seedsCount++;
        spawnSeeds();
    }
  }

}
