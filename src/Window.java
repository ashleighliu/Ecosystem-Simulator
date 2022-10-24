import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Window.java
 * Ashleigh Liu
 * December 5, 2021
 * This code graphically displays a two-dimensional array of Organisms.
 */

class Window extends JFrame {
  private int screenWidth, screenHeight, GridToScreenRatio, days, weeks,
    historyM = 0, historyF = 0, historyZ = 0, historyV = 0;
  private Organism[][] matrix;
  
  /**
   * Constructor for Window, initializing the title, matrix, screenWidth, screenHeight, GridToScreenRatio, and other
   * basic functions for graphics.
   */
  Window(String title, Organism[][] matrix) {
    super(title);
    this.matrix = matrix;
    screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = screenHeight / (matrix.length + 1);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.add(new Displays());
    this.setVisible(true);
  }
  
  /**
   * refresh
   * This method refreshes the graphics pane, redrawing each element.
   */
  
  public void refresh() { 
    this.repaint();
    days++; //Increase day count for every one tick.
    if (((days % 7) == 0) && (days != 0)) {
      weeks++; //Increase week count for every seventh tick.
    }
  }
  
  /**
   * Displays.java
   * Ethan Zhang
   * December 5, 2019
   * This code is responsible for drawing on the panel.
   */
  
  class Displays extends JPanel {
    //Constructor for Displays, creating a new MouseInteractions class for its functions.
    Displays() { 
      addMouseListener(new MouseInteractions());
    }
    
    
    /**
     * paintComponent
     * This method draws elements onto the frame.
     * @Param g, a Graphics class that enables drawing.
     */
    
    public void paintComponent(Graphics g) {
      super.repaint();
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      Image male = Toolkit.getDefaultToolkit().getImage("male.png");
      Image female = Toolkit.getDefaultToolkit().getImage("female.png");
      Image undead = Toolkit.getDefaultToolkit().getImage("undead.png");
      Image plant = Toolkit.getDefaultToolkit().getImage("plant.png");
      Image background = Toolkit.getDefaultToolkit().getImage("background.png");
      Image graph = Toolkit.getDefaultToolkit().getImage("graph.png");
      Image barM = Toolkit.getDefaultToolkit().getImage("barM.png");
      Image barF = Toolkit.getDefaultToolkit().getImage("barF.png");
      Image barZ = Toolkit.getDefaultToolkit().getImage("barZ.png");
      Image barV = Toolkit.getDefaultToolkit().getImage("barV.png");
      Image bestM = Toolkit.getDefaultToolkit().getImage("bestM.png");
      Image bestF = Toolkit.getDefaultToolkit().getImage("bestF.png");
      Image bestZ = Toolkit.getDefaultToolkit().getImage("bestZ.png");
      Image bestV = Toolkit.getDefaultToolkit().getImage("bestV.png");
      Image calendar = Toolkit.getDefaultToolkit().getImage("calendar.png");
      int populationM = 0, populationF = 0, populationZ = 0, populationV = 0, 
        greatestM = 0, greatestF = 0, greatestZ = 0, greatestV = 0; //Statistics for the greatest Organism alive.
      //Draw the simulation and simultanouesly keep track of statistics.
      for (int i = 0; i < matrix[0].length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          g.drawImage(background, j * GridToScreenRatio, i * GridToScreenRatio, 
                      GridToScreenRatio, GridToScreenRatio, this);
          if ((matrix[i][j] instanceof Human) && (((Human)matrix[i][j]).gender == true)) {
            if (matrix[i][j].age > greatestM) {
              greatestM = matrix[i][j].age;
            }
            if (matrix[i][j].age > historyM) {
              historyM = matrix[i][j].age;
            }
            populationM++;
            g.drawImage(male, j * GridToScreenRatio, i * GridToScreenRatio, 
                        GridToScreenRatio, GridToScreenRatio, this);
          } else if ((matrix[i][j] instanceof Human) && (((Human)matrix[i][j]).gender == false)) {
            if (matrix[i][j].age > greatestF) {
              greatestF = matrix[i][j].age;
            }
            if (matrix[i][j].age > historyF) {
              historyF = matrix[i][j].age;
            }
            populationF++;
            g.drawImage(female, j * GridToScreenRatio, i * GridToScreenRatio, 
                        GridToScreenRatio, GridToScreenRatio, this);
          } else if (matrix[i][j] instanceof Zombie) {
            if (matrix[i][j].health > greatestZ) {
              greatestZ = matrix[i][j].health;
            }
            if (matrix[i][j].health > historyZ) {
              historyZ = matrix[i][j].health;
            }
            populationZ++;
            g.drawImage(undead, j * GridToScreenRatio, i * GridToScreenRatio, 
                        GridToScreenRatio, GridToScreenRatio, this);
          } else if (matrix[i][j] instanceof Vegetable) {
            if (matrix[i][j].health > greatestV) {
              greatestV = matrix[i][j].health;
            }
            if (matrix[i][j].health > historyV) {
              historyV = matrix[i][j].health;
            }
            populationV++;
            g.drawImage(plant, j * GridToScreenRatio, i * GridToScreenRatio, 
                        GridToScreenRatio, GridToScreenRatio, this);
          }
        }
      }
      //Displaying statistics.
      g.drawImage(calendar, 965, 5, 150, 150, this);
      g.setFont(new Font("Yu Gothic", Font.PLAIN, 22));
      g.drawImage(bestM, 965, 165, 125, 125, this); //Most Sagacious Male
      g.drawString("Most Sagacious Male: " + greatestM + " Days Old", 1065, 225);
      g.drawString("Historically: " + historyM + " Days Old", 1065, 255);
      g.drawImage(bestF, 965, 295, 125, 125, this); //Most Venerable Female
      g.drawString("Most Venerable Female: " + greatestF + " Days Old", 1065, 355);
      g.drawString("Historically: " + historyF + " Days Old", 1065, 385);
      g.drawImage(bestZ, 965, 425, 125, 125, this); //Most Herculean Zombie
      g.drawString("Most Herculean Zombie: " + greatestZ + " Health", 1065, 485);
      g.drawString("Historically: " + historyZ + " Health", 1065, 515);
      g.drawImage(bestV, 965, 525, 125, 125, this); //Most Nutritious Vegetable
      g.drawString("Most Nutritious Vegetable: " + greatestV + " Nutrition", 1065, 600);
      g.drawString("Historically: " + historyV + " Nutrition", 1065, 630);
      g.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
      g.drawString("Days Passed: " + days, 1110, 65);
      g.drawString("Weeks Passed: " + weeks, 1110, 115);
      g.drawImage(graph, 1072, 698, 400, 200, this);
      g.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
      g.drawString("Population Graph", 1072, 690);
      g.drawString("Males", 1010, 727);
      g.drawString("Females", 988, 776);
      g.drawString("Zombies", 988, 825);
      g.drawString("Vegetables", 965, 874);
      if ((populationM * 3) < 360) {
        g.drawImage(barM, 1075, 700, populationM * 3 + 1, 44, this);
        g.drawString("" + populationM, populationM * 3 + 1080, 727);
      } else {
        g.drawImage(barM, 1075, 700, 360 + 1, 44, this);
        g.drawString("" + populationM, 360 + 1080, 727);
      }
      if ((populationF * 3) < 360) {
        g.drawImage(barF, 1075, 749, populationF * 3 + 1, 44, this);
        g.drawString("" + populationF, populationF * 3 + 1080, 776);
      } else {
        g.drawImage(barF, 1075, 749, 360 + 1, 44, this);
        g.drawString("" + populationF, 360 + 1080, 776);
      }
      if ((populationZ * 3) < 360) {
        g.drawImage(barZ, 1075, 798, populationZ * 3 + 1, 44, this);
        g.drawString("" + populationZ, populationZ * 3 + 1080, 825);
      } else {
        g.drawImage(barZ, 1075, 798, 360 + 1, 44, this);
        g.drawString("" + populationZ, 360 + 1080, 776);
      }
      if ((populationV * 3) < 360) {
        g.drawImage(barV, 1075, 847, populationV * 3 + 1, 44, this);
        g.drawString("" + populationV, populationV * 3 + 1080, 874);
      } else {
        g.drawImage(barV, 1075, 847, 360 + 1, 44, this);
        g.drawString("" + populationV, 360 + 1080, 874);
      }
      //Display "The End" screen when no more Humans remain.
      if ((populationM == 0) && (populationF == 0)){
        g.fillRect(0, 0, screenWidth, screenHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Yu Gothic", Font.PLAIN, 50)); 
        g.drawString("The Zombies Have Prevailed...", (screenWidth / 2) - 350, screenHeight / 2);
      }
    }
  }
  
  /**
   * MouseInteractions.java
   * Ethan Zhang
   * December 5, 2019
   * This code is responsible for mouse inputs applied to the frame.
   */
  
  class MouseInteractions implements MouseListener {
    private int clickX, clickY;
    
    /**
     * mousePressed
     * This method displays the statistics in the console of a specific Organism upon being clicked on.
     * @Param e, a MouseEvent class that reacts to mouse inputs.
     */
    
    public void mousePressed(MouseEvent e) {
      clickX = e.getPoint().x / GridToScreenRatio;
      clickY = e.getPoint().y / GridToScreenRatio;
      if ((clickX < matrix[0].length) && (clickY < matrix.length) &&
          (matrix[clickY][clickX] instanceof Human) && (((Human)matrix[clickY][clickX]).gender == true)) {
        System.out.println("\nMale\nCoordinates: " + clickX + ", " + clickY + "\nAge: " + 
                           matrix[clickY][clickX].age + " Days Old\nHealth: " + 
                           matrix[clickY][clickX].health + " Health");
      } else if ((clickX < matrix[0].length) && (clickY < matrix.length) &&
                 (matrix[clickY][clickX] instanceof Human) && (((Human)matrix[clickY][clickX]).gender == false)) {
        System.out.println("\nFemale\nCoordinates: " + clickX + ", " + clickY + "\nAge: " + 
                           matrix[clickY][clickX].age + " Days Old\nHealth: " + 
                           matrix[clickY][clickX].health + " Health");
      } else if ((clickX < matrix[0].length) && (clickY < matrix.length) &&
                 (matrix[clickY][clickX] instanceof Zombie)) {
        System.out.println("\nZombie\nCoordinates: " + clickX + ", " + clickY + "\nAge: " + 
                           matrix[clickY][clickX].age + " Days Old\nHealth: " + 
                           matrix[clickY][clickX].health + " Health");
      } else if ((clickX < matrix[0].length) && (clickY < matrix.length) &&
                 (matrix[clickY][clickX] instanceof Vegetable)) {
        System.out.println("\nVegetable\nCoordinates: " + clickX + ", " + clickY + "\nAge: " + 
                           matrix[clickY][clickX].age + " Days Old\nNutrition: " + 
                           matrix[clickY][clickX].health + " Nutrition");
      }
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }
  }
}