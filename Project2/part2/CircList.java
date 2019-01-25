

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class CircList<E> implements List {

    private int size;
    private Node<E> first;

    public CircList() {
        this.size = 0;
        this.first = null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if the list contains no elements
     *
     * @return true if the list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns true if the list contains the specified element
     *
     * @param o the element
     * @return true if the list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        if (this.size == 0) {
            return false;
        }
        Node ptr = this.first;
        for (int i = 0; i < this.size; i++) {
            if (ptr.getElement().equals(o)) {
                return true;
            }
            ptr = ptr.getNext();
        }
        return false;
    }

    /**
     * Returns an iterator over the elements
     *
     * @return an iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    /**
     * Returns an array containing all the elements in the list
     *
     * @return an array containing all the elements in the list
     */
    @Override
    public E[] toArray() {
        Node ptr = this.first;
        Object[] arr = new Object[this.size];
        for (int i = 0; i < this.size; i++, ptr = ptr.getNext()) {
            arr[i] = ptr.getElement();
        }
        return (E[]) arr;
    }

    @Override
    public E[] toArray(Object[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Appends the specified element to the end of the list
     *
     * @param e the element to append
     * @return true
     */
    @Override
    public boolean add(Object e) {
        Node n = new Node(e);
        if (this.size == 0) {
            first = n;
        } else {
            Node last = getNode(this.size - 1);
            last.setNext(n);
            n.setNext(this.first);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int i, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns the element at the specified position in the list
     *
     * @param i list index
     * @return the element at the specified index
     */
    @Override
    public E get(int i) {
        Node n = getNode(i);
        if (n == null) {
            return null;
        }
        return (E) n.getElement();
    }

    /**
     * Replaces the element at the given index with the given element
     *
     * @param i the list index
     * @param e the element to replace the current element
     * @return the element if successful, otherwise null
     */
    @Override
    public E set(int i, Object e) {
        Node n = getNode(i);
        if (n == null) {
            return null;
        }
        n.setElement(e);
        return (E) e;
    }

    @Override
    public void add(int i, Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Removes the element at the given index
     *
     * @param i index of the element to remove
     * @return the element if it exists, otherwise null
     */
    @Override
    public E remove(int i) {
        if (i >= this.size || this.size == 0) {
            // The node does not exist
            return null;
        }
        // If removing the first element the previous node is the last node,
        // otherwise, it is the node at the previous index
        Node prev = getNode(i == 0 ? this.size - 1 : i - 1);
        if (--this.size == 0) {
            // This was the last node
            first = null;
        } else if (i == 0) {
            // first is the node to remove, move the pointer to the next node
            first = first.getNext();
        }
        // Remove the node
        Node n = prev.getNext();
        prev.setNext(n.getNext());
        n.setNext(null);
        return (E) n.getElement();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns the given node
     *
     * @param n a node in the list
     * @return the node before the given node
     */
    private Node getNode(int index) {
        if (index >= this.size || index < 0) {
            // It does not exist
            return null;
        }
        Node ptr = this.first;
        // Move the pointer to the given index
        for (int i = 0; i < index; i++) {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    private class Node<E> {

        private E element;
        private Node next;

        public Node(E e) {
            this.element = e;
            next = this;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            this.next = n;
        }
    }

    public class Iterator implements java.util.Iterator {

        private Node ptr, prev;

        public Iterator() {
            this.prev = CircList.this.getNode(CircList.this.size - 1);
        }

        /**
         * @return true if there are more elements
         */
        @Override
        public boolean hasNext() {
            return this.prev != null;
        }

        /**
         * @return the next element in the iteration
         */
        @Override
        public Object next() {
            if (this.prev == null) {
                throw new NoSuchElementException();
            }
            if (this.ptr == null) {
                // The first call to next should return the first element
                this.ptr = this.prev.getNext();
            } else {
                this.ptr = this.ptr.getNext();
                this.prev = this.prev.getNext();
            }
            return this.ptr.getElement();
        }

        /**
         * Removes from the underlying collection the last element returned
         */
        @Override
        public void remove() {
            if (this.prev == null || this.ptr == null) {
                // the next method has not yet been called, or the remove method
                // has already been called after the last call to the next method
                throw new IllegalStateException();
            }
            if (CircList.this.size == 1) {
                // This is the last node, set pointers to null
                CircList.this.first.setNext(null);
                CircList.this.first = null;
                this.ptr = null;
                this.prev = null;
            } else {
                // Remove the current node from the list
                this.prev.setNext(this.ptr.getNext());
                this.ptr.setNext(null);
                this.ptr = null;
            }
            CircList.this.size--;
        }
    }
}
