/*

A template method defines an algorithm in a base class using abstract operations that subclasses override to provide concrete behavior.


Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
lets subclasses redefine certain steps of an algorithm without letting them to change the algorithm's structure.



*/


abstract class Trip {
  public final void performTrip(){
           doComingTransport();
           doDayA();
           doDayB();
           doDayC();
           doReturningTransport();
  }
  public abstract void doComingTransport();
  public abstract void doDayA();
  public abstract void doDayB();
  public abstract void doDayC();
  public abstract void doReturningTransport();
}

class PackageA extends Trip {
  public void doComingTransport() {
           System.out.println("The turists are comming by air ...");
  }
  public void doDayA() {
           System.out.println("The turists are visiting the aquarium ...");
  }
  public void doDayB() {
           System.out.println("The turists are going to the beach ...");
  }
  public void doDayC() {
           System.out.println("The turists are going to mountains ...");
  }
  public void doReturningTransport() {
           System.out.println("The turists are going home by air ...");
  }
}
class PackageB extends Trip {
  public void doComingTransport() {
           System.out.println("The turists are comming by train ...");
  }
  public void doDayA() {
           System.out.println("The turists are visiting the mountain ...");
  }
  public void doDayB() {
           System.out.println("The turists are going to the beach ...");
  }
  public void doDayC() {
           System.out.println("The turists are going to zoo ...");
  }
  public void doReturningTransport() {
           System.out.println("The turists are going home by train ...");
  }
}

public class TemplateMethodPattern {
  public static void main(String[] args) {
    Trip pkgA = new PackageA();
    Trip pkgB = new PackageB();

    pkgA.performTrip();
    pkgB.performTrip();
  }
}