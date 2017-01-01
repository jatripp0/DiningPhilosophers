package edu.frostburg.cosc460.TrippJohnathan;

/**
 * Interface for the Dining Philosopher Server
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public interface DiningServer {
    
    /* Called by philosopher that wishes to eat */
    public void takeChopsticks(int philNumber) throws InterruptedException;
    
    /* Called by philosopher when it has finished eating */
    public void returnChopsticks(int philNumber) throws InterruptedException;
}
