/*

The intent of this pattern is to add additional responsibilities dynamically to an object.

Component - Interface.
ConcreteComponent - needs additional responsibilities.
Decorator - Maintains a reference to a Component object and defines an interface that conforms to Component's interface.
Concrete Decorators - Concrete Decorators extend the functionality of the component by adding state or adding behavior.

The decorator pattern applies when there is a need to dynamically add as well as remove responsibilities to a class, and when subclassing would be impossible due to the large number of subclasses that could result.

where? - gui toolkits
Decorator pattern is used a lot in Java IO classes, such as FileReader, BufferedReader etc.

Decoration adds functionality to objects at runtime which would make debugging system functionality harder.

*/

interface Car {
  public void assemble();
}

class BasicCar implements Car {       // concrete component

  @Override
  public void assemble() {
    System.out.println("Basic Car.");
  }

}

class CarDecorator implements Car {     // decorator 

  protected Car car;
  
  public CarDecorator(Car c){
    this.car = c;
  }
  
  @Override
  public void assemble() {
    this.car.assemble();
  }

}

class SportsCar extends CarDecorator {      // concrete decorator

  public SportsCar(Car c) {
    super(c);
  }

  @Override
  public void assemble(){
    super.assemble();
    System.out.println("Adding features of Sports Car.");
  }
}

class LuxuryCar extends CarDecorator {

  public LuxuryCar(Car c) {
    super(c);
  }
  
  @Override
  public void assemble(){
    super.assemble();
    System.out.println(" Adding features of Luxury Car.");
  }
}

public class DecoratorPattern {

  public static void main(String[] args) {
    Car sportsCar = new SportsCar(new BasicCar());
    sportsCar.assemble();
    
    Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
    sportsLuxuryCar.assemble();
  }
}