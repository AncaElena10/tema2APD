import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	Buffer buffer;
	int id;
	static int i = 0;
	ExecutorService tpe;

	Consumer(Buffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
		tpe = Executors.newFixedThreadPool(4);
	}

	int getNextI() {
		int value;
		synchronized (Consumer.class) {
			value = i;
			i++;
		}
		return value;
	}

	@Override
	public void run() {
		while (true) {
			int i = getNextI();
			if (i >= Main.N_PRODUCERS * Main.eventsNum) {
				tpe.shutdown();
//				try {
//					tpe.awaitTermination(30, TimeUnit.SECONDS);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				return;
			}
				try {
					Events event = buffer.get();
					
					if (event.eventType.equals("PRIME")) {
						tpe.submit(new MyRunnableForPRIME(event.N));
					}
					
					if (event.eventType.equals("FACT")) {
						tpe.submit(new MyRunnableForFACT(event.N));
					}
					
					if(event.eventType.equals("SQUARE")) {
						tpe.submit(new MyRunnableForSQUARE(event.N));
					}
					if(event.eventType.equals("FIB")) {
						tpe.submit(new MyRunnableForFIB(event.N));
					}
					//System.out.println(event);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
