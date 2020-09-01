This program simulates a 2x2 Rubik's cube, and displays it in a 2d grid so that the user can see all side of the cube at once.
I've drawn it so that the when you first run the program the white is the top face, orange is the left face, green is the front face, red is the right face, blue is the back face, and yellow is the bottom face.
The user should hold their cube the same with the cube is displayed on the screen (green as the front face and white as the top face).
Within the program the user can get a random scramble aswell as the solution for that scramble.
The user can interact with the program to get a step by step turotiral on how to solve the displayed scramble.
Inbetween the 4 button and the displayed cube is the solution to solve the cube.
Below the cube is the scramble.
Both the solution and scramble are made up of a string of letters.
Each letter represent a move that can be done on the cube. 
This link has the notation for the different move, the each move is also described in comments inside of the Cube class. 
Although the images show a 3x3 cube, the moves are still the same. 
https://jperm.net/3x3/moves
The next two links ware the algorithms used to solve to the cube. 
https://jperm.net/algs/2x2/oll
https://jperm.net/algs/2x2/pbl
At the top of the screen are 4 arrows used to interact with the cube and solution. 
The on labeled "Scramble" generates a random scrambles and displayed cube used that scramble. 
The button labled "Reset" resets the cube back to its scrambled version. 
This way if the user messes up during the solve they can easily go back to the beginning.
The left and right arrows next to the "Scramble" and "Reset" button allows the user to move through the solution and the displayed cube will follow along. 
In the solution is a red letter, that letter represents the move that will be execute if the user were to press the right arrow button.
If the user pressed the left arrow, it'll undo a move in the move left of the red letter. 
To run the program run the Driver class.
