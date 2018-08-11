/*
Intent
- encapsulate a request in an object
- allows the parameterization of clients with different requests
- allows saving the requests in a queue

The Client asks for a command to be executed.
The Invoker takes the command, encapsulates it and places it in a queue
ConcreteCommand that is in charge of the requested command, sends its result to the Receiver.

meal order at a restaurant
The waiter (Invoker) takes the order from the customer on his pad.
The order is then queued for the order cook and gets to the cook (Receiver) where it is processed.

Another usage for the command design pattern is to run commands asynchronous in background of an application.
In this case the invoker is running in the main thread and sends the requests to the receiver which is running in a separate thread.
The invoker will keep a queue of commands to be run and will send them to the receiver while it finishes running them.

*/
interface Order {
    public abstract void execute ( );
}

class StockTrade {
    public void buy() {
        System.out.println("You want to buy stocks");
    }
    public void sell() {
        System.out.println("You want to sell stocks ");
    }
}

class Agent {  // invoker

    public Agent() {
    }
    
    void placeOrder(Order order) {
        order.execute();
    }    
}

class BuyStockOrder implements Order {
    private StockTrade stock;
    public BuyStockOrder (StockTrade st) {
        stock = st;
    }
    public void execute( ) {
        stock.buy();
    }
}

class SellStockOrder implements Order { 
    private StockTrade stock;
    public SellStockOrder (StockTrade st) {
        stock = st;
    }
    public void execute( ) {
        stock.sell();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        StockTrade stock = new StockTrade();
        BuyStockOrder bsc = new BuyStockOrder(stock);
        SellStockOrder ssc = new SellStockOrder(stock);
        Agent agent = new Agent();

        agent.placeOrder(bsc);
        agent.placeOrder(ssc);
    }
}