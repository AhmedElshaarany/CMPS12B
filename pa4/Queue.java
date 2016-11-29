//----------------------------------------------------------------------------                           
// Queue.java                                                                                       
//----------------------------------------------------------------------------                           
// Name:        Ahmed Elshaarany                                                                         
// CruzID:      aelshaar                                                                                 
// Class:       12B                                                                                      
// Assignment:  pa4                                                                                      
//-----------------------------------------------------------------------------                          
// Description:                                                                                          
// This class contains the implementation of the QueueInterface ADT                                 
//-----------------------------------------------------------------------------                          

public class Queue implements QueueInterface{

// -------------------------- Private Section ------------------------------- //
   
    // Define the Node class
    private class Node{
	Object item;
	Node next;

	// Node()                                                                                                       
	// constructor for new Node                                                                                     
	Node(Object newItem){
	    item = newItem;
	    next = null;
	}

    }
    
    private Node  head;
    private Node  tail;
    private int   numItems;

// -------------------------- Public section ------------------------------- // 

    // Queue()
    // constructor for Queue
    public Queue(){	
	// init head and numItems
	head = null;
	tail = null;
	numItems = 0;
    }


    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
	return ( numItems == 0 );
    }


    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length(){
	return( numItems );
    }

    
    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem){
	
	// create new Node with null next
	Node N = new Node(newItem);
	
	// check if queue is empty
	if ( isEmpty() ){
	    // set head and tail to new node
	    head = tail = N;
	}
	else {
	    // set tail next to new node
	    tail.next = N;
	    // set new node as tail
	    tail = N;
	}
	
	// increment the number of items
	numItems++;
    }

    
    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
	
	// check if queue is empty
	if ( isEmpty() ){
	    throw new QueueEmptyException(
					  "Queue: dequeue() called on empty queue");
	}
	
	// if not empty, store head item, set new head, decrement numItems
	Object retObj = head.item;
	
	// if only one node in queue
	if ( head == tail ){
	    head = tail = null;
	}
	else {
	    head = head.next;
	}
	
	// decrement number of items
	numItems--;

	return retObj;

    }


    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException{

	// check if queue is empty                                                                                       
	if ( isEmpty() ){
            throw new QueueEmptyException(
                                          "Queue: peek() called on empty queue");
        }

	return head.item;

    }

    
    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
	
	// check if queue is empty                                                                                    
	if ( isEmpty() ){
            throw new QueueEmptyException(
                                          "Queue: dequeueAll() called on empty queue");
        }

	// init queue
	head = tail = null;
	numItems = 0;
    }


    // toString()                                                                                                       
    // overrides Object's toString() method                                                                               
    public String toString(){
        String qString = "";
        Node n = head;

        // loop over all queue nodes                                                                                      
        for (; n != null ; n = n.next){
            qString += n.item.toString() + " ";
        }

        return ( qString );
    }

}
