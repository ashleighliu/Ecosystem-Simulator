# Ecosystem Simulator
A simulation of an ecosystem, depicting a facetious zombie apocalypse.
* Ecosystem Simulation project introducing the use of JFrame and demonstrating basic principles of object-oriented programming
* Humans of opposite gender that encounter each other will produce another human of random gender
* Zombies that encounter a human will infect the human
* Plants periodically clone themselves in all four cardinal directions
* Humans that move onto a tile belonging to a plant will consume it, gaining its nutrition as health and removing it from the tile
* Zombies that move onto a tile belonging to a plant will trample it, removing it from the tile
* Each tick decreases the health of every organism by one
    * When the health of an organism reaches zero, it will perish
* When all humans have been infected, the simulation ends
