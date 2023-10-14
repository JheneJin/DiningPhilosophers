/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */


public class DiningPhilosophers {  
   public static void main(String args[]) {
        int numOfPhilosophers = 5;

        //create empty array for philosophers
        Philosopher[] philosophers = new Philosopher[numOfPhilosophers];

        //create server for philosophers
        DiningServerImpl server = new DiningServerImpl(numOfPhilosophers);
       
        for (int i = 0; i < numOfPhilosophers; i++) {
            //creating philosophers 
            philosophers[i] = new Philosopher(i, server); 
            //starting philosophers
            new Thread(philosophers[i]).start();
        }
   }
}
