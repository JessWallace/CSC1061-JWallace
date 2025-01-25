
public class TestFan {

	private static final int FAST = 3;
	private static final int MEDIUM = 2;


	public static void main(String[] args) {
		Fan f1 = new Fan();
		Fan f2 = new Fan();
		
		f1.setSpeed(FAST);
		f1.setRadius(10);
		f1.setColor("yellow");
		f1.setOn(true);
		
		f2.setSpeed(MEDIUM);
		
		f1.toString();
		f2.toString();		
	}

}
