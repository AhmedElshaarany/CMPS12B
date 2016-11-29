//----------------------------------------------------------------------------
// DictionaryTest.java
//----------------------------------------------------------------------------
// Name:        Ahmed Elshaarany
// CruzID:      aelshaar
// Class:       12B
// Assignment:  pa3
//-----------------------------------------------------------------------------
// Description:
// This file serves as a test client for the Dictionary ADT while it is under
// construction. This file will define the class DictionaryTest, which need not
// contain any more than a main() method (although you may add other static
// methods at your discretion.) The design philosophy here is that an ADT should
// be thoroughly tested in isolation before it is used in any application.
//-----------------------------------------------------------------------------

public class DictionaryTest{


    public static void main(String[] args){
        
        Dictionary D = new Dictionary();
        
        // ---------------- Testing the isEmpty() method ----------------------- //
        
        /*
        System.out.println("Is dictionary empty? " + D.isEmpty());
        */
        
        
        
        
        // ---------------- Testing the size() method ----------------------- //
        
        /*
        System.out.println("List size = " + D.size());
        */
        
        
        
        // ---------------- Testing insert() and lookup() methods ----------------------- //

        /*
        String v;
        Dictionary A = new Dictionary();
        A.insert("1","a");
        A.insert("2","b");
        A.insert("3","c");
        A.insert("4","d");
        A.insert("5","e");
        A.insert("6","f");
        A.insert("7","g");
        
        
        v = A.lookup("1");
        System.out.println("key=1 "+(v==null?"not found":("value="+v)));
        v = A.lookup("3");
        System.out.println("key=3 "+(v==null?"not found":("value="+v)));
        v = A.lookup("7");
        System.out.println("key=7 "+(v==null?"not found":("value="+v)));
        v = A.lookup("8");
        System.out.println("key=8 "+(v==null?"not found":("value="+v)));
        System.out.println();
        
        // A.insert("2","f");  // causes DuplicateKeyException
        */
        
        
        
        // ---------------- Testing insert(), lookup(), delete(), and toString() ----------------------- //

        /* 
        String v;
        Dictionary A = new Dictionary();
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
        
        // A.insert("2","f");  // causes DuplicateKeyException
        
        A.delete("1");
        A.delete("3");
        A.delete("7");
        System.out.println(A);
        
        // A.delete("8");  // causes KeyNotFoundException
        */
        
        
        
        // ---------------- Testing DictionaryClient ----------------------- //
        
        /*
        String v;
        Dictionary A = new Dictionary();
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
        
        // A.insert("2","f");  // causes DuplicateKeyException
        
        A.delete("1");
        A.delete("3");
        A.delete("7");
        System.out.println(A);
        
        // A.delete("8");  // causes KeyNotFoundException
        
        System.out.println(A.isEmpty());
        System.out.println(A.size());
        A.makeEmpty();
        System.out.println(A.isEmpty());
        System.out.println(A);
        */ 
    }

}