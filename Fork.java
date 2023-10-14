import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    //initalizing forks id
    private int id;
    //creating lock and condion variable for fork
    private ReentrantLock lock;
    private Condition condition;
    private Boolean inUse;
    
    Fork(int id) {
        //giving fork an id
        this.id = id;
        //making lock a ReentrantLock
        this.lock = new ReentrantLock();
        //making condion connected to lock (MIGHT BE WRONG)
        this.condition = lock.newCondition();

        this.inUse = false;
    }

    public void takeFork(int philNumber, int eatTime) {
        lock.lock();
        try {
            while (inUse == true) {
                condition.await(); 
            }
            inUse = true;
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    
        // Move the sleep and print statements outside the lock
        try {
            Thread.sleep(eatTime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    
        System.out.println(philNumber + " ate for " + eatTime + " ms ");
    
        lock.lock(); // Reacquire the lock to signal completion
        try {
            inUse = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public void returnFork() {
        lock.lock();
        try {
            inUse = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
