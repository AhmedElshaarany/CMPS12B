//----------------------------------------------------------------------------       
// charType.c                                                                      
//----------------------------------------------------------------------------             
// Name:        Ahmed Elshaarany                                                          
// CruzID:      aelshaar                                                                   
// Class:       12M                                                                      
// Assignment:  lab4                                                                       
//-----------------------------------------------------------------------------          
// Description:                                                                           
// 
// This program takes two command line arguments giving the input and output 
// file names respectively, then classifies the characters on each line of the 
// input file into the following categories: alphabetic characters (upper or 
// lower case), numeric characters (digits 0-9), punctuation, and white space 
// (space, tab, or newline). Any characters on a given line of the input file 
// that cannot be placed into one of these four categories (such as control or 
// non-printable characters) will be ignored.
//-----------------------------------------------------------------------------           
  

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototypes
void extract_chars(char* s, char* a, char* d, char* p, char* w);
void report_line(FILE* out, int line_n, char* a, char* d, char* p, char* w);

// function main which reads command line arguments
int main(int argc, char* argv[]){

  FILE* in;               // handle for input file
  FILE* out;              // handle for output file
  char* line;             // string holding input line
  char* alpha;            // string holding all alphabaetic characters
  char* num;              // string holding all numeric characters
  char* punc;             // string holding all punctuation characters
  char* wspace;           // string holding all white space
  
  // check command line for correct number of arguments
  if( argc != 3){
    printf("Usage: %s input-file outputfile\n", argv[0]);
    return (EXIT_FAILURE);
  }

  // open input file for reading
  if( (in=fopen(argv[1], "r"))==NULL ){
    printf("Unable to read from file %s\n", argv[1]);
    return (EXIT_FAILURE);
  }
  
  // open output file for writing
  if( (out=fopen(argv[2], "w"))==NULL ){
    printf("Unable to write to file %s\n", argv[2]);
    return (EXIT_FAILURE);
  }

  // allocate strings line, alpha, num, punc, wspace on the heap
  line   = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  alpha  = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  num    = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  punc   = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  wspace = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  assert( line!=NULL && alpha!=NULL && num!=NULL && punc!=NULL && wspace!=NULL );
  
  // read each line in input file, extract different types of characters
  int i = 1;
  while (fgets(line, MAX_STRING_LENGTH, in) != NULL){
    extract_chars(line, alpha, num, punc, wspace);
    report_line(out, i++, alpha, num, punc, wspace);
  }

  // free heap memory associated with string variables line, alpha, num, puc, wspace
  free(line);
  line = NULL;
  free(alpha);
  alpha = NULL;
  free(num);
  num = NULL;
  free(punc);
  punc = NULL;
  free(wspace);
  wspace = NULL;
  
  
  // close input and output files
  fclose(in);
  fclose(out);

  return (EXIT_SUCCESS);

}


// definition of the function extract_chars
void extract_chars(char* s, char* a, char* d, char* p, char* w){

  // initialize indices for line and each type of character
  int si=0, ai=0, di=0, pi=0, wi=0;

  // line is not over and less than MAX_STRING_LENGTH
  while(s[si]!='\0' && si<MAX_STRING_LENGTH){

    // if alphabetic character
    if( isalpha(s[si]) ){ 
      a[ai++] = s[si];
    }
    // if digit
    else if( isdigit(s[si]) ){
      d[di++] = s[si];
    }
    // if punctuation
    else if( ispunct(s[si]) ){
      p[pi++] = s[si];
    } 
    // if white space
    else if( isspace(s[si]) ){
      w[wi++] = s[si];
    }
    si++;
  }

  // End each category of characters with the null character
  a[ai] = '\0';
  d[di] = '\0';
  p[pi] = '\0';
  w[wi] = '\0';
}


// definition of the function report_line
void report_line   (FILE* out, int line_n, char* a, char* d, char* p, char* w){

  fprintf(out, "line %d contains:\n", line_n);
  int l = 0;

  // print alphabet report
  if( (l=strlen(a)) == 1){
    fprintf(out, "1 alphabetic character: %s\n", a);
  }
  else{
    fprintf(out, "%d alphabetic characters: %s\n", l, a);
  }

  // print numeric report
  if( (l=strlen(d)) == 1){
    fprintf(out, "1 numeric character: %s\n", d);
  }
  else{
    fprintf(out, "%d numeric characters: %s\n", l, d);
  }

  // print punctuation report
  if( (l=strlen(p)) == 1){
    fprintf(out, "1 punctuation character: %s\n", p);
  }
  else{
    fprintf(out, "%d punctuation characters: %s\n", l, p);
  } 

  // print whitespace report
  if( (l=strlen(w)) == 1){
    fprintf(out, "1 whitespace character: %s\n", w);
  }
  else{
    fprintf(out, "%d whitespace characters: %s\n", l, w);
  }
  
}
