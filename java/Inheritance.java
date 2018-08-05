// protected means: same package or by inheritance
class Base {
  private int num;

  Base(int num) {
    this.num = num;
  }

  protected int getNum() {
    return num;
  }
}

class Derived extends Base {
  private String name;

  Derived(int num, String name) {
    super(num);
    this.name = name;
  }

  int getNumber() {
    return getNum();
  }

  String getName() {
    return name;
  }
}

public class Inheritance {
  public static void main(String[] args) {
    Derived obj = new Derived(1, "name");
    System.out.println(obj.getNumber() + ":" + obj.getName());
  }
}