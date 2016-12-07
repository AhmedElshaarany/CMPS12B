//-----------------------------------------------------------------------------
// DictionaryTest.c
//----------------------------------------------------------------------------                           
// Name:        Ahmed Elshaarany                                                                         
// CruzID:      aelshaar                                                                                 
// Class:       12B                                                                                      
// Assignment:  pa5                                                                                      
//-----------------------------------------------------------------------------                          
// Description:                                                                                          
// Test C file for the Dictionary ADT
//-----------------------------------------------------------------------------                          

#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){


  Dictionary A = newDictionary();

  // ----------------------------- Testing insert(), isEmpty(), and size() functions ---------------------------------- //

  /*
  printf("Dictionary init size = %d\n", size(A));
  printf("Is Dictionary emtpy at the begining? %s\n", (isEmpty(A)==1)? "Yes":"No");
  //  char* k;
  //  char* v;
  char* word1[] = {"one","two","three","four","five","six","seven"};
  char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
  int i;
  
  for(i=0; i<7; i++){
    insert(A, word1[i], word2[i]);
  }

  printf("Dictionary size now = %d\n", size(A));
  printf("Is Dictionary emtpy now? %s\n", (isEmpty(A)==1)? "Yes":"No");
  */

  // ----------------------------- Testing insert() and printDictionary() functions ---------------------------------- //

  /*
  char* word1[] = {"one","two","three","four","five","six","seven"};
  char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
  int i;
  
  for(i=0; i<7; i++){
    insert(A, word1[i], word2[i]);
  }
  
  printDictionary(stdout, A);
  */
  

  // ----------------------------- Testing insert(), printDictionary(), lookup functions ---------------------------------- //

  /*
  char* k;
  char* v;
  char* word1[] = {"one","two","three","four","five","six","seven"};
  char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
  int i;
  
  for(i=0; i<7; i++){
    insert(A, word1[i], word2[i]);
  }
  
  printDictionary(stdout, A);
  
  for(i=0; i<7; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }

  //insert(A, "five", "glow"); // error: duplicate keys
  */


  // ------------ Testing insert(), printDictionary(), lookup(), delete() functions ---------------------- //

  /*
  char* k;
  char* v;
  char* word1[] = {"one","two","three","four","five","six","seven"};
  char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
  int i;
  
  for(i=0; i<7; i++){
    insert(A, word1[i], word2[i]);
  }
  
  printDictionary(stdout, A);
  
  for(i=0; i<7; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }
  
  // insert(A, "five", "glow"); // error: duplicate keys
  
  delete(A, "one");
  delete(A, "three");
  delete(A, "seven");
  
  printDictionary(stdout, A);
  
  for(i=0; i<7; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }
  
  // delete(A, "one");  // error: key not found
  
  printf("%s\n", (isEmpty(A)?"true":"false"));
  printf("%d\n", size(A));
  */


  // ------------ Testing all of DictionaryClient.c ---------------------- //
  
  char* k;
  char* v;
  char* word1[] = {"one","two","three","four","five","six","seven"};
  char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
  int i;
  
  for(i=0; i<7; i++){
    insert(A, word1[i], word2[i]);
  }
  
  printDictionary(stdout, A);
  
  for(i=0; i<7; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }
  
  // insert(A, "five", "glow"); // error: duplicate keys
  
  delete(A, "one");
  delete(A, "three");
  delete(A, "seven");
  
  printDictionary(stdout, A);
  
  for(i=0; i<7; i++){
    k = word1[i];
    v = lookup(A, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  }
  
  // delete(A, "one");  // error: key not found
  
  printf("%s\n", (isEmpty(A)?"true":"false"));
  printf("%d\n", size(A));
  makeEmpty(A);
  printf("%s\n", (isEmpty(A)?"true":"false"));
  
  freeDictionary(&A);
  
  return (EXIT_SUCCESS);
}
