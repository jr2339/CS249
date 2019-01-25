/**
 * The purpose of this project is to ensure that you understand how to work with
 * linked lists, and can use them to implement other ADTs (e.g., stack and
 * queues) and solve non-trivial problems.
 *

 * Implement a stack class based on a circular singly linked list. One should be
 * able to push and pop items onto the stack, just as we discussed with an array
 * implementation of a stack.
 */
public class Stack<E> {

    private CircList<E> stack;
    
    public Stack() {
        this.stack = new CircList<>();
    }

    /**
     * Tests if the stack is empty
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns the element on the top of the stack without removing it.
     *
     * @return the top element
     */
    public E peek() {
        return stack.get(stack.size()-1);
    }

    /**
     * Returns the top element and removes it from the stack.
     *
     * @return the top element
     */
    public E pop() {
        E e = stack.remove(stack.size()-1);
        return e;
    }

    /**
     * Adds the element to the top of the stack.
     *
     * @param e the element to add
     */
    public void push(E e) {
        stack.add(e);
    }
}
