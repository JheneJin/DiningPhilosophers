import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    //initalizing forks id
    private int id;
    //creating lock and condion variable for fork
    private Lock lock;
    private Condition condition;
    private inUse boolean;
    
    Fork(int id) {
        //giving fork an id
        this.id = id;
        //making lock a ReentrantLock
        this.lock = new ReentrantLock();
        //making condion connected to lock (MIGHT BE WRONG)
        this.condition = lock.newCondition();

        this.inUse = false;
    }

    public void pickupForks(philNumber, eatTime) {
        lock.lock();
        try {
            if (inUse == true) {
                inUse.await(); 
            }
            inUse = true;
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            Thread.sleep(eatTime);
            System.out.println(philNumber + "ate for " + eatTime;
            lock.unlock();
        }
    }
    
    public void putDownFork() {
        lock.lock();
        try {
            inUse = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
