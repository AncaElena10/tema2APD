import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Producer implements Runnable {
	Buffer buffer;
	int id;
	String fileName;
	BufferedReader reader;

	Producer(Buffer buffer, int id, String fileName) {
		this.buffer = buffer;
		this.id = id;
		this.fileName = fileName;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Producer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		String first = null;
		String second = null;
		String third = null;
		try {
			// citire linie
			for (int i = 0; i < Main.eventsNum; i++) {
				String line;
				line = reader.readLine(); 
				String[] delims = line.split(",");
				first = delims[0];
				
				// asteapta durata in milisecunde
				Thread.sleep(Integer.parseInt(first));
				
				second = delims[1];
				third = delims[2];
				
				// generare evenimente
				int GIVEN_N = Integer.parseInt(third);
				Events event = new Events(GIVEN_N, second);
				
				buffer.put(event);			
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
