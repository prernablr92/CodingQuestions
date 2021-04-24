
public class SynchronizationExample {
	
	public int counter = 0; 
	public boolean shouldprint = false;
	public synchronized void printCounter() throws InterruptedException {
		if(shouldprint) {
			System.out.println(Thread.currentThread().getName() + " is incrementing counter to " + (counter++));
		} else {
			.
		}
		
		Thread.sleep(20000);
	}
	
	
	public static void main(String args[]) {
		SynchronizationExample synch1 = new SynchronizationExample();
		SynchronizationExample synch2 = new SynchronizationExample();
		Thread t1 = new MyThread(synch1);
		t1.setName("first");
		Thread t2 = new MyThread(synch1);
		t2.setName("second");
		t1.start();
		t2.start();
		
	}

}


class MyThread extends Thread {
	public SynchronizationExample synch = null;
	
	MyThread(SynchronizationExample synch) {
		this.synch = synch;
	}
	
	public void run() {
		try {
			if(synch.shouldprint) {
				synch.printCounter();
			}
			
			wait(100);
			System.out.println("current milliseconfs" + System.currentTimeMillis());
			//Thread.sleep(2000000000);
			System.out.println("current milliseconfs" + System.currentTimeMillis());
		} catch(Exception e) {
			System.out.println("exception occured");
		}
	}
}