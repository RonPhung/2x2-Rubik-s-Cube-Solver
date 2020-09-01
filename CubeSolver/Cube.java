package CubeSolver;
import java.util.ArrayList;

public class Cube {
	//the six faces of a cube, each representing a color
	private String [][] faceF = {{"g","g"},{"g","g"}};
	private String [][] faceU = {{"w","w"},{"w","w"}};
	private String [][] faceR = {{"r","r"},{"r","r"}};
	private String [][] faceL = {{"o","o"},{"o","o"}};
	private String [][] faceD = {{"y","y"},{"y","y"}};
	private String [][] faceB = {{"b","b"},{"b","b"}};
	//set of moves that lead to the solution
	private ArrayList<String> solveMoves = new ArrayList<String>();
	//list holds the scramble
	private ArrayList<String> scramble = new ArrayList<String>();
	//variable describing the state of the cube
	private boolean solving = false;
	private boolean scrambling = false;
	private boolean doubleMove = false;

	/**
	 * Empty Constructor
	 */
	public Cube() {
		solveMoves = new ArrayList<String>();
	}

	/**
	 * Takes list of moves from the solveMoves list and reduces the moves required to solve the cube
	 */
	public void trimSolution() {
		//temporary list that stores the necessary moves
		ArrayList<String> tempMoves = new ArrayList<String>();
		//hold the single move used to compare with future moves
		String firstMove = "";
		String secondMove = "";
		//index of where to look
		int index = 0;
		//determines if a double move is detected
		boolean doubleIncrement = false;
		//loops through the solution list
		while(index < solveMoves.size()) {
			//index of the next move
			int tempIndex = 1;
			//number of duplicate moves
			int count = 0;
			//stores the move at index
			firstMove = solveMoves.get(index);
			//checks if the future move is within the list to prevent an index out of bounds error
			if(index + tempIndex < solveMoves.size()) {
				//the future move is stores
				secondMove = solveMoves.get(index + tempIndex);
				//counts the number of consecutive moves that are the same as the one at index
				while(index + tempIndex < solveMoves.size() && secondMove.equals(firstMove)) {
					count++;
					tempIndex++;
					if(index + tempIndex < solveMoves.size())
						secondMove = solveMoves.get(index + tempIndex);
				}
			}
			switch(count) {
			//case for if the next move is different from the one at index
			case 0:
				if(firstMove.equals("U'") && secondMove.equals("U2") ||
						secondMove.equals("U2") && firstMove.equals("U'")) {
					tempMoves.add("U");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("U") && secondMove.equals("U2") ||
						secondMove.equals("U2") && firstMove.equals("U")) {
					tempMoves.add("U'");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("L'") && secondMove.equals("L2") ||
						secondMove.equals("L2") && firstMove.equals("L'")) {
					tempMoves.add("L");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("L") && secondMove.equals("L2") ||
						secondMove.equals("L2") && firstMove.equals("L")) {
					tempMoves.add("L'");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("F'") && secondMove.equals("F2") ||
						secondMove.equals("F2") && firstMove.equals("F'")) {
					tempMoves.add("F");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("F") && secondMove.equals("F2") ||
						secondMove.equals("F2") && firstMove.equals("F") ) {
					tempMoves.add("F'");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("R'") && secondMove.equals("R2") ||
						secondMove.equals("R2") && firstMove.equals("R'")) {
					tempMoves.add("R");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("R") && secondMove.equals("R2") ||
						secondMove.equals("R2") && firstMove.equals("R")) {
					tempMoves.add("R'");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("B'") && secondMove.equals("B2") ||
						secondMove.equals("B2") && firstMove.equals("B'")) {
					tempMoves.add("B");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("B") && secondMove.equals("B2") ||
						secondMove.equals("B2") && firstMove.equals("B")) {
					tempMoves.add("B'");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("D'") && secondMove.equals("D2") ||
						secondMove.equals("D2") && firstMove.equals("D'") ) {
					tempMoves.add("D");
					index += 2;
					doubleIncrement = true;
				}
				else if(firstMove.equals("D") && secondMove.equals("D2") ||
						secondMove.equals("D2") && firstMove.equals("D")) {
					tempMoves.add("D'");
					index += 2;
					doubleIncrement = true;
				}
				else
					tempMoves.add(firstMove);
				break;
				//case for if there is one duplicate move, then a double move is used to place the two duplicate moves
			case 1:
				if(firstMove.equals("U") || firstMove.equals("U'"))
					tempMoves.add("U2");
				else if(firstMove.equals("L") || firstMove.equals("L'"))
					tempMoves.add("L2");
				else if(firstMove.equals("F") || firstMove.equals("F'"))
					tempMoves.add("F2");
				else if(firstMove.equals("R") || firstMove.equals("R'"))
					tempMoves.add("R2");
				else if(firstMove.equals("B") || firstMove.equals("B'"))
					tempMoves.add("B2");
				else if(firstMove.equals("D") || firstMove.equals("D'"))
					tempMoves.add("D2");
				else if(firstMove.equals("Y") || firstMove.equals("Y'"))
					tempMoves.add("Y2");
				else if(firstMove.equals("X") || firstMove.equals("X'"))
					tempMoves.add("X2");
				else if(firstMove.equals("Z") || firstMove.equals("Z'"))
					tempMoves.add("Z2");
				else {
					tempIndex--;
					tempMoves.add(firstMove);
				}
				break;
				//case for if there are 3 consecutive moves that are the same, the inverse of the move replaces is substituted
			case 2:
				switch(firstMove) {
				case "U":
					tempMoves.add("U'");
					break;
				case "L":
					tempMoves.add("L'");
					break;
				case "F":
					tempMoves.add("F'");
					break;
				case "R":
					tempMoves.add("R'");
					break;
				case "B":
					tempMoves.add("B'");
					break;
				case "D":
					tempMoves.add("D'");
					break;
				case "Y":
					tempMoves.add("Y'");
					break;
				default:
					tempIndex++;
					tempMoves.add(firstMove);
					break;
				}
				break;				
			}
			//index incremented if a double move is found
			if(!doubleIncrement)
				index += tempIndex;
			doubleIncrement = false;

		}
		//solution list is replaces with temporary list of moves
		solveMoves = tempMoves;
		tempMoves = new ArrayList <String>();
		index = 0;
		//loops through the solution list
		while(index < solveMoves.size()) {
			//stores the move at index
			firstMove = solveMoves.get(index);
			if(index + 1 < solveMoves.size()) {
				//stores the move at index
				firstMove = solveMoves.get(index);
				//stores the next move
				secondMove = solveMoves.get(index + 1);

				//list of checks that determine whether 2 moves can be reduced to a cube rotation
				if(firstMove.equals("R") && secondMove.equals("L'") ||
						firstMove.equals("L'") && secondMove.equals("R")) {
					tempMoves.add("X");
					index += 2;
				}
				else if(firstMove.equals("R2") && secondMove.equals("L2") ||
						firstMove.equals("L2") && secondMove.equals("R2")) {
					tempMoves.add("X2");
					index += 2;
				}
				else if(firstMove.equals("R'") && secondMove.equals("L") ||
						firstMove.equals("L") && secondMove.equals("R'")) {
					tempMoves.add("X'");
					index += 2;
				}
				else if(firstMove.equals("U") && secondMove.equals("D'") ||
						firstMove.equals("D'") && secondMove.equals("U")) {
					tempMoves.add("Y");
					index += 2;
				}
				else if(firstMove.equals("U2") && secondMove.equals("D2") ||
						firstMove.equals("D2") && secondMove.equals("U2")) {
					tempMoves.add("Y2");
					index += 2;
				}
				else if(firstMove.equals("U'") && secondMove.equals("D") ||
						firstMove.equals("D") && secondMove.equals("U'")) {
					tempMoves.add("Y'");
					index += 2;
				}
				else if(firstMove.equals("F") && secondMove.equals("B'") ||
						firstMove.equals("B'") && secondMove.equals("F")) {
					tempMoves.add("Z");
					index += 2;
				}
				else if(firstMove.equals("F2") && secondMove.equals("B2") ||
						firstMove.equals("B2") && secondMove.equals("F2")) {
					tempMoves.add("Z2");
					index += 2;
				}
				else if(firstMove.equals("F'") && secondMove.equals("B") ||
						firstMove.equals("B") && secondMove.equals("F'")) {
					tempMoves.add("Z'");
					index += 2;
				}
				//if the 2 moves can't be reduced to a cube rotation then its added to the list
				else {
					tempMoves.add(firstMove);
					index++;
				}
			}
			//no more moves are after the one at index and it gets added
			else {
				tempMoves.add(firstMove);
				index++;
			}
		}
		//solution list is replaces with temporary list of moves
		solveMoves = tempMoves;
	}

	/**
	 * Prints the solution list as well as it's size, this is mainly used for testing
	 */
	public void printSolution() {
		System.out.println(solveMoves);
		System.out.println(solveMoves.size());
	}

	/**
	 * Gets the solution list
	 * @return A list that hold the solution
	 */
	public ArrayList <String> getSolution() {
		return solveMoves;
	}

	/**
	 * Gets the scramble list
	 * @return A list that holds the scramble
	 */
	public ArrayList <String> getScramble() {
		return scramble;
	}

	/**
	 * Sets the solution list with the given list
	 * @param s The list that holds the new solution
	 */
	public void setSolution(ArrayList <String> s) {
		solveMoves = s;
	}

	/**
	 * Sets the cube in scrambling mode
	 */
	public void scramblingOn() {
		scrambling = true;
	}

	/**
	 * Sets the cube to no longer be in scrambling mode
	 */
	public void scramblingOff() {
		scrambling = false;
	}

	/**
	 * Sets the cube in solving mode
	 */
	public void solvingModeOn() {
		solving = true;
	}

	/**
	 * Sets the cube to no longer be in solving mode
	 */
	public void solvingModeOff() {
		solving = false;
	}

	/**
	 * Gets the front face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceF(){
		return faceF;
	}

	/**
	 * Gets the top face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceU(){
		return faceU;
	}

	/**
	 * Gets the left face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceL(){
		return faceL;
	}

	/**
	 * Gets the right face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceR(){
		return faceR;
	}

	/**
	 * Gets the back face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceB(){
		return faceB;
	}

	/**
	 * Gets the bottom face of the cube
	 * @return An array of colors represented by string
	 */
	public String [][] getFaceD(){
		return faceD;
	}

	/**
	 * Gets the faces of the cube
	 * @return An array list holding the faces of the cube
	 */
	public ArrayList<String[][]> getCube(){
		ArrayList<String[][]> faces = new ArrayList<String[][]>();
		faces.add(faceU);
		faces.add(faceL);
		faces.add(faceF);
		faces.add(faceR);
		faces.add(faceB);
		faces.add(faceD);
		return faces;
	}

	/**
	 * Sets the faces of the cube to the faces in the given array list
	 * @param arr Array list with faces for the cube
	 */
	public void setCube(ArrayList<String[][]> arr){
		//ArrayList<String[][]> faces = new ArrayList<String[][]>();
		faceU = arr.get(0);
		faceL = arr.get(1);
		faceF = arr.get(2);
		faceR = arr.get(3);
		faceB = arr.get(4);
		faceD = arr.get(5);
	}

	/**
	 * rotate a face of the cube clockwise
	 * @param arr A face of the cube
	 */
	public void rotateFace(String[][] arr) {
		String [] temp = new String [4];
		int count = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				temp[count] = arr[i][j];
				count++;
			}			
		}
		arr[0][0] = temp[2];
		arr[0][1] = temp[0];
		arr[1][0] = temp[3];
		arr[1][1] = temp[1];			
	}

	/**
	 * Rotate a face of the cube counterclockwise
	 * @param arr A face of the cube
	 */
	public void rotateFaceInvert(String[][] arr) {
		String [] temp = new String [4];
		int count = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				temp[count] = arr[i][j];
				count++;
			}			
		}
		arr[0][0] = temp[1];
		arr[0][1] = temp[3];
		arr[1][0] = temp[0];
		arr[1][1] = temp[2];
	}

	/**
	 * Rotate the front face of the cube clockwise
	 */
	public void fMove() {
		rotateFace(faceF);

		String[] sideTemp = {faceU[1][0],faceU[1][1]};

		faceU[1][0] = faceL[1][1];
		faceU[1][1] = faceL[0][1];

		faceL[0][1] = faceD[0][0];
		faceL[1][1] = faceD[0][1];

		faceD[0][0] = faceR[1][0];
		faceD[0][1] = faceR[0][0];

		faceR[0][0] = sideTemp[0];
		faceR[1][0] = sideTemp[1];

		if(solving && !scrambling && !doubleMove)
			solveMoves.add("F");
		if(scrambling && !doubleMove)
			scramble.add("F");
	}

	/**
	 * Rotate the top face of the cube clockwise
	 */
	public void uMove() {
		rotateFace(faceU);

		String[] sideTemp = {faceB[0][0],faceB[0][1]};

		faceB[0][0] = faceL[0][0];
		faceB[0][1] = faceL[0][1];

		faceL[0][0] = faceF[0][0];
		faceL[0][1] = faceF[0][1];

		faceF[0][0] = faceR[0][0];
		faceF[0][1] = faceR[0][1];

		faceR[0][0] = sideTemp[0];
		faceR[0][1] = sideTemp[1];

		if(solving && !scrambling && !doubleMove)
			solveMoves.add("U");
		if(scrambling && !doubleMove)
			scramble.add("U");
	}

	/**
	 * Rotate the right face of the cube clockwise
	 */
	public void rMove() {
		rotateFace(faceR);

		String[] sideTemp = {faceU[0][1],faceU[1][1]};

		faceU[0][1] = faceF[0][1];
		faceU[1][1] = faceF[1][1];

		faceF[0][1] = faceD[0][1];
		faceF[1][1] = faceD[1][1];

		faceD[0][1] = faceB[1][0];
		faceD[1][1] = faceB[0][0];

		faceB[0][0] = sideTemp[1];
		faceB[1][0] = sideTemp[0];

		if(solving && !scrambling && !doubleMove)
			solveMoves.add("R");
		if(scrambling && !doubleMove)
			scramble.add("R");
	}

	/**
	 * Rotate the left face of the cube clockwise
	 */
	public void lMove() {
		rotateFace(faceL);

		String[] sideTemp = {faceU[0][0],faceU[1][0]};

		faceU[0][0] = faceB[1][1];
		faceU[1][0] = faceB[0][1];

		faceB[0][1] = faceD[1][0];
		faceB[1][1] = faceD[0][0];

		faceD[0][0] = faceF[0][0];
		faceD[1][0] = faceF[1][0];

		faceF[0][0] = sideTemp[0];
		faceF[1][0] = sideTemp[1];

		if(solving && !doubleMove)
			solveMoves.add("L");
	}

	/**
	 * Rotate the bottom face of the cube clockwise
	 */
	public void dMove() {
		rotateFace(faceD);

		String[] sideTemp = {faceF[1][0],faceF[1][1]};

		faceF[1][0] = faceL[1][0];
		faceF[1][1] = faceL[1][1];

		faceL[1][0] = faceB[1][0];
		faceL[1][1] = faceB[1][1];

		faceB[1][0] = faceR[1][0];
		faceB[1][1] = faceR[1][1];

		faceR[1][0] = sideTemp[0];
		faceR[1][1] = sideTemp[1];

		if(solving && !doubleMove)
			solveMoves.add("D");
	}

	/**
	 * Rotate the back face of the cube clockwise
	 */
	public void bMove() {
		rotateFace(faceB);

		String[] sideTemp = {faceU[0][1],faceU[0][0]};

		faceU[0][1] = faceR[1][1];
		faceU[0][0] = faceR[0][1];

		faceR[1][1] = faceD[1][0];
		faceR[0][1] = faceD[1][1];

		faceD[1][0] = faceL[0][0];
		faceD[1][1] = faceL[1][0];

		faceL[0][0] = sideTemp[0];
		faceL[1][0] = sideTemp[1];

		if(solving && !doubleMove)
			solveMoves.add("B");
	}

	/**
	 * Rotate the front face of the cube counterclockwise
	 */
	public void fMoveInvert() {
		rotateFaceInvert(faceF);
		String[] sideTemp = {faceU[1][0],faceU[1][1]};

		faceU[1][0] = faceR[0][0];
		faceU[1][1] = faceR[1][0];

		faceR[0][0] = faceD[0][1];
		faceR[1][0] = faceD[0][0];

		faceD[0][0] = faceL[0][1];
		faceD[0][1] = faceL[1][1];

		faceL[0][1] = sideTemp[1];
		faceL[1][1] = sideTemp[0];

		if(solving && !scrambling)
			solveMoves.add("F'");
		if(scrambling && !doubleMove)
			scramble.add("F'");
	}

	/**
	 * Rotate the top face of the cube counterclockwise
	 */
	public void uMoveInvert() {
		rotateFaceInvert(faceU);
		String[] sideTemp = {faceB[0][0],faceB[0][1]};

		faceB[0][0] = faceR[0][0];
		faceB[0][1] = faceR[0][1];

		faceR[0][0] = faceF[0][0];
		faceR[0][1] = faceF[0][1];

		faceF[0][0] = faceL[0][0];
		faceF[0][1] = faceL[0][1];

		faceL[0][0] = sideTemp[0];
		faceL[0][1] = sideTemp[1];

		if(solving && !scrambling)
			solveMoves.add("U'");
		if(scrambling && !doubleMove)
			scramble.add("U'");
	}

	/**
	 * Rotate the right face of the cube counterclockwise
	 */
	public void rMoveInvert() {
		rotateFaceInvert(faceR);
		String[] sideTemp = {faceU[0][1],faceU[1][1]};

		faceU[0][1] = faceB[1][0];
		faceU[1][1] = faceB[0][0];

		faceB[1][0] = faceD[0][1];
		faceB[0][0] = faceD[1][1];

		faceD[0][1] = faceF[0][1];
		faceD[1][1] = faceF[1][1];

		faceF[0][1] = sideTemp[0];
		faceF[1][1] = sideTemp[1];

		if(solving && !scrambling)
			solveMoves.add("R'");
		if(scrambling && !doubleMove)
			scramble.add("R'");
	}

	/**
	 * Rotate the left face of the cube counterclockwise
	 */
	public void lMoveInvert() {
		rotateFaceInvert(faceL);
		String[] sideTemp = {faceU[0][0],faceU[1][0]};

		faceU[0][0] = faceF[0][0];
		faceU[1][0] = faceF[1][0];

		faceF[0][0] = faceD[0][0];
		faceF[1][0] = faceD[1][0];

		faceD[0][0] = faceB[1][1];
		faceD[1][0] = faceB[0][1];

		faceB[0][1] = sideTemp[1];
		faceB[1][1] = sideTemp[0];

		if(solving)
			solveMoves.add("L'");
	}

	/**
	 * Rotate the bottom face of the cube counterclockwise
	 */
	public void dMoveInvert() {
		rotateFaceInvert(faceD);
		String[] sideTemp = {faceF[1][0],faceF[1][1]};

		faceF[1][0] = faceR[1][0];
		faceF[1][1] = faceR[1][1];

		faceR[1][0] = faceB[1][0];
		faceR[1][1] = faceB[1][1];

		faceB[1][0] = faceL[1][0];
		faceB[1][1] = faceL[1][1];

		faceL[1][0] = sideTemp[0];
		faceL[1][1] = sideTemp[1];

		if(solving)
			solveMoves.add("D'");
	}

	/**
	 * Rotate the back face of the cube counterclockwise
	 */
	public void bMoveInvert() {
		rotateFaceInvert(faceB);
		String[] sideTemp = {faceU[0][1],faceU[0][0]};

		faceU[0][1] = faceL[0][0];
		faceU[0][0] = faceL[1][0];

		faceL[0][0] = faceD[1][0];
		faceL[1][0] = faceD[1][1];

		faceD[1][0] = faceR[1][1];
		faceD[1][1] = faceR[0][1];

		faceR[1][1] = sideTemp[0];
		faceR[0][1] = sideTemp[1];

		if(solving)
			solveMoves.add("B'");
	}

	/**
	 * Rotate the front face of the cube twice
	 */
	public void F2() {
		doubleMove = true;
		fMove();
		fMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("F2");
		if(scrambling)
			scramble.add("F2");
	}

	/**
	 * Rotate the top face of the cube twice
	 */
	public void U2() {
		doubleMove = true;
		uMove();
		uMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("U2");
		if(scrambling)
			scramble.add("U2");
	}

	/**
	 * Rotate the right face of the cube twice
	 */
	public void R2() {
		doubleMove = true;
		rMove();
		rMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("R2");
		if(scrambling)
			scramble.add("R2");
	}

	/**
	 * Rotate the left face of the cube twice
	 */
	public void L2() {
		doubleMove = true;
		lMove();
		lMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("L2");
	}

	/**
	 * Rotate the bottom face of the cube twice
	 */
	public void D2() {
		doubleMove = true;
		dMove();
		dMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("D2");
	}

	/**
	 * Rotate the back face of the cube twice
	 */
	public void B2() {
		doubleMove = true;
		bMove();
		bMove();
		doubleMove = false;

		if(solving && !scrambling)
			solveMoves.add("B2");
	}

	/**
	 * Rotate the entire cube like doing an R move
	 */
	public void xMove() {
		rotateFace(faceR);
		rotateFaceInvert(faceL);
		rotateFace(faceB);
		rotateFace(faceB);
		rotateFace(faceU);
		rotateFace(faceU);

		String [][] faceTemp = faceF;

		faceF = faceD;
		faceD = faceB;
		faceB = faceU;
		faceU = faceTemp;

		if(solving && !doubleMove)
			solveMoves.add("X");
	}

	/**
	 * Rotate the entire cube like doing an U move
	 */
	public void yMove() {
		rotateFace(faceU);
		rotateFaceInvert(faceD);

		String [][] faceTemp = faceF;

		faceF = faceR;
		faceR = faceB;
		faceB = faceL;
		faceL = faceTemp;

		if(solving && !doubleMove)
			solveMoves.add("Y");
	}

	/**
	 * Rotate the entire cube like doing an F move
	 */
	public void zMove() {
		rotateFace(faceF);
		rotateFaceInvert(faceB);
		rotateFace(faceL);
		rotateFace(faceD);
		rotateFace(faceR);
		rotateFace(faceU);

		String [][] faceTemp = faceU;

		faceU = faceL;
		faceL = faceD;
		faceD = faceR;
		faceR = faceTemp;

		if(solving && !doubleMove)
			solveMoves.add("Z");
	}

	/**
	 * Rotate the entire cube like doing an R' move
	 */
	public void xMoveInvert() {
		rotateFace(faceB);
		rotateFace(faceB);
		rotateFace(faceD);
		rotateFace(faceD);
		rotateFace(faceL);
		rotateFaceInvert(faceR);

		String [][] faceTemp = faceU;

		faceU = faceB;
		faceB = faceD;
		faceD = faceF;
		faceF = faceTemp;

		if(solving)
			solveMoves.add("X'");
	}

	/**
	 * Rotate the entire cube like doing an U' move
	 */
	public void yMoveInvert() {
		rotateFaceInvert(faceU);
		rotateFace(faceD);

		String [][] faceTemp = faceF;

		faceF = faceL;
		faceL = faceB;
		faceB = faceR;
		faceR = faceTemp;

		if(solving)
			solveMoves.add("Y'");
	}

	/**
	 * Rotate the entire cube like doing an F' move
	 */
	public void zMoveInvert() {
		rotateFaceInvert(faceR);
		rotateFaceInvert(faceD);
		rotateFaceInvert(faceL);
		rotateFaceInvert(faceU);
		rotateFace(faceB);
		rotateFaceInvert(faceF);

		String [][] faceTemp = faceU;

		faceU = faceR;
		faceR = faceD;
		faceD = faceL;
		faceL = faceTemp;

		if(solving)
			solveMoves.add("Z'");
	}

	/**
	 * Rotate the entire cube like doing an R2 move
	 */
	public void X2() {
		doubleMove = true;
		xMove();
		xMove();
		doubleMove = false;
	}

	/**
	 * Rotate the entire cube like doing an U2 move
	 */
	public void Y2() {
		doubleMove = true;
		yMove();
		yMove();
		doubleMove = false;
	}

	/**
	 * Rotate the entire cube like doing an F2 move
	 */
	public void Z2() {
		doubleMove = true;
		zMove();
		zMove();
		doubleMove = false;
	}
}