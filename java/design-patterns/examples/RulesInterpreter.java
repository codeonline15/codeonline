import java.util.*;

abstract class Expression {
  abstract public boolean interpret(String str); 
}

class TerminalExpression extends Expression {
  
    private String literal = null;

    public TerminalExpression(String str) { 
        literal = str; 
    }

    public boolean interpret(String str) { 
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) { 
            String test = st.nextToken();
            if (test.equals(literal)) {
                return true;
            }
        }
        return false;
    }

}

class OrExpression extends Expression{
    private Expression expression1 = null;
    private Expression expression2 = null;

    public OrExpression(Expression expression1, Expression expression2) { 
        this.expression1 = expression1;
        this.expression2 = expression2; 
    }

    public boolean interpret(String str) { 
        return expression1.interpret(str) || expression2.interpret(str);
    } 
}

class AndExpression extends Expression{
  
    private Expression expression1 = null;
    private Expression expression2 = null;

    public AndExpression(Expression expression1, Expression expression2) { 
      this.expression1 = expression1;
      this.expression2 = expression2;
    }

    public boolean interpret(String str) { 
        return expression1.interpret(str) && expression2.interpret(str);
    } 
}

class RulesInterpreter {

  /**
   * this method builds the interpreter tree
   * It defines the rule "Owen and (John or (Henry or Mary))"
   * @return
   */
    static Expression buildInterpreterTree() 
    {
        // Literal
        Expression terminal1 = new TerminalExpression("John");
        Expression terminal2 = new TerminalExpression("Henry");
        Expression terminal3 = new TerminalExpression("Mary");
        Expression terminal4 = new TerminalExpression("Owen");

        // Henry or Mary
        Expression alternation1 = new OrExpression(terminal2, terminal3); 

        // John or (Henry or Mary)
        Expression alternation2 = new OrExpression(terminal1, alternation1);

        // Owen and (John or (Henry or Mary))
        return new AndExpression(terminal4, alternation2);
    }

  
  /**
   * main method - build the interpreter
   *  and then interpret a specific sequence 
   * @param args
   */
  public static void main(String[] args) {
    
        String context = "Mary Owen";

        Expression define = buildInterpreterTree();

        System.out.println(context + " is " + define.interpret(context));
  }
}