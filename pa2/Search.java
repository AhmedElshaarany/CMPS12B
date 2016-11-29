//----------------------------------------------------------------------------
// Recuriosn.java
//----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12B
// Assignment:  pa2
//-----------------------------------------------------------------------------
// Description:
// This class contains the implement the Binary Search and Merge Sort algorithms.
// These methods are adapted to operate on String arrays rather than int arrays.
// Each line of the input file for this project will contain a single word, i.e.
// a string containing no spaces or tabs. This program will determine whether
// or not the target word is amongst the words in the input file, print a message
// to stdout stating whether or not the target was found, and states the line
// onwhich the target was found, if it is found. I'm assuming that this program
// will be tested only on properly formatted input files.
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;
class Search{
    
    // This function will return the number of lines in a file pointed to by in
    public static int countLines(Scanner in){
        int numLines = 0;
        
        // read lines from in and counts number of lines
        while( in.hasNextLine() ){
            numLines++;
            in.nextLine();
        }
    
        return numLines;
        
    }
    
    // This function will store every word in the file in an array position
    public static void scanStore(Scanner in, String[] word, int[] lineNum){
        
        for(int i=0; in.hasNextLine(); i++){
            word[i] = in.nextLine();
            lineNum[i] = i+1;
        }
        
    }
    
    // This function will sort word lexicographically using mergeSort technique and also sort the lineNumber accordingly
    public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
        int q = 0;
        if(p < r){
            q = (p + r)/2;
            
            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q+1, r);
            merge(word, lineNumber, p, q, r);
        }
    }
    
    
    // This function merges sorted subarrays word[p..q] and word[q+1..r] and sorts lineNumber accordingly
    public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
        
        int n1 = q-p+1; //length of Left array
        int n2 = r - q; // length of Right array
        String[] L = new String[n1];
        String[] R = new String[n2];
        int[] leftLineNum = new int[n1];
        int[] rightLineNum = new int[n2];
        int i,j,k;
        
        for (i=0; i<n1; i++){
            L[i] = word[p+i];
            leftLineNum[i] = lineNumber[p+i];
        }
        
        for(j=0; j<n2; j++){
            R[j] = word[q+1+j];
            //System.out.println("j="+j+" R[j]="+R[j]);
            rightLineNum[j] = lineNumber[q+1+j];
        }
        
        i=0; j=0;
        for(k=p; k<=r; k++){
            if(i<n1 && j<n2){
                if(L[i].compareTo(R[j]) < 0){
                    word[k] = L[i];
                    lineNumber[k] = leftLineNum[i];
                    i++;
                }
                else{
                    word[k] = R[j];
                    lineNumber[k] = rightLineNum[j];
                    j++;
                }
            }
            else if(i<n1){
                word[k] = L[i];
                lineNumber[k] = leftLineNum[i];
                i++;
            }
            else{
                word[k] = R[j];
                lineNumber[k] = rightLineNum[j];
                j++;
            }
        }
    }
    
    // This function prints the word along with the lineNumber
    public static void printWord(String[] word, int[] lineNum){
        
        for(int i=0; i<word.length; i++){
            System.out.println("word["+i+"]="+word[i]+"   location="+lineNum[i]);
        }
        
    }
    
    // This function applies the Binary search alogrithm to find a target word in the word array
    public static int binarySearch(String[] word, String target, int p, int r){
        
        int q = 0;
        if(p>r){
            return -1;
        }
        else{
            q = (p+r)/2;
            if(word[q].compareTo(target)==0){
                return q;
            }
            else if(word[q].compareTo(target)<0){
                return binarySearch(word, target, q+1, r);
            }
            else{
                return binarySearch(word, target, p, q-1);
            }
        }
        
    }
    
    // This function searches for the target words in args in the word array and prints out if they are found or not
    public static void searchAndPrint(String[] args, String[] word, int[] lineNumber){
        int index = 0;
        
        // search for all target words stored in args in word array
        for(int i=1; i<args.length; i++){
            // get index
            index = binarySearch(word, args[i], 0, word.length-1);
            
            // if target not found
            if(index == -1){
                System.out.println(args[i]+" not found");
            }
            // if target found
            else{
                System.out.println(args[i]+" found on line "+lineNumber[index]);
            }
        }
    }

    
    public static void main(String[] args) throws IOException{
        int numLines = 0;
        
        // check number of command line arguments is at least 2
        if(args.length < 2){
            System.out.println("Usage: Search file target1 [target2 target3 ..]");
            System.exit(1);
        }
        
        
        // open files
        Scanner in = new Scanner(new File(args[0]));
        // count the number of lines
        numLines = countLines(in);
        // create a String array of length numLines
        String[] word = new String[numLines];
        int[]    lineNumber =     new int[numLines];
        // close file
        in.close();
        
        
        // open file again
        in = new Scanner(new File(args[0]));
        // Scan the file again and store in word and fill lineNum array with order (1,2,3,...)
        scanStore(in, word, lineNumber);
        // close file
        in.close();
        
        
        // sort the word lexicographically
        mergeSort(word, lineNumber, 0, word.length-1);
        // search for all target words and print if found or not
        searchAndPrint(args, word, lineNumber);
        

        
        
    }
    
    
}