import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    //initalizing forks id
    private int id;
    //creating lock and condion variable for fork
    private Lock lock;
    private Condition inUse;
    
    Fork(int id) {
        //giving fork an id
        this.id = id;
        //making lock a ReentrantLock
        this.lock = new ReentrantLock();
        //making condion connected to lock (MIGHT BE WRONG)
        this.inUse = lock.newCondition();
    }

    public void pickupForks(philNumber, eatTime) {
        lock.lock();
        try {
            while(lock.hasWaiters(inUse)) {
                inUse.await(); 
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            Thread.sleep(eatTime);
            lock.unlock();
        }
    }
    
    public void putDownFork(int
}
