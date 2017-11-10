
public class MyRunnableForPRIME implements Runnable {
	int N;

	public MyRunnableForPRIME(int N) {
		this.N = N;
	}

	public static int checkPrime(int N) {
		if (N <= 1) {
			return 0;
		}
		for (int i = 2; i < N; i++) {
			if (N % i == 0) {
				return 0; // nu e prim
			}
		}
		return 1; // e prim
	}

	@Override
	public void run() {
		int ok = 0;
		int i = N;

		while (ok == 0) {
			if (i <= N) {
				if (checkPrime(i) == 1) {
					ok = 1;
					synchronized (Main.primeOUT) {					
						Main.primeOUT.add(i);
					}

				}
			}
			if (checkPrime(N) == 0) {
				i--;
			}
		}
	}
}
