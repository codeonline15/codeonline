/*

Intent
access the elements of an aggregate object sequentially without exposing its underlying representation. 

modify the collection implementation without making any changes outside of collection. 
support multiple simultaneous traversals of a collection.
provide a uniform interface for traversing different collection.

It enables you to create a general purpose GUI component that will be able to iterate through any collection of the application.

Implementation
IIterator - This interface represent the AbstractIterator, defining the iterator
ObjIterator - This is the implementation of Iterator(implements the IIterator interface)
IContainer - This is an interface defining the Agregate
ObjCollection - An implementation of the collection

when creating a multithreading iterator, that allows insertions and deletions without affection transversal. 
Then the blocks which are changing or accessing resources changed by another thread have to be synchronized. 

*/

interface Iterator {
   public boolean hasNext();
   public Object next();
}

interface Container {
   public Iterator getIterator();
}

class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }   
   }
}


public class IteratorPattern {
  
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      }   
   }
}