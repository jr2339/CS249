

/*
 * Customer represents a customer in a business
 */
public class Customer {

    // Number of products in the shopping cart
    protected int nItems;

    public Customer() {
        // The customer has 1-100 items
        nItems = 1 + (int) (Math.random() * (100 + 1));
    }

    /**
     * getNItems returns the number of products the customer is carrying
     * @return the number of products the customer is carrying
     */
    public int getNItems() {
        return this.nItems;
    }

    /**
     * checkoutItem simulates an item being removed from the customers cart at
     * a checkout line
     */
    public void checkoutItem() {
        this.nItems--;
    }
    
    /**
     * toString returns the String representation of the Customer
     * @return the String representation of the Customer
     */
    @Override
    public String toString() {
        return "{'items':" + this.nItems + "}";
    }
}
