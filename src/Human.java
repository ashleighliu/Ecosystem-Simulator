/**
 * Human.java
 * Ashleigh Liu
 * December 5, 2021
 * This code contains the Human class that inherits x-coordinate, y-coordinate, health, age, and gender variables from
 * the Biped class.
 */

class Human extends Biped {
  private int cooldown = 0; //Cooldown for procreation; defaulted at zero so that Humans are primed for procreation.
  private int LONGEST = 5; //Maximum cooldown for procreation.
  
  //Constructor for Human, inheriting coordinateX, coordinateY, health, age, and gender from Biped.
  Human(int coordinateX, int coordinateY, int health, int age, boolean gender) {
    super(coordinateX, coordinateY, health, age, gender);
  }
  
  /**
   * consume
   * This method increases the health of a Human based on the nutrition, otherwise known as health, of a Vegetable.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   * @Param targetX, an integer that stores the x-coordinate of the Vegetable being consumed.
   * @Param targetY, an integer that stores the y-coordinate of the Vegetable being consumed.
   */
  
  public void consume(Organism[][] map, int targetX, int targetY) {
    map[coordinateY][coordinateX].health += map[targetY][targetX].health;
  }
  
  /**
   * setCooldown
   * This method sets the cooldown in ticks for procreation on a Human.
   * @Param cooldown, an integer that stores the value that cooldown is to be set to.
   */
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  /**
   * getCooldown
   * This method returns the current cooldown for procreation on a Human.
   * @Return the current cooldown for procreation on a Human.
   */
  
  public int getCooldown() {
    return cooldown;
  }
  
  /**
   * eligibility
   * This helper method returns a boolean value based on whether a Human is of the opposite sex of a neighbouring
   * Human: true for eligible and false for ineligible.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   * @Param targetX, an integer that stores the x-coordinate of the Human being romanced.
   * @Param targetY, an integer that stores the y-coordinate of the Human being romanced.
   */
  
  public boolean eligibility(Organism[][] map, int targetX, int targetY) {
    if (((((Human)map[coordinateY][coordinateX]).gender == true) && 
          (((Human)map[targetY][targetX]).gender == false)) ||
         (((Human)map[coordinateY][coordinateX]).gender == true) && 
         (((Human)map[targetY][targetX]).gender == false)) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * procreate
   * This method spawns a newborn Human in the vicinity as a result of the procreation of two neighbouring Humans of
   * the opposite sex, setting their cooldowns for procreation to ten ticks and cancelling their next movement on the
   * map.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   * @Param targetX, an integer that stores the x-coordinate of the Human being romanced.
   * @Param targetY, an integer that stores the y-coordinate of the Human being romanced.
   */
  
  public void procreate(Organism[][] map, int targetX, int targetY) {
    ((Human)map[coordinateY][coordinateX]).setCooldown(LONGEST);
    ((Human)map[targetY][targetX]).setCooldown(LONGEST);
    ((Human)map[targetY][targetX]).setMovement(true);
    if (!(coordinateX - 1 < 0) && (map[coordinateY][coordinateX - 1] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(coordinateX - 1, coordinateY, 
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(coordinateX - 1, coordinateY, 
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(coordinateX + 1 >= map[0].length) && (map[coordinateY][coordinateX + 1] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(coordinateX + 1, coordinateY,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(coordinateX + 1, coordinateY,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(coordinateY - 1 < 0) && (map[coordinateY - 1][coordinateX] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(coordinateX, coordinateY - 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(coordinateX, coordinateY - 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(coordinateY + 1 >= map.length) && (map[coordinateY + 1][coordinateX] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(coordinateX, coordinateY + 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(coordinateX, coordinateY + 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(targetX - 1 < 0) && (map[targetY][targetX - 1] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(targetX - 1, targetY, 
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(targetX - 1, targetY, 
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(targetX + 1 >= map[0].length) && (map[targetY][targetX + 1] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(targetX + 1, targetY,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(targetX + 1, targetY,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(targetY - 1 < 0) && (map[targetY - 1][targetX] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(targetX, targetY - 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(targetX, targetY - 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    } else if (!(targetY + 1 >= map.length) && (map[targetY + 1][targetX] == null)) {
      if ((int)Math.floor(Math.random() * 2) == 0) {
        Simulation.spawnHuman(targetX, targetY + 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, true);
      } else {
        Simulation.spawnHuman(targetX, targetY + 1,
                              (map[coordinateY][coordinateX].health + map[targetY][targetX].health) / 2, 0, false);
      }
    }
  }
}