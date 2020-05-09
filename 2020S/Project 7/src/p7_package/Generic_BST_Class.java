package p7_package;

public class Generic_BST_Class< GenericData extends Comparable< GenericData > >
{
	private class BST_Node
    {
        BST_Node leftChildRef;
        private GenericData nodeData;
        BST_Node rightChildRef;

        public BST_Node(GenericData inData)
        {
            leftChildRef = null;
            rightChildRef = null;
            nodeData = inData;
        }
    }
	
	/**
	 * root of binary search tree
	 */
    private BST_Node BST_Root;
    
    /**
     * item removed from binary search tree
     */
    private GenericData removed;
    
    public Generic_BST_Class()
    {
    	BST_Root = null;
    }
    
    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = null;
    }
    
    /**
     * display inOrder traversal action
     */
    void displayInOrder()
    {
    	displayInOrderHelper(BST_Root);
    }

    /**
     * Provides in order traversal action
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayInOrderHelper(BST_Node localRoot)
    {
        System.out.println("InOrder Display: ");

        if(localRoot.leftChildRef != null)
        {
        	displayInOrderHelper(localRoot.leftChildRef);
        }
        if(localRoot.rightChildRef != null)
        {
        	displayInOrderHelper(localRoot.rightChildRef);
        }
        System.out.println(localRoot.nodeData.toString());
    }
    
    /**
     * Provides postOrder traversal action
     */
    void displayPostOrder()
    {
    	displayPostOrderHelper(BST_Root);
    }

    /**
     * Provides postorder traversal action
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayPostOrderHelper(BST_Node localRoot)
    {
        System.out.println("PostOrder Display: ");
        System.out.println(localRoot.nodeData.toString());

        if(localRoot.rightChildRef != null)
        {
            displayInOrderHelper(localRoot.rightChildRef);
        }
        if(localRoot.leftChildRef != null)
        {
        	displayInOrderHelper(localRoot.leftChildRef);
        }
    }
    
    /**
     * Provides preOrder traversal action
     */
    void displayPreOrder()
    {
    	displayPreOrderHelper(BST_Root);
    }

    /**
     * Provides pre order traversal action
     * @param localRoot BST_Node tree root reference at the current recursion level
     */
    private void displayPreOrderHelper(BST_Node localRoot)
    {
        System.out.println("PreOrder Display: ");
        
        if(localRoot.leftChildRef != null)
        {
        	displayInOrderHelper(localRoot.leftChildRef);
        }
        if(localRoot.rightChildRef != null)
        {
        	displayInOrderHelper(localRoot.rightChildRef);
        }
        System.out.println(localRoot.nodeData.toString());
    }
    
    /**
     * Insert method for BST
     * Note: uses insert helper method
     * @param inData GenericData data to be added to BST
     */
    public void insert(GenericData inData)
    {
        if(isEmpty())
        {
            BST_Root = new BST_Node(inData);
        }
        else
        {
            insertHelper(BST_Root, inData);
        }
    }

    /**
     * Insert helper method for BST insert action
     * @param localRoot BST_Node tree root reference at the current recursion level
     * @param inData GenericData item to be added to BST
     */
    public void insertHelper(BST_Node localRoot, GenericData inData)
    {
        if(localRoot == null)
        {
            localRoot = new BST_Node(inData);
        }
        else if(localRoot.nodeData.compareTo(inData) < 0)
        {
            if(localRoot.leftChildRef == null)
            {
                localRoot.leftChildRef = new BST_Node(inData);
            }
            else
            {
                insertHelper(localRoot.leftChildRef, inData);
            }
        }
        else if(localRoot.nodeData.compareTo(inData) > 0)
        {
            if(localRoot.rightChildRef == null)
            {
                localRoot.rightChildRef = new BST_Node(inData);
            }
            else
            {
                insertHelper(localRoot.rightChildRef, inData);
            }
        }
    }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return BST_Root == null;
    }
    
    /**
     * Searches tree from given node to maximum value node below it,
     * stores data value found, and then unlinks the node
     * @param maxParent BST_Node reference to current node
     * @param maxLoc BST_Node reference to child node to be tested
     * @return BST_Node reference containing removed node
     */
    private BST_Node removeFromMax(BST_Node maxParent, BST_Node maxLoc)
    {
    	{
            BST_Node tempMax;
            
            if(maxLoc.rightChildRef != null)
            {
                return removeFromMax(maxLoc, maxLoc.rightChildRef);
            }
            tempMax = maxLoc;
            maxParent.rightChildRef = maxLoc.leftChildRef;
            return tempMax;
        }
    }
    
    /**
     * Removes data node from tree using given key
     * Note: uses remove helper method
     * @param inData GenericData that includes the necessary key
     * @return GenericData result of remove action
     */
    public GenericData removeItem(GenericData inData)
    {
    	BST_Root = removeItemHelper( BST_Root, inData );
    	
        return removed;
    }

    /**
     * Remove helper for BST remove action
     * Note: uses removeFromMax method
     * @param localRoot BST_Node tree root reference at the current recursion level
     * @param outData   GenericData item that includes the necessary key
     * @return BST_Node reference result of remove helper action
     */
    private BST_Node removeItemHelper(BST_Node localRoot, GenericData outData)
    {
        if(localRoot.rightChildRef == null)
        {
        	removed = localRoot.nodeData;
            return localRoot.leftChildRef;
        }
        if(localRoot.leftChildRef == null)
        {
        	removed = localRoot.nodeData;
            return localRoot.rightChildRef;
        }
        if(localRoot.leftChildRef.rightChildRef != null)
        {
            BST_Node temp = removeFromMax(localRoot, localRoot.leftChildRef);
            localRoot.nodeData = temp.nodeData;
        }
        else
        {
            localRoot = localRoot.leftChildRef;
            localRoot.leftChildRef = localRoot.leftChildRef.leftChildRef;
        }
        return localRoot;
    }
    
    /**
     * Searches for data in BST given GenericData with necessary key
     * @param searchData GenericData item containing key
     * @return GenericData reference to found data
     */
    public GenericData search(GenericData searchData)
    {
    	return searchHelper(BST_Root, searchData);
    }

    /**
     * Helper method for BST search action
     * @param localRoot  BST_Node tree root reference at the current recursion level
     * @param searchData GenericData item containing key
     * @return GenericData item found
     */
    private GenericData searchHelper(BST_Node localRoot, GenericData searchData)
    {
    	if(localRoot.nodeData.compareTo(searchData) < 0)
        {
            return searchHelper(localRoot.leftChildRef, searchData);
        }
        else if(localRoot.nodeData.compareTo(searchData) > 0)
        {
            return searchHelper(localRoot.rightChildRef, searchData);
        }
        return localRoot.nodeData;
    }
}