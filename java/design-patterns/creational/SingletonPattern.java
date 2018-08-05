/*
Ensure that only one instance of a class is created and Provide a global access point to the object.

only one instance for a class
Example: window manager, file system, print spooler
used for centralized management of internal or external resources
provide a global point of access to themselves

one class which is responsible to instantiate itself

The implementation involves 
  a static member in the "Singleton" class,
  a private constructor,
  a static public method that returns a reference to the static member.

The constructor should not be accessible from the outside of the class to ensure the only way of instantiating the class would be only through the getInstance method.

Examples:
  Logger class
  Configuration Classes
  Accesing resources in shared mode

In java, if a Singleton class implements the java.io.Serializable interface, when the singleton is serialized and then deserialized more than once, there will be multiple instances of the Singleton class created.
In order to avoid this the readResolve method should be implemented.
See Serializable () and readResolve Method () in javadocs.

A special care should be taken in multi-threading environments when multiple threads must access the same resources through the same singleton object.

*/

class Singleton {
  private int count;
  private Singleton() {    
    System.out.println("Initializing instance");
  }

  private static Singleton instance;

  static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }

    return instance;
  }

  int getCount() {
    return this.count;
  }

  void setCount(int count) {
    this.count = count;
  }

}


public class SingletonPattern {
  public static void main(String[] args) {
    System.out.println(Singleton.getInstance().getCount());
    Singleton.getInstance().setCount(20);
    System.out.println(Singleton.getInstance().getCount());
  }
}