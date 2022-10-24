/**
 * Simulation.java
 * Ashleigh Liu
 * December 5, 2021
 * This code manages the basic functions, particularly Organism spawning, of the simulation.
 */

class Simulation {
  public static Organism[][] map;
  
  Simulation(Organism[][] map) {
    this.map = map;
  }
  
  /**
   * spawnVegetable
   * This method spawns a Vegetable and returns a boolean value based on success.
   * @Param coordinateX, an integer that stores the initial x-coordinate of the imminent Vegetable.
   * @Param coordinateY, an integer that stores the initial y-coordinate of the imminent Vegetable.
   * @Param health, an integer that stores the initial nutrition, or health, of the imminent Vegetable.
   * @Param age, an integer that stores the initial age of the imminent Vegetable.
   * @Return a boolean value based on success: true for being placed and false for not being placed.
   */
  
  public static boolean spawnVegetable(int coordinateX, int coordinateY, int health, int age) {
    if (map[coordinateY][coordinateX] == null) {
      map[coordinateY][coordinateX] = new Vegetable(coordinateX, coordinateY, health, age);
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * spawnHuman
   * This method spawns a Human and returns a boolean value based on success.
   * @Param coordinateX, an integer that stores the initial x-coordinate of the imminent Human.
   * @Param coordinateY, an integer that stores the initial y-coordinate of the imminent Human.
   * @Param health, an integer that stores the initial health of the imminent Human.
   * @Param age, an integer that stores the initial age of the imminent Human.
   * @Param gender, a boolean value that stores the gender of the imminent Human.
   * @Return a boolean value based on success: true for being placed and false for not being placed.
   */
  
  public static boolean spawnHuman(int coordinateX, int coordinateY, int health, int age, boolean gender) {
    if (map[coordinateY][coordinateX] == null) {
      map[coordinateY][coordinateX] = new Human(coordinateX, coordinateY, health, age, gender);
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * spawnZombie
   * This method spawns a Zombie and returns a boolean value based on success.
   * @Param coordinateX, an integer that stores the initial x-coordinate of the imminent Zombie.
   * @Param coordinateY, an integer that stores the initial y-coordinate of the imminent Zombie.
   * @Param health, an integer that stores the initial health of the imminent Zombie.
   * @Param age, an integer that stores the initial age of the imminent Zombie.
   * @Return a boolean value based on success: true for being placed and false for not being placed.
   */
  
  public static boolean spawnZombie(int coordinateX, int coordinateY, int health, int age) {
    if (map[coordinateY][coordinateX] == null) {
      map[coordinateY][coordinateX] = new Zombie(coordinateX, coordinateY, health, age);
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * convertZombie
   * This method turns a Human into a Zombie by replacing the tile of the provoked Human with a Zombie, inheriting its
   * qualities, such as health and age.
   * @Param targetX, an integer that stores the x-coordinate of the provoked Human.
   * @Param targetY, an integer that stores the y-coordinate of the provoked Human.
   * @Param health, an integer that stores the health of the provoked Human.
   * @Param age, an integer that stores the age of the provoked Human.
   */
  
  public static void convertZombie(int targetX, int targetY, int health, int age) {
    map[targetY][targetX] = new Zombie(targetX, targetY, health, age);
  }
}
