package p6_package;

public class QueueClass extends LinkedListClass 
{
	/**
	 * private constant for use in accessing front of queue
	 */
	private static final int QUEUE_FRONT_INDEX = 0;
	
	/**
	 * space constant for supporting display operations
	 */
	private static final char SPACE = 32;
	
	/**
	 * Default constructor for QueueClass
	 */
	public QueueClass()
	{
		super();
	}
	
	/**
	 * Copy constructor for QueueClass
	 * @param copied - QueueClass object to be duplicated
	 */
	public QueueClass(QueueClass copied)
	{
		super(copied);
	}
	
	/**
	 * Dequeue operation
	 * @return BoxClass object removed from the front of the queue
	 */
	public BoxClass dequeue()
	{
		return super.removeItemAt(QUEUE_FRONT_INDEX);
	}
	
	/**
	 * Displays queue from rear or back to front
	 */
	public void displayQueue()
	{
		displaySpaces(QUEUE_FRONT_INDEX);
	}
	
	/**
	 * Recursive method displays spaces for displayQueue
	 * @param numSpaces - integer value specifying number of spaces to display
	 */
	private void displaySpaces(int numSpaces)
	{
		 int listIndex;
		 
		 System.out.println("Tail of Queue:");
	     if(numSpaces != super.getCurrentSize())
	        {
	    	 	System.out.print(SPACE);
	            for(listIndex = 0; listIndex < numSpaces; listIndex++)
	            {
	                System.out.print(SPACE);
	            }
	            System.out.println(accessItemAt(getCurrentSize() - numSpaces - 1).toString());
	            displaySpaces(numSpaces + 1);
	        }
	        for(listIndex = 0; listIndex < numSpaces; listIndex++)
	        {
	            System.out.print(SPACE);
	        }
	        System.out.println("Head of Queue:");
	}
	
	/**
	 * Enqueue operation
	 * @param newItem - BoxClass object to be enqueued
	 */
	public void enqueue(BoxClass newItem)
	{
		super.appendItem(newItem);
	}
	
	/**
	 * Peek front operation, returns value at front without removing it
	 * @return BoxClass object found at front of queue
	 */
	public BoxClass peekFront()
	{
		return super.accessItemAt(QUEUE_FRONT_INDEX);
	}
}

