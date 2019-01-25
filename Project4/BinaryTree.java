

import java.util.Stack;


public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree btree = new BinaryTree();
        btree.insert("abcdefghij");
        btree.displayTree();
    }

    public Node root;

    /**
     * insert recursively creates a tree from the characters of the given string
     * in ascending order
     *
     * @param s
     */
    public void insert(String s) {
        this.root = insert_r(null, s, 0);
    }

    /**
     * insert_r creates a tree of the characters in the given String using
     * pre-order traversal.
     *
     * @param n the current node
     * @param s the String to parse
     * @param index the character index for the current node
     * @return root node
     */
    private Node insert_r(Node n, String s, int index) {
        if (index == 0 && n == null) {
            // Insert the first node in an empty tree
            n = new Node(s.charAt(0));
        }
        if (s.length() <= 2 * index + 1) {
            // No left child
            return null;
        }
        // Insert left child
        n.left = new Node(s.charAt(2 * index + 1));
        // Recursively insert left branch
        insert_r(n.left, s, 2 * index + 1);
        if (s.length() <= 2 * index + 2) {
            // No right child
            return null;
        }
        // Insert right child
        n.right = new Node(s.charAt(2 * index + 2));
        // Recursively insert right branch
        insert_r(n.right, s, 2 * index + 2);
        return n;
    }

    public void inOrder(Node n) {
        if (n != null) {
            inOrder(n.left);
            System.out.print(n.c + " ");
            inOrder(n.right);
        }
    }
    
    public void preOrder(Node n) {
        if (n != null) {
            System.out.print(n.c + " ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }
    public void postOrder(Node n) {
        if (n != null) {
            postOrder(n.left);
            postOrder(n.right);
            System.out.print(n.c + " ");
        }
    }

    public void levelorder(Node root) {
        Stack<Node> q = new Stack();
        q.push(root);
        while (!q.isEmpty()) {
            Node n = q.pop();
            System.out.print(n.c + " ");
            if (n.left != null) {
                q.push(n.left);
            }
            if (n.right != null) {
                q.push(n.right);
            }
        }
    }
    
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(String.valueOf(temp.c));
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        } // end while isRowEmpty is false
        System.out.println("......................................................");
    }

    private class Node {

        Node left, right;
        char c;

        private Node(char c) {
            this.c = c;
        }
    }
}
