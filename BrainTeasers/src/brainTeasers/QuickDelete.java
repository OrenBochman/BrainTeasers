package brainTeasers;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/** create a class with
 * o(1) add, get, delete and delete all.
 * you cannot allocate more data after start.
 * 
 * Note: I have added a small delay in the get method to simulate delay of a hard drive seek.
 * 
 * @author Oren Bochman
 *
 */
public class QuickDelete {

	final int EXPECTED_HITS=50;
	final int STORE_SIZE=100;
	final int SEED=1024;
	final int PAUSE=1;
	Random random = new  Random(SEED);
	long startNanos,endNanos;

	/**
	 * The performance test
	 * 
	 * @param quickDelete
	 */
	public void test(QuickDelete quickDelete){

		this.start();

		for(int i=0;i<STORE_SIZE*100;i++) {
			if(this.random.nextInt(100)>50) {
				quickDelete.add(i%STORE_SIZE, i+1);
				if(i%100==0) {
					System.out.println("-");
					quickDelete.deleteAll();
				}
			}else {
				for(int j=0;j<50;j++) {
					quickDelete.get(this.random.nextInt(STORE_SIZE));
				}
				System.out.print(".");
			}
		}

		this.end();
	}
	/**
	 * Measure the end time of the test
	 */
	public void start() {
		startNanos = System.nanoTime();
	}	

	/**
	 * Measure the end time of the test
	 */
	public void end() {
		endNanos = System.nanoTime();
		System.out.format("timeing: %s ms",(endNanos- startNanos)/1000000);

	}

	/** Entry point and performance testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		QuickDelete quickDelete = new QuickDelete();

		quickDelete.test(quickDelete);
	}

	private long[] _epoch = new long[STORE_SIZE];
	private int[] _data = new int[STORE_SIZE];
	private long minimalEpoch=0;
	private long currentEpoch=0;

	/**
	 * Data is time-stamped using an epoch counter.
	 * 
	 * @param index	- offset to add at.
	 * @param amount - value to set.
	 */
	public void add(int index,int amount) {
		_data[index]  = amount;
		_epoch[index] = ++currentEpoch; 	
	}

	/**
	 * get the data 
	 * @param index - offset to seek
	 * @return - value at offset.
	 */
	public int get(int index){
		//we simulate 5 nanosecond disk access for get operations
		try        
		{
			TimeUnit.NANOSECONDS.sleep(PAUSE);

		} 
		catch(InterruptedException ex) 
		{
			Thread.currentThread().interrupt();
		}

		if( _epoch[index] > minimalEpoch) {

			return _data[index];
		}else {
			return 0;	
		}
	}

	/**
	 * delete a single value
	 * @param index - index where to delete
	 */
	public void delete(int index){
		_data[index] = 0;
	}

	/**
	 * Delete all entries - done using soft deletion.
	 * 
	 */
	public void deleteAll(){
		minimalEpoch = ++currentEpoch;
	}
}