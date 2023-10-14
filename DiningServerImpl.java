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
    //creating empty array for forks and locks
    Condition forks[] = new Condition[5];
	ReentrantLock locks[] = new ReentrantLock[5];

    DiningServerImpl(int numberOfPhilosphers) {
		// loops until it creates forks and locks for every individual philosopher
        for (int i=0; i < numberOfPhilosphers; i++) {
            locks[i] = new ReentrantLock();
			forks[i] = locks[i].newCondition();
        }
    }

	public void takeForks(int philNumber) {
        try {
			//searches if the philosopher to the left is holding the left fork
			if (locks[philNumber].getHoldCount()  > 0) {
				System.out.println("Philosopher " + philNumber + " waiting for left fork");
				//waiting for left fork when it is unavailable
				forks[philNumber].await();
			}
			//searches if the philosopher to the right is holding the right fork
			if (locks[(philNumber + 1) % 5].getHoldCount() > 0) {
				System.out.println("Philosopher " + philNumber + " waiting for right fork");
				//waiting for right fork when it is unavailable
				forks[(philNumber + 1) % 5].await();
			}
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
    }

    public void returnForks(int philNumber) {
			// if left fork is available, signals the left philosopher
			if (locks[philNumber].getHoldCount() > 0) {
				forks[philNumber].signal();
			}
			// if right fork is available, signals the right philosopher
			if (locks[(philNumber + 1) % 5].getHoldCount() > 0) {
				forks[(philNumber + 1) % 5].signal();
			}
		}

}
