package p9_package;

public class OpCodeHeapClass 
{
	/**
     * Management data for array
     */
    private int arrayCapacity;
    /**
     * Management data for array
     */
    private int arraySize;
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    /**
     * Array for heap
     */
    private OpCodeClass[] heapArray;
    
    /**
     * Default constructor sets up array management 
     * conditions and default display flag setting
     */
    public OpCodeHeapClass()
    {
        this.arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        this.arraySize = 0;
        displayFlag = false;
        heapArray = new OpCodeClass[this.arrayCapacity];
    }
    /**
     * Copy constructor copies array and array management conditions
     * and default display flag setting
     * @param copied - GenericHeapClass object to be copied
     */
    public OpCodeHeapClass(OpCodeHeapClass copied)
    {
        int index;
        
        this.arrayCapacity = copied.arrayCapacity;
        this.arraySize = copied.arraySize;
        this.displayFlag = copied.displayFlag;
        this.heapArray = new OpCodeClass[this.arrayCapacity];
        
        for(index = 0; index < this.arraySize; index++)
        {
            this.heapArray[index] = copied.heapArray[index];
        }
    }
    
    /**
     * Note: uses bubbleUpArrayHeap to resolve
     * Note: must check for resize before attempting to add an item
     * unbalanced heap after data addition
     * @param newItem - generic data item to be added to heap
     */
    public void addItem(OpCodeClass newItem)
    {
        checkForResize();
        heapArray[arraySize] = newItem;
        bubbleUpArrayHeap(arraySize);
        arraySize++;
    }
    
    /**
     * Recursive operation to reset data in the correct 
     * order for the min heap after new data addition
     * @param currentIndex - index of current item being 
     * assessed, and moved up as needed
     */
    private void bubbleUpArrayHeap(int currentIndex)
    {
    	OpCodeClass OpCode1 = (OpCodeClass)heapArray[currentIndex];
    	OpCodeClass OpCode2 = (OpCodeClass)heapArray[(currentIndex - 1) / 2];
        int parentIndex = (currentIndex - 1) / 2;
        
        if(displayFlag)
        {
            System.out.println("Adding new process: " + heapArray[currentIndex].toString());
            
            if(OpCode1.compareTo(OpCode2) < 0)
            {
                System.out.println(" - Bubble Up: ");
                System.out.println(" - Swapping Parent: " + 
                		heapArray[parentIndex].toString() +
                			"; with child: " +heapArray[currentIndex].toString());
                System.out.println();
                heapArray[currentIndex] = OpCode2;
                heapArray[parentIndex] = OpCode1;
                bubbleUpArrayHeap(parentIndex);
            }
            System.out.println();
        }
            if(OpCode1.compareTo(OpCode2) < 0)
            {
                heapArray[currentIndex] = OpCode2;
                heapArray[parentIndex] = OpCode1;
                bubbleUpArrayHeap(parentIndex);
            }
    }
    
    /**
     * Automatic resize operation used prior to any new data 
     * addition in the heap
     * Tests for full heap array, and resizes 
     * to twice the current capacity as required
     */
    private void checkForResize()
    {
        OpCodeClass[] resizeArr;
        int index;
        
        if(arraySize == arrayCapacity)
        {
            arrayCapacity = arrayCapacity * 2;
            resizeArr = new OpCodeClass[arrayCapacity];
            for(index = 0; index < arraySize; index++)
            {
            	resizeArr[index] = heapArray[index];
            }
            heapArray = resizeArr;
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
     * Removes GenericData item from top of min heap, 
     * thus being the operation with the lowest priority value
     * Note: Uses trickleDownArrayHeap to resolve unbalanced 
     * heap after data removal
     * @return - GenericData item removed
     */
    public OpCodeClass removeItem()
    {
        OpCodeClass removedOpCode = (OpCodeClass) heapArray[0];
        
        heapArray[0] = heapArray[arraySize - 1];
        arraySize--;
        trickleDownArrayHeap(0);
        return removedOpCode;
    }
    
    /**
     * Utility method to set the display flag for displaying 
     * internal operations of the heap bubble and trickle operations
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
        int index;
        
        for(index = 0; index < arraySize; index ++)
        {
            System.out.print(heapArray[index].toString() + " - ");
        }
    }
    
    /**
     * Recursive operation to reset data in the correct order for the min 
     * heap after data removal
     * @param currentIndex - index of current item being 
     * assessed, and moved down as required
     */
    private void trickleDownArrayHeap(int currentIndex)
    {
    	int leftIndex = (currentIndex) * 2 + 1;
    	int rightIndex = (currentIndex) * 2 + 2;
    	OpCodeClass Op1;
    	OpCodeClass Op2;
    	OpCodeClass Op3;
    	OpCodeClass tempOp;
    
    	if( displayFlag )
    	{
        System.out.println( "Removing process: " + 
        		heapArray[ currentIndex ].toString());
        if( rightIndex < arraySize )
        {
            Op1 = (OpCodeClass) heapArray[leftIndex];
            Op2 = (OpCodeClass) heapArray[rightIndex];
            Op3 = (OpCodeClass) heapArray[currentIndex];
            
            if(Op1.compareTo(Op3) < 0 || Op2.compareTo(Op3) < 0)
            {
                System.out.println(" - Trickle down: ");
                if(Op1.compareTo(Op2) < 0)
                {
                    System.out.println(" -Swapping Parent: " + Op3.toString() + 
                    		" with left child: " + Op1.toString());
                    
                    tempOp = Op3;
                    heapArray[currentIndex] = heapArray[leftIndex];
                    heapArray[leftIndex] = tempOp;
                    trickleDownArrayHeap(leftIndex);
                }
                else
                {
                    System.out.println(" -Swapping Parent: " + Op3.toString() + 
                    		" with right child: " + Op2.toString());
                    
                    tempOp = Op3; 
                    heapArray[currentIndex] = heapArray[rightIndex];
                    heapArray[rightIndex] = tempOp;
                    trickleDownArrayHeap(rightIndex);
                }
            }
            System.out.println();
        }
        else if(leftIndex < arraySize)
        {
            Op1 = (OpCodeClass) heapArray[leftIndex];
            Op3 = (OpCodeClass) heapArray[currentIndex];
            
            if( Op1.compareTo(Op3) < 0)
            {
                System.out.println(" -Swapping Parent: " + Op3.toString() + 
                		" with left child: " + Op1.toString());
                
                tempOp = Op3;
                heapArray[currentIndex] = heapArray[leftIndex];
                heapArray[leftIndex] = tempOp;
                trickleDownArrayHeap(leftIndex);
            }
            System.out.println();
        }
    }
    else
    {
        if(rightIndex < arraySize)
        {
            Op1 = (OpCodeClass) heapArray[leftIndex];
            Op2 = (OpCodeClass) heapArray[rightIndex];
            Op3 = (OpCodeClass) heapArray[currentIndex];
            
            if(Op1.compareTo(Op3) < 0 || Op2.compareTo(Op3) < 0)
            {
                if(Op1.compareTo( Op2 ) < 0)
                {
                    tempOp = Op3;
                    heapArray[currentIndex] = heapArray[leftIndex];
                    heapArray[leftIndex] = tempOp;
                    trickleDownArrayHeap(leftIndex);
                }
                else
                {
                    tempOp = Op3; 
                    heapArray[currentIndex] = heapArray[rightIndex];
                    heapArray[rightIndex] = tempOp;
                    trickleDownArrayHeap(rightIndex);
                }
            }
        }
        else if(leftIndex < arraySize)
        {
            Op1 = (OpCodeClass) heapArray[leftIndex];
            Op3 = (OpCodeClass) heapArray[currentIndex];
            
            if(Op1.compareTo(Op3) < 0)
            {
                tempOp = Op3;
                heapArray[currentIndex] = heapArray[leftIndex];
                heapArray[leftIndex] = tempOp;
                trickleDownArrayHeap(leftIndex);
            }
        }
    }
}
}
