package p6_package;

public class LinkedListClass {

    /**
     * Reference to head of linked list
     */
    private BoxClass headRef;

    /**
     * Default constructor
     * Just set headRef to null
     */
    public LinkedListClass()
    {
        headRef = null;
    }

    /**
     * Initializing constructor, fills all elements with
     * specified size value up to given size;
     * overwrites any data already in list
     * @param size - sets the number of items to be filled in linked list
     * @param fillValue - value to be placed in all elements of
     * initialized linked list up to the size
     */
    public LinkedListClass(int size, BoxClass fillValue)
    {
        BoxClass workingRef = headRef;

        headRef = new BoxClass(fillValue);

        for(int index = 0;index < size ; index++)
        {
            workingRef.nextRef = fillValue;

            workingRef = workingRef.nextRef;
        }
    }

    /**
     * Copy constructor, initializes linked list to the size of
     * copied linked list, then copies only the elements up to the given size
     * @param copied - LinkedListClass object to be copied
     */
    public LinkedListClass(LinkedListClass copied)
    {
        int size = copied.getCurrentSize();
        LinkedListClass local = new LinkedListClass();
        for (int index =0; index < size; index++){
            local.appendItem(copied.accessItemAt(index));
        }
        local.appendItem(null);
    }

    /**
     * Accesses item in linked list at specified index
     * if index within linked list bounds
     * @param accessIndex - index of requested element value
     * @return accessed value if successful, null if not
     */
    public BoxClass accessItemAt(int accessIndex)
    {
        BoxClass workingRef = headRef;
       if (accessIndex >=0 && accessIndex <= getCurrentSize()){
           for (int index = 0; index < accessIndex; index++){
               workingRef = workingRef.nextRef;
           }
           return workingRef;
       }
        return null;

    }

    /**
     * Appends item to end of linked list, if linked list is not full, e.g.,
     * no more values can be added
     * @param newValue - BoxClass object to be appended to linked list
     */
    public void appendItem(BoxClass newValue)
    {
        BoxClass workingRef = headRef;
        // check base case, header is null.
        if (headRef == null){
            headRef = newValue;
        }
        else{
            // loop until we find the end of the list
            while ((workingRef.nextRef != null)) {
                workingRef = workingRef.nextRef;
            }
            // set the new node to the newValue
            workingRef.nextRef = newValue;
            workingRef = workingRef.nextRef;
            workingRef.nextRef = null;
        }

    }

    /**
     * Clears linked list of all valid values by
     * setting linked list size to zero,
     * values remain in linked list but are not accessible
     */
    public void clear()
    {
        headRef = null;
    }

    /**
     * Simple linked list dump for testing purposes
     */
    public void dump()
    {
        int index;

        for(index = 0; index<getCurrentSize();index++)
        {
            System.out.println(index+ "box"+index+","+"width: "+headRef.getWidth()
                    +","+"height: "+headRef.getHeight()+","+ " used state: "+headRef.isUsed());
            headRef = headRef.nextRef;
        }
    }

    /**
     * Description: Gets current size of linked list
     * Note: size of linked list indicates number of valid or
     * viable values in the linked list
     * @return
     */
    public int getCurrentSize()
    {
        return getCurrentSizeHelper(headRef);
    }

    /**
     * Recursive helper method finds length of linked list
     * @param workingRef - BoxClass reference used for recursion
     * @return integer value with size of linked list
     * at a given point in the recursion
     */
    public int getCurrentSizeHelper(BoxClass workingRef)
    {
        //base case
        if(workingRef == null)
            return 0;
        // Count is this node plus rest of the list
        return getCurrentSizeHelper(workingRef.nextRef)+1;
    }

    /**
     * Description: Inserts item into linked list at specified index
     * Note: Value is inserted at given index which is inserted
     * into the linked list at that point
     * Note: Value can be inserted after the last valid element
     * but not at any index past that point
     * @param insertIndex - index of element into which value is to be inserted
     * @param newValue - value to be inserted into linked list
     * @return boolean success if inserted, or failure if linked list was full
     */
    public boolean insertItemAt(int insertIndex,
                                BoxClass newValue)
    {
        int index = getCurrentSize();

        BoxClass temp;

        if(insertIndex >=0 && insertIndex < index)
        {
            temp = accessItemAt(insertIndex);

            if(temp != null)
            {
                headRef = temp;

                headRef = newValue;

                return true;
            }
        }
        return false;
    }

    /**
     * Tests for size of linked list equal to zero, no valid values
     * stored in linked list
     * @return boolean result of test for empty
     */
    public boolean isEmpty()
    {
        return headRef == null;
    }

    /**
     * Tests for value found in object linked list; returns true
     * if value within linked list, false otherwise
     * @param testVal - value to be tested
     * @return boolean true if value is found in linked list, false otherwise
     */
    public boolean isInlinkedList(BoxClass testVal)
    {
        int index = 0;

        for(index = 0; index < getCurrentSize();index++)
        {
            if(headRef == testVal)
                return true;
            headRef = headRef.nextRef;
        }
        return false;
    }

    /**
     * Description: Removes item from linked list at specified index
     * if index within linked list size bounds
     * @param removeIndex - index of element value to be removed
     * @return removed value if successful, null if not
     */
    public BoxClass removeItemAt(int removeIndex)
    {
        BoxClass temp = accessItemAt(removeIndex);

        int index = getCurrentSize();

        if(removeIndex <0 || removeIndex>=index)
            return null;

        if(temp != null)
        {
            headRef = headRef.nextRef.nextRef;

            return temp;
        }
        else
            return null;
    }

}
