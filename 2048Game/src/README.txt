Patrick Phillips
Class ID:107

There is a single class that extends JFrame, use Java compiler and run.
Use arrow keys or WASD to make moves. 
Information is also printed to the console, if you want to look at it as well.

The game has a generate() function which generates a 2 or 4 in the array.
The move() and checkAndMerge() functions are the two main gameplay functions. The move() uses
a temporary array to store values and then copies them back to the original array. The checkAndMerge
function sees if any of the boxes in the given direction should merge, and then calls the move()
function again.
The updateBoard() function simply updates the jframe and the array of buttons that is used to 
display the game.
A keylistener is used to check for when the keys are pressed, and then calls the main game functions
The main method is at the bottom of the file, and just creates a copy of TheGame to run.