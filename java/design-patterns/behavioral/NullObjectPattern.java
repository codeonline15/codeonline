/*

Intent
Provide an object as a surrogate for the lack of an object of a given type.
The Null Object Pattern provides intelligent do nothing behavior, hiding the details from its collaborators.


Implementation
The participants classes in this pattern are:
AbstractClass
RealClass - a real implementation with some actions
NullClass - a implementation which do nothing of the abstract class, in order to provide a non-null object to the client.
Client - the client gets an implementation of the abstract class and uses it. 
it can be null object or an real object since both of them are used in the same way.

Example: Log System
Removing old functionality
The Null Object can be used to remove old functionality by replacing it with null objects. 
The big advantage is that the existing code doesn't need to be touched.

used to avoid special if blocks for do nothing code by putting the “do nothing” code

*/

abstract class AbstractCustomer {
   protected String name;
   public abstract boolean isNil();
   public abstract String getName();
}

class RealCustomer extends AbstractCustomer {

   public RealCustomer(String name) {
      this.name = name;   
   }
   
   @Override
   public String getName() {
      return name;
   }
   
   @Override
   public boolean isNil() {
      return false;
   }
}

class NullCustomer extends AbstractCustomer {

   @Override
   public String getName() {
      return "Not Available in Customer Database";
   }

   @Override
   public boolean isNil() {
      return true;
   }
}


class CustomerFactory {
  
   public static final String[] names = {"Rob", "Joe", "Julie"};

   public static AbstractCustomer getCustomer(String name) {
   
      for (int i = 0; i < names.length; i++) {
         if (names[i].equalsIgnoreCase(name)) {
            return new RealCustomer(name);
         }
      }
      return new NullCustomer();
   }
}

public class NullObjectPattern {
   public static void main(String[] args) {

      AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
      AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
      AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
      AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

      System.out.println("Customers");
      System.out.println(customer1.getName());
      System.out.println(customer2.getName());
      System.out.println(customer3.getName());
      System.out.println(customer4.getName());
   }
}