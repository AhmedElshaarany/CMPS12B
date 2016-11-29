//-----------------------------------------------------------------------------
// FileReverse.java
//-----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12M
// Assignment:  lab2
//-----------------------------------------------------------------------------
// Description:
// This program takes two command line arguments giving the names of the input
// and output files respectively. The program Your program will read each line
// of input, parse the tokens, then print each token backwards to the output
// file on a line by itself
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;
class FileReverse{
    
    
    // This function will return a String that is the reversal of the first n characters of s
    public static String stringReverse(String s, int n){
        String sFinal = "";
        // if n==0 do nothing
        if( n > 0 ){
            sFinal += s.charAt(n-1) + stringReverse(s.substring(0,n-1), n-1); // add the last character in s to sFinal recursively
        }
        return sFinal;
    }
    
    
    
    public static void main(String[] args) throws IOException{
        int lineNumber = 0;
        
        // check number of command line arguments is at least 2
        if(args.length < 2){
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }
        
        // open files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        
        // read lines from in, extract and print tokens from each line
        while( in.hasNextLine() ){
            lineNumber++;
            // trim leading and trailing spaces, then add one trailing space so
            // split works on blank lines
            String line = in.nextLine().trim() + " ";
            
            // split line around white space
            String[] token = line.split("\\s+");
            
            // print out each reversed token on a new line
            int n = token.length;
            for(int i=0; i<n; i++){
                out.println(stringReverse(token[i],token[i].length()));
            }
        }
        // close files
        in.close();
        out.close();
        
    }
    
    
}