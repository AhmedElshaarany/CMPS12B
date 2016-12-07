//----------------------------------------------------------------------------                           
// Dictionary.c                                                                                      
//----------------------------------------------------------------------------                           
// Name:        Ahmed Elshaarany                                                                         
// CruzID:      aelshaar                                                                                 
// Class:       12B                                                                                      
// Assignment:  pa5                                                                                      
//-----------------------------------------------------------------------------                          
// Description:                                                                                          
// This class contains the implementation of the Dictionary ADT                                 
//-----------------------------------------------------------------------------                          

// ----------------------------------- header files ---------------------------------------- //
#include<assert.h>
#include<string.h>
#include<stdlib.h>
#include<stdio.h>
#include "Dictionary.h"

// ----------------------------------- constants ---------------------------------------- //

const int tableSize = 101;

// ------------------------------ hash helper functions ---------------------------------- //

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);  // remainder on division by sizeInBits
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
   unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100
   while (*input) {                   // while *input is not '\0' (not end of string)
      result ^= *input++;                // result = result ^ *input (current char alters result) 
                                         // input++  (go to next char)
      result = rotate_left(result, 5);   // rotate result by fixed amount
   }
   return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// ------------------------------ private types and functions ------------------------------ //

// PairObj
typedef struct PairObj{
  char* key;
  char* value;
}PairObj;


// Pair
typedef PairObj* Pair;


// newPair()
// constructor for the pair type
Pair newPair(char* k, char *v){
  
  // allocate memory in heap for new Pair
  Pair P = malloc(sizeof(PairObj));
  
  // check that memory is allocated
  assert( P != NULL );
  
  // allocate memory for key and value
  P->key = calloc(strlen(k)+1, sizeof(char));
  P->value = calloc(strlen(v)+1, sizeof(char));
  
  // init Pair with k and v
  strcpy(P->key, k);
  strcpy(P->value, v);
  
  // return the pointer to pair
  return (P);
}


// freePair()
// destructor for the pair type
void freePair(Pair* pP){
  if( pP != NULL && *pP != NULL){
    
    // free memory allocated for key and value
    free((*pP)->key);
    free((*pP)->value);
    
    // free memory allocated for PairObj
    free(*pP);
    *pP=NULL;
  }
}


// NodeObj
typedef struct NodeObj{
  Pair pair;
  struct NodeObj* next;
}NodeObj;


// Node
typedef NodeObj* Node;


// newNode()
// constructor for the Node type
Node newNode(char* k, char* v){

  // allocate memory in heap for new node and corresponding pair
  Node N = malloc(sizeof(NodeObj));
  Pair P = newPair(k, v);

  // make sure that memory is allocated
  assert( N != NULL && P != NULL);

  // initialize new node fields
  N->pair = P;
  N->next = NULL;

  // return pointer to NodeObj
  return (N);
}


// freeNode()
// destructor for the Node type
void freeNode(Node* pN){

  // first free memory allocated for pair of Node
  freePair(&(*pN)->pair);

  // Now free memory allocated for NodeObj
  if( pN != NULL && *pN != NULL){
    free(*pN);
    *pN=NULL;
  }
}


// define LinkedListObj
typedef struct LinkedListObj{
  Node head;
  Node tail;
  int numItems;
}LinkedListObj;

// define pointer to LinkedListObj
typedef LinkedListObj* LinkedList;


// newLinkedList()
// allocates linked list on the heap, returns linkedlist
LinkedList newLinkedList(void){

  // allocate Linked list on heap
  LinkedList L = malloc(sizeof(LinkedListObj));

  // assert that linked list is allocated
  assert( L != NULL );

  // initialize LinkedList
  L->head = NULL;
  L->tail = NULL;
  L->numItems = 0;

  // return pointer to LinkListObj
  return L;
}


// freeLinkedList()
// destructor for the LinkedList type
void freeLinkedList(LinkedList* pL){

  // free memory allocated for LinkedListObj
  if( pL != NULL && *pL != NULL ){
    free(*pL);
    *pL = NULL;
  }
}


// linkedListIsEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int linkedListIsEmpty(LinkedList L){

  // check if referencing a NULL LinkedList
  if ( L == NULL ){
    fprintf(stderr, 
	    "LinkedList Error: calling linkedListisEmpty() on NULL LinkedList reference\n");
    exit(EXIT_FAILURE);
  }
  
  // return true if empty
  return (L->numItems==0);
}


// linkedListSize()
// returns the number of (key, value) pairs in L
// pre: none
int linkedListSize(LinkedList L){
  
  // check if referencing a NULL LinkedList
  if( L == NULL){
    fprintf(stderr, 
	    "LinkedList Error: calling linkedListsize() on NULL LinkedList reference\n");
    exit(EXIT_FAILURE);
  }

  // return numItems
  return (L->numItems);
}


// linkedListLookup()
// returns the value v such that (k, v) is in LinkedList, or returns NULL if no 
// such value v exists.
// pre: none
char* linkedListLookup(LinkedList L, char* k){

  // check if referencing a NULL LinkedList
  if ( L == NULL ){
    fprintf(stderr,
	    "LinkedList Error: calling linkedListlookup() on NULL LinkedList reference\n");
    exit(EXIT_FAILURE);
  }


  // set N to point to begining of list
  Node N = L->head;
  // set F (the found Node) to be List head too
  Node F = N;
  // set found to be false
  int found = 0;  

  // search list for matching key
  while( N != NULL && !found ){

    // if key is found
    if( !strcmp(N->pair->key, k) ){
      // set F to found node N
      F = N;
      // set found to true
      found = 1;
    }
    // if not found, move on to next node
    N = N->next;
  } 
  
  // if found, return corresponding value
  if (found){
    return(F->pair->value); 
  }
  else{
    return NULL;
  }
   
}


// linkedListInsert()
// inserts new (key,value) pair into L
// pre: lookup(L, k)==NULL
void linkedListInsert(LinkedList L, char* k, char* v){

  // check if key already exists
  if( linkedListLookup(L, k) != NULL ){
    fprintf(stderr, 
	    "LinkedList Error: calling insert() with a duplicate key: %s\n", k);
  }
  else{
    // create a new node with k and v as newPair                                               
    Node N = newNode(k, v);

    // if list is empty                                                                         
    if(linkedListIsEmpty(L)){
      L->head = N;
      L->tail = N;
    }
    else{
      // add new node to head of list                                                          
      N->next = L->head;
      L->head = N;
    }
    
    // increment number of items                                                              
    L->numItems++;
  }
}


// linkedListDelete()
// deletes pair with the key k
// pre: linkedListLookup(L, k)!=NULL
void linkedListDelete(LinkedList L, char* k){

  // if key does not exist
  if( linkedListLookup(L, k) == NULL ) {
    fprintf(stderr,
	    "LinkedList Error: calling linkedListDelete() with a non-existing key: %s\n", k);
  }
  else{
  Node current  = L->head;
  Node previous = L->head;
  int found     = 0;

  // if dictionary has only only one node
  if( L->numItems == 1 ){
    freeNode(&L->head);
    L->head = NULL;
    L->tail = NULL;
  }
  else{
    // loop until key is found
    while( !found ){

      // if key is found
      if( !strcmp(current->pair->key, k) ){
	// set found to true
	found = 1;
	
	// if key is head
	if( current == L->head ){
	  L->head = L->head->next;
	  current->next = NULL;
	  freeNode(&current);
	}
	// if key is tail
	else if( current == L->tail){
	  L->tail = previous;
	  previous->next = NULL;
	  freeNode(&current);
	}
	// if key is a middle node
	else{
	  previous->next = current->next;
	  current->next = NULL;
	  freeNode(&current);
	}
      }
      // if key is not yet found
      else{
	// if still at beginning
	if( current == L->head){
	  // advance current only
	  current = current->next;
	}
	else{
	  // advance both previous and current
	  current = current->next;
	  previous = previous->next;
	}
      }
    }
  }
  // decrement numItems after removing node
  L->numItems--;
  }
}


// linkedListMakeEmpty()
// re-sets D to the empty state.
// pre: none
void linkedListMakeEmpty(LinkedList L){
  
  // check if NULL referencing D
  if( L == NULL ){
    fprintf(stderr, "LinkedList Error: calling linkedListMakeEmpty() on NULL LinkedList reference\n");
    exit(EXIT_FAILURE);
  }

  Node current = L->head;
  Node delete  = L->head;

  while( current != NULL ){
    // move current to next    
    current = current->next;
    // delete node
    freeNode(&delete);
    // move delete to be current
    delete = current;
  }
  // init Dictionary
  L->numItems = 0;
  L->head     = NULL;
  L->tail     = NULL;
}


// printChain()
// prints the chain that has a head node N
void printChain(FILE* out, Node N){
  while ( N != NULL ){
    fprintf(out, "%s %s\n", N->pair->key, N->pair->value);
    N = N->next;
  }
}


// freeLinkedListArray()
// frees the LinkedListArray allocated for the dictionary
void freeLinkedListArray(LinkedList** pL){

  if( pL != NULL && *pL != NULL ){
    free(*pL);
    *pL = NULL;
  }
}


// define DictionaryObj
typedef struct DictionaryObj{

  LinkedList* L;
  int totalNumItems;
  
}DictionaryObj;


// ------------------------------ public and functions ------------------------------ //

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
  
  // create new Dictionary on heap
  Dictionary D = malloc( sizeof(DictionaryObj) );

  // allocate tableSize LinkLists on heap
   D->L = calloc( tableSize, sizeof(LinkedList*));
  // create new LinkedList for each hash table entry
  for(int i = 0; i < tableSize; i++){
    D->L[i] = newLinkedList();
  }

  // set numItems to zero
  D->totalNumItems = 0;

  return D;
}


// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){

  // check if referencing a NULL Dictionary
  if ( D == NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  // return 1 if empty, 0 otherwise
  if( D->totalNumItems == 0 ){
    return 1;
  }
  else{
    return 0;
  }
  
}


// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){

  // check if referencing a NULL Dictionary
  if ( D == NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  // return totalNumItems in Dictionary
  return D->totalNumItems;
}


// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){

  // get hash value for given key
  int hashTableIndex = hash(k);

  // return value from LinkedListlookup
  return (linkedListLookup(D->L[hashTableIndex], k));
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){

  // check if key already exists
  if( lookup(D, k) != NULL ){
    fprintf(stderr, 
	    "Dictionary Error: calling insert() with a duplicate key: %s\n", k);
  }
  else{

    // get hash table index
    int hashTableIndex = hash(k);

    // insert pair to linked list of hashtableindex
    linkedListInsert(D->L[hashTableIndex], k, v);
    
    // increment number of items
    D->totalNumItems++;
  }
}


// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){

  // if key does not exist
  if( lookup(D, k) == NULL ) {
    fprintf(stderr,
	    "Dictionary Error: calling delete() with a non-existing key: %s\n", k);
  }
  else{

    // get hastTableIndex for key
    int hashTableIndex = hash(k);

    // delete key from chain
    linkedListDelete(D->L[hashTableIndex], k);

    // decrement numItems after removing node
    D->totalNumItems--;
  }
}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
  
  // check if NULL referencing D
  if( D == NULL ){
    fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }

  // empty all chains
  for(int i = 0; i < tableSize; i++){
    linkedListMakeEmpty(D->L[i]);
  }

  // set totalNumberItems to 0
  D->totalNumItems = 0;
  
}


// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){

  // make all chains empty, if they are not already
  makeEmpty(*pD);
  
  if( pD != NULL && *pD != NULL ){

    // free every linkedlist chain
    for(int i = 0; i < tableSize; i++){
      freeLinkedList(&((*pD)->L[i]));
    }

    // free array of pointers to linked lists
    freeLinkedListArray(&((*pD)->L));
    
    // free dictionary
    free(*pD);
    *pD = NULL;
  }
}


// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){

  // go through all the hashtable entries and print non-empty chains
  for(int i = 0; i < tableSize; i++){
    if ( D->L[i]->head != NULL ){
      printChain(out, D->L[i]->head);
    }
  }
}
