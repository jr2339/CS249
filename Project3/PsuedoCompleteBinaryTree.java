package project3;

import java.util.Arrays;

/**
 * PsuedoCompleteBinaryTree prints a recursively generated character representation
 * of a binary tree
 * @author Jason Travis
 */
public class PsuedoCompleteBinaryTree {

    private final char[][] tree;

    public static void main(String[] args) {
        int lineLength = 32;
        PsuedoCompleteBinaryTree tree = new PsuedoCompleteBinaryTree(lineLength);
        // Recursively generate the tree
        int depth = 0, left = 0, right = lineLength - 1;
        tree.makeBranches(tree.tree, depth, left, right);
        // Print the tree
        tree.display(tree.tree);
    }

    /**
     * 
     * @param lineLength the number of leaves and consequently the width of the
     * output array
     */
    public PsuedoCompleteBinaryTree(int lineLength) {
        if (lineLength <= 0) {
            System.err.println("parameter must be positive");
            System.exit(1);
        }
        int n = lineLength;
        while (n != 1) {
            if (n % 2 == 1) {
                System.err.println("parameter must be a power of 2");
                System.exit(1);
            }
            n /= 2;
        }

        // Discrete math equation for the height of a complete binary tree
        int height = (int) Math.ceil(Math.log(lineLength + 1) / Math.log(2));
        this.tree = new char[height][lineLength];

        // Initialize tree with hyphens
        for (int i = 0; i < height; i++) {
            Arrays.fill(this.tree[i], '-');
        }
    }

    /**
     * makeBranch recursively writes X's to a M by N array in a tree data
     * structure pattern
     *
     * @param tree the output array
     * @param level the current tree depth
     * @param left branch left endpoint index
     * @param right branch right endpoint index
     */
    public void makeBranches(char[][] tree, int level, int left, int right) {
        int mid = (right - left + 1) / 2 + left;
        tree[level][mid] = 'X';
        // Base case
        if (left == right) {
            return;
        }
        // Make the left branches
        makeBranches(tree, level + 1, left, mid - 1);
        // Make the right branches
        makeBranches(tree, level + 1, mid, right);
    }

    /**
     * display the array contents
     *
     * @param tree the array to print
     */
    public void display(char[][] tree) {
        for (char[] t : tree) {
            for (char c : t) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
