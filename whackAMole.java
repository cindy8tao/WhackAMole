package whackAMole;

import java.util.Scanner; // Java built in scanner (later use for user to input data)

public class WhackAMole {


    // ----------------------------------------INSTANT VARIABLES----------------------------------------//
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;


    // ----------------------------------------CONSTRUCTOR----------------------------------------//
    /* Constructor for the game, specified total number of whacks and the grid dimension*/

    public WhackAMole (int numAttempts, int gridDimension) {

	attemptsLeft =numAttempts;				// Set the number of attempts
	moleGrid = new char [gridDimension][gridDimension]; 	// Set moleGrid to equal the gridDimension


	for(int i = 0; i< gridDimension;i++) {			// Read all the x location in the array and replace with *
	    for(int j = 0; j<gridDimension; j++) {		// Read all the y location in the array and replace with *
		moleGrid[i][j] = '*';
	    } 
	    molesLeft = 0; 					// Initialize the number of moles
	    score = 0;						// Initialize the score
	}

    }
    // ----------------------------------------METHOD----------------------------------------//

    /* Given a location, place a mole in that location, return true if hit (update the number of moles left)*/

    boolean place(int x, int y) {
	if (moleGrid [x][y]=='*') {
	    moleGrid[x][y] = 'M'; // Set * to M (this program will initialize 10 times to place 10 moles) 					
	    molesLeft++;	  // Add 1 to moles (molesLeft = molesLeft+1);
	    return true;	  // return true
	}
	else {
	    return false;
	}
    }
    /* Given a location, take a whack at that location --- 
     * if true -> update score+10, number of attempts -1, moleleft -1
     * if false-> number of attempts left -1
     */

    public boolean whack (int x, int y) {
	if( x<0 || y<0) {							// Enter positive number 
	    System.out.println("Please enter a valid number ");
	    return false;
	}

	else if ((moleGrid)[x][y]=='W') {					// If coordinate entered = W, then it is already whacked, enter another coordinate
	    System.out.println("Aleady WHACKED, please enter another coordinate");
	    return false;
	}
	else {
	    if((moleGrid)[x][y] == 'M') {
		System.out.println("Yay! You Whacked a Mole");
		score = score + 10;				// Score +10
		attemptsLeft--;					// Attempts -1	
		molesLeft--; 					// Moles -1
		moleGrid[x][y] = 'W';
		System.out.println(molesLeft + " moles lefted");
		System.out.println("You have " + attemptsLeft + "attempts left");
		System.out.println("SCORE: " + score);
	    }
	    else {						// If the user x and y does not hit in the grid
		System.out.println("Awww, you missed, please try again");
		attemptsLeft--;					// Attempts -1
		System.out.println("You have " + attemptsLeft + "attempts left");
		System.out.println("SCORE: " + score);
	    }
	    return true;
	}

    }


    /* Print the grid without showing where the moles are, Every spot with whacked mole print -> "W" */

    void printGridtoUser() {
	for(int i = 0; i< moleGrid.length;i++) {		// Read all the x location in the array
	    for(int j = 0; j<moleGrid.length; j++) {		// Read all the y location in the array
		if (moleGrid[i][j]=='W') {			// If already whacked, print "W"
		    System.out.println("W");
		}
		else if (moleGrid[i][j]=='M') {			// Show "*" for all the rest
		    System.out.println("*");
		}
		else {
		    System.out.print(moleGrid[i][j]);	
		}
	    }
	}


    }

    /* Print the grid completely, Every spot with mole -> "M"*/ 
    void printGrid() {
	for(int i = 0; i< moleGrid.length;i++) {		
	    for(int j = 0; j < moleGrid.length; j++) {				
		System.out.print(moleGrid[i][j]);		// Print all the spots with moles
	    }
	    System.out.println(" ");
	}

    }
 // ----------------------------------------CALL----------------------------------------//

    void score() {						// Call out the final score
	System.out.print("FINAL SCORE:  " + score);
    }
    void molesLeft() {						// Call out the moles and the moles lefted
	int moles = 10 - molesLeft;
	System.out.print("MOLES HIT: "+ moles);
	System.out.println(" ");
	System.out.print("MOLES LEFT: "+ molesLeft);
    }

 // ----------------------------------------MAIN----------------------------------------//
    
    /* Main function of the game, ask user for input*/

    public static void main(String[] args) {

	WhackAMole wam=new WhackAMole(50,10); // Allocate wam as the new WhackAMole function (Call class to get method)

	for(int m = 0; m<10;m++) {
	    int i, j;
	    i = (int)(Math.random()*10);
	    j = (int)(Math.random()*10);
	    wam.place(i, j);
	    //System.out.println(i + "," + j);
	}
	System.out.println("START WHACK A MOLE GAME");
	System.out.println("REMEMBER YOU ONLY HAVE 50 ATTEMPTS");
	while(wam.attemptsLeft > 0 && wam.molesLeft > 0) {
	    Scanner player = new Scanner(System.in); // Ask player in input data
	    System.out.println("Please enter a coordinate x to WHACK A MOLE:   ");
	    int x = player.nextInt();
	    System.out.println("Please enter a coordinate y to WHACK A MOLE:   ");
	    int y = player.nextInt();
	    System.out.println("(" + x + "," + y +")");
	    if (x==-1 && y==-1) {
		System.out.println("GAME OVER");
		wam.score();
		System.out.println(" ");
		wam.molesLeft();
		System.out.println(" ");
		wam.printGridtoUser();
		return;
	    }
	    else {
		wam.whack(x, y);
	    }

	}
	System.out.println("GAME OVER");
	wam.printGrid();

    }
}
//}
