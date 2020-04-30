CS249 Project 3
===============

Northern Arizona University Data Structures

Project Description: The purpose of this project is to ensure you understand how to use
recursion.

Project Submission: You should upload a zip file of your project on BBLearn. The project
should contain all of your code (except IDE-specific files), a readable screen capture (or UNIX
script) of your program compiling and running, and a write up. Your write-up MUST include:

* Details exactly how to execute your code to test each of the four parts
* Discusses the efficiency of each part
* Identifies anyone you collaborated* with.

*Note: Collaboration with respect to how to solve the problem is acceptable. However, you must
write and comment your own code individually.

Grading: Your grade will be based on completeness and correctness (60%), design and
consistent use of coding standards (e.g., formatting, comments, etc.) (30%), and the screen shot
and write-up (10%).

Part A
------

Suppose you buy a cheap pocket PC and discover that the chip inside can’t do multiplication, but
it can do addition. Write a recursive method, mult(), that performas multiplication of x and y by
adding x to itself y times. Write a method in the main program to call it and show it works for
various values of X and Y. Does the addition take place the method calls itself of when it returns?

Part B
------

In Chapter 8, “Binary Trees,” we’ll look at binary trees, where every branch has (potentially)
exactly two sub-branches. If we draw a binary tree on the screen using characters, we might have
1 branch on the top row, 2 on the next row, then 4, 8, 16, and so on.

(Note that the bottom line should be shifted a half character-width right, but there’s nothing we
can do about that with character-mode graphics.) You can draw this tree using a recursive
makeBranches() method with arguments left and right, which are the endpoints of a horizontal
range. When you first enter the routine, left is 0 and right is the number of characters (including
dashes) in all the lines, minus 1. You draw an X in the center of this range. Then the method calls
itself twice: once for the left half of the range and once for the right half. Return when the range
gets too small. You will probably want to put all the dashes and Xs into an array and display the
array all at once, perhaps with a display() method. Write a main() program to draw the tree by
calling makeBranches() and display(). Allow main() to determine the line length of the display
(32, 64, or whatever). Ensure that the array that holds the characters for display is no larger than it
needs to be. What is the relationship of the number of lines (five in the picture here) to the line
width?

Part C
------

Write a program that solves the knapsack problem for an arbitrary knapsack capacity and a series
of weights (i.e., these should be input). You should NOT assume the weights are sorted. Write a
main method to execute this function.

Part D
------

Implement a recursive approach to showing all the teams that can be created from a group (n
things taken k at a time). Letters should be used to represent group members and teams should
simply list all group members. Write the recursive showTeams() method and a main() method to
prompt the user for the group size and team size to provide arguments for showTeam(), which
then displays all the possible combinations. For example, if there are 6 groups members and you
want teams of size two, you should get the following output:
Group Members: A, B, C, D, E, F

Teams:
* AB
* AC
* AD
* AE
* AF
* BC
* BD
* BE
* BF
* CD
* CE
* CF
* DE
* DF
* EF
