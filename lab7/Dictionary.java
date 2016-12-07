//-----------------------------------------------------------------------------
// Dictionary.java
//-----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12M
// Assignment:  lab7
//-----------------------------------------------------------------------------
// Description:
// Binary Search Tree implementation of the Dictionary ADT
//----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface{


// private variables, classes,  and functions ------------------------------------------------

    int numPairs;
    Node root;
    
    private class Node{
	String key;
	String value;
	Node left;
	Node right;

	// Node()
	// constructor for the Node class
	Node(String k, String v){
	    key = k;
	    value = v;
	    left  = null;
	    right = null;
	}

    }


    // findKey()
    // returns the Node containing key k in the subtree rooted at R, or returns
    // null if no such Node exists
    private Node findKey(Node R, String k){
	if(R==null || k.equals(R.key)) 
	    return R;
	if( k.compareTo(R.key)<0 ) 
	    return findKey(R.left, k);
	else // k.compareTo(R->key)>0
	    return findKey(R.right, k);
    }


    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns null 
    // if N is equal to R. (pre: R != null)
    private Node findParent(Node N, Node R){
	Node P = null;
	if( N!=R ){
	    P = R;
	    while( P.left!=N && P.right!=N ){
		if(N.key.compareTo(P.key)<0)
		    P = P.left;
		else
		    P = P.right;
	    }
	}
	return P;
    }

    
    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or null if R is null.
    private Node findLeftmost(Node R){
	Node L = R;
	if( L!=null ) for( ; L.left!=null; L=L.left) ;
	return L;
    }


    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R in order
    // of increasing keys to file pointed to by out.
    private String printInOrder(Node R){
	String S = "";
	if( R!=null ){
	    S += printInOrder(R.left);
	    S += R.key + " " + R.value + "\n";
	    S += printInOrder(R.right);
	}
	return S;
    }

    
    // deleteAll()
    // deletes all Nodes in the subtree
    private void deleteAll(){
	    root = null;
	    numPairs = 0;
    }    
    
// public functions -----------------------------------------------------------

    
    // Dictionary()
    // constructor for the Dictionary class
    Dictionary(){
	numPairs = 0;
	root = null;
    }

    
    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
       return (numPairs == 0);
    }

    
    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size(){
	return numPairs;
    }

    
    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key){
	Node N = findKey(root, key);
	if( N == null ){
	    return null;
	}
	else{
	    return N.value;
	}
    }

    
    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException{

	// check if key already exists
	if( findKey( root, key) != null ){
	    throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: " + key);
	}

	Node N = new Node(key, value);
	Node A,B;

	A = root;
	B = null;

	// search for bottom of tree
	while( A != null ){
	    B = A;
	    if( key.compareTo(A.key) < 0 ){
		A = A.left;
	    }
	    else{
		A = A.right;
	    }
	}

	// if no tree exists, set Node as root
	if ( B == null ){
	    root = N;
	}
	else if( key.compareTo(B.key) < 0 ){
	    B.left = N;
	}
	else{
	    B.right = N;
	}

	// increment number of pairs
	numPairs++;
    }

    
    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException{

	Node N = findKey(root, key);
	// check if key doesn't exist
	if( N == null ){
	    throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: " + key);
	}

	Node P,S;

	if( N.left == null && N.right == null ){ // case (1) no children 
	    if( N == root ){
		root = null;
	    }
	    else{
		P = findParent(N, root);
		if( P.right == N ) P.right = null;
		else P.left = null;
		// N = null;
	    }
	    
	}
	else if ( N.right == null ){ // case 2 (left but no right child)
	    if( N == root ){
		root = N.left;
	    }
	    else{
		P = findParent(N, root);
		if( P.right == N ) P.right = N.left;
		else P.left = N.left;
		// N = null;
	    }
	}
	else if ( N.left == null ){ // case 2 (right but no lef child)
	    if( N == root ){
		root = N.right;
	    }
	    else{
		P = findParent(N, root);
		if( P.right == N ) P.right = N.right;
		else P.left = N.right;
		// N = null;
	    }
	}
	else{                      // case3: (two children: N->left!=NULL && N->right!=NULL)
	    S = findLeftmost(N.right);
	    N.key = S.key;
	    N.value = S.value;
	    P = findParent(S, N);
	    if ( P.right == S ) P.right = S.right;
	    else P.left = S.right;
	}

	// decrement the number of pairs
	numPairs--;
    }

    
    // makeEmpty()
    // pre: none
    public void makeEmpty(){
	deleteAll();
    }

    
    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString(){
	return printInOrder(root);
    }

    

}
