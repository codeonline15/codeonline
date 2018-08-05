/*

intent:
create objects without exposing the instantiation logic to the client.
refer to the newly created object through a common interface

implementation:
client needs obj
asks factory obj for new obj by giving the kind of obj it needs
factory instantiates new obj and returns to client

Advantage:
new classes can be added without much changes required in the client

If you have many objects of the same base type and you manipulate them mostly casted to abstract types, then you need a factory

if the methods called in a class change a lot based on the obj class, dont use?

Factory pattern should be used when:
  a framework delegate the creation of objects derived from a common superclass to the factory
  we need flexibility in adding new types of objects that must be created by the class

Example:
  parsers

*/

abstract class Shape {
  public abstract Shape createObject();
  abstract int sides();
}

class Circle extends Shape {
  public Circle createObject() {
    return new Circle();
  }

  int sides() {
    return 0;
  }
}

class Triangle extends Shape {
  public Triangle createObject() {
    return new Triangle();
  }

  int sides() {
    return 3;
  }
}

class ShapeFactory {

  ShapeFactory() {
    System.out.println("Creating ShapeFactory object");
  }

  public Shape getShapeObject(String type){
    switch (type) {
      case "circle":
        return new Circle();
      case "triangle":
        return new Triangle();
    }
    return null;
  }
}

class FactoryMethodPattern {
  public static void main(String[] args) {
    ShapeFactory sfObj = new ShapeFactory();

    Shape obj1 = sfObj.getShapeObject("circle");
    Shape obj2 = sfObj.getShapeObject("triangle");

    System.out.println(obj1.sides() + "::" + obj1.getClass());
    System.out.println(obj2.sides() + "::" + obj2.getClass());
  }
}