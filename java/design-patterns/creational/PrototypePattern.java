/*

If the cost of creating a new object is large and creation is resource intensive, we clone the object.
It allows an object to create customized objects without knowing their class or any details of how to create them.

Intent
  specifying the kind of objects to create using a prototypical instance
  creating new objects by copying this prototype

Implementation:
  Client  - clone of client
  Prototype -  interface
  ConcretePrototype - implements cloning operation  -> returns Object

*/

import java.util.*;

interface Prototype extends Cloneable {
  public abstract Object clone ();
}

class ConcretePrototype implements Prototype {
  int number;

  ConcretePrototype(int num) {
    this.number = num;
  }

  void setNum(int num) {
    this.number = num;
  }

  int getNum() {
    return number;
  }

  public Object clone() {
    try {
      return super.clone();  
    }
    catch(Exception e) {
      System.out.println(e);
      return e;
    }
    
  }
}

public class PrototypePattern {
  public static void main(String arg[]) {
    ConcretePrototype obj1 = new ConcretePrototype(22);
    ConcretePrototype obj2 = (ConcretePrototype)obj1.clone();

    System.out.println(obj2.getClass() + ":" + obj1.getClass() + "::" + obj1.clone().getClass());
    System.out.println(obj1.getNum() + ":" + obj2.getNum());
    obj2.setNum(33);
    System.out.println(obj1.getNum() + ":" + obj2.getNum());
  }

}