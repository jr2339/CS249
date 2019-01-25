package project1;

// InsertionSort.java
// demonstrates insertion sort
//--------------------------------------------------------------
class InsertionSort {
    
    /**
     * Run insertionSort on various amounts of inversely sorted and sorted
     * data to verify O(N^2) efficiency
     */
    public static void main(String[] args) {
        InsertionSort iSort;
        
        iSort = new InsertionSort(10);
        for(int i = 1; i<=10; i++) {
            iSort.insert(i);
        }
        System.out.println("10 sorted items:");
        iSort.insertionSort();
        
        iSort = new InsertionSort(10);
        for(int i = 10; i>=1; i--) {
            iSort.insert(i);
        }
        System.out.println("\n10 inversely sorted items:");
        iSort.insertionSort();
        
        iSort = new InsertionSort(100);
        for(int i = 1; i<=100; i++) {
            iSort.insert(i);
        }
        System.out.println("\n100 sorted items:");
        iSort.insertionSort();
        
        iSort = new InsertionSort(100);
        for(int i = 100; i>=1; i--) {
            iSort.insert(i);
        }
        System.out.println("\n100 inversely sorted items:");
        iSort.insertionSort();
        
        iSort = new InsertionSort(1000);
        for(int i = 1; i<=1000; i++) {
            iSort.insert(i);
        }
        System.out.println("\n1000 sorted items:");
        iSort.insertionSort();
        
        iSort = new InsertionSort(1000);
        for(int i = 1000; i>=1; i--) {
            iSort.insert(i);
        }
        System.out.println("\n1000 inversely sorted items:");
        iSort.insertionSort();
    }
    
    private long[] a;
// ref to array a
    private int nElems;
// number of data items
//--------------------------------------------------------------

    public InsertionSort(int max) // constructor
    {
        a = new long[max];
// create the array
        nElems = 0;
// no items yet
    }
//--------------------------------------------------------------

    public void insert(long value) // put element into array
    {
        a[nElems] = value;
// insert it
        nElems++;
// increment size
    }
//--------------------------------------------------------------

    public void display() // displays array contents
    {
        for (int j = 0; j < nElems; j++) // for each element,
        {
            System.out.print(a[j] + " "); // display it
        }
        System.out.println("");
    }
//--------------------------------------------------------------

    public void insertionSort() {
        // Count the number of comparisons and assignments made during the sort
        int comparisons = 0, assignments = 0;
        int in, out;
        // out = 1
        assignments++;
        for (out = 1; out < nElems; out++) {
            // out < nElems
            comparisons++;
            // out++
            assignments++;
            
            assignments++;
            long temp = a[out];
            assignments++;
            in = out;
            while (true) {
                comparisons++;
                if (in <= 0) {
                    break;
                }
                comparisons++;
                if (a[in - 1] < temp) {
                    break;
                }
                assignments++;
                a[in] = a[in - 1];
                assignments++;
                --in;
            }
// out is dividing line
// remove marked item
// start shifts at out
// until one is smaller,
// shift item to right
// go left one position
            assignments++;
            a[in] = temp;
// insert marked item
        } // end for
        // Display comparison and assignment totals
        System.out.println("Comparisons: " + comparisons + "\nAssignments: " + assignments);
    } // end insertionSort()
//--------------------------------------------------------------
} // end class ArrayIns