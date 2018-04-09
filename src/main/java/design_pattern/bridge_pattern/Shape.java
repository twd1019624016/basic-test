package design_pattern.bridge_pattern;

public abstract class Shape {

	protected DrawAPI drawAPI;

	protected Shape(DrawAPI drawAPI) {
		super();
		this.drawAPI = drawAPI;
	}
	
	public abstract void draw();
	
}
