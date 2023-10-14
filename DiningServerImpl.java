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
        //using fork's pickup method
		// System.out.println("Fork: " + forks[2]);
        forks[philNumber].takeFork(philNumber);
		forks[(philNumber + 1) % amountOfPhilosphers].takeFork(philNumber);
        try {
            Thread.sleep(eatTime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
		System.out.println("Philosopher #" + philNumber + " ate for " + eatTime + " ms ");
    }

    public void returnForks(int philNumber) {
		forks[philNumber].returnFork();
		forks[(philNumber + 1) % amountOfPhilosphers].returnFork();
    }
}
