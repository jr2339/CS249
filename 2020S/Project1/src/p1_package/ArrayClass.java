package p1_package;

public class ArrayClass {
    private static final int DEFAULT_CAPACITY = 10;
    public static final int FAILED_ACCESS = -999999;
    private int[] localArray;
    private int arraySize;
    private int arrayCapacity;


    /*
      *Default constructor, initializes array to default capacity (10)
     */
    public ArrayClass(){
        arrayCapacity = DEFAULT_CAPACITY;
        arraySize = 0;
        localArray = new int[arrayCapacity];
    }

    /*
     * @param capacity - integer maximum capacity specification for the array
     */
    public ArrayClass(int capacity){
        arrayCapacity = capacity;
        arraySize = 0;
        localArray = new int[arrayCapacity];
    }

    /*
     * @param capacity - maximum capacity specification for the array
     * @param size - sets the number of items to be filled in array, and sets the size of the ArrayClass object
     * @param fillValue - value to be placed in all elements of initialized array up to the size
     */
    public ArrayClass(int capacity, int size, int fillValue){
        arrayCapacity = capacity;
        arraySize = size;
        localArray = new int[capacity];
        for (int index = 0; index < size; index++){
            localArray[index] = fillValue;
        }
    }

    /*
     * @param copied - ArrayClass object to be copied
     */
    public ArrayClass(ArrayClass copied)
    {
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        localArray = new int[arrayCapacity];
        for (int index = 0; index < arraySize; ++index){
            localArray[index] = copied.localArray[index];
        }
    }

    /**
     *
     * @param accessIndex - index of requested element value
     * @return accessed value if successful, FAILED_ACCESS (-999999) if not
     */
    public int accessItemAt(int accessIndex)
    {

        for (int index = 0; index < arraySize; index++)
        {

            if (index == accessIndex){
                //return accessIndex;
                return localArray[accessIndex];
            }

        }
        return FAILED_ACCESS;

    }

    /*
     * @param newValue - value to be appended to array
     * @return Boolean success if appended, or failure if array was full
     * Appends item to end of array, if array is not full, e.g., no more values can be added
     */
    public boolean appendItem(int newValue){
            if (arraySize < arrayCapacity){
                localArray[arraySize] = newValue;
                //Now incresement array size;
                arraySize++;
                return true;
            }
            else {
                return false;
            }
    }

    /*
     *Clears array of all valid values by setting array size to zero,
     * values remain in array but are not accessible
     */
    public void clear()
    {
        arraySize = 0;
    }

    /**
     *
     * @return capacity of array
     */
    public int getCurrentCapacity()
    {

        return arrayCapacity;

    }

    /**
     *
     * @return size of array
     */
    public int getCurrentSize()
    {

        return arraySize;

    }

    /**
     *
     * @param insertIndex - index of element into which value is to be inserted
     * @param newValue - value to be inserted into array
     * @return Boolean success if inserted, or failure if array was full
     */
    public boolean insertItemAt(int insertIndex, int newValue)
    {

        if (isFull()){
            return false;
        }
        else{

            for (int index = arraySize - 1; index > insertIndex - 1; index--)
            {
                localArray[index + 1] = localArray[index];
            }
            localArray[insertIndex] = newValue;
            arraySize++;
            return true;
        }

    }

    /**
     *
     * @return Boolean result of test for full
     */
    public boolean isFull()
    {

        if (arraySize == arrayCapacity){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @return Boolean result of test for empty
     */
    public boolean isEmpty()
    {

        if (arraySize == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param removeIndex - index of element value to be removed
     * @return removed value if successful, FAILED_ACCESS (-999999) if not
     */
    public int removeItemAt(int removeIndex)
    {
        if (removeIndex >= arrayCapacity || isEmpty()){
            return FAILED_ACCESS;
        }
        else{

            for (int index = removeIndex; index < arraySize; index++)

            {
                    localArray[index] = localArray[index + 1];

            }
            arraySize--;
            return localArray[removeIndex];
        }
    }

    /**
     *
     * @param newCapacity - new capacity to be set; must be larger than current capacity
     * @return Boolean condition of resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        if (newCapacity > arrayCapacity){
            int[] copiedArray = new int[newCapacity];
            for(int index = 0; index<arraySize;index++)

            {
                copiedArray[index]=localArray[index];
            }

            arrayCapacity = newCapacity;

            int localArray[] = new int [arrayCapacity];

            for(int index = 0; index<arraySize;index++)
            {
                localArray[index] = copiedArray[index];
            }

            return true;
        }
        else {
            return false;
        }
    }

}
