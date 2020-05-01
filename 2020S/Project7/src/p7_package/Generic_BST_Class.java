package p7_package;

public class Generic_BST_Class<GenericData extends java.lang.Comparable<GenericData>>{
    public class BST_Node{
        /**
         * Member value left child reference
         */
        Generic_BST_Class.BST_Node leftChildRef;

        /**
         * Member value right child reference
         */
        Generic_BST_Class.BST_Node rightChildRef;

        /**
         * Member value GenericData node
         */
        private GenericData  nodeData;

        /**
         * Initialization constructor for data
         * @param inData - GenericData quantity
         */
        public BST_Node(GenericData inData)
        {
            leftChildRef = null;

            rightChildRef = null;

            nodeData = inData;
        }

        /**
         * Copy constructor for data
         * Note: Not used in class but available to user
         * @param copied - GenericData quantity
         */
        public BST_Node(Generic_BST_Class.BST_Node copied)
        {
            leftChildRef = copied.leftChildRef;

            rightChildRef = copied.rightChildRef;

            nodeData = (GenericData) copied.nodeData;
        }
    }

    /**
     * Root of BST
     */
    private Generic_BST_Class.BST_Node BST_Root;

    /**
     * Returned value reference for remove operation
     */
    private GenericData removed;

    /**
     * Default class constructor, initializes BST
     */
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
     * Provides inOrder traversal action
     * Note: Calls displayInOrderHelper
     */
    public void displayInOrder()
    {
        if (BST_Root != null){
            displayInOrderHelper(BST_Root);
        }
    }

    /**
     * Provides inOrder traversal action using recursion
     * @param localRoot - BST_Node tree root reference
     * at the current recursion level
     */
    private void displayInOrderHelper(Generic_BST_Class.BST_Node localRoot)
    {
        if (localRoot.leftChildRef != null)
        {
            displayInOrderHelper(localRoot.leftChildRef);
        }

        if (localRoot.rightChildRef != null)
        {
            displayInOrderHelper(localRoot.rightChildRef);
        }

        System.out.println(localRoot.nodeData);
    }

    /**
     * Provides postOrder traversal action
     * Note: Calls displayPostOrderHelper
     */
    public void displayPostOrder()
    {
        if (BST_Root != null){
            displayPostOrderHelper(BST_Root);
        }

    }

    /**
     * Provides postOrder traversal action using recursion
     * @param localRoot - BST_Node tree root reference
     * at the current recursion level
     */
    private void displayPostOrderHelper(Generic_BST_Class.BST_Node localRoot)
    {
        System.out.println(localRoot.nodeData);

        if (localRoot.rightChildRef != null)
        {
            displayInOrderHelper(localRoot.rightChildRef);
        }

        if (localRoot.leftChildRef != null)
        {
            displayInOrderHelper(localRoot.leftChildRef);
        }
    }

    /**
     * Provides preOrder traversal action
     * Note: Calls displayPreOrderHelper
     */
    public void displayPreOrder()
    {
        if (BST_Root != null){
            displayPreOrderHelper(BST_Root);
        }

    }

    /**
     * Provides preOrder traversal action using recursion
     * @param localRoot - BST_Node tree root reference
     * at the current recursion level
     */
    private void displayPreOrderHelper(Generic_BST_Class.BST_Node localRoot)
    {
        if (localRoot.leftChildRef != null)
        {
            displayInOrderHelper(localRoot.leftChildRef);
        }

        if (localRoot.rightChildRef != null)
        {
            displayInOrderHelper(localRoot.rightChildRef);
        }

        System.out.println(localRoot.nodeData);
    }

    /**
     * Insert method for BST
     * Note: uses insert helper method which returns root reference
     * @param inData - GenericData data to be added to BST
     */
    public void insert(GenericData inData)
    {
        insertHelper(BST_Root, inData);
    }

    /**
     * Insert helper method for BST insert action
     * Note: Recursive method returns updated local root to maintain tree linkage
     * Note: Must not allow duplicate keys (names)
     * @param localRoot - BST_Node tree root reference
     * at the current recursion level
     * @param inData - GenericData item to be added to BST
     * @return BST_Node reference used to maintain tree linkage
     */
    private Generic_BST_Class.BST_Node insertHelper(Generic_BST_Class.BST_Node localRoot,
                                                    GenericData inData)
    {
        if (localRoot == null)
        {
            return BST_Root = new BST_Node(inData);
        }

        //right side
        else if (localRoot.nodeData.compareTo(inData) > 0)
        {
            if (localRoot.rightChildRef != null)
            {
                return insertHelper(localRoot.rightChildRef, inData);
            }
            else
            {
                return localRoot.rightChildRef = new BST_Node(inData);
            }
        }

        //left side
        else if (localRoot.nodeData.compareTo(inData) < 0)
        {
            if (localRoot.leftChildRef != null)
            {
                return insertHelper(localRoot.leftChildRef, inData);
            }
            else
            {
                return localRoot.leftChildRef = new BST_Node(inData);
            }
        }
        else
        {
            return null;
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
     * @param maxParent - BST_Node reference to current node
     * @param maxLoc - BST_Node reference to child node to be tested
     * @return BST_Node reference containing removed node
     */
    private Generic_BST_Class.BST_Node removeFromMax(Generic_BST_Class.BST_Node maxParent,
                                                     Generic_BST_Class.BST_Node maxLoc) {
        //as long as the right child doesnt equal null
        if( maxLoc.rightChildRef != null )
        {
            //you keep going down the right children
            return removeFromMax( maxLoc, maxLoc.rightChildRef );
        }
        BST_Node myTemp = maxLoc;
        // once its null it sets that last right child equal to the parents
        // right child
        maxParent.rightChildRef = maxLoc.leftChildRef;

        return myTemp;
    }

    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * <p>
     * Note:uses search initially to get value, if it is in tree; if value
     * found, remove helper method is called, otherwise returns false
     *
     * @param inData GenericData that includes the necessary key
     *
     * @return GenericData result of remove action
     */
    public GenericData removeItem( GenericData inData )
    {
        GenericData tempVar = search( inData );
        //checks to see if the key is in the tree
        if( tempVar != null )
        {
            BST_Root = removeItemHelper( BST_Root, inData);

        }
        return tempVar;
    }

    /**
     * Remove helper for BST remove action
     * <p>
     * Note: Recursive method returns updated local root to
     * maintain tree linkage
     * <p>
     * Note:uses removeFromMax method
     *
     * @param localRoot BST_Node tree root reference at
     * the current recursion level
     *
     * @param outData GenericData item that includes the necessary key
     *
     * @return BST_Node reference result of remove helper action
     */
    private BST_Node removeItemHelper( BST_Node localRoot,
                                       GenericData outData )
    {
        if( localRoot == null )
        {
            return null;
        }
        //checks if test item is less than local
        if( localRoot.nodeData.compareTo( outData )>0 )
        {
            localRoot.leftChildRef =
                    removeItemHelper( localRoot.leftChildRef, outData );
        }
        //checks if test item is greater than local
        else if( localRoot.nodeData.compareTo( outData )<0 )
        {
            localRoot.rightChildRef =
                    removeItemHelper( localRoot.rightChildRef, outData );
        }
        //checks for no left child
        else if( localRoot.leftChildRef == null )
        {
            localRoot = localRoot.rightChildRef;

        }
        //checks for no right child
        else if( localRoot.rightChildRef == null )
        {
            localRoot = localRoot.leftChildRef;

        }
        else
        {
            //checks for left child with no right child
            if(localRoot.leftChildRef.rightChildRef == null )
            {
                //assigns the data from left child to the removed node
                localRoot.leftChildRef.nodeData = outData;
                //assign my left child to leftchild's leftchild
                localRoot.leftChildRef =
                        localRoot.leftChildRef.leftChildRef;
            }
            else
            {
                //calls method remove from max
                BST_Node newTemp = removeFromMax(localRoot,
                        localRoot.leftChildRef);

                localRoot.nodeData = newTemp.nodeData;
            }

        }

        return localRoot;
    }

    /**
     * Searches for data in BST given GenericData with necessary key
     *
     * @param searchData GenericData item containing key
     *
     * @return GenericData reference to found data
     */
    public GenericData search( GenericData searchData )
    {
        return (GenericData) searchHelper( BST_Root, searchData );
    }

    /**
     * Helper method for BST search action
     *
     * @param localRoot BST_Node tree root reference at the current
     * recursion level
     *
     * @param searchData GenericData item containing key
     *
     * @return GenericData item found
     */
    private GenericData searchHelper( BST_Node localRoot,
                                      GenericData searchData )
    {
        // checks if the root is equal to null
        if ( localRoot == null )
        {
            return null;
        }
        //checks if the two are equal to each other
        if( localRoot.nodeData.compareTo( searchData ) == 0 )
        {
            return localRoot.nodeData;

        }
        //checks if the local is less than the one passed in
        if( localRoot.nodeData.compareTo( searchData )< 0 )
        {
            return (GenericData) searchHelper( localRoot.rightChildRef, searchData );

        }
        else
        {
            return (GenericData) searchHelper(localRoot.leftChildRef, searchData);
        }


    }

}
