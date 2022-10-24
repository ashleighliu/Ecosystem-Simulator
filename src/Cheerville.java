import java.util.Scanner;

/**
 * Cheerville.java
 * Ashleigh Liu
 * December 5, 2021
 * This code runs a simulation of a post-apocalyptic scenario in Cheerville, the hometown of Duber McDonlad.
 */

class Cheerville {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int amount;
    boolean placement;
    int humans = 1;
    int tick = 0;
    System.out.println("Recommended Size:Vegetables:Humans:Zombies Ratio = 25:120:50:10");
    System.out.print("Enter size of the map: ");
    amount = input.nextInt();
    Organism[][] map = new Organism[amount][amount];
    Simulation ecosystem = new Simulation(map);
    
    System.out.print("Enter the amount of vegetables: ");
    amount = input.nextInt();
    for (int i = 0; i < amount; i++) {
      placement = ecosystem.spawnVegetable((int)Math.floor(Math.random() * map[0].length),
                                           (int)Math.floor(Math.random() * map.length),
                                           (int)Math.ceil(Math.random() * 25) + 25, 0);
      if (placement == false) {
        i--;
      }
    }
    
    System.out.print("Enter the amount of humans: ");
    amount = input.nextInt();
    for (int j = 0; j < amount; j++) {
      if (j <= amount / 2) {
        placement = ecosystem.spawnHuman((int)Math.floor(Math.random() * map[0].length),
                                         (int)Math.floor(Math.random() * map.length), 
                                         (int)Math.ceil(Math.random() * 50) + 75, 18, true);
      } else {
        placement = ecosystem.spawnHuman((int)Math.floor(Math.random() * map[0].length),
                                         (int)Math.floor(Math.random() * map.length), 
                                         (int)Math.ceil(Math.random() * 50) + 75, 18, false);
      }
      if (placement == false) {
        j--;
      }
    }
    
    System.out.print("Enter the amount of zombies: ");
    amount = input.nextInt();
    for (int k = 0; k < amount; k++) {
      placement = ecosystem.spawnZombie((int)Math.floor(Math.random() * map[0].length),
                                        (int)Math.floor(Math.random() * map.length), 
                                        (int)Math.ceil(Math.random() * 50) + 100, 0);
      if (placement == false) {
        k--;
      }
    }
    
    Window screen = new Window("Cheerville Simulation", map);
    while (humans > 0) {
      humans = 0;
      screen.refresh();
      try{Thread.sleep(250);} catch(Exception e){};
      for (int l = 0; l < map.length; l++) {
        for (int m = 0; m < map[l].length; m++) {
          //Check the population of Humans; if they have gone extinct, terminate the program.
          if (map[l][m] instanceof Human) {
            humans++;
          }
          //Check if selected Biped is able to move or not, preventing numerous moves during a single tick.
          if ((map[l][m] instanceof Biped) && (((Biped)map[l][m]).getMovement() == false)) {
            ((Biped)map[l][m]).setMovement(true); 
            ((Biped)map[l][m]).move(map);
            //Check if selected Vegetable is able to proliferate or not based on cooldown, preventing overgrowth.
          } else if ((map[l][m] instanceof Vegetable) && 
                     (((Vegetable)map[l][m]).getCooldown() == 0) &&
                     ((int)Math.floor(Math.random() * 11) == 0)) {
            ((Vegetable)map[l][m]).setCooldown(35);
            ((Vegetable)map[l][m]).proliferate(map);
          }
          //Reduce cooldown of procreation on a Human with a cooldown greater than zero.
          if ((map[l][m] instanceof Human) && (((Human)map[l][m]).getCooldown() > 0)) {
            ((Human)map[l][m]).setCooldown(((Human)map[l][m]).getCooldown() - 1);
            //Reduce cooldown of proliferation on a Vegetable with a cooldown greater than zero.
          } else if ((map[l][m] instanceof Vegetable) && 
                     (((Vegetable)map[l][m]).getCooldown() > 0)) {
            ((Vegetable)map[l][m]).setCooldown(((Vegetable)map[l][m]).getCooldown() - 1);
          }
          //Increase nutrition of a Vegetable based on probability (1/25), simulating growth.
          if ((map[l][m] instanceof Vegetable) && ((int)Math.floor(Math.random() * 26) == 0)) {
            ((Vegetable)map[l][m]).health++;
          }
        }
      }
      for (int n = 0; n < map.length; n++) {
        for (int o = 0; o < map[n].length; o++) {
          if (map[n][o] instanceof Organism) {
            map[n][o].age++;
            //Inflict death upon Organisms with zero health.
            if (map[n][o].health == 0) {
              map[n][o].perish(map);
              //Inflict death upon Organisms, excluding Zombies, with old age based on probability (1/150).
            } else if ((map[n][o].age >= 65) && ((int)Math.floor(Math.random() * 151) == 0) && 
                       !(map[n][o] instanceof Zombie)) {
              map[n][o].perish(map);
            }
          }
          //At the end of each tick, enable all Bipeds to be able to move again for the next tick.
          if ((map[n][o] instanceof Biped) && (((Biped)map[n][o]).getMovement() == true)) {
            ((Biped)map[n][o]).setMovement(false);
          }
        }
      }
      tick++;
      //Every fifth tick spawns a Vegetable.
      if ((tick % 5) == 0) {
        placement = ecosystem.spawnVegetable((int)Math.floor(Math.random() * map[0].length),
                                             (int)Math.floor(Math.random() * map.length),
                                             (int)Math.ceil(Math.random() * 25) + 25, 0);
        while (placement == false) {
          placement = ecosystem.spawnVegetable((int)Math.floor(Math.random() * map[0].length),
                                               (int)Math.floor(Math.random() * map.length),
                                               (int)Math.ceil(Math.random() * 25) + 25, 0);
        }
      }
      /**
       * Due to the relative difficulty that Zombies experience while maintaining their population, a Zombie spawns on
       * every seventy-fifth tick; Zombies will always eventually succeed rather than the Humans.
       */
      if ((tick % 75) == 0) {
        placement = ecosystem.spawnZombie((int)Math.floor(Math.random() * map[0].length),
                                          (int)Math.floor(Math.random() * map.length), 
                                          (int)Math.ceil(Math.random() * 50) + 100, 0);
        while (placement == false) {
          placement = ecosystem.spawnZombie((int)Math.floor(Math.random() * map[0].length),
                                            (int)Math.floor(Math.random() * map.length), 
                                            (int)Math.ceil(Math.random() * 50) + 100, 0);
        }
      }
    }
  }
}