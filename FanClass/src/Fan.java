
public class Fan {	
	
	public static final int SLOW = 1;
	public static final int MEDIUM = 2;
	public static final int FAST = 3;
	
	private int speed;
	private double radius;	
	private boolean on;
	private	String color;
		
	private static int nextID = 1;
	private int id;

	public int getID() {
		return this.id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Fan() {
		this.id = nextID++;
		
		speed = SLOW;
		on = false;
		radius = 5;
		color = "blue";
	}	

	public String toString() {
		if (on == false) {
// "Fan number #: is [color] with a radius of [radius] and the fan is off.
			System.out.println("Fan number " + this.id + ": is " + this.color + " with a radius of " + this.radius + " and the fan is off.");
			return "\n";
		} else if (on == true) {
// "Fan number #: is [color] and running at speed [speed] with a radius of [radius].
			System.out.println("Fan number " + this.id + ": is " + this.color + " and running at speed " + this.speed + " with a radius of " + this.radius + ".");
			return "";
		}
		else {return "error";}
		
	}
	

}
