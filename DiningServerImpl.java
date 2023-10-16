/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer {
    //creating empty array for forks
    private Fork[] forks;
    private int amountOfPhilosphers;

    DiningServerImpl(int numberOfPhilosphers) {
        //adding number of elements for forks array
        forks = new Fork[numberOfPhilosphers];
        amountOfPhilosphers = numberOfPhilosphers;

        for (int i=0; i < numberOfPhilosphers; i++) {
            //adding forks in forks array
            forks[i] = new Fork(i);
        }
    }

	public void takeForks(int philNumber, int eatTime) {
	//take left fork
        forks[philNumber].takeFork(philNumber);
	//take right fork thru modulo
		forks[(philNumber + 1) % amountOfPhilosphers].takeFork(philNumber);
        try {
	//thread(user) will sleep after both forks are taken	
            Thread.sleep(eatTime);
        } catch (InterruptedException e) {
            System.out.println(e);
        //
		System.out.println("Philosopher #" + philNumber + " ate for " + eatTime + " ms ");
    }

    public void returnForks(int philNumber) {
	    	//return left  fork
		forks[philNumber].returnFork();
	    	//return right fork through modulo
		forks[(philNumber + 1) % amountOfPhilosphers].returnFork();
    }
}
