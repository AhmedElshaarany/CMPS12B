//----------------------------------------------------------------------------
// Recuriosn.java
//----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12B
// Assignment:  pa1
//-----------------------------------------------------------------------------
// Description:
// This class contains the five functions that the professor asked us to do on
// pa1. The first three functions provide a way to reverse an array, and the
// last two functions find the max and min of a subarray
//-----------------------------------------------------------------------------

class Recursion {

   
    // copy the leftmost n elements in X into the rightmost n elements in Y
    static void reverseArray1(int[] X, int n, int[] Y) {
     // if n==0 do nothing
     if( n > 0 ){
         reverseArray1(X, n-1, Y);      // copy leftmost n-1 elements in X into rightmost n-1 positions in Y
         Y[n-1] = X[X.length-n];        // store nth element from left in X in nth element from right in Y
     }
    }
    
    // copy the rightmost n elements in X into the leftmost n elements in Y
    static void reverseArray2(int[] X, int n, int[] Y) {
        // if n==0 do nothing
        if( n > 0 ){
            reverseArray2(X, n-1, Y);      // copy rightmost n-1 elements in X into leftmost n-1 positions in Y
            Y[Y.length-n] = X[n-1];        // store nth element from right in X in nth element from left in Y
        }
    }
 
    //  reverses the subarray X[i,..,j] consisting of those elements from index i to index j, inclusive
    static void reverseArray3(int[] X, int i, int j){
        // if i == j do nothing
        if( i < j ){
            swap(X, i, j);                 // swap the elements at positions i and j
            reverseArray3(X, i+1, j-1);    //  reverses the subarray X[i+1,..,j-1] consisting of those elements
                                           //  from index i+1 to index j-1, inclusive
        }
    }
    
    //  swap elements X[i] and X[j]
    private static void swap(int[] X, int i, int j){
        int temp = X[i];
        X[i] = X[j];
        X[j] = temp;
    }
    
    
    //  returns the index of the maximum element in the subarray X[p,..,r]
    static int maxArrayIndex(int[] X, int p, int r){
        int q;
        // if p > r do nothing
        if(p < r){
            q = (p+r)/2;
            
            int index1 = maxArrayIndex(X, p, q);
            int index2 = maxArrayIndex(X, q+1, r);
            
            return maxIndex(X, index1, index2);
        }
        // no comparison for one element then return index
        else /* p==q */ {
            return p;
        }
    }
    
    //  returns the index of the maximum of X[index1] and X[index2]
    private static int maxIndex(int[] X, int index1, int index2){
        // if int at index1 >= int at index2 return index1
        if(X[index1] >= X[index2]){
            return index1;
        }
        else{
            return index2;
        }
    }

    
    //  returns the index of the minimum element in the subarray X[p,..,r]
    static int minArrayIndex(int[] X, int p, int r){
        int q;
        // if p > r do nothing
        if(p < r){
            q = (p+r)/2;
            
            int index1 = minArrayIndex(X, p, q);
            int index2 = minArrayIndex(X, q+1, r);
            
            return minIndex(X, index1, index2);
        }
        // no comparison for one element then return index
        else /* p==q */ {
            return p;
        }
    }
    
    //  returns the index of the minimum of X[index1] and X[index2]
    private static int minIndex(int[] X, int index1, int index2){
        // if int at index1 >= int at index2 return index1
        if(X[index1] <= X[index2]){
            return index1;
        }
        else{
            return index2;
        }
    }
 
    
    
   public static void main(String[] args) {

      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
       
      for(int x: A) System.out.print(x+" ");
      System.out.println();
       
      System.out.println( "minIndex = " + minIndex );
      System.out.println( "maxIndex = " + maxIndex );
    
      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
       
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
       
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();
       

   }
}
