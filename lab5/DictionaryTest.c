//-----------------------------------------------------------------------------
// DictionaryTest.c
// Test C file for the Dictionary ADT
//-----------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include "Dictionary.h"

int main(int argc, char* argv[]){
 
  // Defining a newDictionary D
  // Dictionary D = newDictionary();
  
  // ---------------- Testing the isEmpty() method ----------------------- //
  
  /*
     printf("Is Dictionary Empty? %s\n",(isEmpty(D)?"true":"false"));
  
  */ 
  
  
  // ---------------- Testing the size() method ----------------------- //
  
  /*
     printf("Dictionary Size= %d\n", size(D));
  */

  // free the Dictionary D
  // freeDictionary(&D);

  
  // ---------------- Testing the insert(), lookup(), and printDictionary() ------------------ //

  /*
   Dictionary A = newDictionary();
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
   
   // insert(A, "five", "glow"); // error: key collision
  */

  
  // ---------------- Testing the insert, lookup, and printDictionary, delete ---- //
  /*
  
  Dictionary A = newDictionary();
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

  // insert(A, "five", "glow"); // error: key collision

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
  
  */


  // --------------------- Testing the whole DictionaryClient.c --------------------------- //

    Dictionary A = newDictionary();
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

    insert(A, "five", "glow"); // error: key collision

    delete(A, "one");
    delete(A, "three");
    delete(A, "seven");

    printDictionary(stdout, A);

    for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
    }

    delete(A, "one");  // error: key not found

    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    makeEmpty(A);
    printf("%s\n", (isEmpty(A)?"true":"false"));

    freeDictionary(&A);

    return(EXIT_SUCCESS);


}
