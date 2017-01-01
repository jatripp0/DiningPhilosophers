package edu.frostburg.cosc460.TrippJohnathan;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to implement the philosophers of the Dining Philosophers problem
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public class Philosopher implements Runnable {
    
    private int id;
    public Condition condition;
    public ReentrantLock lock; //lock to reference the lock managed in the driver class
    private PhilosopherServer server; //reference object to the philosopher server
    public State state;

    /**
     * Public constructor for the Philosopher class
     * @param id the id of the philosopher
     * @param lock the lock associated with the philosopher
     */
    public Philosopher(int id, ReentrantLock lock) {
        this.id = id;
        this.lock = lock;
        condition = lock.newCondition();
    }
    
    /**
     * Returns the id of the philosopher
     * @return the id of the philosopher
     */
    public int getID(){ return id;}
    
    /**
     * Sets the server associated with the Philosopher, in the case of this 
     * program there is one server associated with all philosophers
     * @param server a reference to the associated server object
     */
    public void setServer(PhilosopherServer server) {
        this.server = server;
    }
    
    /**
     * Run method for the Philosopher class, to be executed in a thread in the
     * driver class
     */
    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        while(true){
            try {
                r.setSeed(System.currentTimeMillis());
                Thread.sleep(r.nextInt(4500) + 500); //wait between operations
                server.takeChopsticks(id);
                r.setSeed(System.currentTimeMillis());
                Thread.sleep(r.nextInt(4500) + 500);
                server.returnChopsticks(id);
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
