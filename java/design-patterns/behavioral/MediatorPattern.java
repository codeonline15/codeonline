/*

Intent
Define an object that encapsulates how a set of objects interact. 
Mediator promotes loose coupling by keeping objects from referring to each other explicitly,
it lets you vary their interaction independently.

Implementation

Mediator - defines an interface for communicating with Colleague objects.
ConcreteMediator - knows the colleague classes and keep a reference to the colleague objects.
- implements the communication and transfer the messages between the colleague classes
Colleague classes - keep a reference to its Mediator object
- communicates with the Mediator whenever it would have otherwise communicated with another Colleague.

+Decoupled colleagues
+Simple Object protocols
-Complexity

mediator pattern is used to takes the role of a hub or router and facilitates the communication between many classes

*/

import java.util.Date;

class ChatRoom {
   public static void showMessage(User user, String message){
      System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
   }
}

class User {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public User(String name){
      this.name  = name;
   }

   public void sendMessage(String message){
      ChatRoom.showMessage(this, message);
   }
}

public class MediatorPattern {
   public static void main(String[] args) {
      User robert = new User("Robert");
      User john = new User("John");

      robert.sendMessage("Hi! John!");
      john.sendMessage("Hello! Robert!");
   }
}

