
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static ArrayList<String> files = new ArrayList<String>();
	public static int N_PRODUCERS = files.size();
	public static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors(); // 4
	public static int dimBuf;
	public static int eventsNum;
	public static ArrayList<Integer> factOUT = new ArrayList<Integer>();
	public static ArrayList<Integer> squareOUT = new ArrayList<Integer>();
	public static ArrayList<Integer> primeOUT = new ArrayList<Integer>();
	public static ArrayList<Integer> fibOUT = new ArrayList<Integer>();
	static BufferedWriter bwFACT;
	static BufferedWriter bwFIB;
	static BufferedWriter bwPRIME;
	static BufferedWriter bwSQUARE;

	public static void main(String[] args) {
		dimBuf = Integer.parseInt(args[0]);
		eventsNum = Integer.parseInt(args[1]);

		for (int i = 2; i < args.length; i++) {
			files.add(args[i]);
		}

		N_PRODUCERS = files.size();

		// deschidere thread-uri
		Buffer buffer = new Buffer();
		Thread threads[] = new Thread[N_CONSUMERS + N_PRODUCERS];

		for (int i = 0; i < N_PRODUCERS; i++) {
			threads[i] = new Thread(new Producer(buffer, i, files.get(i)));
		}

		for (int i = N_PRODUCERS; i < N_CONSUMERS + N_PRODUCERS; i++) {
			threads[i] = new Thread(new Consumer(buffer, i - N_PRODUCERS));
		}

		for (int i = 0; i < N_CONSUMERS + N_PRODUCERS; i++) {
			threads[i].start();
		}

		for (int i = 0; i < N_CONSUMERS + N_PRODUCERS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		///////////////////////////////////////////////////////////////////////
		//////////////////////////////SORTARE//////////////////////////////////
		Collections.sort(fibOUT);
		Collections.sort(factOUT);
		Collections.sort(primeOUT);
		Collections.sort(squareOUT);
		
		///////////////////////////////////////////////////////////////////////
		//////////////////////// SCRIERE IN FISIER/////////////////////////////

		File file1 = new File("FACT.out");
		FileWriter fwFACT;
		try {
			fwFACT = new FileWriter(file1);
			bwFACT = new BufferedWriter(fwFACT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (Integer objs : factOUT) {
				bwFACT.write(Integer.toString(objs));
				bwFACT.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwFACT != null)
					bwFACT.close();
			} catch (Exception ex) {
				System.out.println("err" + ex);
			}
		}
		
		
		/////////////////////////////////////////////////////////////
		
		
		File file2 = new File("FIB.out");
		FileWriter fwFIB;
		try {
			fwFIB = new FileWriter(file2);
			bwFIB = new BufferedWriter(fwFIB);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (Integer objs : fibOUT) {
				bwFIB.write(Integer.toString(objs));
				bwFIB.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwFIB != null)
					bwFIB.close();
			} catch (Exception ex) {
				System.out.println("err" + ex);
			}
		}
		
		/////////////////////////////////////////////////////////////
		
		File file3 = new File("PRIME.out");
		FileWriter fwPRIME;
		try {
			fwPRIME = new FileWriter(file3);
			bwPRIME = new BufferedWriter(fwPRIME);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (Integer objs : primeOUT) {
				bwPRIME.write(Integer.toString(objs));
				bwPRIME.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwPRIME != null)
					bwPRIME.close();
			} catch (Exception ex) {
				System.out.println("err" + ex);
			}
		}
		
		/////////////////////////////////////////////////////////////
		
		File file4 = new File("SQUARE.out");
		FileWriter fwSQUARE;
		try {
			fwSQUARE = new FileWriter(file4);
			bwSQUARE = new BufferedWriter(fwSQUARE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (Integer objs : squareOUT) {
				bwSQUARE.write(Integer.toString(objs));
				bwSQUARE.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwSQUARE != null)
					bwSQUARE.close();
			} catch (Exception ex) {
				System.out.println("err" + ex);
			}
		}
	}
}
