import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    //initalizing forks id
    private int id;
    //creating lock and condion variable for fork
    private Lock lock;
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

    public void takeFork(int philNumber) {
        lock.lock();
        try {
            System.out.println("Fork #" + id + " is being used by Philosopher #" + philNumber);
            while (inUse == true) {
                condition.await(); 
            }
            inUse = true;
        } catch (InterruptedException e) {
            System.out.println(e);
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
