
public class Events {
	public int N;
	public String eventType;
	
	Events(int N, String eventType) {
		this.N = N;
		this.eventType = eventType;
	}
	
	public String toString() {
		return this.eventType + " " + this.N;
	}
}
