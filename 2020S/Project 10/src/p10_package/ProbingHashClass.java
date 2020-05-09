package p10_package;

public class ProbingHashClass 
{
	/**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 11;
    
    /**
     * Constant for returning item not found with search
     */
    public final int ITEM_NOT_FOUND = -1;
    
    /**
     * Constant for setting linear probing
     */
    public static final int LINEAR_PROBING = 101;
    
    /**
     * Flag for setting linear or quadratic probing
     */
    private int probeFlag;
    
    /**
     * Constant for setting quadratic probing
     */
    public static final int QUADRATIC_PROBING = 102;
    
    /**
     * Array for hash table
     */
    private StudentClass[] tableArray;
    
    /**
     * Size of the array table
     */
    private int tableSize;
    
    /**
     * Default constructor
     * Initializes to default table size, number of hash digits, with 
     * probe flag
     * set to linear probing
     */
    public ProbingHashClass()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        probeFlag = LINEAR_PROBING;
        tableArray = new StudentClass[tableSize];
    }
    
    /**
     * Initialization constructor
     * @param inProbeFlag sets linear or quadratic probing
     */
    public ProbingHashClass(int inProbeFlag)
    {
        tableSize = DEFAULT_TABLE_SIZE;
        probeFlag = inProbeFlag;
        tableArray = new StudentClass[tableSize];
    }
    
    /**
     * Initialization constructor
     * @param inTableSize  sets table size but does 
     * not allow it to be less than default capacity
     * @param inProbeFlag  sets linear or quadratic probing
     */
    public ProbingHashClass(int inTableSize, int inProbeFlag)
    {
        tableSize = inTableSize;
        probeFlag = inProbeFlag;
        tableArray = new StudentClass[tableSize];
    }
    
    /**
     * Copy constructor
     * @param copied ProbingHashClass copied object
     */
    public ProbingHashClass(ProbingHashClass copied)
    {
        int index;

        tableSize = copied.tableSize;
        tableArray = new StudentClass[tableSize];
        probeFlag = copied.probeFlag;

        for(index = 0; index < tableSize; index++)
        {
            tableArray[index] = copied.tableArray[index];
        }
    }
    
    /**
     * Adds StudentClass item to hash table
     * note: uses hash index val from generateHash
     * note: shows probed index with data at point of insertion
     * note: probe attemps are limited to the current size
     * @param newItem StudentClass item
     * @return Boolean success of operation
     */
    public boolean addItem(StudentClass newItem)
    {
        int hash = generateHash(newItem) % tableSize;
        int workingIndex = hash; 
        int probe = 0;
        int QUAD_VAL = 2;
        
        StudentClass itemAccessed;

        System.out.print(newItem.toString() + generateHash(newItem));

        while(workingIndex < tableSize)
        {
        	itemAccessed = tableArray[workingIndex];
        	
            if(itemAccessed == null)
            {
                tableArray[workingIndex] = newItem;
                System.out.print("\n" + "indices probed: " + workingIndex +
                "\n");
                return true;
            }
            else if(itemAccessed.compareTo(newItem) == 0)
            {
                tableArray[workingIndex] = newItem;
                return true;
            }
            probe++;

            if(probeFlag == QUADRATIC_PROBING)
            {
                workingIndex = generateHash(newItem) %
                		tableSize + toPower(probe, QUAD_VAL);
            }
            else
            {
                workingIndex += 1;
            }
            workingIndex %= tableSize;
        }
        return false;
    }

    /**
     * Clears hash table by setting all bins to null	
     */
    public void clearHashTable()
    {
        int index;

        for(index = 0; index < tableSize; index++)
        {
            tableArray[index] = null;
        }
    }
    
    /**
     * Returns item found
     * @param searchItem studentclass value to be found; uses findItemIndex
     * @return studentclass item found
     */
    public StudentClass findItem(StudentClass searchItem)
    {
        int index = findItemIndex(searchItem);

        if(index == ITEM_NOT_FOUND)
        {
            return null;
        }
        else
        {
            return tableArray[index];
        }
    }

    /**
     * Searches for item index in hash table
     * Uses linear or quadratic probing as configured
     * probe attemps limited to table size
     * @param searchItem StudentClass value to be found
     * @return integer index location of search item
     */
    private int findItemIndex(StudentClass searchItem)
    {
        int searchedHash = generateHash(searchItem) % tableSize;
        int workingIndex = searchedHash;
        int probe = 0; 
        int QUAD_VAL = 2;
        StudentClass accessedItem;

        while(workingIndex < tableSize)
        {
            accessedItem = tableArray[workingIndex];

            if(searchItem.compareTo(accessedItem) == 0)
            {
                return workingIndex;
            }
            probe++;

            if(probeFlag == QUADRATIC_PROBING)
            {
                workingIndex = searchedHash + toPower(probe, QUAD_VAL);
            }
            else
            {
                workingIndex += 1;
            }
            workingIndex %= tableSize;
        }
        return ITEM_NOT_FOUND;
    }
    
    /**
     * Method converts studentclass item to hash value for use in hash table
     * @param item Studentclass value to be converted to hash value
     * Note: gets data from object via hashcode, then calculates index
     * Note: Uses hashcode from objects
     * @return integer hash value
     */
    public int generateHash(StudentClass item)
    {
    	 return item.hashCode() % tableSize;
    }
    
    /**
     * Removes item from hash table
     * @param toBeRemoved studentclass value used 
     * for requesting data uses findItemIndex
     * @return studentclass item removed, or null if not found
     */
	 public StudentClass removeItem(StudentClass toBeRemoved)
     {
    	 int removedIndex = findItemIndex(toBeRemoved);
    	 StudentClass returnedValue = null;
    	    	 
    	 if(removedIndex < tableSize) 
    	 {
    		 returnedValue = tableArray[removedIndex];
    	 }
    	 return returnedValue;
     }
	 
	 /**
	     * traverses through all array bins, finds min and max number 
	     * of contiguous elements, and number of empty nodes;
	     * also shows table loading
	     * note: Generates string of used and unused bins in 
	     * addition to displaying results on screen
	     * @return String result of hash table analysis
	     */
	     public String showHashTableStatus()
	     {
	    	 int index;
	    	 int Empty = 0;
	    	 int contCount = 0;
	    	 int Min = tableSize;
	    	 int Max = 0;
	    	 String binOut = "";
	    	 
	    	 for(index = 0; index < tableSize; index++) 
	    	 {
	    		 if(tableArray[index] == null) 
	    		 {
	    			 binOut += "N";
	    		 }
	    		 else 
	    		 {
	    			 binOut += "D";
	    		 }
	    	 }
	    	 for(index = 0; index < tableSize; index++) 
	    	 {
	    		 if(tableArray[index] != null) 
	    		 {
	    			 contCount++;
	    			 if(contCount > 0) 
	    			 {
	    				 if(Max < contCount) 
	    				 {
	    					 Max = contCount;
	    				 }
	    				 if(Min > contCount) 
	    				 {
	    					 Min = contCount;
	    				 }
	    			 }
	    		 }
	    		 else 
	    		 {
	    			 Empty++;
	    			 contCount = 0;
	    		 }
	    	 }			 
			 System.out.println("\n");			 
			 System.out.println("Hash Table Status: " + binOut);
			 System.out.println("\n");
			 System.out.println("	Minimum Contiguous Bins: " + Min);			 
			 System.out.println("	Maximum Contiguous Bins: " + Max);			 
			 System.out.println("		Number of empty bins: " + Empty);		 
			 System.out.println("\n");
			 System.out.println("Array Dump:");
			 
			 for(index = 0; index < tableSize; index++) 
			 {
				 if(tableArray[index] == null) 
				 {
					 System.out.println("null");
				 }
				 else 
				 {
					 System.out.println(tableArray[index].toString());
				 }
			 }
			 return null;	 
	     }
	     
	     /**
	      * Local recursive method to calculate exponentiation with integers 
	      * @param base base of exponentiation 
	      * @param exponent exponent of exponentiation
	      * @return result of exponentiation calculation
	      */
	      private int toPower(int base, int exponent)
	      {
	     	 if(exponent > 0)
	          {
	              return toPower(base, exponent - 1) * base;
	          }
	          return 1;
	      } 
	 }
