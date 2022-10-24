/**
 * Vegetable.java
 * Ashleigh Liu
 * December 5, 2021
 * This code contains the Vegetable class that inherits x-coordinate, y-coordinate, health, and age variables from
 * the Organism class.
 */

class Vegetable extends Organism {
  private int cooldown = 0;
  private int MATURITY = 18;
  
  //Constructor for Vegetable, inheriting coordinateX, coordinateY, health, and age from Organism.
  Vegetable(int coordinateX, int coordinateY, int health, int age) {
    super(coordinateX, coordinateY, health, age);
  }
  
  /**
   * setCooldown
   * This method sets the cooldown in ticks for proliferation on a Vegetable.
   * @Param cooldown, an integer that stores the value that cooldown is to be set to.
   */
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  /**
   * getCooldown
   * This method returns the current cooldown for proliferation on a Vegetable.
   * @Return the current cooldown for proliferation on a Vegetable.
   */
  
  public int getCooldown() {
    return cooldown;
  }
  
  /**
   * proliferate
   * This method causes a vegetable to spread out in all four directions, simulating proliferation.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   */
  
  public void proliferate(Organism[][] map) {
    if ((coordinateX != 0) && (map[coordinateY][coordinateX - 1] == null) && (age >= MATURITY)) {
      Simulation.spawnVegetable(coordinateX - 1, coordinateY, (int)Math.ceil(Math.random() * 25), 0);
    }
    if ((coordinateX != map[0].length - 1) && (map[coordinateY][coordinateX + 1] == null) && (age >= MATURITY)) {
      Simulation.spawnVegetable(coordinateX + 1, coordinateY, (int)Math.ceil(Math.random() * 25), 0);
    }
    if ((coordinateY != 0) && (map[coordinateY - 1][coordinateX] == null) && (age >= MATURITY)) {
      Simulation.spawnVegetable(coordinateX, coordinateY - 1, (int)Math.ceil(Math.random() * 25), 0);
    }
    if ((coordinateY != map.length - 1) && (map[coordinateY + 1][coordinateX] == null) && (age >= MATURITY)) {
      Simulation.spawnVegetable(coordinateX, coordinateY + 1, (int)Math.ceil(Math.random() * 25), 0);
    }
  }
}