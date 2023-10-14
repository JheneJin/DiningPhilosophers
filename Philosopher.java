/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */


public class Philosopher implements Runnable {
    //creating id and server variable
    int id;
    DiningServerImpl server;

    Philosopher (int id, DiningServerImpl server) {
        //giving the philosopher an id
        this.id = id; 
        //giving the server to philosopher
        this.server = server;
    }

    public void run() {
        //repeating the cycle of think and eat
        while(true) {
            think();
            eat();
        }
    }

    private void think() {
        try {
            //getting random number to think
            int randomNumber = randWait(); 
            //setting sleep time and putting philosopher to sleep
            int sleepTime = randomNumber;
            Thread.sleep(sleepTime);

            //printing amount philosopher slept
            System.out.println("Philosopher #" + id + " took " + sleepTime 
                    + "ms thinking");
        } catch (InterruptedException e) {
            //printing possible InterruptedException for sleeping
            System.out.println(e);
        }
    }

    private void eat() {
        //philosopher going to server to pickup fok
        int waitTime = randWait();
        server.takeForks(id, waitTime);
    }

    private int randWait() {
        //giving a max and min amount of seconds to think
        int maxSeconds = 3000;
        int minSeconds = 1000;
        //getting random number to think
        int randomNumber = (int)(Math.random()*(maxSeconds-minSeconds+1)+minSeconds);
        
        return randomNumber;
    }
}
