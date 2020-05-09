package p2_package;

public class GenericArrayClass<GenericData extends java.lang.Comparable<GenericData>> 
{
	
	private int arrayCapacity;
	
	private int arraySize;
	
	private static int DEFAULT_CAPACITY = 10;
	
	private java.lang.Object[] localArray;

	/**
	 * default constructor, initializes array to default capacity
	 */
	public GenericArrayClass()
	{
		localArray = new Object[DEFAULT_CAPACITY];
		arrayCapacity = DEFAULT_CAPACITY;
		arraySize = 0;
	}
	
	/**
	 * Initializing constructor, initializes array to specified capacity
	 * @param capacity - maximum capacity specification for the array
	 */
	public GenericArrayClass(int capacity)
	{
		localArray = new Object [capacity];
		arrayCapacity = capacity;
		arraySize = 0;
	}
	
	/**
	 * Accesses item in array at specified index if index within array size bounds
	 * @param accessIndex - index of requested element value
	 * @return - accessed value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData accessItemAt(int accessIndex)
	{	
		 if (accessIndex > arraySize - 1)
	        {
	            return null;
	        }
	        return (GenericData) localArray[accessIndex];
	}
	
	/**
	 * Appends item to end of array, if array is not full, e.g., no more values can be added
	 * @param newValue - value to be appended to array
	 * @return - Boolean success if appended, or failure if array was full
	 */
	public boolean appendItem(GenericData newValue)
	{
		if (!isFull())
		{
			localArray[arraySize] = newValue;
			arraySize++;
		}
		return false;
	}
	
	/**
	 * Clears array of all valid values by setting array size to zero, values remain in array but are not accessible
	 */
	public void clear()
	{
		arraySize = 0;
	}
	
	/**
	 * Gets current capacity of array
	 * Note: capacity of array indicates number of values the array can hold
	 * @return - capacity of array
	 */
	public int getCurrentCapacity()
	{
		return arrayCapacity;
	}
	
	/**
	 * Gets current size of array
	 * Note: size of array indicates number of valid or viable values in the array
	 * @return - size of array
	 */
	public int getCurrentSize() 
	{
		return arraySize;
	}
	
	/**
	 * Inserts item to array at specified index if array is not full, e.g., no more values can be added
	 * Note: Value is inserted at given index, all data from that index to the end of the array is shifted up by one
	 * @param insertIndex - index of element into which value is to be inserted
	 * @param newValue - value to be inserted into array
	 * @return - Boolean success if inserted, or failure if array was full
	 */
	public boolean insertItemAt(int insertIndex, GenericData newValue) 
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
	 * Tests for size of array equal to zero, no valid values stored in array
	 * @return Boolean result of test for empty
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
	 * Tests for size of array equal to capacity, no more values can be added
	 * @return - Boolean result of test for full
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
	 * Description: Removes item from array at specified index if index within array size bounds
	 * Note: Each data item from the element immediately above the 
	   remove index to the end of the array is moved down by one element
	 * @param removeIndex - index of element value to be removed
	 * @return - removed value if successful, null if not
	 */
	@SuppressWarnings("unchecked")
	public GenericData removeItemAt(int removeIndex) 
	{
		int index; 
		GenericData removedItem;

        if(removeIndex >= 0 && removeIndex < arraySize)
        {
            removedItem = (GenericData) localArray[removeIndex];
            index = removeIndex;
            arraySize--;

            while(index < arraySize)
            {
                localArray[index] = localArray[index++];
                index++;
            }
            return removedItem;
        }
        return null;
	}
	
	/**
	 * Description: Resets array capacity, copies current size and current size number of elements
	 * Exception: Method will not resize capacity below current array capacity, 
	   returns false if this is attempted, true otherwise
	 * @param newCapacity - new capacity to be set; must be larger than current capacity
	 * @return - Boolean condition of resize success or failure
	 */
	public boolean resize(int newCapacity) 
	{
		int iterator;
		
		Object[] tempArr = new Object[newCapacity];
		if(newCapacity > arrayCapacity)
		{
			for(iterator = 0; iterator < arrayCapacity; iterator++)
			{
				tempArr[iterator] = localArray[iterator];
			}
			localArray = tempArr;
			arrayCapacity = newCapacity;
		}
		return false;
	}
	
	/**
	 * Sorts elements using the bubble sort algorithm
	 * Note: The data is sorted using the compareTo method of the given data set; 
	   the compareTo method decides the key and the order resulting
	 */
	@SuppressWarnings("unchecked")
	public void runBubbleSort() 
	{
		int index;
		boolean swapped = true;
		
		while(swapped)
		{
			swapped = false;
			for(index = 0; index < arraySize - 1; index++)
			{
				GenericData valOne = (GenericData) localArray[index];
				GenericData valTwo = (GenericData) localArray[index+1];
				
				if(valOne.compareTo(valTwo) > 0)
				{
					swapElements((int)valOne, (int)valTwo);
					swapped = true;
				}
			}
		}
	}
	
	/**
	 * Sorts elements using the insertion sort algorithm
	 * Note: The data is sorted using the compareTo method of the given data set; \
	   the compareTo method decides the key and the order resulting
	 */
	@SuppressWarnings("unchecked")
	public void runInsertionSort() 
	{
		int index;
		
		for(index = 1; index < arraySize; index++)
		{
			int innerIndex = index;
			while(innerIndex > 0 && ((GenericData) localArray[innerIndex-1]).compareTo((GenericData) localArray[innerIndex]) > 0)
			{
				swapElements(innerIndex, innerIndex - 1);
				innerIndex--;
			}
		}
	}
	
	/**
	 * Description: Sorts elements using the selection sort algorithm
	 * Note: The data is sorted using the compareTo method of the given data set; 
	   the compareTo method decides the key and the order resulting
	 */
	@SuppressWarnings("unchecked")
	public void runSelectionSort() 
	{
		int selectedIndex;
		
		for(selectedIndex = 0; selectedIndex < arraySize; selectedIndex++)
		{
			int arrayIndex;
			int lowIndex = selectedIndex;
			
			for(arrayIndex = selectedIndex; arrayIndex < arraySize; arrayIndex++)
			{
				if(((GenericData)localArray[arrayIndex]).compareTo((GenericData)localArray[lowIndex]) < 0)
				{
					lowIndex = arrayIndex;
				}
			}
			if(lowIndex != selectedIndex)
			{
				swapElements(selectedIndex, lowIndex);
			}
		}
	}
	
	/**
	 * Swaps one element in the local array at a given index with another element in the array at the other given element
	 * @param oneIndex - index of one of two elements to be swapped
	 * @param otherIndex - index of second of two elements to be swapped
	 */
	private void swapElements( int oneIndex, int otherIndex )
    {
     Object temp = localArray[ oneIndex ];
     
     localArray[ oneIndex ] = localArray[ otherIndex ];
     
     localArray[ otherIndex ] = temp;
    }
}
	