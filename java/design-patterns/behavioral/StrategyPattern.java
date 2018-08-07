/*

Intent
Define a family of algorithms,
encapsulate each one,
make them interchangeable.
Strategy lets the algorithm vary independently from clients that use it

Implementation:
Strategy - defines an interface common to all supported algorithms.
Context uses this interface to call the algorithm defined by a ConcreteStrategy.

ConcreteStrategy - each concrete strategy implements an algorithm.

Context
contains a reference to a strategy object.
may define an interface that lets strategy accessing its data.
The Context objects contains a reference to the ConcreteStrategy that should be used. 
When an operation is required then the algorithm is run from the strategy object. 
The Context is not aware of the strategy implementation. 
If necessary, addition objects can be defined to pass data from context object to strategy. 

The context object receives requests from the client and delegates them to the strategy object. 
Usually the ConcreteStartegy is created by the client and passed to the context. 
From this point the clients interacts only with the context.


Example:

Robots Application


In the classic implementation of the pattern the client should be aware of the strategy concrete classes. 
In order to decouple the client class from strategy classes is possible to use a factory class inside the context object to create the strategy object to be used. 
By doing so the client has only to send a parameter to the context asking to use a specific algorithm, 
being totally decoupled of strategy classes.


*/

interface IBehaviour {                        // Strategy
  public int moveCommand();
}

class AgressiveBehaviour implements IBehaviour{       // Concrete Strategies
  public int moveCommand()
  {
    System.out.println("\tAgressive Behaviour: if find another robot attack it");
    return 1;
  }
}

class DefensiveBehaviour implements IBehaviour{
  public int moveCommand()
  {
    System.out.println("\tDefensive Behaviour: if find another robot run from it");
    return -1;
  }
}

class NormalBehaviour implements IBehaviour{
  public int moveCommand()
  {
    System.out.println("\tNormal Behaviour: if find another robot ignore it");
    return 0;
  }
}

class Robot {                                        // Context
  IBehaviour behaviour;
  String name;

  public Robot(String name) {
    this.name = name;
  }

  public void setBehaviour(IBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  public IBehaviour getBehaviour() {
    return behaviour;
  }

  public void move() {
    System.out.println(this.name);
    int command = behaviour.moveCommand();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

public class StrategyPattern {      // client setting the beh based on which the strategy is adopted

  public static void main(String[] args) {

    Robot r1 = new Robot("Big Robot");
    Robot r2 = new Robot("George v.2.1");
    Robot r3 = new Robot("R2");

    r1.setBehaviour(new AgressiveBehaviour());
    r2.setBehaviour(new DefensiveBehaviour());
    r3.setBehaviour(new NormalBehaviour());

    r1.move();
    r2.move();
    r3.move();

    r1.setBehaviour(new DefensiveBehaviour());
    r2.setBehaviour(new AgressiveBehaviour());

    r1.move();
    r2.move();
    r3.move();
  }
}