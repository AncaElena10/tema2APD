
public class MyRunnableForFACT implements Runnable {
	int N;
	int value;

	MyRunnableForFACT(int N) {
		this.N = N;
	}

	public static int FACT(int N) {
		int result = 1;
		for (int i = 2; i <= N; i++) {
			result *= i;
		}
		return result;
	}

	@Override
	public void run() {
		int i = 0;
		while (i < N) {
			if (FACT(i) == N) {

				synchronized (Main.factOUT) {					
					Main.factOUT.add(i);
				}
				break;
			} else if (FACT(i) > N) {

				synchronized (Main.factOUT) {					
					Main.factOUT.add(i - 1);
				}
				break;
			}
			i++;
		}
	}
}

// 4 10 67 770