//----------------------------------------------------------------------------
// Dictionary.java
//----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12B
// Assignment:  pa3
//-----------------------------------------------------------------------------
// Description:
// This class contains the implementation of the DictionaryInterface ADT
//-----------------------------------------------------------------------------


public class Dictionary implements DictionaryInterface{

    // Node class
    // The node is defined as a pair and a next pointer
    class Node{
        Pair p;
        Node next;
        
        Node(String k, String v){
            p = new Pair(k, v);
            next = null;
        }
    }
    
    // Pair class
    // The pair is defined as a key-value pair of Strings
    class Pair{
        String key;
        String value;
        
        Pair(String k, String v){
            key = k;
            value = v;
        }
    }
    
    // Define private class variables
    private Node head;
    private Node tail;
    private int numItems;
    
    
    public Dictionary(){
        // Initialize class variables
        head = null;
        tail = null;
        numItems = 0;
    }
    
    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
        return (numItems == 0);
    }
    
    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size(){
        return numItems;
    }
    
    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key){
        
        // Initialize value with null
        String value = null;
        
        // start at head
        Node n = head;
        
        // value is not yet found
        boolean found = false;
        
        // loop until found or reached end of list
        while( !found && n != null ){
            
            // set value if key is found and exit loop
            if(n.p.key.equals(key)){
                value = n.p.value;
                found = true;
            }
            // if not found, move on to next node
            n = n.next;
        }
        
        // return value if found, null otherwise
        return value;
    }
    
    
    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException{
        
        // if key already exists
        if(lookup(key) != null){
            throw new DuplicateKeyException(" insert() called with a key that aleady exists ");
        }
        
        Node new_node = new Node(key, value);
        
        // if list is empty
        if(isEmpty()){
            head = new_node;
            tail = new_node;
        }
        else{
            // add new node to end of list
            tail.next = new_node;
            tail = tail.next;
        }
        numItems++;
    }
    
    
    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException{
        
        // if key does not exist
        if( lookup(key) == null){
            throw new KeyNotFoundException(" delete() called to a key that does not exist ");
        }
        
        Node current  = head;
        Node previous = head;
        boolean found = false;
        
        // if dictionary has only one node
        if(numItems == 1){
            head = tail = null;
        }
        else{
            // loop until key is found
            while(!found){
                // if key is found
                if(current.p.key.equals(key)){
                    found = true;
                    
                    // if key is head
                    if(current == head){
                        head = head.next;
                    }
                    // if key is tail
                    else if(current == tail){
                        tail = previous;
                        previous.next = null;
                    }
                    // if key is a middle node
                    else{
                        previous.next = current.next;
                        current.next = null;
                    }
                }
                // if key is not yet found
                else{
                    // if still at beginning
                    if(current == head){
                        // advance current only
                        current = current.next;
                    }
                    else{
                        // advance both previous and current
                        current  = current.next;
                        previous = previous.next;
                    }
                }
            }
        }
        numItems--;
    }
    
    // makeEmpty()
    // pre: none
    public void makeEmpty(){
        head = tail = null;
        numItems = 0;
    }
    
    
    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString(){
        
        // start at head
        Node n = head;
        
        // define empty string
        String S = "";
        
        // for all nodes, add key-value String pair to S
        for(; n != null; n = n.next ){
            S += n.p.key+" "+n.p.value+"\n";
        }
        return S;
    }
    
    
}