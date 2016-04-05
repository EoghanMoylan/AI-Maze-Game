# AI-Maze-Game

##How To Run
Navigate to the "JARS" folder and type the command "java -cp AiMazeGame.jar ie.gmit.sw.Runner"

##Features
The game spawns a number of enemies at the begining. They come in two forms, and AStar enemy and And Iterative DFS enemy
(named after their search algorithms). They will track(or hunt) the player across the map. They will travel to the players last known 
location and once it is reached will begin hunting again provided the player is not present.

There are a number of blocks that add more functionality to the game. The blue "help" box will generate a path towards the exit node
for the player for a few seconds.
The Red TNT (or Bomb) box will turn the tiles adjacent to the player into fire, killing enemies, using a limited DFS.
The sword can be collected adding to your weapon count, which reduces the amount of damage taken when fighting enemies. However, 
the sword can only be used once no matter the weapon count.
When fighting the weapon count and the players health are taken into account by the Fuzzy Logic system in place. The higher of each will 
result in less damage.

##The Goal
The goal of the game is to find the exit node and survive the maze in as few steps as possible and as much health as possible.

###Known Issues
1) Sometimes hitting the blue or red boxes will not generate the associated sprites on the adjacent tiles. You will notice, however, that these can be seen from the map view by pressing "z".

2)The player can be injured when loading in as enemies are spawned near by and attack before the game has loaded.

3)Occasional crashing when activating boxes. Rare and can't replicate, possibily just my machine. 

4) Player can spawn very close to end node, or end node can spawn at cross section, making it unreachable

####NOTES
Even though there are several maze algorithms present, only the one implemented (recurive backtracker) actually has full functionality.

##References:
[ELLERS MAZE](http://www.neocomputer.org/projects/eller.html)

[RECURSIVE BACK TRACKER](http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking)

[THREADS](http://www.tutorialspoint.com/java/java_multithreading.htm)

[GUI AND HUD](https://github.com/DiarmuidByrne/AI-Assignment-Heuristic-Maze-Game)

[FUZZY LOGIC](http://www.tutorialspoint.com/java/java_multithreading.htm)

Game View, search algorithms etc. - John Healy
