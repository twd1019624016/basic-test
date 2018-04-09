package design_pattern.bridge_pattern;

public class RedCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("红色圆圈");
	}

}
