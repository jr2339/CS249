package p8_package;

import static p8_package.TwoThreeNodeClass.LEFT_CHILD_SELECT;
import static p8_package.TwoThreeNodeClass.RIGHT_CHILD_SELECT;

public class TwoThreeTreeClass {



    /**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;

    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEMS = 2;

    /**
     * constant used for identifying three data items stored
     */
    private final int THREE_DATA_ITEMS = 3;

    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;

    /**
     * root of tree
     */
    private TwoThreeNodeClass root;

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
     * @param copied - TwoThreeTreeClass object to be duplicated;
     * data is copied, but new nodes and references must be created
     */
    public TwoThreeTreeClass(TwoThreeTreeClass copied)
    {
        root = copyConstructorHelper(copied.root);
    }

    /**
     * Implements tree duplication effort with recursive method; copies data
     * into newly created nodes and creates all new references to child nodes
     *
     * @param workingRef TwoThreeNodeClass reference that is updated to lower
     *                   level children with each recursive call
     * @return TwoThreeNodeClass reference to next higher level node; last
     * return is to root node of THIS object
     */
    private TwoThreeNodeClass copyConstructorHelper( TwoThreeNodeClass workingRef )
    {
        TwoThreeNodeClass copiedNode;

        if( workingRef == null )
        {
            return null;
        }
        else
        {
            copiedNode = new TwoThreeNodeClass( workingRef );

            copiedNode.leftChildRef =
                    copyConstructorHelper( workingRef.leftChildRef );
            if( copiedNode.leftChildRef != null )
            {
                copiedNode.leftChildRef.parentRef = copiedNode;
            }

            copiedNode.rightChildRef =
                    copyConstructorHelper( workingRef.rightChildRef );
            if( copiedNode.rightChildRef != null )
            {
                copiedNode.rightChildRef.parentRef = copiedNode;
            }

            copiedNode.centerChildRef =
                    copyConstructorHelper( workingRef.centerChildRef );
            if( copiedNode.centerChildRef != null )
            {
                copiedNode.centerChildRef.parentRef = copiedNode;
            }

            return copiedNode;
        }
    }

    /**
     * Method is called when addItemHelper arrives
     * at the bottom of the 2-3 search tree (i.e., all node's children are null);
     * Assumes one- or two- value nodes and adds one more
     * to the appropriate one resulting in a two- or three- value node
     * @param localRef - TwoThreeNodeClass reference has original node data
     * and contains added value when method completes;
     * method does not manage any node links
     * @param itemVal - integer value to be added to 2-3 node
     */
    private void addAndOrganizeData(TwoThreeNodeClass localRef,
                                    int itemVal)
    {
        if(localRef.numItems == ONE_DATA_ITEM)
        {
            if(itemVal > localRef.centerData)
            {
                localRef.leftData = localRef.centerData;

                localRef.rightData = itemVal;

                localRef.numItems = TWO_DATA_ITEMS;
            }
            else
            {
                localRef.rightData = localRef.centerData;

                localRef.leftData = itemVal;

                localRef.numItems = TWO_DATA_ITEMS;
            }
        }
        else
        {
            if(localRef.rightData < itemVal )
            {
                localRef.centerData = localRef.rightData;

                localRef.rightData = itemVal;

                localRef.numItems = THREE_DATA_ITEMS;
            }
            else if(localRef.leftData > itemVal)
            {
                localRef.centerData = localRef.leftData;

                localRef.leftData = itemVal;

                localRef.numItems = THREE_DATA_ITEMS;
            }
        }
    }

    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     *
     * @param itemVal integer value to be added to the tree
     */
    public void addItem( int itemVal )
    {
        addItemHelper( null, root, itemVal );
    }

    /**
     * Helper method searches from top of tree to bottom using divide and
     * conquer strategy to find correct location (node) for new added value;
     * once location is found, item is added to node using addAndOrganizeData
     * and then fixUpInsert is called in case the updated node has become a
     * three-value node
     *
     * @param parRef   TwoThreeNodeClass reference to the parent of the current
     *                 reference at a given point in the recursion process
     * @param localRef TwoThreeNodeClass reference to the current item at the
     *                 same given point in the recursion process
     * @param itemVal  integer value to be added to the tree
     */
    private void addItemHelper( TwoThreeNodeClass parRef
            , TwoThreeNodeClass localRef
            , int itemVal )
    {
        // recursion doesn't start if at root
        if( localRef == null )
        {
            root = new TwoThreeNodeClass( itemVal );
        }
        // breaks recursion when localRef children are null
        else if( localRef.leftChildRef == null || localRef.rightChildRef == null )
        {
            addAndOrganizeData( localRef, itemVal );
        }
        else if( localRef.numItems == ONE_DATA_ITEM )
        {
            if( itemVal < localRef.centerData )
            {
                addItemHelper( null, localRef.leftChildRef, itemVal );
            }
            else
            {
                addItemHelper( null, localRef.rightChildRef, itemVal );
            }
        }
        else if( localRef.numItems == TWO_DATA_ITEMS )
        {
            if( itemVal < localRef.leftData )
            {
                addItemHelper( null, localRef.leftChildRef, itemVal );
            }
            else if( itemVal > localRef.rightData )
            {
                addItemHelper( null, localRef.rightChildRef, itemVal );
            }
            else
            {
                addItemHelper( null, localRef.centerChildRef, itemVal );
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
     * Method used to fix tree any time a three-value node has been added to
     * the bottom of the tree; it is always called but decides to act only if
     * it finds a three-value node
     * <p>
     * Resolves current three-value node which
     * may add a value to the node above; if the node above becomes a three-
     * value node, then this is resolved with the next recursive call
     * <p>
     * Recursively climbs from bottom to top of tree resolving any three-value
     * nodes found
     *
     * @param localRef TwoThreeNodeClass reference initially given the currently
     *                 updated node, then is given parent node recursively each
     *                 time a three-value node was resolved
     */
    private void fixUpInsert( TwoThreeNodeClass localRef )
    {
        TwoThreeNodeClass parentRef;
        // short circuit keeps java from NullPointerException
        // recursion stops after root
        if( localRef != null && localRef.numItems == THREE_DATA_ITEMS )
        {
            parentRef = localRef.parentRef;

            if( parentRef == null )
            {
                parentRef = new TwoThreeNodeClass( localRef.centerData );
                parentRef.leftChildRef =
                        new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef);
                parentRef.rightChildRef =
                        new TwoThreeNodeClass( RIGHT_CHILD_SELECT, localRef);
                root = parentRef;
            }
            else if( parentRef.numItems == ONE_DATA_ITEM )
            {
                if( parentRef.leftChildRef == localRef ) // is left
                {
                    parentRef.leftData = localRef.centerData;
                    parentRef.leftChildRef =
                            new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef);
                    parentRef.centerChildRef =
                            new TwoThreeNodeClass( RIGHT_CHILD_SELECT, localRef);
                }
                else // is right
                {
                    parentRef.rightData = localRef.centerData;
                    parentRef.rightChildRef =
                            new TwoThreeNodeClass( RIGHT_CHILD_SELECT,
                                    localRef);
                    parentRef.centerChildRef =
                            new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef );
                }
                parentRef.centerData = 0;
                parentRef.numItems++;
            }
            else // is two data node
            {
                if( parentRef.leftChildRef == localRef ) // is left
                {
                    parentRef.centerData = parentRef.leftData;
                    parentRef.leftData = localRef.centerData;
                    parentRef.leftChildRef =
                            new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef);
                    parentRef.auxLeftRef =
                            new TwoThreeNodeClass( RIGHT_CHILD_SELECT, localRef);
                    parentRef.auxRightRef = parentRef.centerChildRef;
                }
                else if( parentRef.centerChildRef == localRef ) // is center
                {
                    parentRef.centerData = localRef.centerData;
                    parentRef.auxLeftRef =
                            new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef);
                    parentRef.auxRightRef =
                            new TwoThreeNodeClass( RIGHT_CHILD_SELECT, localRef);
                }
                else // is right
                {
                    parentRef.centerData = parentRef.rightData;
                    parentRef.leftData = localRef.centerData;
                    parentRef.auxRightRef =
                            new TwoThreeNodeClass( LEFT_CHILD_SELECT, localRef);
                    parentRef.rightChildRef =
                            new TwoThreeNodeClass( RIGHT_CHILD_SELECT, localRef);
                    parentRef.auxLeftRef = parentRef.centerChildRef;
                }

                parentRef.centerChildRef = null;
                parentRef.numItems++;
            }

            // goes higher in tree
            fixUpInsert( localRef.parentRef );
        }

    }

    /**
     * Tests center value if single node, tests left and right values if two-value node;
     * returns true if specified data is found in any given node
     * Note: Method does not use any branching operations such as if/else/etc.
     * @param localRef - TwoThreeNodeClass reference to node with one or two data items in it
     * @param searchVal - integer value to be found in given node
     * @return boolean result of test
     */


    private boolean foundInNode( TwoThreeNodeClass localRef, int searchVal )
    {
        return localRef.leftData == searchVal
                || localRef.centerData == searchVal
                || localRef.rightData == searchVal;
    }

    /**
     * Public method called by user to display data in order
     * @return String result to be displayed and/or analyzed
     */
    public String inOrderTraversal()
    {
        inOrderTraversalHelper(root);

        return outputString;
    }

    /**
     * Helper method conducts in order traversal with 2-3 tree
     * Shows location of each value in a node: "C" at center of single node
     * "L" or "R" at left or right of two-value node
     * @param localRef - TwoThreeNodeClass reference to current location
     * at any given point in the recursion process
     */
    private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
    {
        if( localRef != null )
        {
            if( localRef.numItems == ONE_DATA_ITEM )
            {

                inOrderTraversalHelper(localRef.leftChildRef);

                outputString += "C" + localRef.centerData + " ";

                inOrderTraversalHelper(localRef.rightChildRef);
            }
            else
            {
                inOrderTraversalHelper(localRef.leftChildRef);

                outputString += "L" + localRef.leftData + " ";

                inOrderTraversalHelper(localRef.centerChildRef);

                outputString += "R" + localRef.rightData + " ";

                inOrderTraversalHelper(localRef.rightChildRef);
            }
        }
    }
    /**
     * Search method used by programmer to find specified item in 2-3 tree
     *
     * @param searchVal integer value to be found
     * @return boolean result of search effort
     */
    public boolean search( int searchVal )
    {
        return searchHelper( root, searchVal );
    }

    /**
     * Search helper method that traverses through tree in a recursive divide
     * and conquer search fashion to find given integer in 2-3 tree
     *
     * @param localRef  TwoThreeNodeClass reference to given node at any point
     *                  during the recursive process
     * @param searchVal integer value to be found in tree
     * @return boolean result of search effort
     */
    private boolean searchHelper( final TwoThreeNodeClass localRef
            , int searchVal )
    {
        // java short circuits and will not keep searching if
        // localRef is null or
        return ( localRef != null )
                && ( foundInNode( localRef, searchVal )
                || searchHelper( localRef.leftChildRef, searchVal )
                || searchHelper( localRef.centerChildRef, searchVal )
                || searchHelper( localRef.rightChildRef, searchVal ) );
    }

}
