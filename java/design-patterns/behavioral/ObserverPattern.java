/*

Intent
Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

Implementation

Observable - interface or abstract class defining the operations for attaching and de-attaching observers to the client
ConcreteObservable - concrete Observable class. It maintain the state of the object and when a change in the state occurs it notifies the attached Observers.
Observer - interface or abstract class defining the operations to be used to notify this object.
ConcreteObserverA, ConcreteObserver2 - concrete Observer implementations.


the main framework instantiate the ConcreteObservable object. 
Then it instantiate and attaches the concrete observers to it using the methods defined in the Observable interface. 
Each time the state of the subject it's changing it notifies all the attached Observers using the methods defined in the Observer interface. 
When a new Observer is added to the application, all we need to do is to instantiate it in the main framework and to add attach it to the Observable object. 

Example:
Model View Controller Pattern
  View - observer, model - observable object.

sometimes observers need to observe diff type of subjects.. in that case send obj also to update..

*/

import java.util.ArrayList;
import java.util.List;

class Subject {                     // subject whose state changes.. maintains list of observers.. and notifies all observers
  
   private List<Observer> observers = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);    
   }

   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   }  
}

abstract class Observer {
   protected Subject subject;
   public abstract void update();
}


class BinaryObserver extends Observer{  //concreate observers which will handle the request when it is notified..
                                                    // will maintain the subject also..
   public BinaryObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
   }
}


class OctalObserver extends Observer{

   public OctalObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
     System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) ); 
   }
}

class HexaObserver extends Observer{

   public HexaObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() ); 
   }
}

public class ObserverPattern {
  public static void main(String[] args) {
    Subject subject = new Subject();

    new HexaObserver(subject);
    new OctalObserver(subject);
    new BinaryObserver(subject);

    System.out.println("First state change: 15"); 
    subject.setState(15);
    System.out.println("Second state change: 10");  
    subject.setState(10);
  }
}