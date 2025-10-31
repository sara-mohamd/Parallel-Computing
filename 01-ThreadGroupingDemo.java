class Main {
    public static void main(String[] args) {
        threadOne();
    }
     public static void threadOne()
    {
        System.out.println("Running threads and ThreadGrouping concurrent and \n--------------------\n");
        Thread t1 = new Thread( () ->{
          System.out.println("Running Thread One...");
          System.out.println("Thread in: { " + Thread.currentThread().getName() + " }");
        }, "Elephant");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt(); 
            System.err.println("The main thread was interrupted while waiting for 'Elephant' to finish.");
            return;
        }

        System.out.println("--------------------\nRunning Threads of same threadGroup:\n--------------------");
        ThreadGroup group = new ThreadGroup("My Group");

        Thread t2 = new Thread(group, () -> {
            System.out.println("Running Thread One...");
            System.out.println("Thread in: { " + Thread.currentThread().getName() + " } Group Name: " + Thread.currentThread().getThreadGroup().getName() + "\n");
            }, "Duck");
        
        Thread t3 = new Thread(group, () -> {
            System.out.println("Running Thread Two...");
            System.out.println("Thread in: { " + Thread.currentThread().getName() + " } Group Name: " + Thread.currentThread().getThreadGroup().getName());
            }, "Cat");
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt(); 
            System.err.println("The main thread was interrupted while waiting for 'Duck' to finish.");
            return;
        }
        try {
            t3.join();
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt(); 
            System.err.println("The main thread was interrupted while waiting for 'Duck' to finish.");
            return;
        }
        t3.start();
    }

}