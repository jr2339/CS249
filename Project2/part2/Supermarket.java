/**
 * Write a program that models checkout lines at a supermarket.
 * You should use you Queue class from Project #1 (with the display method).
 * You’ll need to determine how the customer will decide which line to join.
 * The checkers take random amounts of time to process each customer
 * (presumably depending on how many groceries the customer has).
 * Once checked out, the customer is removed from the line.
 * For simplicity, you can simulate the passing of time by pressing a key.
 * For example, every key press indicates the passage of one minute.
 * 
 * You must write a driver or simulation class that takes in two parameters:
 * the number of checkers, and the maximum line length.
 * The driver class must then prompt the user for input asking whether to
 * show the queues, simulate a minute of time passed, or enqueue a new customer into a queue.
 */


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Supermarket is a driver class for interactively simulating the checkout lines
 * at a Supermarket.
 * @author Jason Travis
 */
public class Supermarket {
    
    public static void main(String[] args) {
        Supermarket store = new Supermarket(3, 5);
        store.run();
    }

    private Checker[] checkers;

    /**
     * @param nCheckers the number of checkers
     * @param maxLineLength the maximum line length
     */             
    public Supermarket(int nCheckers, int maxLineLength) {
        // Initialize an array of checkers with the given parameters
        checkers = new Checker[nCheckers];
        for (int i = 0; i < nCheckers; i++) {
            checkers[i] = new Checker(maxLineLength);
        }
    }

    /**
     * 
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        String action = "";
        while (true) {
            // Prompt user
            System.out.print("(s)how, (e)lapse minute, (q)ueue customer, e(x)it\n"
                + "Enter an action key: ");
            // Read byte
            try {
                action = in.next();
            } catch (InputMismatchException e) {
                System.err.println(e);
                System.exit(1);
            }
            // Execute the matching action
            switch (action) {
                case "s":
                    show();
                    break;
                case "e":
                    elapseMinute();
                    break;
                case "q":
                    Customer c = new Customer();
                    queueCustomer(c);
                    break;
                case "x":
                    System.exit(0);
                default:
                    System.out.println("Invalid action key: " + action);
            }
        }
    }

    /**
     * A customer ready to checkout joins the shortest line
     * @param customer the queuing customer
     */
    private void queueCustomer(Customer customer) {
        int shortestLine = 0;
        for (int checker = 0; checker < this.checkers.length; checker++) {
            if (checkers[checker].getTotalItems() < checkers[shortestLine].getTotalItems()) {
                shortestLine = checker;
            }
        }
        checkers[shortestLine].enqueue(customer);
    }

    /**
     * The checkers are very efficient and process 60 items a minute
     */
    private void elapseMinute() {
        for (int checker = 0; checker < this.checkers.length; checker++) {
            checkers[checker].elapseTime(60);
        }
    }

    /**
     * show prints the checkout lines
     */
    private void show() {
        for(int i=0; i<this.checkers.length; i++) {
            // Print the checker identifier
            System.out.println("Checker" + i + ":");
            // Print the checker String representation
            System.out.println(checkers[i].toString());
        }
    }
}
