//-----------------------------------------------------------------------------
// DictionaryTest.java
//-----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12M
// Assignment:  lab7
//-----------------------------------------------------------------------------
// Description:
// This file tests the Dictionary ADT
//----------------------------------------------------------------------------

public class DictionaryTest{

    public static void main(String[] args){

      String v;
      Dictionary A = new Dictionary();

      // ------------------ Testing isEmpty() and size() methods ---------------------//
      /*
      System.out.println(A.isEmpty());
      System.out.println(A.size());
      */

      // ------------------ Testing insert(), lookup() and toString() methods ---------------------//
      A.insert("1","a");
      A.insert("2","b");
      A.insert("3","c");
      A.insert("4","d");
      A.insert("5","e");
      A.insert("6","f");
      A.insert("7","g");
      System.out.println(A);

      v = A.lookup("1");
      System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      v = A.lookup("3");
      System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      v = A.lookup("7");
      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      v = A.lookup("8");
      System.out.println("key=8 "+(v==null?"not found":("value="+v)));
      System.out.println();

      //A.insert("2","f");  // causes KeyCollisionException

      // ------------------ Testing insert(), lookup(), toString(), and delete()  methods ---------------------//

      A.delete("1");
      A.delete("3");
      A.delete("7");
      System.out.println(A);
      
      //A.delete("8");  // causes KeyNotFoundException
      
      
      // ------------------ Testing the rest of the DictionaryClient file ---------------------//

      System.out.println(A.isEmpty());
      System.out.println(A.size());
      A.makeEmpty();
      System.out.println(A.isEmpty());
      System.out.println(A);
      
    }
    

}
