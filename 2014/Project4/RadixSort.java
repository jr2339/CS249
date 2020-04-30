

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class RadixSort {
    
    public static void main(String[] args) {
        Random rnd = new Random();
        RadixSort rSort = new RadixSort();
        rSort.sortRandom(rnd);
        rSort.sortRandom(rnd);
        rSort.sortRandom(rnd);
    }
    
    /**
     * Create a list with 5-10 elements where each is between +/-1000 and sort it
     */
    public void sortRandom(Random rnd) {
        LinkedList<Integer> list = new LinkedList();
        int size = 5 + (int)(rnd.nextFloat()*5 + 1);
        for(int i = 0; i < size; i++) {
            list.add((int)(rnd.nextFloat()*1000) * (rnd.nextBoolean() ? -1 : 1));
        }
        // Print the unsorted list
        System.out.println("Unsorted:");
        for(int n : list) System.out.print(n + " ");
        System.out.println();
        // Sort the list
        list = this.binarySort(list);
        // Print the sorted list
        System.out.println("Sorted:");
        for(int n : list) System.out.print(n + " ");
        System.out.println("\n");
    }
    
    /**
     * binarySort performs a binary radix sort on a list of 32bit integers
     * @param src list of integers to sort
     * @return sorted list
     */
    public LinkedList binarySort(Collection<Integer> src) {
        LinkedList<Integer> subLow = new LinkedList(), subHigh = new LinkedList();
        // Initialize the subsorted lists with the unsorted elements
        split(subLow, subHigh, src, 0);
        // For each integer bit, split the subsorted lists
        for(byte i = 1; i < 32; i++) {
            LinkedList<Integer> low = new LinkedList(), high = new LinkedList();
            split(low, high, subLow, i);
            split(low, high, subHigh, i);
            // update the subsorted lists with the new elements
            subLow = low;
            subHigh = high;
        }
        // Because of 2s complement, subHigh contains any negative numbers and
        // subLow positive numbers. Append the positive numbers to the negative
        // numbers
        subHigh.addAll(subLow);
        return subHigh;
    }

    /**
     * split divides a list into high and low elements based on the given bit
     * @param high list to insert elements when the given bit is 1
     * @param low list to insert elements when the given bit is 0
     * @param src the list to split
     * @param bit the bit to determine which elements are high or low
     */
    private void split(Collection low, Collection high, Collection<Integer> src, int bit) {
        for (int n : src) {
            if ((n >> bit & 1) == 0) {
                low.add(n);
            } else {
                high.add(n);
            }
        }
    }
}
