

import project1.Dequeue;


public class Checker extends Dequeue<Customer> {

    private int totalItems;

    public Checker(int capacity) {
        super(capacity);
        totalItems = 0;
    }

    /**
     * elapseTime simulates the passage of a unit of time by removing the given
     * number of items from the customer's carts
     *
     * @param itemsPerTime the number of items to remove
     */
    public void elapseTime(int itemsPerTime) {
        for (int item = 0; item < itemsPerTime; item++) {
            if (isEmpty()) {
                // There are no customers in line
                return;
            }
            // Remove an item from the customer's cart
            if (((Customer) this.dequeue[this.start]) != null) {
                ((Customer) this.dequeue[this.start]).checkoutItem();
                this.totalItems--;
            }
            // If the current customer has no more items to checkout, move on to
            // the next customer
            if (((Customer) this.dequeue[this.start]).getNItems() == 0) {
                dequeue();
            }
        }
    }

    /**
     * enqueue queues a customer in the checkout line
     *
     * @param c the customer entering the queue
     * @return false if the queue is full
     */
    public boolean enqueue(Customer c) {
        this.totalItems += c.getNItems();
        return insertRight(c);
    }

    /**
     * dequeue removes a customer from the checkout line
     *
     * @return the next customer in line
     */
    public Customer dequeue() {
        Customer c = removeLeft();
        if (c != null) {
            this.totalItems -= c.getNItems();
        }
        return c;
    }

    /**
     * getTotalItems returns the sum of the queued customer's items
     *
     * @return the sum of the queued customer's items
     */
    public int getTotalItems() {
        return this.totalItems;
    }
}
