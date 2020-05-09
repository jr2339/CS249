package p1_package;

public class ArrayClass {
	
	private int arrayCapacity; 
	
	private int arraySize;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	public static final int FAILED_ACCESS = -999999;
	
	private int [] localArray;
	
	/**
	 * default constructor, initializes array to default capacity (10)
	 */
	public ArrayClass() 
	{
		localArray = new int[DEFAULT_CAPACITY];
		arrayCapacity = DEFAULT_CAPACITY;
		arraySize = 0;
	}
	
	/**
	 * initializing constructor, initializes array to specified capacity
	 * @param capacity - integer maximum capacity specified for the array
	 */
	public ArrayClass(int capacity) 
	{
		localArray = new int [capacity];
		arrayCapacity = capacity;
		arraySize = 0;
	}
	
	/**
	 * initializing constructor, initializes array to specified
	   capacity, size to specified value, then files all elements
	   with specified size value
	 * @param capacity - maximum capacity specification for the array
	 * @param size - sets the number of items to be filled in array, and sets the size of the ArrayClass object
	 * @param fillValue - value to be placed in all elements of the initialized array up to the size
	 */
	public ArrayClass(int capacity, int size, int fillValue) 
	{
		int index; 
		
		localArray = new int[capacity];
		arrayCapacity = capacity;
		arraySize = size;
		
		for(index = 0; index < arraySize; index++)
		{
			localArray[index] = fillValue;
		}
	}
	
	/**
	 * copy constructor, initializes array to size and capacity of 
	   copied array, then copies only the elements up to the given size
	 * @param copied - ArrayClass object to be copied
	 */
	public ArrayClass(ArrayClass copied) 
	{
		int index;
		
		arrayCapacity = copied.arrayCapacity;
		arraySize = copied.arraySize;
		localArray = new int[this.arrayCapacity];
		
		for(index = 0; index < arraySize; index++)
		{
			localArray[index] = copied.localArray[index];
		}
	}
	
	/**
	 * accesses item in array at specified location if index is within array bounds
	 * @param accessIndex - index of requested element value
	 * @return access value if successful, FAILED_ACCESS if not
	 */
	public int accessItemAt(int accessIndex)
	{
		if(accessIndex <= arraySize)
		{
			return localArray[accessIndex];
		}
		return FAILED_ACCESS;
	}
	
	/**
	 * appends item to end of array, if array is not full, no more values can be added
	 * @param newValue - value to be appended
	 * @return boolean success if appended, or failure if array was full
	 */
	public boolean appendItem(int newValue)
	{
		if (!isFull())
		{
			localArray[arraySize] = newValue;
			arraySize++;
		}
		return false;
	}
	
	/**
	 * clears array of all values by setting size to 0, values remain but are not accessible
	 */
	public void clear()
	{
		arraySize = 0;
	}
	
	/**
	 * gets current capacity of array
	 * @return capacity of array
	 */
	public int getCurrentCapacity()
	{
		return arrayCapacity;
	}
	
	/**
	 * gets current size of array
	 * @return size of array
	 */
	public int getCurrentSize()
	{
		return arraySize;
	}
	
	/**
	 * inserts item to array at specified index if not full
	 * note: all data above insert point is shifted up by one
	 * note: can be inserted after the last element but not past that point
	 * @param insertIndex - index of element into which value is to be inserted
	 * @param newValue - value to be inserted into array
	 * @return success if inserted, failure if not
	 */
	public boolean insertItemAt(int insertIndex, int newValue)
	{
        int index;

        if(!isFull() && insertIndex >= 0 && insertIndex <= arraySize)
        {
            index = arraySize;
            while(index > insertIndex)
            {
                localArray[ index ] = localArray[ index-- ];
                index--;
            }
            localArray[ index ] = newValue;
            arraySize++;
        }
        return false;
    }
	
	/**
	 * tests for size of array equal to capacity, no more values can be added
	 * @return result of test
	 */
	public boolean isFull()
	{
		if(arraySize == arrayCapacity)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * tests for size of array equal to zero, no values stored in array
	 * @return result of test
	 */
	public boolean isEmpty()
	{
		if(arraySize == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * removes item from array at specified index if it is within size bounds
	 * note: each element above the remove index is shifted down one
	 * @param removeIndex - index of element to be removed
	 * @return removed value if successful, FAILED_ACCESS if not
	 */
	public int removeItemAt(int removeIndex)
	{
        int index, removedItem;

        if(removeIndex >= 0 && removeIndex < arraySize)
        {
            removedItem = localArray[ removeIndex ];
            index = removeIndex;
            arraySize--;

            while(index < arraySize)
            {
                localArray[ index ] = localArray[ index++ ];
                index++;
            }
            return removedItem;
        }
        return FAILED_ACCESS;
    }
	
	/**
	 * resets array capacity, copies current size and size number of elements
	 * exception: method will not resize below current capacity, returns false if this is attempted, true otherwise
	 * @param newCapacity - new capacity to be set, must be larger than current capacity
	 * @return boolean condition of resize success or failure
	 */
	public boolean resize(int newCapacity)
	{
        int index;
        int[] newArr = new int[ newCapacity ];

        if(newCapacity > arrayCapacity)
        {
            for(index = 0; index < arraySize; index++)
            {
            	newArr[ index ] = localArray[ index ];
            }
            arrayCapacity = newCapacity;
            localArray = newArr;
        }
        return false;
    }
}
