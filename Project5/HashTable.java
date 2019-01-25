

import project4.BinarySearchTree;

/**
 * HashTable demonstrates a hash table with separate chaining
 */
public class HashTable {

    private final BinarySearchTree<Link>[] hashArray; // array of lists
    private final int arraySize;

    public HashTable(int size) // constructor
    {
        arraySize = size;
        hashArray = new BinarySearchTree[arraySize]; // create array
        for (int j = 0; j < arraySize; j++) // fill array
        {
            hashArray[j] = new BinarySearchTree(); // with lists
        }
    }

    public void displayTable() {
        for (int j = 0; j < arraySize; j++) // for each cell,
        {
            System.out.println(j); // display cell number
            hashArray[j].displayTree(); // display list
        }
    }

    public int hashFunc(int key) // hash function
    {
        return key % arraySize;
    }

    public void insert(Link theLink) // insert a link
    {
        int key = theLink.getKey();
        int hashVal = hashFunc(key); // hash the key
        hashArray[hashVal].insert(theLink); // insert at hashVal
    }

    public void delete(int key) // delete a link
    {
        int hashVal = hashFunc(key); // hash the key
        hashArray[hashVal].delete(new Link(key)); // delete link
    }

    public Link find(int key) // find link
    {
        int hashVal = hashFunc(key); // hash the key
        Link theLink = (Link) hashArray[hashVal].find(new Link(key)); // get link
        return theLink; // return link
    }
}
