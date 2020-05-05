package p2_package;

public class GenericArrayClass<GenericData extends Comparable<GenericData>>
{
    private int arrayCapacity;

    private int arraySize;

    private static int DEFAULT_CAPACITY = 10;

    private Object[] localArray;

//Default constructor, initializes array to default capacity (10)

    GenericArrayClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;

        arraySize = 0;

        localArray = new Object[arrayCapacity];
    }

//Initializing constructor, initializes array to specified capacity

    GenericArrayClass(int capacity)
    {
        arrayCapacity = capacity;

        arraySize = 0;

        localArray = new Object[arrayCapacity];
    }

//Accesses item in array at specified index if index within array size bounds

    @SuppressWarnings("unchecked")

    public GenericData accessItemAt(int accessIndex)
    {
        while(accessIndex>=0&&accessIndex<arraySize)
        {
            return (GenericData) localArray[accessIndex];
        }

        return null;

    }

    /*Appends item to end of array, if array is not full, e.g.,
     * no more values can be added
     */
    public boolean appendItem(GenericData newValue)
    {
        if(!isFull())
        {
            localArray[arraySize] = newValue;

            arraySize++;

            return true;
        }

        return false;

    }

    /*	Clears array of all valid values by setting array size to zero,
     * 	 values remain in array but are not accessible
     */

    public void clear()
    {
        arraySize = 0;
    }

//Description: Gets current capacity of array

    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }

//Description: Gets current size of array

    public int getCurrentSize()
    {
        return arraySize;
    }

    /*	Description: Inserts item to array at specified index if array is not full,
     *  e.g., no more values can be added
     */
    public boolean insertItemAt(int insertIndex,GenericData newValue)

    {
        int index;

        if(!isFull()&&insertIndex>=0&&insertIndex<=arraySize)
        {
            for(index = arraySize-1; index>insertIndex;index--)
            {
                localArray[index+1]=localArray[index];
            }
            localArray[insertIndex] = newValue;

            arraySize++;

            return true;
        }

        return false;

    }

//Tests for size of array equal to zero, no valid values stored in array

    public boolean isEmpty()
    {
        return arraySize ==0;
    }

//Tests for size of array equal to capacity, no more values can be added

    public boolean isFull()
    {
        return arrayCapacity == arraySize;
    }

    /* Description: Removes item from array at specified index
     * if index within array size bounds
     */

    @SuppressWarnings("unchecked")

    public GenericData removeItemAt(int removeIndex)
    {
        if(removeIndex>=arraySize)
        {

            return null;
        }

        Object temp = localArray[removeIndex];

        while(removeIndex>=0&&removeIndex<arraySize)
        {

            localArray[removeIndex] = localArray[removeIndex+1];

            removeIndex++;
        }

        arraySize--;

        return (GenericData) temp;


    }

    /*	Description: Resets array capacity, copies current size
     *  and current size number of elements
     */

    public boolean resize(int newCapacity)
    {
        int index;


        while(newCapacity>=arrayCapacity)
        {
            Object [] copiedArray = new Object [newCapacity];

            for(index=0; index<arraySize;index++)
            {
                copiedArray[index] = localArray[index];
            }

            Object[] localArray = new Object[newCapacity];

            for(index = 0; index<arraySize;index++)
            {
                localArray[index] = copiedArray[index];
            }

            return true;
        }

        return false;
    }

//Description: Sorts elements using the bubble sort algorithm

    @SuppressWarnings("unchecked")

    public void runBubbleSort()
    {
        int outIndex,innerIndex;


        GenericData tempItem1,tempItem2;

        for(outIndex=0;outIndex<arraySize-1;outIndex++)
        {
            for(innerIndex=0;innerIndex<arraySize-1-outIndex;innerIndex++)
            {
                tempItem1 =  (GenericData) localArray[innerIndex];

                tempItem2 =  (GenericData) localArray[innerIndex+1];

                if(((GenericData) tempItem1).compareTo((GenericData)tempItem2)>0)
                {
                    swapElements(innerIndex, innerIndex+1);
                }
            }
        }
    }

//Description: Sorts elements using the insertion sort algorithm

    @SuppressWarnings("unchecked")

    public void runInsertionSort()
    {
        int oneIndex,otherIndex;

        Object temp;

        GenericData key,tempItem,tempItem3,tempItem4;

        for(oneIndex=1;oneIndex<arraySize;++oneIndex) {
            key = (GenericData) localArray[oneIndex];

            otherIndex = oneIndex - 1;

            tempItem = (GenericData) localArray[otherIndex];
            while (otherIndex >= 0 && (tempItem.compareTo(key) > 0)) {
                localArray[otherIndex + 1] = localArray[otherIndex];
                otherIndex = otherIndex - 1;
            }
            localArray[otherIndex + 1] = key;

        }
    }

//Description: Sorts elements using the selection sort algorithm

    @SuppressWarnings("unchecked")

    public void runSelectionSort()
    {
        int outIndex,innerIndex,minIndex;

        // One by one move boundary of unsorted subarray
        for (outIndex = 0; outIndex < arraySize -1; outIndex++){

            // Find the minimum element in unsorted array
            minIndex = outIndex;
            for (innerIndex = outIndex + 1; innerIndex < arraySize; innerIndex++) {
                if (((GenericData) localArray[innerIndex]).compareTo((GenericData) localArray[minIndex]) < 0) {
                    minIndex = innerIndex;
                }
            }
            swapElements(outIndex,minIndex);
        }

    }

    /*	Swaps one element in the local array at a given index
     * 	with another element in the array at the other given element
     */

    private void swapElements(int oneIndex, int otherIndex)
    {
        Object temp = localArray[oneIndex];

        localArray[oneIndex] = localArray[otherIndex];

        localArray[otherIndex] = temp;
    }

}