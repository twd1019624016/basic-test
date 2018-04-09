package design_pattern.bridge_pattern;

public class GreenCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("绿色圆圈");
	}

}
