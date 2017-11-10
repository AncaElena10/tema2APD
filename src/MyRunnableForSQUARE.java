
public class MyRunnableForSQUARE implements Runnable {
	int N;

	MyRunnableForSQUARE(int N) {
		this.N = N;
	}

	public static boolean SQUARE(int N) {
		for (int i = 0; i < N; i++) {
			if ((N - i * i) <= 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void run() {
		int ok = 0;
		int i = N;

		while (ok == 0) {
			if (i <= N) {
				if (SQUARE(i) == true) {
					ok = 1;

					synchronized (Main.squareOUT) {					
						Main.squareOUT.add((int)Math.sqrt(i));
					}
				}
			}
			if (SQUARE(N) == false) {
				i--;
			}
		}
	}
}
