package p3_package;

public class ArrayClass
{
    // array capacity member

    private int arrayCapacity;

    // array size member

    private int arraySize;

    // Constant for default capacity

    private static int DEFAULT_CAPACITY;

    // Constant for failed access

    static int FAILED_ACCESS;

    // local array member

    private int[] localArray;

    // Default constructor, initializes array to default capacity

    ArrayClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;

        arraySize = 0;

        localArray = new int[arrayCapacity];
    }

    /**
     * Copy constructor,initializes array to size
     * and capacity of copied array, then
     * copies only the elements up to the given size
     */

    ArrayClass(ArrayClass copied)
    {
        arrayCapacity = copied.arrayCapacity;

        arraySize = copied.arraySize;

        localArray = new int[arrayCapacity];

        int index = 0;

        for (index = 0; index < arraySize; index++)
        {
            localArray[index] = copied.localArray[index];

        }
    }

    // Initializing constructor, initializes array to specified capacity

    ArrayClass(int capacity)
    {
        arrayCapacity = capacity;

        arraySize = 0;

        localArray = new int[arrayCapacity];
    }

    /**
     * Initializing constructor, initializes array to specified capacity,
     * size to specified value, then fills all elements
     * with specified size value
     */

    ArrayClass(int capacity, int size, int fillValue)
    {
        if (capacity > arraySize)
        {
            arrayCapacity = capacity;

            arraySize = size;

            localArray = new int[arrayCapacity];

            int index = 0;

            for (index = 0; index < arraySize; index++)
            {
                localArray[index] = fillValue;
            }
        }

    }

    /**
     * Accesses item in array at specified index if index
     * within array size bounds
     * @param: accessIndex - index of requested element value
     * @return: accessed value if successful, FAILED_ACCESS (-999999) if not
     */

    public int accessItemAt(int accessIndex)
    {
        while (accessIndex < arraySize && accessIndex >= 0)
        {
            return localArray[accessIndex];
        }
        return FAILED_ACCESS;
    }

    /**
     * Appends item to end of array, if array is not full,
     *  e.g., no more values can be added
     * @param: newValue - value to be appended to array
     * @return: boolean success if appended, or failure if array was full
     */

    public boolean appendItem(int newValue)
    {
        if (!isFull())
        {
            localArray[arraySize] = newValue;

            arraySize++;

            return true;
        }

        return false;
    }

    /**
     * Clears array of all valid values by setting array size to zero,
     * values remain in array but are not accessible
     */

    public void clear()
    {
        arraySize = 0;
    }

    // Simple array dump for testing purposes

    private void dump()
    {
        int index;

        for (index = 0; index < arraySize; index++)
        {
            System.out.println(localArray[index]);
        }
    }

    /**
     * Description: Gets current capacity of array Note: capacity of array
     * indicates number of values the array can hold
     *
     * @return: capacity of array
     */

    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }

    /**
     * Description: Gets current size of array Note: size of array indicates
     *number of valid or viable values in the array
     *
     * @return: size of array
     */

    public int getCurrentSize()
    {
        return arraySize;
    }

    /**
     * Generates random number between given low and high values
     *
     * @param: low - lowest value that will be generated by method
     *  high - highest value that will be generated by method
     * @return: the generated random value
     *
     */

    private int getRandBetween(int low, int high)
    {
        int value, range = high - low + 1;

        value = (int) (Math.random() * range);

        return low + value;
    }

    /**
     * Description: Inserts item to array at specified index
     * if array is not full, e.g., no more values can be added
     * Note: Value is inserted at given index, all data from that index
     *  to the end of the array is shifted up by one
     *  Note: Value can be inserted after the last valid element
     *  but not at any index past that point
     * @Param: insertIndex - index of element into which value is
     * to be inserted newValue - value to be inserted into array
     * @return: boolean success if inserted, or failure if array was full
     *
     */

    public boolean insertItemAt(int insertIndex, int newValue)
    {
        int index;

        if (!isFull() && insertIndex >= 0 && insertIndex <= arraySize)
        {

            for (index = arraySize - 1; index >= insertIndex; index--)
            {
                localArray[index + 1] = localArray[index];
            }
            localArray[insertIndex] = newValue;

            arraySize++;

            return true;
        }
        return false;
    }

    /**
     * Tests for size of array equal to zero, no valid values stored in array
     *
     * @return: boolean result of test for empty
     *
     */

    public boolean isEmpty()
    {
        return arraySize == 0;
    }

    /**
     * Tests for size of array equal to capacity, no more values can be added
     *
     * @return: boolean result of test for full
     */

    public boolean isFull()
    {
        return arraySize == arrayCapacity;
    }

    /**
     * Tests for value found in object array; returns true
     * if value within array,false otherwise
     * @param: testVal - value to be tested return: boolean true
     *  if value is found in array, false otherwise
     */

    public boolean isInArray(int testVal)
    {
        int index;

        for (index = 0; index < arraySize; index++)
        {
            if (localArray[index] == testVal)
                return true;
        }

        return false;
    }

    /**
     * Loads a specified number of unique random numbers in object
     * Note: This method overwrites all data in the array
     * up to the number of randoms requested
     * Note:If requested number of randoms is greater than the array capacity,
     * the array is resized
     *  Note: Size is set to number of random numbers requested
     * Exceptional Condition: If more values are requested
     * than are possible given the range of numbers, method returns false,
     * otherwise, it returns true
     * @param: numRands - number of random values requested
     * lowLimit - lowest value to be generated
     * highLimit - highest value to be generated
     * @return: boolean true if method sucessful; false otherwise
     */

    public boolean loadUniqueRandoms(int numRands,
                                     int lowLimit, int highLimit)
    {
        int index;

        int number;


        if (numRands > highLimit - lowLimit + 1 || numRands < 0)
            return false;

        if (numRands>arrayCapacity)
        {
            resize(numRands);
        }
        arraySize = numRands;
        index = 0;
        do {
            number = getRandBetween(lowLimit, highLimit);
            //if it is not unique, re generate
            while(isInArray(number)){
                number = getRandBetween(lowLimit, highLimit);
            }

            localArray[index] = number;
            index++;
            
        }while(true);
    }

    /**
     * Description: Removes item from array at specified index
     * if index within array size bounds
     * Note: Each data item from the element immediately above the
     * remove index to the end of the array is moved down by one element
     * @param: removeIndex - index of element value to be removed
     * @return: removed value if successful, FAILED_ACCESS (-999999) if not
     */

    public int removeItemAt(int removeIndex)
    {

        if(removeIndex>=arraySize||removeIndex<0)
        {
            return FAILED_ACCESS;
        }

        int temp = localArray[removeIndex];

        while (removeIndex >= 0 && removeIndex < arraySize)
        {

            localArray[removeIndex] = localArray[removeIndex + 1];

            removeIndex++;
        }

        arraySize--;

        return temp;
    }

    /**
     * Description: Resets array capacity, copies current size
     * and current size number of elements
     * Exception: Method will not resize capacity below current
     * array size, returns false if this is attempted, true otherwise
     * @param newCapacity- new capacity to be set; must
     * be larger than current capacity
     * @return boolean condition of resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        int index;

        while (newCapacity > arrayCapacity)
        {
            int[] copiedArray = new int[arrayCapacity];

            for (index = 0; index < arraySize; index++)

            {
                copiedArray[index] = localArray[index];
            }

            arrayCapacity = newCapacity;

            int localArray[] = new int[arrayCapacity];

            for (index = 0; index < arraySize; index++)
            {
                localArray[index] = copiedArray[index];
            }

            return true;
        }
        return false;
    }

    /**
     * Merges values brought in between a low and high index
     * segment of an array
     * Note: uses locally sized single array for temporary storage
     * @param lowIndex-    lowest index of array segment to be managed
     * @param middleIndex- middle index of array segment to be managed
     * @param highIndex-   high index of array segment to be managed
     */
    private void runMerge(int lowIndex, int middleIndex, int highIndex)
    {
        int[] temp = new int[highIndex - lowIndex + 1];

        int leftIndex = lowIndex;

        int rightIndex = middleIndex + 1;

        int tempIndex = 0;

        int index;

        while (leftIndex < middleIndex && rightIndex < highIndex)
        {
            if (localArray[leftIndex] < localArray[rightIndex])
            {
                temp[tempIndex] = localArray[leftIndex];

                tempIndex++;

                leftIndex++;
            }
            else
            {
                temp[tempIndex] = localArray[rightIndex];

                tempIndex++;

                leftIndex++;
            }
        }
        while (leftIndex <= middleIndex)
        {
            temp[tempIndex] = localArray[leftIndex];

            tempIndex++;

            leftIndex++;
        }
        while (rightIndex <= highIndex)
        {
            temp[tempIndex] = localArray[rightIndex];

            tempIndex++;

            rightIndex++;
        }
        for (index = 0; index < highIndex - lowIndex + 1; index++)
        {
            localArray[index + lowIndex] = temp[index];
        }
    }

    /**
     * Data sorted using merge sort algorithm
     * Note: Call runMergeSortHelper with
     * lower and upper indices of array to be sorted
     */
    public void runMergeSort()
    {
        runMergeSortHelper(0, arraySize-1);
    }

    /**
     * Merge sort helper, places low and high indices of array segment to be
     * processed into recursive method,
     * then sorts data using merge sort algorithm
     * @param lowIndex   - lowest index of array segment to be managed;
     *  this varies as the segments are broken down recursively
     * @param highIndex- highest index of array segment to be managed;
     *  this varies as the segments are broken down recursively
     */
    private void runMergeSortHelper(int lowIndex, int highIndex)
    {
        int mid = (lowIndex + highIndex) / 2;

        if (lowIndex < highIndex)
        {
            runMergeSortHelper(lowIndex, mid);

            runMergeSortHelper(mid + 1, highIndex);

            runMerge(lowIndex, mid, highIndex);
        }
    }

    /**
     * partitions array using the first value as the partition;
     * when this method is complete the partition value is
     * in the correct location in the array
     * @param lowIndex-  low index of array segment to be partitioned
     * @param highIndex- high index of array segment to be partitioned
     * @return integer index of partition pivot
     */
    private int runPartition(int lowIndex, int highIndex)
    {
        int oneIndex = lowIndex;

        int otherIndex = highIndex;

        int pivot = localArray[lowIndex];

        while (oneIndex != otherIndex)
        {
            while (localArray[otherIndex] >= pivot &&
                    oneIndex < otherIndex)
            {
                otherIndex--;
            }

            while (localArray[oneIndex] <= pivot && oneIndex < otherIndex)
            {
                oneIndex++;
            }
            swapValuesAtIndex(otherIndex, oneIndex);

        }
        localArray[lowIndex] = localArray[oneIndex];

        localArray[oneIndex] = pivot;

        return oneIndex;

    }

    /**
     * Data sorted using quick sort algorithm
     * Note: Call runQuickSortHelper with
     * lower and upper indices of array to be sorted
     */
    public void runQuickSort()
    {
        runQuickSortHelper(0, arraySize-1);
    }

    /**
     * helper method run with parameters that support recursive access
     *
     * @param lowIndex - low index of the segment of the array to be processed
     * @param highIndex - high index of the segment of the array to be processed
     */
    private void runQuickSortHelper(int lowIndex, int highIndex)
    {

        int mid;

        mid=runPartition(lowIndex, highIndex);

        swapValuesAtIndex(lowIndex, mid-1);

        swapValuesAtIndex(mid +1 , highIndex);
    }

    // Sorts data using the Shell's sort algorithm
    public void runShellSort()
    {
        int gap, gapPassIndex, insertionIndex;
        int tempItem, testItem;
        boolean continueSearch;

        for (gap = arraySize / 2; gap > 0; gap /= 2)
        {
            for (gapPassIndex = gap; gapPassIndex < arraySize;
                 gapPassIndex++)
            {
                tempItem = localArray[gapPassIndex];

                insertionIndex = gapPassIndex;

                continueSearch = true;

                while (continueSearch && insertionIndex >= gap)
                {
                    testItem = localArray[insertionIndex - gap];

                    if (testItem > tempItem)
                    {
                        localArray[insertionIndex] =
                                localArray[insertionIndex - gap];

                        insertionIndex -= gap;
                    }

                    else
                    {
                        continueSearch = false;
                    }

                }

                localArray[insertionIndex] = tempItem;
            }

        }
    }

    /**
     * swaps values in the object array by taking in the indices of the array
     * locations Note: for a small level of optimization,
     *  this method does not swap values if the indices are the same
     *
     * @param oneIndex   - index of the of the values to be swapped
     * @param otherIndex - index of the other value to be swapped
     */
    private void swapValuesAtIndex(int oneIndex, int otherIndex)
    {
        int temp = localArray[oneIndex];

        if (oneIndex != otherIndex)
        {
            localArray[oneIndex] = localArray[otherIndex];

            localArray[otherIndex] = temp;
        }
    }

}