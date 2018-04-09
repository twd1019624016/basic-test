package design_pattern.bridge_pattern;

public class Demo {

	public static void main(String[] args) {
		Shape redCircle=new Circle(new RedCircle(), 0, 0, 0);
		Shape greeCircle=new Circle(new GreenCircle(), 0, 0, 0);
		
		redCircle.draw();
		greeCircle.draw();
	}
}
