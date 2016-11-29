//-----------------------------------------------------------------------------
// QueueTest.java
//-----------------------------------------------------------------------------
//----------------------------------------------------------------------------                           
// Name:        Ahmed Elshaarany                                                                         
// CruzID:      aelshaar                                                                                 
// Class:       12B                                                                                      
// Assignment:  pa4                                                                                      
//-----------------------------------------------------------------------------                          
// Description:                                                                                          
// This class contains a main mehtod that tests the QueueADT                              
//----------------------------------------------------------------------------- 

import java.io.*;
import java.util.Scanner;

public class QueueTest{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

   public static void main(String[] args) throws IOException{

       // ------------ Testing the QueueADT ---------------------------- //

       // System.out.println("Hello World!");   
       // Queue Q = new Queue();
       

       // ------------- Testing isEmpty() and length() ----------------- //
       
       /*
       System.out.println("Is Queue Empty? "+Q.isEmpty());
       System.out.println("Queue Length = "+Q.length());
       */


       // ------------- Testing enqueue() and toString() ----------------- //

       /*
       Job j1 = new Job(3, 4);
       Job j2 = new Job(1, 1);
       Q.enqueue(j1);
       Q.enqueue(j2);
       System.out.println("Is Queue Empty? "+Q.isEmpty());                                                               
       System.out.println("Queue Length = "+Q.length());
       System.out.println("Queue elements: ");
       System.out.println(Q.toString());
       */


       // ------------- Testing enqueue(), toString(), peek(), and dequeue() ----------------- //                                
       /*
       Job j1 = new Job(3, 4);
       Job j2 = new Job(1, 1);
       Q.enqueue(j1);
       Q.enqueue(j2);
       System.out.println("Is Queue Empty? "+Q.isEmpty());
       System.out.println("Queue Length = "+Q.length());
       System.out.println("Queue elements: "+ Q.toString());
       System.out.println("First element in current Q: "+Q.peek().toString());
       Q.dequeue();
       System.out.println("After first dq: "+Q.toString());
       System.out.println("First element in current Q: "+Q.peek().toString());
       Q.dequeue();
       System.out.println("After second dq: "+Q.toString());
       // Q.dequeue(); // test to see if exception is working dequeuing an Empty Q
       // Q.peek().toString(); // test to see if exception is wokring when peeking at Empty Q
       System.out.println("Is Queue Empty? "+Q.isEmpty());
       */


       // ------------- Testing all Queue ADT methods ----------------- //                        

       Queue Q = new Queue();       
       Job j1 = new Job(3, 4);                                                                                           
       Job j2 = new Job(1, 1);                                                                                           
       Job j3 = new Job(2, 12);
       Q.enqueue(j1);                                                                                                     
       Q.enqueue(j2);          
       Q.enqueue(j3);
       System.out.println("Is Queue Empty? "+Q.isEmpty());                                                                
       System.out.println("Queue Length = "+Q.length());                                                                  
       System.out.println("Queue elements: "+ Q.toString());                  
       System.out.println("First element in current Q: "+Q.peek().toString());                                            
       Q.dequeue();                                                                                                       
       System.out.println("After first dq: "+Q.toString());                                                             
       Q.dequeueAll();
       System.out.println("Is Queue Empty? "+Q.isEmpty());     
       // Q.dequeueAll(); // test to see if exception is working dequeuingAll() on an Empty Q 
       System.out.println("Queue Length = "+Q.length());
       
   }    
}





