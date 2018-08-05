class myClass {
  private int count = 0;
  private String name = "";

  myClass(int count, String name) {
    this.count = count;
    this.name = name;
  }

  private int count() {
    System.out.println("in private count..");
    return count;
  }

  protected String name() {
    System.out.println("in protected name..");
    return name;
  }

  int getCount() {
    System.out.println("getting count...");
    return count();
  }

  String getName() {
    return name();
  }
}

public class AccessSpecifiers {
  public static void main(String[] args) {
    myClass obj = new myClass(1, "name");
    System.out.println(obj.getCount() + ":" + obj.getName());
  }
}