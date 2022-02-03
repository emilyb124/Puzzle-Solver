# Puzzle-Solver

_Note: This is a Data Structures 2021 class project, assigned by Professor Joanna Klukowska, NYU._

This program requires a list of space separated numbers between 0 and 100 in command line,
ending in a 0. 
The puzzle starts on the first number, and move that many steps to the right, landing on
another number. It then is allowed to move that new number amount of steps in either direction.
The goal is to eventually land on the 0 at the end of the list. 

This program uses recursive to go through the possible paths and build a list of correct solutions.
All possible solutions are printed out to the console.
