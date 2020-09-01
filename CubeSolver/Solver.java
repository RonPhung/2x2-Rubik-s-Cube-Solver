package CubeSolver;
public class Solver {
	//Cube used to find solution
	private Cube c;
	//color of the bottom face
	private String bottomColor;
	//color of the top face
	private String oppColor;

	/**
	 * Constructor for solver object
	 * @param c Cube object
	 */
	public Solver(Cube c) {
		this.c = c;
	}

	/**
	 * Solves the bottom layer, solves the top layer, then permutes both layers 
	 */
	public void solveCube() {
		//solve the bottom by making all squares one color
		solveBottom();
		//solve the top layer making all the squares the opposite color of the bottom
		OLL();
		//both top and bottom layer get permuted
		PBL();
		//the solution is trimmed until it can not longer be trimmed any further
		int initialSize = c.getSolution().size();
		int finalSize = -1;
		while(initialSize != finalSize) {
			initialSize = c.getSolution().size();
			c.trimSolution();
			finalSize = c.getSolution().size();
		}
	}

	/*
	 * Determines the opposite color of the bottom face
	 */
	public void oppositeColor() {
		switch(bottomColor) {
		case"w":
			oppColor = "y";
			break;
		case"y":
			oppColor = "w";
			break;
		case"o":
			oppColor = "r";
			break;
		case"r":
			oppColor = "o";
			break;
		case"g":
			oppColor = "b";
			break;
		case"b":
			oppColor = "g";
			break;
		}
	}

	/**
	 * Determines which color will be on the bottom based on the color each square holds
	 */
	public void determineBottom() {
		//stores the bottom face of the cube
		String [][] tempSide = c.getFaceD();
		//stores the number of times the cube has to move to determine the bottom color
		int count = 0;
		//stores the color into a string
		while(bottomColor == null) {
			String face = "";
			for(String [] arr : tempSide) {
				for(String str : arr) {
					face += str;
					//index++;
				}
			}
			//the next three if statements check for multiple squares holding the same color
			if(face.substring(1).contains(face.substring(0,1))){
				bottomColor = face.substring(0,1);
				break;
			}
			else if(face.substring(2).contains(face.substring(1,2))) {
				bottomColor = face.substring(1,2);
				break;
			}
			else if(face.substring(0,3).contains(face.substring(3))) {
				bottomColor = face.substring(3);
				break;
			}
			//the if statements below will perform moves in order to get multiple squares with the same color onto the 
			if(count < 4) {
				c.fMove();
				count++;
			}
			else if(count < 8) {
				c.rMove();
				count++;
			}
			else if(count < 12){
				c.lMove();
				count++;
			}
			else if(count < 16){
				c.bMove();
				count++;
			}
			else if(count < 20){
				rotateBottomCorner();
				count++;
			}
			else if(count < 24){
				rotateBottomCornerInvert();
				count++;
			}
		}
	}

	/**
	 * The bottom face will become one color
	 */
	public void solveBottom() {
		//stores the number of squares that are not the correct color
		int count = 0;
		determineBottom();
		//counts the number of squares that don't have the bottom color
		for(String [] arr : c.getFaceD()) {
			for(String s : arr) {
				if(!s.equals(bottomColor))
					count++;
			}
		}

		while(count > 0) {
			//rotates the cube so the bottom right corner doesn't hold the bottom color
			if(c.getFaceD()[0][1].equals(bottomColor)) {
				if(!c.getFaceD()[0][0].equals(bottomColor))
					c.yMoveInvert();
				else {
					while(c.getFaceD()[0][1].equals(bottomColor)) {
						c.yMove();
					}
				}
			}

			//based on the location of the square that hold the bottom color
			//different algorithms could be executed
			if(c.getFaceL()[0][1].equals(bottomColor)) {
				rotateBottomCornerInvert();
			}
			else if(c.getFaceR()[0][0].equals(bottomColor)) {
				rotateBottomCorner();
			}	
			else if(c.getFaceU()[1][1].equals(bottomColor)){
				rotateBottomCornerInvert();
				//c.U2();
			}
			else if(c.getFaceF()[1][1].equals(bottomColor)) {
				rotateBottomCornerInvert();
				c.uMove();
			}
			else if(c.getFaceR()[1][0].equals(bottomColor)) {
				rotateBottomCorner();
				c.uMoveInvert();
			}
			//if the square with the bottom color isn't in a favorable position
			//the top half of the cube will rotate
			else {
				c.uMove();
			}
			//checks for the number of squares that don't have the bottom color
			count = 0;
			for(String [] arr : c.getFaceD()) {
				for(String s : arr) {
					if(!s.equals(bottomColor))
						count++;
				}
			}
		}
	}

	/**
	 * Orient the top layer	
	 */
	public void OLL() {
		//stores the number of squares that hold the color opposite the bottom color
		int numOppColor = 0;
		oppositeColor();
		//checks for the number of squares that don't have the bottom color
		for(String [] arr : c.getFaceU()) {
			for(String s : arr) {
				if(s.equals(oppColor))
					numOppColor++;
			}
		}
		//an algorithm is performed based on the number of squares that hold the color opposite to the bottom
		switch(numOppColor){
		case 0:
			while(true) {
				if(c.getFaceF()[0][0].equals(oppColor) &&
						c.getFaceF()[0][1].equals(oppColor) &&
						c.getFaceB()[0][0].equals(oppColor) &&
						c.getFaceB()[0][1].equals(oppColor)) {
					oll_H();
					return;
				}
				else if(c.getFaceL()[0][0].equals(oppColor) &&
						c.getFaceL()[0][1].equals(oppColor) &&
						c.getFaceF()[0][1].equals(oppColor)) {
					oll_Pi();
					return;
				}			
				//if the top layer isn't oriented correction, it gets rotated
				c.uMove();
			}
		case 1:
			while(true) {
				if(c.getFaceU()[0][1].equals(oppColor) &&
						c.getFaceF()[0][0].equals(oppColor)) {
					oll_Antisune();
					return;
				}
				else if(c.getFaceU()[1][0].equals(oppColor) &&
						c.getFaceF()[0][1].equals(oppColor)) {
					oll_Sune();
					return;
				}			
				//if the top layer isn't oriented correction, it gets rotated
				c.uMove();
			}
		case 2:
			while(true) {
				if(c.getFaceU()[0][0].equals(oppColor) &&
						c.getFaceU()[1][1].equals(oppColor) &&
						c.getFaceF()[0][0].equals(oppColor)) {
					oll_L();
					return;
				}
				else if(c.getFaceU()[0][1].equals(oppColor) &&
						c.getFaceU()[1][1].equals(oppColor) &&
						c.getFaceF()[0][0].equals(oppColor)) {
					oll_T();
					return;
				}
				else if(c.getFaceU()[0][1].equals(oppColor) &&
						c.getFaceU()[1][1].equals(oppColor) &&
						c.getFaceL()[0][1].equals(oppColor)) {
					oll_U();
					return;
				}			
				//if the top layer isn't oriented correction, it gets rotated
				c.uMove();
			}
		}
	}
	
	/**
	 * Permute top and bottom layer
	 */
	public void PBL() {
		Cube tempCube = c;
		//bars are two squares horizontally next to one another that have the same color
		//these are stores because each algorithms requires a certain number of bars 
		int numTopBars = 0;
		int numBotBars = 0;
		//the solving cube turns off solving mode to count the number of bars each half has
		c.solvingModeOff();
		for(int j = 0; j < 4; j++) {
			//counts the number of bars on the top half
			if(tempCube.getFaceF()[0][0].contentEquals(tempCube.getFaceF()[0][1])) {
				numTopBars++;
			}
			//counts the number of bars on the bottom half
			if(tempCube.getFaceF()[1][0].contentEquals(tempCube.getFaceF()[1][1])) {
				numBotBars++;
			}
			tempCube.yMove();
		}
		c.solvingModeOn();

		//if the bars are in a unfavorable position the cube is flip upside down	
		if((numBotBars == 1 && numTopBars == 0) ||(numTopBars == 4 && (numBotBars == 1 || numBotBars == 0))) {
			c.zMove();
			c.zMove();
			int temp = numTopBars;
			numTopBars = numBotBars;
			numBotBars = temp;
		}

		//based on the number of bars each half has an algorithm is chosen
		switch(numBotBars) {
		case 0:
			if(numTopBars == 0) {
				pbl_DiaDia();
				break;
			}
			else {
				while(c.getFaceF()[0][0] != c.getFaceF()[0][1])
					c.uMove();
				pbl_AdjDia();
				break;
			}
		case 1:
			while(c.getFaceF()[0][0] != c.getFaceF()[0][1])
				c.uMove();
			while(c.getFaceF()[1][0] != c.getFaceF()[1][1])
				c.dMove();
			pbl_AdjAdj();
			break;
		case 4:
			if(numTopBars == 4) {
				break;
			}
			if(numTopBars == 0) {
				pbl_DiaU();
				break;
			}
			else {
				while(c.getFaceL()[0][0] != c.getFaceL()[0][1])
					c.uMove();
				pbl_AdjU();
				break;
			}
		}
		//this rotates the top half so that all faces are complete
		while(c.getFaceF()[0][0] != c.getFaceF()[1][0])
			c.uMove();
	}

	/**
	 * Rotates the bottom right corner to the left
	 */
	public void rotateBottomCorner() {
		c.rMove();
		c.uMove();
		c.rMoveInvert();
	}

	/**
	 * Rotates the bottom right corner to the right
	 */
	public void rotateBottomCornerInvert() {
		c.rMove();
		c.uMoveInvert();
		c.rMoveInvert();

	}

	/**
	 * OLL H algorithm
	 */
	public void oll_H()	{
		c.R2();
		c.U2();
		c.rMove();
		c.U2();
		c.R2();
	}
	/**
	 * OLL Pi algorithm
	 */
	public void oll_Pi() {
		c.rMove();
		c.U2();
		c.R2();
		c.uMoveInvert();
		c.R2();
		c.uMoveInvert();
		c.R2();
		c.U2();
		c.rMove();
	}
	/**
	 * OLL Antisune algorithm
	 */
	public void oll_Antisune() {
		c.rMove();
		c.U2();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMove();
		c.uMoveInvert();
		c.rMoveInvert();		
	}
	/**
	 * OLL Sune algorithm
	 */
	public void oll_Sune() {
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.uMove();
		c.rMove();
		c.U2();
		c.rMoveInvert();
	}

	/**
	 * OLL L algorithm
	 */	
	public void oll_L() {
		c.fMove();
		c.rMoveInvert();
		c.fMoveInvert();
		c.rMove();
		c.uMove();
		c.rMove();
		c.uMoveInvert();
		c.rMoveInvert();
	}

	/**
	 * OLL T algorithm
	 */
	public void oll_T() {
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMoveInvert();
		c.fMove();
		c.rMove();
		c.fMoveInvert();
	}

	/**
	 * OLL U algorithm
	 */
	public void oll_U() {
		c.fMove();
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.uMoveInvert();
		c.fMoveInvert();
	}

	/**
	 * PBL ADJ/ADJ algorithm
	 */
	public void pbl_AdjAdj() {
		c.R2();
		c.uMoveInvert();
		c.B2();
		c.U2();
		c.R2();
		c.uMoveInvert();
		c.R2();
	}

	/**
	 * PBL ADJ/DIA algorithm
	 */
	public void pbl_AdjDia() {
		c.rMove();
		c.uMoveInvert();
		c.rMove();
		c.F2();
		c.rMoveInvert();
		c.uMove();
		c.rMoveInvert();
	}

	/**
	 * PBL DIA/DIA algorithm
	 */
	public void pbl_DiaDia() {
		c.R2();
		c.F2();
		c.R2();
	}

	/**
	 * PBL ADJ U algorithm
	 */
	public void pbl_AdjU() {
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMoveInvert();
		c.fMove();
		c.R2();
		c.uMoveInvert();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.fMoveInvert();
	}

	/**
	 * PBL DIA U algorithm
	 */
	public void pbl_DiaU() {
		c.fMove();
		c.rMove();
		c.uMoveInvert();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.fMoveInvert();
		c.rMove();
		c.uMove();
		c.rMoveInvert();
		c.uMoveInvert();
		c.rMoveInvert();
		c.fMove();
		c.rMove();
		c.fMoveInvert();
	}
}
