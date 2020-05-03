package p9_package;

public class OpCodeHeapClass {

    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY=10;

    /**
     * Array for heap
     */
    private OpCodeClass[] heapArray;

    /**
     * Management data for array
     */
    private int arraySize;

    /**
     * Management data for array
     */
    private int arrayCapacity;

    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;

    /**
     * Default constructor sets up array management conditions and default display flag setting
     */
    public OpCodeHeapClass()
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        heapArray = new OpCodeClass[arrayCapacity];
        displayFlag = false;
    }

    /**
     * Copy constructor copies array and array management conditions and default display flag setting
     * @param copied - OpCodeHeapClass object to be copied
     */
    public OpCodeHeapClass(OpCodeHeapClass copied)
    {
        arraySize = copied.arraySize;
        arrayCapacity = copied.arrayCapacity;
        displayFlag = copied.displayFlag;
        int index = 0;
        for(index = 0 ; index < copied.arraySize ; index ++)
        {
            heapArray[index] = copied.heapArray[index];
        }
    }

    /**
     * Accepts OpCodeData item and adds it to heap
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data addition
     * Note: must check for resize before attempting to add an item
     * @param newItem - OpCodeClass data item to be added
     */
    public void addItem(OpCodeClass newItem)
    {
        checkForResize();
        heapArray[arraySize] = newItem;
        if(displayFlag)
        {
            System.out.println("Adding new process: " + newItem);
        }
        arraySize ++;
        if(arraySize>1)
        {
            bubbleUpArrayHeap(arraySize-1);
        }
    }

    /**
     * Recursive operation to reset data in the correct order for the min heap after new data addition
     * @param currentIndex - index of current item being assessed, and moved up as needed
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
        int parentIndex;
        OpCodeClass localItem;
        OpCodeClass parentItem;
        if (currentIndex > 0 && currentIndex < arraySize)
        {
            localItem = heapArray[currentIndex];
            parentIndex = (currentIndex - 1) / 2;
            parentItem = heapArray[parentIndex];
            if (localItem.compareTo(parentItem) < 0)
            {
                if (displayFlag)
                {
                    System.out.println("   - Bubble up: ");
                    System.out.println("     - Swapping parent: " +
                            parentItem + "; with child: " + localItem);
                }
                heapArray[parentIndex] = localItem;
                heapArray[currentIndex] = parentItem;
                bubbleUpArrayHeap(parentIndex);
            }
        }
    }

    /**
     * Automatic resize operation used prior to any new data addition in the heap
     * Tests for full heap array, and resizes to twice the current capacity as required
     */
    private void checkForResize()
    {
        int index=0;
        if (arraySize == arrayCapacity)
        {
            arrayCapacity = arrayCapacity * 2;
            OpCodeClass[] newHeapArray = new OpCodeClass[arrayCapacity];
            for (index = 0; index < arraySize; index ++)
            {
                newHeapArray[index] = heapArray[index];
            }
            heapArray = newHeapArray;
        }
    }

    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }

    /**
     * Removes OpCodeClass data item from top of min heap, thus being the operation
     * with the lowest priority value
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data removal
     * @return OpCodeClass item removed
     */
    public OpCodeClass removeItem()
    {
        if (!isEmpty()){
            OpCodeClass removeItem = new OpCodeClass();
            removeItem = heapArray[0];
            heapArray[0] = heapArray[arraySize - 1];
            heapArray[arraySize-1] = null;
            arraySize -- ;
            if (displayFlag)
            {
                System.out.println("Removing process: " + removeItem);
            }
            trickleDownArrayHeap(0);
            return removeItem;
        }
        else{
            System.out.println("The heapArray is empty");
            return null;
        }

    }

    /**
     * Utility method to set the display flag for displaying internal
     * operations of the heap bubble and trickle operations
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag(boolean setState)
    {
        displayFlag = setState;
    }

    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        if(displayFlag)
        {
            int index = 0;
            for(index = 0 ; index < arraySize ; index ++)
            {
                System.out.print(heapArray[index].toString() + " ");
            }
        }
    }

    /**
     * Recursive operation to reset data in the correct order for the min heap after data removal
     * @param currentIndex - index of current item being assessed, and moved down as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
        int largestIndex = currentIndex;
        int leftChildIndex=0;
        int rightChildIndex=0;
        leftChildIndex = (currentIndex + 1) * 2 - 1;
        rightChildIndex = (currentIndex + 1) * 2;
        OpCodeClass parentData = heapArray[currentIndex];

        if (leftChildIndex < arraySize && heapArray[leftChildIndex].compareTo(heapArray[largestIndex]) < 0)
        {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < arraySize && heapArray[rightChildIndex].compareTo(heapArray[largestIndex]) < 0)
        {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != currentIndex)
        {
            heapArray[currentIndex] = heapArray[largestIndex];
            heapArray[largestIndex] = parentData;
            trickleDownArrayHeap(largestIndex);
        }
    }

}