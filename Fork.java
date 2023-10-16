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
        //initiallize condition of the lock
        this.condition = lock.newCondition();
        //boolean to flag if the fork is in use or not in use
        this.inUse = false;
    }

    public void takeFork(int philNumber) {
        //locks the fork
        lock.lock();
        try {
            //display the fork id and who is using it
            System.out.println("Fork #" + id + " is being used by Philosopher #" + philNumber);
            while (inUse == true) {
                //philosopher waits for the fork to not be in use
                condition.await(); 
            }
            inUse = true;
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            //after the philosopher is done using the fork, it will unlock the fork
            lock.unlock();
        }
    }
    
    public void returnFork() {
        //locks the fork
        lock.lock();
        try {
            //fork is not in use, so signals to the philospher can freely take it
            inUse = false;
            condition.signal();
        } finally {
             //after the philosopher is done using the fork, it will unlock the fork
            lock.unlock();
        }
    }
}
