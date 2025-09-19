import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main class of the application, responsible for creating the game window,
 * initializing the game stage, and handling the main game loop
 * It extends JFrame to serve as the application's window
 */
public class Main extends JFrame {
  //The single insatnce of the game's stage
  Stage stage = new Stage();

  /**
   * The main entry point of the program
   * @param args Command-line arguments
   * @throws Exception if an error occurs during window creation
   */
  public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    /**
     * A nested class that acts as the drawing cancas for the game
     * It extends JPanel and implements MouseListener to handle user input
     */
    class Canvas extends JPanel implements MouseListener{
      /**
       * the constructor for the canvas. Its sets the preffered size of the canvas,
       * adds itself as a mouse listener, and uses a StageRader to load the game satge from a file
       */
      public Canvas() {
        setPreferredSize(new Dimension(1024, 720));
        this.addMouseListener(this);
        StageReader reader = new StageReader();
        stage = reader.readStage("data/stage1.rvb");
      }

      /*
       * The primary method for drawing all game elements
       * It calls the paint method of the Stage to render the grid, actors, and items        
       */
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        stage.paint(g, getMousePosition());
      }

      /*
       * Responds to mouse click event. It passes the click coordinates to the 
       * Stage to handle game logic and then calls repaint() to redraw the window
       */
      @Override
      public void mouseClicked(MouseEvent e){
        stage.mouseClicked(e.getX(), e.getY());
        repaint();
      }

      //The following methods are part of the MouseListener interface
      @Override public void mousePressed(MouseEvent e){}
      @Override public void mouseReleased(MouseEvent e) {}
      @Override public void mouseEntered(MouseEvent e) {}
      @Override public void mouseExited(MouseEvent e) {}
    }

    /*
     * The constructor for the main class. It sets up teh JFrame, creates a Canvas
     * and makes the window visible
     */
    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    //The main run method that intiates the drawing process by calling repaint()
    public void run() {
      repaint();
    }
}

