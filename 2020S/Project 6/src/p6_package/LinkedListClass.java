package p6_package;

public class LinkedListClass 
{
	/**
	 * Reference to head of linked list
	 */
	private BoxClass headRef;
	
	/**
	 * Default constructor
	 */
	public LinkedListClass()
	{
		headRef = null;
	}
	
	/**
	 * Initializing constructor, fills all elements with specified size value up to given size; 
	 * overwrites any data already in list
	 * @param size - sets the number of items to be filled in linked list
	 * @param fillValue - value to be placed in all elements of initialized linked list up to the size
	 */
	public LinkedListClass(int size, BoxClass fillValue)
	{
		 int listIndex;
		 
	     BoxClass currentRef = headRef;
	     BoxClass temp;
	     for(listIndex = 0; listIndex < size; listIndex++)
	     	{
	    	 	temp = new BoxClass(fillValue);
	    	 	currentRef.nextRef = temp;
	    	 	currentRef = currentRef.nextRef;
	        }
	}
	
	/**
	 * Copy constructor, initializes linked list to the size of copied linked list, 
	 * then copies only the elements up to the given size
	 * @param copied - LinkedListClass object to be copied
	 */
	public LinkedListClass(LinkedListClass copied)
	{
		headRef = copied.headRef;
	}
	
	/**
	 * Accesses item in linked list at specified index if index within linked list bounds
	 * @param accessIndex - index of requested element value
	 * @return accessed value if successful, null if not
	 */
	public BoxClass accessItemAt(int accessIndex)
	{
		 int listIndex;
		 
	     if(accessIndex < 0 || accessIndex > getCurrentSize())
	     	{
	    	 	return null;
	        }
	        	BoxClass temp = headRef.nextRef;
	        	for(listIndex = 1; listIndex < accessIndex; listIndex++)
	        {
	        	temp = temp.nextRef;
	        }
	        return temp;
	}
	
	/**
	 * Appends item to end of linked list, if linked list is not full, e.g., no more values can be added
	 * @param newValue - BoxClass object to be appended to linked list
	 */
	public void appendItem(BoxClass newValue)
	{
		BoxClass tail = findLastItemRef();
		tail.nextRef = newValue;
	}
	
	/**
	 * Clears linked list of all valid values by setting linked list size to zero, 
	 * values remain in linked list but are not accessible
	 */
	public void clear()
	{
		headRef = null;
	}
	
	/**
	 * Simple list dump for testing purposes
	 */
	@SuppressWarnings("unused")
	private void dump()
	{
		BoxClass currentNode = headRef;
		
		while (currentNode != null)
		{
			System.out.print(currentNode);
			if(currentNode.nextRef != null)
			{
				System.out.print(", ");
			}
			currentNode = currentNode.nextRef;
		}
	}
	
	/**
	 * Finds reference to last node in linked list
	 * @return BoxClass reference to last item
	 */
	private BoxClass findLastItemRef()
	{
		BoxClass current = headRef;
		BoxClass next = headRef.nextRef;
		
		while(next != null)
		{
			current = next;
			next = current.nextRef;
		}
		return current;
	}
	
	/**
	 * Description: Gets current size of linked list
	 * Note: size of linked list indicates number of valid or viable values in the linked list
	 * @return size of list
	 */
	public int getCurrentSize()
	{
		return getCurrentSizeHelper(headRef);
	}
	
	/**
	 * Recursive helper method finds length of linked list
	 * @param workingRef - BoxClass reference used for recursion
	 * @return integer value with size of linked list at a given point in the recursion
	 */
	public int getCurrentSizeHelper(BoxClass workingRef)
	{
		if(workingRef == null)
		{
			return 0;
		}
		return 1 + getCurrentSizeHelper(workingRef.nextRef);
	}
	
	/**
	 * Description: Inserts item into linked list at specified index
	 * Note: Value is inserted at given index which is inserted into the linked list at that point
	 * Note: Value can be inserted after the last valid element but not at any index past that point
	 * @param insertIndex - index of element into which value is to be inserted
	 * @param newValue - value to be inserted into linked list
	 * @return boolean success if inserted, or failure if linked list was full
	 */
	public boolean insertItemAt(int insertIndex, BoxClass newValue)
	{
		if(insertIndex < 0 || insertIndex > getCurrentSize())
		{
			return false;
		}
		if(insertIndex == 0)
		{
			newValue.nextRef = headRef;
			headRef = newValue;
			return true;
		}
		BoxClass temp = accessItemAt(insertIndex - 1);
		newValue.nextRef = temp.nextRef;
		temp.nextRef = newValue;
		return true;
	}
	
	/**
	 * Tests for size of linked list equal to zero, no valid values stored in linked list
	 * @return boolean result of test for empty
	 */
	public boolean isEmpty()
	{
		return headRef == null;	
	}
	
	
	/**
	 * Tests for value found in object linked list; returns true if value within linked list, false otherwise
	 * @param testVal - value to be tested
	 * @return boolean true if value is found in linked list, false otherwise
	 */
	public boolean isInlinkedList(BoxClass testVal)
	{
		BoxClass current = headRef;
		
		while(current != null)
		{
			if(current == testVal)
			{
				current = current.nextRef;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Description: Removes item from linked list at specified index if index within linked list size bounds
	 * @param removeIndex - index of element value to be removed
	 * @return removed value if successful, null if not
	 */
	public BoxClass removeItemAt(int removeIndex)
	{
		BoxClass removedVal = accessItemAt(removeIndex);
		
		if(removedVal == headRef)
		{
			headRef = headRef.nextRef;
		}
		else
		{
			BoxClass temp = accessItemAt(removeIndex - 1);
			temp.nextRef = removedVal.nextRef;
		}
		return removedVal;
	}
}