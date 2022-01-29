/**
* This class represents a puzzle which is originally composed of an array of strings,
* which represent how many steps can be moved. 
* Each puzzle is represented by an array of integers converted from the string array,
* a boolean array representing which indices have been visited in each solution attempt,
* and a solutions integer counting the number of unique solutions.
* 
* It contains methods to solve the puzzle and
* store the solutions. The puzzle is solved recursively.
*
* @author Emily Bruce * @version 11/08/2021
*/

public class Puzzle {
	private int[] intNums; // Empty integer array for the parsed puzzle
	private boolean[] visited;
	private int numSolutions; // Integer number of total solutions
	
	/**
	* Constructs a puzzle object.
	*
	* @param numbers array of numbers (represented as strings).
	*
	*/
	
	public Puzzle (String[] numbers) {
		intNums = new int[numbers.length]; // Parse input array
		visited = new boolean[intNums.length]; // Boolean array to prevent loops
		
		for (int i = 0; i < intNums.length-1; i++) {
			visited[i] = false; // All indices have not been visited 
		}
		
		for (int i = 0; i < numbers.length-1; i++) {
			// Exception for any characters outside of the digits 0 through 9, and spaces
			if (!(numbers[i].matches("[0123456789 ]+"))) {
				System.err.println("Number list should only non-negative integers "
						+ "between 0 and 99");
				System.exit(1);
			}
			
			// Exception for last number not being 0
			if (!(numbers[numbers.length-1].equals("0"))) {
				System.err.println("Number list must end in a 0");
				System.exit(1);
			}
			
			// Exception for any numbers greater than 99
			if (Integer.parseInt(numbers[i])>99) {
				System.err.println("All numbers should be in range 0 to 99");
				System.exit(1);
			}
			
			// Parse input array into integers
			intNums[i] = Integer.parseInt(numbers[i]);	
		}	
	}
	
	/**
	* Solves the puzzle recursively.
	*
	* @param index current index.
	* @param solutions partial solution to either be included in the
	* output if valid, or not.
	*
	*/
	
	public void solvePuzzle (int index, String solutions) {
		// Edge case solution for array containing only 0
		if (intNums.length==1) {
			System.out.println("[ 0 ]\n");
			numSolutions++; // Increment total number of solutions to 1
			return;
		}
		
		// Base case where index is outside the range of the array
		if (index>=intNums.length || index<0) {
			return;
		}
		
		// Successful solution to the puzzle found
		if (index==intNums.length-1) {
			numSolutions++; // Increment total number of solutions for the puzzle
			System.out.println(solutions); // Print this successful solution
			return;
		}
		
		// If an index has not yet been attempted, make recursive calls
		if (visited[index] == false) {
			visited[index] = true; // Index will now be attempted
			// New index variables for moving right and moving left
			int indexR = index + intNums[index];
			int indexL = index - intNums[index];
			
			// Recursive calls using the right and left indices
			solvePuzzle(indexR, solutions + buildSolutions(index,"R"));
			solvePuzzle(indexL, solutions + buildSolutions(index,"L"));
			
			visited[index] = false; // Reset index as unvisited for next solve attempt
		}
	}
	
	/**
	* Prints the number of solutions to a puzzle.
	*/
	
	public void numberOfSolutions () {
		String outputString; // Create empty output string
		
		// Set output string if there is only one solution
		if (numSolutions==1) {
			outputString = "There is 1 way through the puzzle.";
		}
		
		// Set output string for all number of solutions greater than one
		else if (numSolutions>1) {
			outputString = "There are " + numSolutions + " ways through the puzzle.";
		}
		
		// Set output string if there are no solutions
		else {
			outputString = "No way through this puzzle.";
		}
		
		System.out.println(outputString); // Print the number of solutions
	}
	
	/**
	* Creates a solution in a string format.
	*
	* @param index index to be formatted.
	* @param direction direction "L",  "R", or "" to add to the index,
	* (L, R, or empty string).
	*
	* @return the solution string.
	*/
	
	private String buildSolutions (int index, String direction) {
		String str = "["; // Begin all lines with an open bracket
		str += String.format("%2d",intNums[0]); // Right align and add the first index
		
		// If this is the first line of a solution
		if (index==0) {
			str += direction; // Add R to the string after the first number
		}
		
		else {
			str+= " "; // Add a space before each comma
		}
		
		// Reached last index successfully, add the last number with no direction
		if (index==intNums.length-1) {
			str += String.format(" ","%2d",intNums[index]);
		}
		
		// Add all elements to the output string
		for (int i = 1; i < intNums.length; i++) {
			str += String.format(", %2d",intNums[i]); // Add the element to the string
			
			// Add the direction to the index that was passed to the method call
			if (i == index) {
				str += direction;
			}
			
			// Add a space only (no direction) to all other indices
			else {
				str+= " ";
			}
		}
		
		str += "]\n"; // Add closing bracket to the string and go to a new line
		return str; // Return this line of the solution as a string
	}
}