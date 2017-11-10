
import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
	ArrayBlockingQueue<Events> a = new ArrayBlockingQueue<Events>(Main.dimBuf);
	
//	Buffer() {
//		a = new ArrayBlockingQueue<Events>(Main.dimBuf);
//	}

	void put(Events i) {
		try {
			a.put(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Events get() throws InterruptedException {
		return a.take();
	}
}
