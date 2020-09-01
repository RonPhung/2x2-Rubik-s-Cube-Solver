package CubeSolver;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel{
	//cube used to for finding the solution
	private Cube solvingCube = new Cube();
	//cube used for display and user interaction
	private Cube displayCube = new Cube();
	//index used to traverse through the solution
	private int solutionIndex = 0;
	//solver object used to solve the cube
	private Solver s;

	/**
	 * Constructor for panel class 
	 */
	public Panel()	{
	}

	/**
	 * Moves forward on the solution list towards solving cube
	 */
	public void moveRight() {
		//checks if the index is within the range of the solution list
		if(solutionIndex < displayCube.getSolution().size()) {
			String temp = displayCube.getSolution().get(solutionIndex);
			//determines what the displayCube should make
			switch(temp) {
			case "U":
				displayCube.uMove();
				break;
			case "L":
				displayCube.lMove();
				break;
			case "F":
				displayCube.fMove();
				break;
			case "R":
				displayCube.rMove();
				break;
			case "B":
				displayCube.bMove();
				break;
			case "D":
				displayCube.dMove();
				break;
			case "U'":
				displayCube.uMoveInvert();
				break;
			case "L'":
				displayCube.lMoveInvert();
				break;
			case "F'":
				displayCube.fMoveInvert();
				break;
			case "R'":
				displayCube.rMoveInvert();
				break;
			case "B'":
				displayCube.bMoveInvert();
				break;
			case "D'":
				displayCube.dMoveInvert();
				break;
			case "U2":
				displayCube.U2();
				break;
			case "L2":
				displayCube.L2();
				break;
			case "F2":
				displayCube.F2();
				break;
			case "R2":
				displayCube.R2();
				break;
			case "B2":
				displayCube.B2();
				break;
			case "D2":
				displayCube.D2();
				break;
			case "X":
				displayCube.xMove();
				break;
			case "Y":
				displayCube.yMove();
				break;
			case "Z":
				displayCube.zMove();
				break;
			case "X'":
				displayCube.xMoveInvert();
				break;
			case "Y'":
				displayCube.yMoveInvert();
				break;
			case "Z'":
				displayCube.zMoveInvert();
				break;
			case "X2":
				displayCube.X2();
				break;
			case "Y2":
				displayCube.Y2();
				break;
			case "Z2":
				displayCube.Z2();
				break;
			}
			solutionIndex++;
		}
	}

	/**
	 * Moves backwards on the solution list, undoing moves towards it's scrambled version
	 */
	public void moveLeft() {
		//checks if the index hasn't reach the beginning of the solution list
		if(solutionIndex > 0) {
			solutionIndex--;
			String temp = displayCube.getSolution().get(solutionIndex);
			//determines what move in the solution list and does it's opposite to undo the move
			switch(temp) {
			case "U":
				displayCube.uMoveInvert();
				break;
			case "L":
				displayCube.lMoveInvert();
				break;
			case "F":
				displayCube.fMoveInvert();
				break;
			case "R":
				displayCube.rMoveInvert();
				break;
			case "B":
				displayCube.bMoveInvert();
				break;
			case "D":
				displayCube.dMoveInvert();
				break;
			case "U'":
				displayCube.uMove();
				break;
			case "L'":
				displayCube.lMove();
				break;
			case "F'":
				displayCube.fMove();
				break;
			case "R'":
				displayCube.rMove();
				break;
			case "B'":
				displayCube.bMove();
				break;
			case "D'":
				displayCube.dMove();
				break;
			case "U2":
				displayCube.U2();
				break;
			case "L2":
				displayCube.L2();
				break;
			case "F2":
				displayCube.F2();
				break;
			case "R2":
				displayCube.R2();
				break;
			case "B2":
				displayCube.B2();
				break;
			case "D2":
				displayCube.D2();
				break;
			case "X":
				displayCube.xMoveInvert();
				break;
			case "Y":
				displayCube.yMoveInvert();
				break;
			case "Z":
				displayCube.zMoveInvert();
				break;
			case "X'":
				displayCube.xMove();
				break;
			case "Y'":
				displayCube.yMove();
				break;
			case "Z'":
				displayCube.zMove();
				break;
			case "X2":
				displayCube.X2();
				break;
			case "Y2":
				displayCube.Y2();
				break;
			case "Z2":
				displayCube.Z2();
				break;
			}
		}
	}

	/**
	 * Checks that all squares on a face have the same color, this is mainly used for testing
	 * @param face A 2D array that holds the colors of a face
	 * @return True if all squares have the same color, false otherwise
	 */
	public boolean checkFace(String [][] face) {
		String firstColor = face[0][0];
		if(!face[0][1].equals(firstColor))
			return false;
		if(!face[1][1].equals(firstColor))
			return false;
		if(!face[1][0].equals(firstColor))
			return false;
		return true;
	}

	/**
	 * Checks if the cube has been solve, otherwise an error is thrown 
	 */
	public void test() {
		int x;
		
		for(String s : displayCube.getSolution()) {
			moveRight();
		}

		if(!checkFace(displayCube.getFaceU()))
			x = 1/0;
		if(!checkFace(displayCube.getFaceL()))
			x = 1/0;
		if(!checkFace(displayCube.getFaceF()))
			x = 1/0;
		if(!checkFace(displayCube.getFaceR()))
			x = 1/0;
		if(!checkFace(displayCube.getFaceB()))
			x = 1/0;
		if(!checkFace(displayCube.getFaceD()))
			x = 1/0;
	}

	/**
	 * Solves the solving cube and gives the solution to the display cube
	 */
	public void solve() {
		s.solveCube();
		displayCube.setSolution(solvingCube.getSolution());
		displayCube.solvingModeOff();
	}

	/**
	 * Undo all move made on the cube to return it to its original scramble
	 */
	public void reset() {
		while(solutionIndex != 0) {
			moveLeft();
		}
	}

	/**
	 * Randomly performs R, F, and U moves to get the best scramble without performing the same move twice
	 */
	public void scramble2() {
		//both solving and display cube are reinitialized to created a new scramble
		solvingCube = new Cube();
		displayCube = new Cube();
		solutionIndex = 0;
		//both cubes are set to scrambling mode
		solvingCube.scramblingOn();
		displayCube.scramblingOn();

		//stores a value to represent the previous move
		int lastNum = -1;
		//represent represents either a R, U, or F move
		int temp = (int) (Math.random() * 3);
		for(int i = 0; i < 10; i++) {
			//represent which version of a move gets performed
			//EX) R or R' or R2
			int thirds = (int) (Math.random() * 3);
			while(temp == lastNum)
				temp = (int) (Math.random() * 3);
			//determines which move is done
			switch(temp) {
			case 0:
				//determines which version of F is done
				switch(thirds) {
				case 0: 
					solvingCube.fMove();
					displayCube.fMove();
					break;
				case 1:
					solvingCube.fMoveInvert();
					displayCube.fMoveInvert();
					break;
				case 2:
					solvingCube.F2();
					displayCube.F2();
					break;
				}
				break;
			case 1:
				//determines which version of U is done
				switch(thirds) {
				case 0: 
					solvingCube.uMove();
					displayCube.uMove();
					break;
				case 1:
					solvingCube.uMoveInvert();
					displayCube.uMoveInvert();
					break;
				case 2:
					solvingCube.U2();
					displayCube.U2();
					break;
				}
				break;
			case 2:
				//determines which version of R is done
				switch(thirds) {
				case 0: 
					solvingCube.rMove();
					displayCube.rMove();
					break;
				case 1:
					solvingCube.rMoveInvert();
					displayCube.rMoveInvert();
					break;
				case 2:
					solvingCube.R2();
					displayCube.R2();
					break;
				}
				break;
			}
			//the previous move is stored as the current move
			lastNum = temp;
		}

		//solving cube switches from scrambling mode to solving mode
		solvingCube.scramblingOff();
		solvingCube.solvingModeOn();
		//display cube switches off scrambling mode
		displayCube.scramblingOff();
		//a solver object is initialize with the solving cube
		s = new Solver(solvingCube);
	}

	/**
	 * Paints the panel with a blank background, cube, scramble, and solution
	 */
	public void paintComponent(Graphics g)	{
		//sets the font for any string being drawn from methods below
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		blank(g);
		drawCube(g);
		//draws the solution and scramble once the cube has been solved
		if(displayCube.getSolution().size() != 0) {
			drawSolution(g);
			drawScramble(g);
		}
	}

	/**
	 * Cover the panel with a solid color
	 * @param g Graphics object
	 */
	public void blank(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0 , getWidth(), getHeight());
	}

	/**
	 * 	Displays the scramble
	 * @param g Graphics object
	 */
	public void drawScramble(Graphics g) {
		//hold the moves from the scramble
		String str = "Scramble: ";
		//loops through the scramble list and add them to a string
		for(String temp : displayCube.getScramble()) {
			str += temp + " ";
		}
		//removes the last space character from the string
		str.substring(0, str.length() - 1);
		//the height and width of the string is calculated
		int width = g.getFontMetrics().stringWidth(str);
		int height = g.getFontMetrics().getHeight();
		//the scramble is displayed on the panel
		g.drawString(str, getWidth()/2 - width/2, 1150 + (height / 2));
	}

	/**
	 * Displays the solution
	 * @param g Graphics object
	 */
	public void drawSolution(Graphics g) {
		//array list used to store a copy of the solution
		ArrayList <String> s = new ArrayList<String> ();
		//loops through solution list and adds them to array list s
		for(String temp : displayCube.getSolution()) {
			s.add(temp);
		}
		//store the toString of the array list
		int count = 0;
		//hold the location of the cursor, used to determine what move will be performed by the user
		int cursorLocation = -1;
		//determines whether the cursor move has been found or not
		boolean once = false;
		//holds the move the cursor is on
		String cursorLetter = "";
		//checks if the index is within the range of the solution list
		if(solutionIndex < displayCube.getSolution().size()) {
			cursorLetter = s.get(solutionIndex) + " ";
		}
		//whilst the solution list has moves
		while(s.size() > 0) {
			int index = 0;
			String tempStr = "";
			while(s.size() > 0) {
				//this allows for only 30 moves to be displayed on a line
				if(index > 30)
					break;
				//checks if the index is equal to the index that the cursor is on
				//stores the of the string so it knows where to place the cursor letter
				if(index + (count * 31) == solutionIndex) {
					cursorLocation = g.getFontMetrics().stringWidth(tempStr);
					once = true;
				}
				//the move is added to the string and removed from the list
				tempStr += s.get(0);
				tempStr += " ";
				s.remove(0);
				index++;
			}
			//the last space character is removed from the string
			tempStr.substring(0,tempStr.length() - 1);
			//stores the width of the string
			int width = g.getFontMetrics().stringWidth(tempStr);
			//draws the solution
			g.drawString(tempStr, getWidth()/2 - width/2, 100 + ((count) * g.getFontMetrics().getHeight()));	
			//if the location of the cursor is found then it is drawn
			if(cursorLocation != -1 && once && !cursorLetter.equals("")) {
				g.setColor(Color.red);
				g.drawString(cursorLetter, getWidth()/2 - width/2 + cursorLocation, 100 + ((count) *  g.getFontMetrics().getHeight() ));
				g.setColor(Color.black);
				once = false;
			}
			count++;
		}
	}

	/**
	 * Displays the cube
	 * @param g Graphics object
	 */
	public void drawCube(Graphics g) {
		//Graphics2D object to set the thickness of shapes
		Graphics2D g1 = (Graphics2D)g;
		//setting the graphics object color to black
		g.setColor(Color.black);
		//stores the original stroke size
		Stroke firstStroke = g1.getStroke();
		//setting the grpahics2d stroke size/thickness of shapes
		//this is for the individual squares on each face
		g1.setStroke(new BasicStroke(3));
		//used to store a face of the cube
		ArrayList<String[][]> faces = displayCube.getCube();
		//length of the side of the cube
		int cubeLength = 150;
		//x and y coordinates for the location of the square on the face
		int y = 0;
		int x = 0;

		//stores the top face
		String [][] tempUp = faces.get(0);
		y = 0;
		for(String[] arr : tempUp) {
			x = 0;
			//determines the color of a square on the face and sets the graphics to that color
			for(String s : arr) {
				switch(s) {
				case"r":
					g.setColor(Color.red);
					break;
				case"y":
					g.setColor(Color.yellow);
					break;
				case"g":
					g.setColor(Color.green);
					break;
				case"b":
					g.setColor(Color.blue);
					break;
				case"w":
					g.setColor(Color.white);
					break;
				case"o":
					g.setColor(Color.orange);
					break;
				}
				//the square for that face is drawn
				g.fillRect(2 * cubeLength + (cubeLength * x),200 + (cubeLength * y),cubeLength,cubeLength);
				g.setColor(Color.black);
				g.drawRect(2 * cubeLength + (cubeLength * x),200 + (cubeLength * y),cubeLength,cubeLength);
				x++;
			}
			y++;
		}

		for(int i = 0; i < 4; i++) {
			//stores a face from the cube
			String [][] tempMiddle = faces.get(i + 1);
			y = 0;
			for(String[] arr : tempMiddle) {
				x = 0;
				for(String s : arr) {
					//determines the color of a square on the face and sets the graphics to that color
					switch(s) {
					case"r":
						g.setColor(Color.red);
						break;
					case"y":
						g.setColor(Color.yellow);
						break;
					case"g":
						g.setColor(Color.green);
						break;
					case"b":
						g.setColor(Color.blue);
						break;
					case"w":
						g.setColor(Color.white);
						break;
					case"o":
						g.setColor(Color.orange);
						break;
					}
					//the square for that face is drawn
					g.fillRect((i * 2 * cubeLength) + (cubeLength * x),200 + (2 * cubeLength) + (cubeLength * y),cubeLength,cubeLength);
					g.setColor(Color.black);
					g.drawRect((i * 2 * cubeLength) + (cubeLength * x),200 + (2 * cubeLength) + (cubeLength * y),cubeLength,cubeLength);
					x++;
				}
				y++;
			}
		}

		//the bottom face is stored
		String [][] tempDown = faces.get(5);
		y = 0;
		for(String[] arr : tempDown) {
			x = 0;
			for(String s : arr) {
				//determines the color of a square on the face and sets the graphics to that color
				switch(s) {
				case"r":
					g.setColor(Color.red);
					break;
				case"y":
					g.setColor(Color.yellow);
					break;
				case"g":
					g.setColor(Color.green);
					break;
				case"b":
					g.setColor(Color.blue);
					break;
				case"w":
					g.setColor(Color.white);
					break;
				case"o":
					g.setColor(Color.orange);
					break;
				}
				//the square for that face is drawn
				g.fillRect(300 + (cubeLength * x),200 + (4 * cubeLength) + (cubeLength * y),cubeLength,cubeLength);
				g.setColor(Color.black);
				g.drawRect(300 + (cubeLength * x),200 + (4 * cubeLength) + (cubeLength * y),cubeLength,cubeLength);
				x++;
			}
			y++;
		}
		//changes the thickness of shapes 
		//this is used to outline the faces of the cube
		g1.setStroke(new BasicStroke(6));
		//thick squares are draw to make it easier to differentiate the different faces on the cube
		g.drawRect((2 * cubeLength), 200, (2 * cubeLength), (2 * cubeLength));
		g.drawRect((2 * cubeLength), 800, (2 * cubeLength), (2 * cubeLength));
		for(int i = 0; i < 4; i++) {
			g.drawRect(i * (2 * cubeLength), 200 + (2 * cubeLength), (2 * cubeLength), (2 * cubeLength));
		}
		g1.setStroke(firstStroke);
	}
}