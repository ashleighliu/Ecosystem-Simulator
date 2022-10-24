/**
 * Biped.java
 * Ashleigh Liu
 * December 5, 2021
 * This code contains the Biped class that inherits x-coordinate, y-coordinate, health, age, and gender variables from
 * the Organism class.
 * The Biped class is the class that the Human and Zombie classes derive from.
 */

abstract class Biped extends Organism {
  private int direction;
  private boolean movement;
  private int MATURITY = 16; //Constant variable that dictates when Humans are mature enough to procreate.
  
  //First constructor for Biped, inheriting coordinateX, coordinateY, health, and age from the Organism.
  Biped(int coordinateX, int coordinateY, int health, int age) {
    super(coordinateX, coordinateY, health, age);
  }
  
  //Second constructor for Biped, inherting coordinateX, coordinateY, health, age, and gender from the Organism.
  Biped(int coordinateX, int coordinateY, int health, int age, boolean gender) {
    super(coordinateX, coordinateY, health, age, gender);
  }
  
  /**
   * setMovement
   * This method enables or disables the ability for a Biped to move.
   * @Param movement, a boolean value that stores the ability for a Biped to move: true for unable and false for able.
   */
  
  public void setMovement(boolean movement) {
    this.movement = movement;
  }
  
  /**
   * getMovement
   * This method returns the boolean value of the ability for a Biped to move.
   * @Return the boolean value of the ability for a Biped to move: true for unable and false for able.
   */
  
  public boolean getMovement() {
    return movement;
  }
  
  /**
   * move
   * This method moves Organisms on the map, taking intents, such as consumption and procreation, into account.
   * Movement in one direction or remaining stationary for a tick is based on probability (1/5).
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   */
  
  public void move(Organism[][] map) {
    health--;
    direction = (int)Math.ceil(Math.random() * 5);
    //Moving leftwards.
    if ((direction == 1) && (coordinateX != 0)) {
      /**
       * Check if Vegetable consumption should be carried out by a Human; check if the tile to the left of the Human
       * is a Vegetable, increasing the Human's health accordingly and overriding the position of the Vegetable.
       */
      if ((map[coordinateY][coordinateX] instanceof Human) && 
          (map[coordinateY][coordinateX - 1] instanceof Vegetable)) {
        ((Human)map[coordinateY][coordinateX]).consume(map, coordinateX - 1, coordinateY);
        /**
         * Check if procreation should be carried out between two Humans; check if the tile to the left of the Human is
         * another Human that is ready and suitable for procreation.
         */
      } else if ((map[coordinateY][coordinateX] instanceof Human) && 
                 (map[coordinateY][coordinateX - 1] instanceof Human) && 
                 (((Human)map[coordinateY][coordinateX]).getCooldown() == 0) && 
                 (((Human)map[coordinateY][coordinateX - 1]).getCooldown() == 0) &&
                 (map[coordinateY][coordinateX].age >= MATURITY) &&
                 (map[coordinateY][coordinateX - 1].age >= MATURITY) &&
                 (((Human)map[coordinateY][coordinateX]).eligibility(map, coordinateX - 1, coordinateY) == true)) {
        ((Human)map[coordinateY][coordinateX]).procreate(map, coordinateX - 1, coordinateY);
        /**
         * Check if Human consumption should be carried out by a Zombie; check if the tile to the left of the Zombie is
         * a Human that has a lower health than that of the Zombie's.
         */
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) && 
                 (map[coordinateY][coordinateX - 1] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY][coordinateX - 1])) > 0)) {
        ((Zombie)map[coordinateY][coordinateX]).consume(map, coordinateX - 1, coordinateY);
        /**
         * Check if Human infection should be carried out by a Zombie; check if the tile to the left of the Zombie is
         * a Human that has a higher health than that of the Zombie's.
         */
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) && 
                 (map[coordinateY][coordinateX - 1] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY][coordinateX - 1])) <= 0)) {
        ((Zombie)map[coordinateY][coordinateX]).infect(map, coordinateX - 1, coordinateY);
      }
      //After an intent is declared, physically move the biped the map.
      if ((map[coordinateY][coordinateX - 1] == null) || (map[coordinateY][coordinateX - 1] instanceof Vegetable)) {
        map[coordinateY][coordinateX - 1] = map[coordinateY][coordinateX];
        map[coordinateY][coordinateX] = null;
        coordinateX--;
      }
      //Moving rightwards.
    } else if ((direction == 2) && (coordinateX != map[0].length - 1)) {
      if ((map[coordinateY][coordinateX] instanceof Human) && 
          (map[coordinateY][coordinateX + 1] instanceof Vegetable)) {
        ((Human)map[coordinateY][coordinateX]).consume(map, coordinateX + 1, coordinateY);
      } else if ((map[coordinateY][coordinateX] instanceof Human) &&
                 (map[coordinateY][coordinateX + 1] instanceof Human) &&
                 (((Human)map[coordinateY][coordinateX]).getCooldown() == 0) &&
                 (((Human)map[coordinateY][coordinateX + 1]).getCooldown() == 0) &&
                 (map[coordinateY][coordinateX].age >= MATURITY) &&
                 (map[coordinateY][coordinateX + 1].age >= MATURITY) &&
                 (((Human)map[coordinateY][coordinateX]).eligibility(map, coordinateX + 1, coordinateY) == true)) {
        ((Human)map[coordinateY][coordinateX]).procreate(map, coordinateX + 1, coordinateY);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) &&
                 (map[coordinateY][coordinateX + 1] instanceof Human) && 
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY][coordinateX + 1])) > 0)) {
        ((Zombie)map[coordinateY][coordinateX]).consume(map, coordinateX + 1, coordinateY);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) &&
                 (map[coordinateY][coordinateX + 1] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY][coordinateX + 1])) <= 0)) {
        ((Zombie)map[coordinateY][coordinateX]).infect(map, coordinateX + 1, coordinateY);
      }
      if ((map[coordinateY][coordinateX + 1] == null) || (map[coordinateY][coordinateX + 1] instanceof Vegetable)) {
        map[coordinateY][coordinateX + 1] = map[coordinateY][coordinateX];
        map[coordinateY][coordinateX] = null;
        coordinateX++;
      }
      //Moving upwards.
    } else if ((direction == 3) && (coordinateY != 0)) {
      if ((map[coordinateY][coordinateX] instanceof Human) &&
          (map[coordinateY - 1][coordinateX] instanceof Vegetable)) {
        ((Human)map[coordinateY][coordinateX]).consume(map, coordinateX, coordinateY - 1);
      } else if ((map[coordinateY][coordinateX] instanceof Human) &&
                 (map[coordinateY - 1][coordinateX] instanceof Human) &&
                 (((Human)map[coordinateY][coordinateX]).getCooldown() == 0) &&
                 (((Human)map[coordinateY - 1][coordinateX]).getCooldown() == 0) &&
                 (map[coordinateY][coordinateX].age >= MATURITY) &&
                 (map[coordinateY - 1][coordinateX].age >= MATURITY) &&
                 (((Human)map[coordinateY][coordinateX]).eligibility(map, coordinateX, coordinateY - 1) == true)) {
        ((Human)map[coordinateY][coordinateX]).procreate(map, coordinateX, coordinateY - 1);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) &&
                 (map[coordinateY - 1][coordinateX] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY - 1][coordinateX])) > 0)) {
        ((Zombie)map[coordinateY][coordinateX]).consume(map, coordinateX, coordinateY - 1);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) &&
                 (map[coordinateY - 1][coordinateX] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY - 1][coordinateX])) <= 0)) {
        ((Zombie)map[coordinateY][coordinateX]).infect(map, coordinateX, coordinateY - 1);
      }
      if ((map[coordinateY - 1][coordinateX] == null) || (map[coordinateY - 1][coordinateX] instanceof Vegetable)) {
        map[coordinateY - 1][coordinateX] = map[coordinateY][coordinateX];
        map[coordinateY][coordinateX] = null;
        coordinateY--;
      }
      //Moving downwards.
    } else if ((direction == 4) && (coordinateY != map.length - 1)) {
      if ((map[coordinateY][coordinateX] instanceof Human) &&
          (map[coordinateY + 1][coordinateX] instanceof Vegetable)) {
        ((Human)map[coordinateY][coordinateX]).consume(map, coordinateX, coordinateY + 1);
      } else if ((map[coordinateY][coordinateX] instanceof Human) &&
                 (map[coordinateY + 1][coordinateX] instanceof Human) &&
                 (((Human)map[coordinateY][coordinateX]).getCooldown() == 0) &&
                 (((Human)map[coordinateY + 1][coordinateX]).getCooldown() == 0) &&
                 (map[coordinateY][coordinateX].age >= MATURITY) &&
                 (map[coordinateY + 1][coordinateX].age >= MATURITY) &&
                 (((Human)map[coordinateY][coordinateX]).eligibility(map, coordinateX, coordinateY + 1) == true)) {
        ((Human)map[coordinateY][coordinateX]).procreate(map, coordinateX, coordinateY + 1);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) && 
                 (map[coordinateY + 1][coordinateX] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY + 1][coordinateX])) > 0)) {
        ((Zombie)map[coordinateY][coordinateX]).consume(map, coordinateX, coordinateY + 1);
      } else if ((map[coordinateY][coordinateX] instanceof Zombie) &&
                 (map[coordinateY + 1][coordinateX] instanceof Human) &&
                 ((map[coordinateY][coordinateX].compareTo(map[coordinateY + 1][coordinateX])) <= 0)) {
        ((Zombie)map[coordinateY][coordinateX]).infect(map, coordinateX, coordinateY + 1);
      }
      if ((map[coordinateY + 1][coordinateX] == null) || (map[coordinateY + 1][coordinateX] instanceof Vegetable)) {
        map[coordinateY + 1][coordinateX] = map[coordinateY][coordinateX];
        map[coordinateY][coordinateX] = null;
        coordinateY++;
      }
    }
  }
}