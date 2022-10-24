/**
 * Organism.java
 * Ashleigh Liu
 * December 5, 2021
 * This code contains the Organism class that initializes the x-coordinate, y-coordinate, health, and age variables 
 * that all Organism-based classes share.
 * The Organism class is the class that the Biped and Vegetable classes derive from.
 */

abstract class Organism implements Comparable {
  public int coordinateX;
  public int coordinateY;
  public int health;
  public int age;
  public boolean gender;
  
  //First constructor for Organism, initializing coordinateX, coordinateY, health, and age.
  Organism(int coordinateX, int coordinateY, int health, int age) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
    this.health = health;
    this.age = age;
  }
  
  //Second constructor for Organism, initializing coordinateX, coordinateY, health, age, and gender.
  Organism(int coordinateX, int coordinateY, int health, int age, boolean gender) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
    this.health = health;
    this.age = age;
    this.gender = gender;
  }
  
  /**
   * perish
   * This method turns a tile on the simulation map to null, simulating death.
   * @Param map, a two-dimensional object array that stores the map of the simulation.
   */
  
  public void perish(Organism[][] map) {
    map[coordinateY][coordinateX] = null;
  }
  
  /**
   * compareTo
   * This method compares the health of two Organisms and returns an integer based on the difference of the healths.
   * @Param o, an object that stores an Organism
   * @Return an integer based on the difference of the healths: positive if lesser than and negative if greater than.
   */
  
  public int compareTo(Object o) {
    Organism p = (Organism)o;
    return (this.health - p.health);
  }
}