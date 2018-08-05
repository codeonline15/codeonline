/*
to represent a part-whole hierarchy.

Intent
The intent of this pattern is to compose objects into tree structures to represent part-whole hierarchies.
Composite lets clients treat individual objects and compositions of objects uniformly.

Implementation
Component - Component is the abstraction for leafs and composites. It defines the interface that must be implemented by the objects in the composition. 
For example a file system resource defines move, copy, rename, and getSize methods for files and folders.
Leaf - Leafs are objects that have no children. They implement services described by the Component interface. 
For example a file object implements move, copy, rename, as well as getSize methods which are related to the Component interface.
Composite - A Composite stores child components in addition to implementing methods defined by the component interface. 
Composites implement methods defined in the Component interface by delegating to child components. 
In addition composites provide additional methods for adding, removing, as well as getting components.
Client - The client manipulates objects in the hierarchy using the component interface.

A client has a reference to a tree data structure and needs to perform operations on all nodes independent of the fact that a node might be a branch or a leaf. 
The client simply obtains reference to the required node using the component interface, and deals with the node using this interface

The composite pattern applies when there is a part-whole hierarchy of objects and a client needs to deal with objects uniformly regardless of the fact that an object might be a leaf or a branch.

Example:
Graphical Drawing Editor
File System

composite pattern defines class hierarchies consisting of primitive objects and composite objects.

should be applied when group of objects should behave as single object.
*/

import java.util.ArrayList;
import java.util.List;

interface Shape {     // common methods fr leaves and composites
  public void draw(String fillColor);
}

class Triangle implements Shape {       // leaf object implementing common methods

  @Override
  public void draw(String fillColor) {
    System.out.println("Drawing Triangle with color " + fillColor);
  }
}

class Circle implements Shape {         // another leaf obj

  @Override
  public void draw(String fillColor) {
    System.out.println("Drawing Circle with color " + fillColor);
  }
}

class Drawing implements Shape {           // Composite obj

  //collection of Shapes
  private List<Shape> shapes = new ArrayList<Shape>();
  
  @Override
  public void draw(String fillColor) {
    for(Shape sh : shapes)
    {
      sh.draw(fillColor);
    }
  }
  
  public void add(Shape s) {
    this.shapes.add(s);
  }
  
  public void remove(Shape s) {
    shapes.remove(s);
  }
  
  public void clear() {
    System.out.println("Clearing all the shapes from drawing");
    this.shapes.clear();
  }
}

class CompositePattern {

  public static void main(String[] args) {
    Shape tri = new Triangle();
    Shape tri1 = new Triangle();
    Shape cir = new Circle();
    
    Drawing drawing = new Drawing();
    drawing.add(tri1);
    drawing.add(tri1);
    drawing.add(cir);
    
    drawing.draw("Red");
    
    drawing.clear();
    
    drawing.add(tri);
    drawing.add(cir);
    // drawing.draw("Green");
    cir.draw("Green");
  }

}

