package project3;

/**
 * Knapsack is a recursive solution to a simple version of the Knapsack problem
 * where the goal is to try and fit items of different weights into a knapsack
 * so that the knapsack ends up with a specified total weight
 * @author Jason Travis
 */
public class Knapsack {

    private int capacity;
    private int size;
    private int[] knapsack;

    public static void main(String[] args) {
        int[] weights = new int[]{55, 25, 2, 4, 6, 3, 4};
        int capacity = 15;
        System.out.print("Weights: ");
        for(int weight : weights) {
            System.out.print(weight + ", ");
        }
        System.out.println("\nCapacity: " + capacity);
        Knapsack knapsack = new Knapsack(capacity);
        knapsack.fill(weights);
        System.out.println("Knapsack: " + knapsack);
        
        weights = new int[]{5,1,2,4,6,3,4};
        capacity = 99;
        System.out.print("\nWeights: ");
        for(int weight : weights) {
            System.out.print(weight + ", ");
        }
        System.out.println("\nCapacity: " + capacity);
        knapsack = new Knapsack(capacity);
        knapsack.fill(weights);
        System.out.println("Knapsack: " + knapsack);
        
        weights = new int[]{4,3,2,1,0};
        //weights = new int[]{1,2,1};
        capacity = 4;
        System.out.print("\nWeights: ");
        for(int weight : weights) {
            System.out.print(weight + ", ");
        }
        System.out.println("\nCapacity: " + capacity);
        knapsack = new Knapsack(capacity);
        knapsack.fill(weights);
        System.out.println("Knapsack: " + knapsack);
    }

    /**
     * @param capacity
     */
    public Knapsack(int capacity) {
        this.capacity = capacity;
    }
    
    public boolean fill(int[] weights) {
        this.knapsack = new int[weights.length];
        this.size = fill_r(weights, 0, 0) + 1;
        return this.size == 0;
    }

    /**
     * fill_r recursively fills the knapsack to capacity with the given weights
     *
     * @param weights an array of weights to try inserting into the knapsack
     * @param weight the current knapsack weight
     * @param start the index of the next weight to try inserting
     * @return the index of the last weight inserted into the knapsack or -1 if
     * no combinations matched
     */
    private int fill_r(int[] weights, int weight, int start) {
        if (start == weights.length) {
            // No match
            //System.out.println(start + ": No such index");
            return -1;
        }
        //System.out.println(start + ": Start index: " + start + " Value: " + weights[start] + " Total: " + (weight + weights[start]));
        if (weight + weights[start] == capacity) {
            // Found matching combination
            //System.out.println(start + ": Adding weight: " + start + " to index: 0");
            knapsack[0] = weights[start];
            return 0;
        }
        if (weight + weights[start] > capacity) {
            // Too much
            // This if reduces the typical runtime
            //System.out.println(start + ": Capacity exceeded, trying index " + (start + 1));
            // Pass the result of the next weight
            int i = fill_r(weights, weight, start + 1);
            //System.out.println(start + ": Index " + (start + 1) + " returned " + i);
            return i;
        }
        // Too little, add the next weight
        //System.out.println(start + ": Add the next value");
        int i = fill_r(weights, weight + weights[start], start + 1);
        //System.out.println(start + ": Index " + (start + 1) + " returned " + i);
        // Wrong combination
        if (i == -1) {
            // Try the next weight
            if (start < weights.length - 1) {
                //System.out.println(start + ": Resuming from index: " + start);
                i = fill_r(weights, weight, start + 1);
                //System.out.println(start + ": Index " + (start + 1) + " returned " + i);
                return i;
            }
            //System.out.println(start + ": Wrong combination ");
            return i;
        }
        //System.out.println(start + ": Adding weight: " + start + " to index: " + (i + 1));
        knapsack[i + 1] = weights[start];
        return i + 1;
    }

    @Override
    public String toString() {
        String s = "";
        if(this.size == 0) {
            return "empty";
        }
        for(int i=0; i<this.size; i++) {
            s += knapsack[i] + ", ";
        }
        return s.substring(0, s.length()-2);
    }
}
