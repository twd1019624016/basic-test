package design_pattern.bridge_pattern;

public class Circle extends Shape {

	protected int x,y,radius;
	

	public Circle(DrawAPI drawAPI, int x, int y, int radius) {
		super(drawAPI);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}


	@Override
	public void draw() {
		drawAPI.drawCircle(radius, x, y);
	}

}
