
public class MyRunnableForFIB implements Runnable {
	int N;

	MyRunnableForFIB(int N) {
		this.N = N;
	}

	public static int FIB(int N) {
		if (N == 1) {
			return 1;
		}
		if (N == 0) {
			return 0;
		}

		int fiboMinusONE = 1;
		int fiboMinusTWO = 1;
		int keepTemp;
		for (int i = 2; i < N; i++) {
			keepTemp = fiboMinusONE;
			fiboMinusONE = fiboMinusONE + fiboMinusTWO;
			fiboMinusTWO = keepTemp;
		}
		return fiboMinusONE;
	}

	@Override
	public void run() {
		int i = 0;
		while (i < N) {
			if (FIB(i) == N) {
				synchronized (Main.fibOUT) {					
					Main.fibOUT.add(i);
				}
				break;
			} else if (FIB(i) > N) {

				synchronized (Main.fibOUT) {					
					Main.fibOUT.add(i - 1);
				}
				break;
			}
			i++;
		}
	}
}