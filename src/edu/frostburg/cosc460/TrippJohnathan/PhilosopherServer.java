package edu.frostburg.cosc460.TrippJohnathan;

/**
 * Class to implement the philosopher server component of the Dining Philosopher
 * problem
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public class PhilosopherServer implements DiningServer {
    
    private Philosopher[] philosophers;
    
    /**
     * Public constructor for the Philosopher server class
     * @param philosophers an array of Philosophers
     */
    public PhilosopherServer(Philosopher[] philosophers) {
        this.philosophers = philosophers;
    }
    
    /**
     * Method to allow a philosopher to take a set of chopsticks, if they are
     * available to the philosopher
     * @param i the id of the philosopher taking the chopsticks
     * @throws InterruptedException 
     */
    @Override
    public void takeChopsticks(int i) throws InterruptedException{
        philosophers[i].state = State.HUNGRY;
        test(i);
        if(philosophers[i].state != State.EATING)
            philosophers[i].condition.wait();
    }

    /**
     * Method to allow a philosopher to return the chopsticks they are using,
     * returning them to the THINKING state
     * @param i the id of the philosopher returning the chopsticks
     * @throws InterruptedException 
     */
    @Override
    public void returnChopsticks(int i) throws InterruptedException{
        philosophers[i].state = State.THINKING;
        test((i+4)%5);
        test((i+1)%5);
    }
    
    /**
     * Method to check the state of the philosophers seated next to the philosopher
     * with the given id to determine if the philosopher may eat or must continue thinking
     * @param i the id of the philosopher being evaluated
     */
    public void test(int i) {
        if((philosophers[(i+4)%5].state != State.EATING) &&
            (philosophers[i].state == State.HUNGRY) &&
            (philosophers[(i+1)%5].state != State.EATING)) {
                philosophers[i].state = State.EATING;
                //if(philosophers[i].lock.isHeldByCurrentThread())
                    philosophers[i].condition.signal();
        }
    }
    
    /**
     * Print the state of each philosopher in the server
     */
    public void printState() {
        for(Philosopher p : philosophers){
            System.out.print(p.getID() + ": " + p.state + "\t");
        }
    }
}
