//-----------------------------------------------------------------------------
// HelloUser2.java
// Prints greeting to stdout, then prints time.
//-----------------------------------------------------------------------------
class HelloUser2{
   public static void main( String[] args ){
      String userName = System.getProperty("user.name");
      long time = System.currentTimeMillis();     

      System.out.println("Hello "+userName+"! Welcome to My First Program! ");
      System.out.printf("Time now is: %tc.%n", time);
   }
}
