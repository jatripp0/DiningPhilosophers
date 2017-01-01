package edu.frostburg.cosc460.TrippJohnathan;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Driver class for the Dining Philosophers problem. 
 * Author tag shows accurate depiction of my state of mind attempting to solve this problem.
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public class TrippJohnathanDiningPhilos {

    private static final int NUM_PHILOS = 5;
    /**
     * Main method for the Dining Philosophers Driver class
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        Philosopher[] philosophers = new Philosopher[NUM_PHILOS];
        PhilosopherServer server;
        ReentrantLock lock = new ReentrantLock();
        
        for(int i=0; i<NUM_PHILOS; i++){ //Initialize the 5 philosophers and set their state to THINKING
            philosophers[i] = new Philosopher(i, lock);
            philosophers[i].state = State.THINKING;
        }
        server = new PhilosopherServer(philosophers); //Initialize the philosopher server
        for(int i=0; i<NUM_PHILOS; i++){
            philosophers[i].setServer(server); //associate the server with each philosopher
            Thread t = new Thread(philosophers[i]); //create and execute the thread for each philosopher
            t.start();
        }
        
        while(true){
            Thread.sleep(5000);
            server.printState(); //print the state of the server occasionally
            System.out.println();
        }
    }
    
}
