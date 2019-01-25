/**
 * Create a two-dimensional linked list to represent a matrix. Each node (except
 * those in the top row and those in the left column) should be pointed to by
 * the link directly above it and by the link on its left. Thus, you will have
 * to update the Node class to include two pointers (e.g., right and down)
 *
 * The size of matrix should be defined by the user when the program starts. The
 * values stored at each location of the matrix (i.e., node) should be
 * initialized based on the position i,j. For example, the first node in the top
 * left corner should have the value 00, the 1st node in the 4th row should have
 * the value 03 (Note: counting should start at 0). You can assume you the
 * largest matrix would be 10 x 10.
 *
 * Your implementation should allow users to insert a specific value at a
 * specific location, fill the entire matrix with a given value, ask for the sum
 * (which should provide both row and column sums formatted at the ends of the
 * rows and columns), and/or just display the matrix.
 *
 * @author Jason Travis
 */
package project3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LinkedMatrix {

    private Node first;
    // Set public to provide a lazy accessor for the main interface
    public int xDim, yDim;

    public static void main(String[] args) {
        int width = 1, height = 1;
        Scanner in = new Scanner(System.in);
        // Ask the user to for the matrix width and height
        try {
            System.out.println("Enter the matrix width");
            width = in.nextInt();
            System.out.println("Enter the matrix height");
            height = in.nextInt();
            if(width < 1 || height < 1) {
                System.out.println("The width and height cannot be less than 1");
                System.exit(1);
            }
        } catch (InputMismatchException e) {
            System.out.println("Expected an integer");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
        LinkedMatrix matrix = new LinkedMatrix(width, height);
        // Ask the user which method(s) they want to run
        while (true) {
            System.out.println("Enter '1' to insert a specific value at a specific location");
            System.out.println("Enter '2' to fill the matrix with a given value");
            System.out.println("Enter '3' to display the matrix with row and column sums");
            System.out.println("Enter '4' to display the matrix");
            System.out.println("Enter '5' to quit");
            try {
                int action = in.nextInt();
                switch (action) {
                    case 1:
                        System.out.println("Enter the x coordinate [0-" + (matrix.xDim - 1) + "]");
                        int x = in.nextInt();
                        if (action < 0 || action > matrix.xDim - 1) {
                            System.out.println("Invalid x coordinate");
                            continue;
                        }
                        System.out.println("Enter the y coordinate [0-" + (matrix.yDim - 1) + "]");
                        int y = in.nextInt();
                        if (action < 0 || action > matrix.yDim - 1) {
                            System.out.println("Invalid y coordinate");
                            continue;
                        }
                        System.out.println("Enter the value to insert at (" + x + ", " + y + ")");
                        int v = in.nextInt();
                        matrix.insert(x, y, v);
                        break;
                    case 2:
                        System.out.println("Enter the value to fill the matrix");
                        v = in.nextInt();
                        matrix.fill(v);
                        break;
                    case 3:
                        // Get the number of digits in the largest value of the default matrix
                        int nDigits = String.valueOf(matrix.xDim*matrix.yDim).length();
                        matrix.display(nDigits, true);
                        break;
                    case 4:
                        // Get the number of digits in the largest value of the default matrix
                        nDigits = String.valueOf(matrix.xDim*matrix.yDim).length();
                        matrix.display(nDigits, false);
                        break;
                    default:
                        System.out.println("Exiting");
                        System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * @param xDim size in the 'x' dimension
     * @param yDim size in the 'y' dimension
     */
    public LinkedMatrix(int xDim, int yDim) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.first = new Node(0, 0);

        // Initialize rows
        Node rowStart = this.first, current;
        for (int row = 0; row < this.yDim; row++) {
            // Move to the start of the row
            current = rowStart;
            // Initialize and link the columns
            for (int column = 0; column < this.xDim; column++) {
                current = (current.right = new Node(column + 1, row));
            }
            // Initialize the next row if this is not the last row iteration
            if (row < this.yDim - 1) {
                rowStart = (rowStart.down = new Node(0, row + 1));
            }
        }
        // Link the rows
        Node prevColumnStart = this.first, columnStart = this.first.right, prev;
        for (int column = 1; column < this.xDim; column++) {
            // Move to the start of the column
            current = columnStart;
            prev = prevColumnStart;
            // Link the column rows using the previous column references
            for (int row = 0; row < this.yDim - 1; row++) {
                current.down = (prev = prev.down).right;
                current = current.down;
            }
            // Move to the next column
            columnStart = columnStart.right;
            prevColumnStart = prevColumnStart.right;
        }
    }

    /**
     * Displays the matrix.
     *
     * @param pad the max number of zeros to pad the node elements
     * @param withSums include row and column sums
     */
    public void display(int pad, boolean withSums) {
        int[] rowSums = new int[this.yDim];
        int[] colSums = new int[this.xDim];

        Node rowStart = this.first, current;
        for (int row = 0; row < this.yDim; row++) {
            // Move to the start of the row
            current = rowStart;
            // Print and sum the row first element
            // Sum the first column element
            System.out.print(String.format(" %0" + pad + "d", current.element));
            rowSums[row] += current.element;
            colSums[0] += current.element;
            // Print and sum the row elements
            // Sum the column elements
            for (int column = 1; column < this.xDim; column++) {
                current = current.right;
                rowSums[row] += current.element;
                colSums[column] += current.element;
                System.out.print(String.format(" %0" + pad + "d", current.element));
            }
            if (withSums) {
                // Print row sums
                System.out.print(String.format(" |%0" + pad + "d", rowSums[row]));
            }
            // Move to the next row
            rowStart = rowStart.down;
            System.out.println();
        }
        if (withSums) {
            // Create a divider the width of the element padding
            String divider = " ";
            for (int i = 0; i < pad; i++) {
                divider += "_";
            }
            // Print the divider between the matrix and the sums
            for (int column = 0; column < this.xDim; column++) {
                System.out.print(divider);
            }
            System.out.println();
            // Print the column sums
            for (int n : colSums) {
                System.out.print(String.format(" %0" + pad + "d", n));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Fills the entire matrix with a given value
     *
     * @param value the value for the matrix nodes
     */
    public void fill(int value) {
        Node rowStart = this.first, current;
        for (int row = 0; row < this.yDim; row++) {
            // Move to the start of the row and set its value
            current = rowStart;
            current.element = value;
            // Set the value of each element
            for (int column = 0; column < this.xDim - 1; column++) {
                current = current.right;
                current.element = value;
            }
            // Move to the next row
            rowStart = rowStart.down;
        }
    }

    /**
     * Inserts the given value in the node with position 'x', 'y'
     *
     * @param x the x coordinate of the node
     * @param y the y coordinate of the node
     * @param content the content for the node
     */
    public void insert(int x, int y, int content) {
        Node n = first;
        // Move to the node x coordinate
        for (int i = 0; i < x; i++) {
            n = n.right;
        }
        // Move to the node y coordinate
        for (int i = 0; i < y; i++) {
            n = n.down;
        }
        // Insert the given value
        n.element = content;
    }

    private class Node {

        private int element;
        private Node right, down;

        private Node(int x, int y) {
            this.element = y * LinkedMatrix.this.xDim + x;
        }
    }
}
