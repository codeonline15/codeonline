/*

Intent:
use sharing to support a large number of objects that have part of their internal state in common where the other part of state can vary.


Flyweight
ConcreteFlyweight - sharable object
has its own state and must be able to manipulate state that is extrinsic
Client
Flyweight factory: returns the shared objects, used by client
need to keep a map of Objects in the factory that should not be accessible by client application.

Example:
War game
  graphical representation -> intrinsic state,
  location and health -> extrinsic


saves memory by sharing flyweight objects among clients
used when we need to create many obj of a class

The number of Objects to be created by application should be huge.
The object creation is heavy on memory and it can be time consuming too.
The object properties can be divided into intrinsic and extrinsic properties,
extrinsic properties of an Object should be defined by the client program.

NC
*/


import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

interface Shape {
  public void draw(Graphics g, int x, int y, int width, int height, Color color);
}

class Line implements Shape {

  public Line(){
    System.out.println("Creating Line object");
    //adding time delay
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void draw(Graphics line, int x1, int y1, int x2, int y2, Color color) {
    line.setColor(color);
    line.drawLine(x1, y1, x2, y2);
  }

}

class Oval implements Shape {
  

  private boolean fill;           //intrinsic property
  
  public Oval(boolean f){
    this.fill = f;
    System.out.println("Creating Oval object with fill=" + f);
    //adding time delay
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void draw(Graphics circle, int x, int y, int width, int height, Color color) {
    circle.setColor(color);
    circle.drawOval(x, y, width, height);
    if(fill){
      circle.fillOval(x, y, width, height);
    }
  }

}

class ShapeFactory {

  public static enum ShapeType {
    OVAL_FILL, OVAL_NOFILL, LINE;
  }
  private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();

  public static Shape getShape(ShapeType type) {
    Shape shapeImpl = shapes.get(type);

    if (shapeImpl == null) {
      if (type.equals(ShapeType.OVAL_FILL)) {
        shapeImpl = new Oval(true);
      } else if (type.equals(ShapeType.OVAL_NOFILL)) {
        shapeImpl = new Oval(false);
      } else if (type.equals(ShapeType.LINE)) {
        shapeImpl = new Line();
      }
      shapes.put(type, shapeImpl);
    }
    return shapeImpl;
  }
  

}

class FlyweightPattern extends JFrame{

  private static final long serialVersionUID = -1350200437285282550L;
  private final int WIDTH;
  private final int HEIGHT;

  private static final ShapeFactory.ShapeType shapes[] = { ShapeFactory.ShapeType.LINE, ShapeFactory.ShapeType.OVAL_FILL, ShapeFactory.ShapeType.OVAL_NOFILL };
  private static final Color colors[] = { Color.RED, Color.GREEN, Color.YELLOW };
  
  public FlyweightPattern(int width, int height){
    this.WIDTH=width;
    this.HEIGHT=height;
    Container contentPane = getContentPane();

    JButton startButton = new JButton("Draw");
    final JPanel panel = new JPanel();

    contentPane.add(panel, BorderLayout.CENTER);
    contentPane.add(startButton, BorderLayout.SOUTH);
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        Graphics g = panel.getGraphics();
        for (int i = 0; i < 20; ++i) {
          Shape shape = ShapeFactory.getShape(getRandomShape());
          shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
              getRandomHeight(), getRandomColor());
        }
      }
    });
  }
  
  private ShapeFactory.ShapeType getRandomShape() {
    return shapes[(int) (Math.random() * shapes.length)];
  }

  private int getRandomX() {
    return (int) (Math.random() * WIDTH);
  }

  private int getRandomY() {
    return (int) (Math.random() * HEIGHT);
  }

  private int getRandomWidth() {
    return (int) (Math.random() * (WIDTH / 10));
  }

  private int getRandomHeight() {
    return (int) (Math.random() * (HEIGHT / 10));
  }

  private Color getRandomColor() {
    return colors[(int) (Math.random() * colors.length)];
  }

  public static void main(String[] args) {
    DrawingClient drawing = new DrawingClient(500,600);
  }
}



/*
NC
*/