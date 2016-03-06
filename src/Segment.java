
public class Segment {
	private int x_a; 
	private int x_b; 
	private int y_a;
	private int y_b;
	
	public Segment(int x_a, int y_a, int x_b, int y_b){
		this.x_a = x_a;
		this.x_b = x_b;
		this.y_a = y_a;
		this.y_b = y_b;
	}

	public int getX_a() {
		return x_a;
	}

	public void setX_a(int x_a) {
		this.x_a = x_a;
	}

	public int getX_b() {
		return x_b;
	}

	public void setX_b(int x_b) {
		this.x_b = x_b;
	}

	public int getY_a() {
		return y_a;
	}

	public void setY_a(int y_a) {
		this.y_a = y_a;
	}

	public int getY_b() {
		return y_b;
	}

	public void setY_b(int y_b) {
		this.y_b = y_b;
	}
}
