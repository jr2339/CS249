package p4_package;

//import java.util.Scanner;

/**
 * manages a container box into which smaller boxes are placed
 * using recursive backtracking
 * 
 * @author Michael Leverington
 *
 */
public class ContainerBoxClass
   {
    /**
     * Uses dash ('-') as empty box location, 
     */
    private static final char DEFAULT_FIELD_CHAR = '-';
    
    /**
     * Uses -1 as constant for reporting no boxes returned
     * from findNexUnusedBoxIndex
     */
    private static final int NO_BOXES_AVAILABLE = -1;
    
    /**
     * Constant integer value for FILL_BOX control key use in fillBoxLocation
     */
    private static final int FILL_BOX = 101;

    /**
     * Constant integer value for CLEAR_BOX control key use in fillBoxLocation
     */
    private static final int CLEAR_BOX = 102;

    /**
     * Constant integer value for setting max number of boxes
     * <p>
     * Note: Set to 26 using number of capital letters as limit
     */
    private static final int MAX_NUM_BOXES = 26;

    /**
     * Two-dimensional character array that holds boxes
     */
    private char[][] containerBoxField;
    
    /**
     * One-dimensional character array of boxes to be placed
     */
    private BoxClass[] boxList;
    
    /**
     * integer box width and box height fields
     */
    private int containerBoxWidth, containerBoxHeight;
    
    /**
     * integer number of boxes available
     */
    private int numBoxesAvailable;
    
    /**
     * Boolean display flag to indicate if recursive process
     * should be displayed
     */
    private boolean displayFlag;
    
    /**
     * Initialization constructor
     * 
     * @param initBoxWidth Initial width of container box
     * 
     * @param initBoxHeight Initial height of container box
     */
    public ContainerBoxClass( int initBoxWidth, int initBoxHeight )
       {
        int rowIndex, colIndex;
        containerBoxWidth = initBoxWidth;
        containerBoxHeight = initBoxHeight;
        
        numBoxesAvailable = 0;
        
        displayFlag = false;
        
        containerBoxField = new char[ initBoxHeight ][ initBoxWidth ];
        
        for( rowIndex = 0; rowIndex < containerBoxHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < containerBoxWidth; colIndex++ )
               {
                containerBoxField[ rowIndex ][ colIndex ] = DEFAULT_FIELD_CHAR;
               }
           }
        
        boxList = new BoxClass[ MAX_NUM_BOXES ];
       }
   

    /**
     * Implements recursive backtracking to pack boxes
     * into container box
     * 
     * @return Boolean success or failure
     */
    public boolean fillContainerBox()
    {
        int boxIndex = findNextUnusedBoxIndex(0);
        PointClass nextEmptySpot = findNextOpenLocation();
        BoxClass currentBox;

        if(nextEmptySpot == null) //checks if boxes are available
        {
            if(boxIndex == NO_BOXES_AVAILABLE)
            {
                return true;
            }
            return false;
        }
        while(boxIndex != NO_BOXES_AVAILABLE) //while a box is available
        {
        	currentBox = boxList[boxIndex];
            if(checkForFitInField(nextEmptySpot, currentBox)) //check if box fits, if it does place it
            {
                fillBoxLocation(nextEmptySpot, currentBox, FILL_BOX);
                displayField();
                currentBox.setUsedState();

                if(fillContainerBox()) //recursion
                {
                    return true;
                }
                fillBoxLocation(nextEmptySpot, currentBox, CLEAR_BOX);
                displayField();
                currentBox.unsetUsedState();
            }
            currentBox.rotate(); //if the box didn't fit it is rotated to try again
            if(checkForFitInField(nextEmptySpot, currentBox))
            {
                fillBoxLocation(nextEmptySpot, currentBox, FILL_BOX);
                displayField();
                currentBox.setUsedState();

                if(fillContainerBox()) //recursion
                {
                    return true;
                }
                fillBoxLocation(nextEmptySpot, currentBox, CLEAR_BOX);
                displayField();
                currentBox.unsetUsedState();
            }
            boxIndex++ ; //moves to the next box
            boxIndex = findNextUnusedBoxIndex(boxIndex);
        }
        return false;
    }
       
    
    /** 
     * Adds a new box to the list of boxes to be placed
     * into the container box
     * 
     * @param boxWidth width of box to be added
     * 
     * @param boxHeight height of box to be added
     * 
     * @return Boolean success, or failure if box array is full
     */
    public boolean addBoxToList( int boxWidth, int boxHeight )
       {
        if( numBoxesAvailable < MAX_NUM_BOXES )
           {
            boxList[ numBoxesAvailable ] = new BoxClass( boxWidth, boxHeight );
            
            numBoxesAvailable++;
            
            return true;
           }
        
        return false;
       }
    
    /**
     * sets display flag to display container box during operations
     * 
     * @param setState Boolean state to set or unset display flag
     * 
     */
    public void setDisplayFlag( boolean setState )
       {
        displayFlag = setState;
       }
    
    /**
     * Displays entire container box with any boxes
     * currently within container
     * 
     */
    public void displayField()
       {
        int rowIndex, colIndex;
        
        if( displayFlag )
           {
            System.out.println();
            
            for( colIndex = 0; colIndex < containerBoxWidth + 4; colIndex++ )
               {
                System.out.print( '=');
               }
              
            System.out.println();
            
            for( rowIndex = 0; rowIndex < containerBoxHeight; rowIndex++ )
               {
                System.out.print("||");
                
                for( colIndex = 0; colIndex < containerBoxWidth; colIndex++ )
                   {
                    System.out.print( containerBoxField[ rowIndex ][ colIndex ] );
                   }
                
                System.out.println( "||");
               }

            for( colIndex = 0; colIndex < containerBoxWidth + 4; colIndex++ )
               {
                System.out.print( '=');
               }
            
            System.out.println( "\n" );  
            
            //waitForEnter( "" );
           }
       }
    
    /**
     * Finds next open location
     * <p>
     * Note: Starts search from left lower corner, moves right, then up
     * 
     * @return X, Y coordinate of open location as PointClass object,
     * null if no open location found
     */
    private PointClass findNextOpenLocation()
       {
        int rowIndex, colIndex;
        
        for( rowIndex = containerBoxHeight - 1; rowIndex >= 0; rowIndex-- )
           {
            for( colIndex = 0; colIndex < containerBoxWidth; colIndex++ )
               {
                if( containerBoxField[ rowIndex ][ colIndex ] == DEFAULT_FIELD_CHAR )
                   {
                    return new PointClass( colIndex, rowIndex ); 
                   }
               }
           }

        return null;
       }
    
    /**
     * Searches for unused box starting at the given index of the list
     * of boxes to be packed
     * 
     * @param startAtIndex Index used to begin search
     * of box list
     * 
     * @return integer index if a box is found,
     * constant NO_BOXES_AVAILABLE if no boxes available
     * 
     */
    private int findNextUnusedBoxIndex( int startAtIndex )
       {
        while( startAtIndex < numBoxesAvailable )
           {
            if( !boxList[ startAtIndex ].isUsed() )
               {
                return startAtIndex;
               }
            
            startAtIndex++;
           }
        
        return NO_BOXES_AVAILABLE;
       }
    
    /**
     * Checks to see if a box can be placed within the container
     * starting at the test location provided
     * <p>
     * Note: Tests from lower left corner across then up
     * to check for available area
     * 
     * @param testLocation lower left corner location to start testing
     * for available space within the container box
     *  
     * @param testBox box to be checked for fit
     * 
     * @return Boolean result of test
     * 
     */
    private boolean checkForFitInField( PointClass testLocation, BoxClass testBox )
       {
        int rowIndex, colIndex;
        int heightLimit = testLocation.getYPos() - testBox.getHeight();
        int widthLimit = testLocation.getXPos() + testBox.getWidth();
        int topOfBox = -1;
        
        if( heightLimit < topOfBox || widthLimit > containerBoxWidth )
           {
            return false;              
           }
        
        for( rowIndex = testLocation.getYPos(); 
                                            rowIndex > heightLimit; rowIndex-- )
           {
            for( colIndex = testLocation.getXPos(); 
                                             colIndex < widthLimit; colIndex++ )
               {
                if( containerBoxField[ rowIndex ][ colIndex ] 
                                                         != DEFAULT_FIELD_CHAR )
                   {
                    return false;
                   }
               }
           }
        
        return true;
       }
    
    /**
     * Fills container box at given location with box letter;
     * if clear flag is set, sets container box with default character
     * <p>
     * Note: Paints box from lower left corner across then up
     * 
     * @param boxLocation PointClass object location of fill starting point
     * 
     * @param fillBox BoxClass object containing width, height, and ID letter
     * to be used for filling container box
     * 
     * @param clearFlag provides input on whether to use the box letter
     * (FILL_BOX) Or to use the default character to clear box area
     * (CLEAR_BOX)
     */
    private void fillBoxLocation( PointClass boxLocation, 
                                               BoxClass fillBox, int clearFlag )
       {
        int rowIndex, colIndex;
        int heightLimit = boxLocation.getYPos() - fillBox.getHeight();
        int widthLimit = boxLocation.getXPos() + fillBox.getWidth();
        char fillChar = fillBox.getID();
        
        if( clearFlag == CLEAR_BOX )
           {
            fillChar = DEFAULT_FIELD_CHAR;
           }
        
        for( rowIndex = boxLocation.getYPos(); 
                                            rowIndex > heightLimit; rowIndex-- )
           {
            for( colIndex = boxLocation.getXPos(); 
                                             colIndex < widthLimit; colIndex++ )
               {
                containerBoxField[ rowIndex ][ colIndex ] = fillChar;
               }
           }
       }
    
    /**
     * Waits for use to press any key
     * 
     * @param args Object parameter not used
     * 
     * @param message optional message for operation
     * 
     */
/*    private static void waitForEnter( String message, Object...args )
       {
        Scanner scanIn = new Scanner( System.in );
        
        System.out.println( "Press any key to continue . . ." );
        
        scanIn.nextLine();
        
        //scanIn.close();
       }
*/    
   }