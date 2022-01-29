/**
* This WayFinder class is a main class that calls methods from the Puzzle class.
* It takes an array of strings representing a puzzle in command line,
* which is solved using methods from the Puzzle class. 
*
* @author Emily Bruce * @version 11/08/2021
*/

public class WayFinder {
	
	/**
	* Creates a new puzzle object and calls methods from the Puzzle class to solve it.
	* It prints all possible solutions to a given puzzle, along with the total number
	* of solutions.
	*
	* @param args string array of numbers.
	*
	*/
	
	public static void main(String[] args) {
		// Exception for empty array
		if (args.length==0) {
			System.err.println("Please provide a list of numbers");
			System.exit(1);
			}
		
		Puzzle puzzle = new Puzzle(args); // Create new puzzle object
		puzzle.solvePuzzle(0,  ""); // Call method to solve and print each solution
		puzzle.numberOfSolutions(); // Call method to print number of solutions 
	}
}