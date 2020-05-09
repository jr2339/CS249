package p8_package;

public class TwoThreeTreeClass 
{
	/**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;
    
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private TwoThreeNodeClass root;

    /**
     * Root of tree
     */
    private String outputString;

    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEMS = 2;

    /**
     * constant used for identifying three data items stored
     */
    private final int THREE_DATA_ITEMS = 3;
    
    /**
     * Default 2-3 tree constructor
     */
    public TwoThreeTreeClass()
    {
        root = null;
        outputString = "";
    }

    /**
     * Copy 2-3 tree constructor
     * @param copied TwoThreeTreeClass object to be duplicated; data is copied, but new nodes and references must be created
     */
    public TwoThreeTreeClass(TwoThreeTreeClass copied)
    {
        this.root = copyConstructorHelper(copied.root);
    }
    
    /**
     * Method is called when addItemHelper arrives at the bottom of the 2-3 search tree (i.e., all node's children are null);
     * Assumes one- or two- value nodes and adds one more to the appropriate one resulting in a two- or three- value node
     * @param localRef TwoThreeNodeClass reference has original node data and contains added value when method completes; method does
     * not manage any node links
     * @param itemVal  integer value to be added to 2-3 node
     */
    private void addAndOrganizeData(TwoThreeNodeClass localRef, int itemVal)
    {
        if(localRef.numItems == ONE_DATA_ITEM)
        {
            if(itemVal < localRef.centerData)
            {
                localRef.rightData = localRef.centerData;
                localRef.leftData = itemVal;
            }
            else
            {
                localRef.leftData = localRef.centerData;
                localRef.rightData = itemVal;
            }
            localRef.centerData = 0;
        }
        else 
        {
            if(itemVal < localRef.leftData)
            {
                localRef.centerData = localRef.leftData;
                localRef.leftData = itemVal;
            }
            else if(itemVal > localRef.rightData)
            {
                localRef.centerData = localRef.rightData;
                localRef.rightData = itemVal;
            }
            else
            {
                localRef.centerData = itemVal;
            }
        }

        localRef.numItems++;
        fixUpInsert(localRef);
    }
    
    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     * @param itemVal integer value to be added to the tree
     */
    public void addItem(int itemVal)
    {
        addItemHelper(root, itemVal);
    }

    /**
     * Helper method searches from top of tree to bottom using divide and 
     * conquer strategy to find correct location (node) for new added value;
     * once location is found, item is added to node using addAndOrganizeData
     * and then fixUpInsert is called in case the updated node has become a three-value node
     * @param localRef TwoThreeNodeClass reference to the current item at the same given point in the recursion process
     * @param itemVal  integer value to be added to the tree
     */
    private void addItemHelper(TwoThreeNodeClass localRef, int itemVal)
    {
        if(localRef == null)
        {
            root = new TwoThreeNodeClass(itemVal);
        }
        else if(localRef.leftChildRef == null || localRef.rightChildRef == null)
        {
            addAndOrganizeData(localRef, itemVal);
        }
        else if(localRef.numItems == ONE_DATA_ITEM)
        {
            if(itemVal < localRef.centerData)
            {
                addItemHelper(localRef.leftChildRef, itemVal);
            }
            else
            {
                addItemHelper(localRef.rightChildRef, itemVal);
            }
        }
        else if(localRef.numItems == TWO_DATA_ITEMS)
        {
            if(itemVal < localRef.leftData)
            {
                addItemHelper(localRef.leftChildRef, itemVal);
            }
            else if( itemVal > localRef.rightData )
            {
                addItemHelper(localRef.rightChildRef, itemVal);
            }
            else
            {
                addItemHelper(localRef.centerChildRef, itemVal);
            }
        }
    }
    
    /**
     * Method clears tree so that new items can be added again
     */
    public void clear()
    {
        root = null;
    }
    
    /**
     * Implements tree duplication effort with recursive method; copies data
     * into newly created nodes and creates all new references to child nodes
     * @param workingRef TwoThreeNodeClass reference that is updated to lower level children with each recursive call
     * @return TwoThreeNodeClass reference to next higher level node; last
     * return is to root node of THIS object
     */
    private TwoThreeNodeClass copyConstructorHelper(TwoThreeNodeClass workingRef)
    {
        TwoThreeNodeClass nodeCopy;

        if(workingRef == null)
        {
            return null;
        }
        else
        {
        	nodeCopy = new TwoThreeNodeClass(workingRef);
        	nodeCopy.leftChildRef = copyConstructorHelper(workingRef.leftChildRef);
        	
            if(nodeCopy.leftChildRef != null)
            {
            	nodeCopy.leftChildRef.parentRef = nodeCopy;
            }

            nodeCopy.rightChildRef = copyConstructorHelper(workingRef.rightChildRef);
            
            if(nodeCopy.rightChildRef != null)
            {
            	nodeCopy.rightChildRef.parentRef = nodeCopy;
            }

            nodeCopy.centerChildRef = copyConstructorHelper(workingRef.centerChildRef);
            if(nodeCopy.centerChildRef != null)
            {
            	nodeCopy.centerChildRef.parentRef = nodeCopy;
            }

            return nodeCopy;
        }
    }
    
    /**
     * Method used to fix tree any time a three-value node has been added to
     * the bottom of the tree; it is always called but decides to act only if it finds a three-value node
     * Resolves current three-value node which may add a value to the node above; if the node above becomes a three-
     * value node, then this is resolved with the next recursive call
     * Recursively climbs from bottom to top of tree resolving any three-value nodes found
     * @param localRef TwoThreeNodeClass reference initially given the currently updated node, then is given parent node recursively each
     * time a three-value node was resolved
     */
    private void fixUpInsert(TwoThreeNodeClass localRef)
    { 
     TwoThreeNodeClass newNode = null;
   
     if(localRef.numItems == THREE_DATA_ITEMS)
        {
         if(localRef.parentRef == null)  
            {
             newNode = new TwoThreeNodeClass(localRef.centerData);
             localRef.parentRef = newNode;
             root = newNode;
             newNode.leftChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
             newNode.rightChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
           }
         else if( localRef.parentRef.numItems == ONE_DATA_ITEM)
            {
             localRef.parentRef.numItems++;
            
             if( localRef == localRef.parentRef.leftChildRef )
                {
                 localRef.parentRef.rightData = localRef.parentRef.centerData;
                 localRef.parentRef.leftData = localRef.centerData;              
                 localRef.parentRef.leftChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                 localRef.parentRef.centerChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                }
             else
                {
                 localRef.parentRef.leftData = localRef.parentRef.centerData;
                 localRef.parentRef.rightData = localRef.centerData;
                 localRef.parentRef.centerChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                 localRef.parentRef.rightChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                 }
            }
         else
            {
             localRef.parentRef.numItems++;
            
             if(localRef == localRef.parentRef.leftChildRef)
                {
                 localRef.parentRef.centerData = localRef.parentRef.leftData;
                 localRef.parentRef.leftData = localRef.centerData;
                 localRef.parentRef.auxRightRef = localRef.parentRef.centerChildRef;
                 localRef.parentRef.leftChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                 localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                }
             else if( localRef == localRef.parentRef.rightChildRef)
                {
                 localRef.parentRef.centerData = localRef.parentRef.rightData;
                 localRef.parentRef.rightData = localRef.centerData;
                 localRef.parentRef.auxLeftRef = localRef.parentRef.centerChildRef;
                 localRef.parentRef.auxRightRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                 localRef.parentRef.rightChildRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                }
             else
                {
                 localRef.parentRef.centerData = localRef.centerData;
                 localRef.parentRef.auxLeftRef = new TwoThreeNodeClass(TwoThreeNodeClass.LEFT_CHILD_SELECT, localRef);
                 localRef.parentRef.auxRightRef = new TwoThreeNodeClass(TwoThreeNodeClass.RIGHT_CHILD_SELECT, localRef);
                }
            }
         fixUpInsert(localRef.parentRef);
        }
    }
    
    /**
     * Tests center value if single node, tests left and right values if 
     * two-value node; returns true if specified data is found in any given node
     * Note: Method does not use any branching operations such as if/else/etc
     * @param localRef  TwoThreeNodeClass reference to node with one or two data items in it
     * @param searchVal integer value to be found in given node
     * @return boolean result of test
     */
    private boolean foundInNode(TwoThreeNodeClass localRef, int searchVal)
    {
        return localRef.leftData == searchVal || localRef.centerData == searchVal || localRef.rightData == searchVal;
    }
    
    /**
     * Public method called by user to display data in order
     * @return String result to be displayed and/or analyzed
     */
    public String inOrderTraversal()
    {
        outputString = "";
        inOrderTraversalHelper(root);
        return outputString;
    }

    /**
     * Helper method conducts in order traversal with 2-3 tree
     * Shows location of each value in a node: "C" at center of single node "L"
     * or "R" at left or right of two-value node
     * @param localRef TwoThreeNodeClass reference to current location at any given point in the recursion process search
     */
    private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
    {
        if(localRef != null)
        {
            inOrderTraversalHelper(localRef.leftChildRef);
            if(localRef.numItems == ONE_DATA_ITEM)
            {
                outputString += ("C" + localRef.centerData + " ");
            }
            else if(localRef.numItems == TWO_DATA_ITEMS)
            {
                outputString += ("L" + localRef.leftData + " ");
                inOrderTraversalHelper(localRef.centerChildRef);
                outputString += ("R" + localRef.rightData + " ");
            }
            inOrderTraversalHelper(localRef.rightChildRef);
        }
    }
    
    /**
     * Search method used by programmer to find specified item in 2-3 tree
     * @param searchVal integer value to be found
     * @return boolean result of search effort
     */
    public boolean search(int searchVal)
    {
        return searchHelper(root, searchVal);
    }

    /**
     * Search helper method that traverses through tree in a recursive divide and conquer search fashion to find given integer in 2-3 tree
     * @param localRef  TwoThreeNodeClass reference to given node at any point during the recursive process
     * @param searchVal integer value to be found in tree
     * @return boolean result of search effort
     */
    private boolean searchHelper(final TwoThreeNodeClass localRef, int searchVal)
    {
        return (localRef != null) && (foundInNode(localRef, searchVal) || searchHelper(localRef.leftChildRef, searchVal)
        || searchHelper(localRef.centerChildRef, searchVal) || searchHelper(localRef.rightChildRef, searchVal));
    }
}
