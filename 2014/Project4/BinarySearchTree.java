

import java.util.Stack;


public class BinarySearchTree<E extends Comparable<E>> {

    public static void main(String[] args) {
        BinarySearchTree<Student> btree = new BinarySearchTree();
        btree.insert(new Student(4.0));
        btree.insert(new Student(5.0));
        btree.insert(new Student(3.0));
        btree.insert(new Student(10.0));
        btree.insert(new Student(9.0));
        btree.insert(new Student(14.0));
        btree.insert(new Student(0.0));
        btree.insert(new Student(-1.0));
        btree.insert(new Student(2.0));
        btree.insert(new Student(3.5));
        btree.insert(new Student(4.5));
        btree.insert(new Student(4.25));
        btree.displayTree();
        System.out.print("Searching for Student with 3.0 GPA: ");
        System.out.println(btree.find(new Student(3.0)) != null);
        System.out.println("Remove Student with 4.0 GPA");
        btree.delete(new Student(4.0));
        btree.displayTree();
        System.out.print("Searching for Student with 4.0 GPA: ");
        System.out.println(btree.find(new Student(4.0)) != null);
    }

    private Node root;

    public void insert(E key) {
        insert_r(this.root, key);
    }

    /**
     * insert_r recursively inserts an element TODO: The node is ignored if a
     * node with the given key exists
     *
     * @param n the current Node
     * @param key the element id
     * @param e the element
     * @return the current Node
     */
    private Node insert_r(Node<E> n, E key) {
        // Insert the node in a empty list
        if (this.root == null) {
            return this.root = new Node(key);
        }
        // Base case
        if (n == null) {
            return null;
        }
        // Insert left if the position is available and the key is less
        if (key.compareTo(n.key) == -1 && insert_r(n.left, key) == null) {
            n.left = new Node(key);
            return n.left;
        }
        // Insert right if the position is available and the key is greater
        if (key.compareTo(n.key) == 1 && insert_r(n.right, key) == null) {
            n.right = new Node(key);
            return n.right;
        }
        return n;
    }

    public E find(E key) {
        Node n = find_r(this.root, key);
        if (n == null) {
            return null;
        }
        return (E) n.key;
    }

    /**
     * find_r recursively searches for a Node with the given key
     *
     * @param n the current node
     * @param key the id of the Node to find
     * @return a Node with the given id or null if it doesn't exist
     */
    private Node find_r(Node<E> n, E key) {
        // The list is empty
        if (n == null) {
            return null;
        }
        // Search the left branch
        if (key.compareTo(n.key) == -1) {
            return find_r(n.left, key);
        }
        // Search the right branch
        if (key.compareTo(n.key) == 1) {
            return find_r(n.right, key);
        }
        // Either this is a matching Node or it doesn't exist
        return n;
    }

    /**
     * delete removes a node with the given key
     * @param key
     * @return true if successful
     */
    public boolean delete(E key) {
        if(this.root == null) return false;
        // (assumes non-empty list)
        Node<E> parent = root, current = root;
        boolean isLeftChild = true;
        while (current.key.compareTo(key) != 0) {
            parent = current;
            if (key.compareTo(current.key) == -1) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        // Found node to delete
        // if no children, simply delete it
        if (current.left == null && current.right == null) {
            if (current == root) {
                // if root, tree is empty
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } // disconnect
        // from parent
        else if (current.right == null) {
            // if no right child, replace with left subtree
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            // if no left child, replace with right subtree
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // two children, so replace with inorder successor
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);
            // connect parent of current to successor instead
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            // connect successor to current’s left child
            successor.left = current.left;
        }
        // (successor cannot have a left child)
        return true;
    }

    // returns node with next-highest value after delNode
    // goes to right child, then right child’s left descendents
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.right;
        // go to right child until no more
        while (current != null) {
            // left children,
            successorParent = successor;
            successor = current;
            current = current.left;
            // go to left child
        }
        // if successor not right child
        if (successor != delNode.right) {
            // make connections
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
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
                    System.out.print(temp.key.toString());
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

    private class Node<E extends Comparable<E>> {

        Node left, right;
        E key;

        public Node(E e) {
            this.key = e;
        }
    }

}
