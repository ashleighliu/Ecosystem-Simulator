/**
 * Zombie.java
 * Ashleigh Liu
 * December 5, 2021
 * This code contains the Zombie class that inherits x-coordinate, y-coordinate, health, and age variables from
 * the Biped class.
 */

class Zombie extends Biped {
  //Constructor for Zombie, inheriting, coordinateX, coordinateY, health, and age from Biped.
  Zombie(int coordinateX, int coordinateY, int health, int age) {
    super(coordinateX, coordinateY, health, age);
  }
  
  /**
   * consume
   * This method increases the health of a Zombie based on the health of a Human and kills the provoked Human.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   * @Param targetX, an integer that stores the x-coordinate of the Human being consumed.
   * @Param targetY, an integer that stores the y-coordinate of the Human being consumed.
   */
  
  public void consume(Organism[][] map, int targetX, int targetY) {
    map[coordinateY][coordinateX].health += map[targetY][targetX].health;
    ((Biped)map[targetY][targetX]).perish(map);
  }
  
  /**
   * infect
   * This method turns a provoked Human into a Zombie, simulating infection.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   * @Param targetX, an integer that stores the x-coordinate of the Human being infected.
   * @Param targetY, an integer that stores the y-coordinate of the Human being infected.
   */
  
  public void infect(Organism[][] map, int targetX, int targetY) {
    Simulation.convertZombie(targetX, targetY, map[targetY][targetX].health, map[targetY][targetX].age);
    ((Biped)map[targetY][targetX]).setMovement(true);
  }
}