package design_pattern.visitor_pattern;

public class Demo {

	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.accept(new ComputerPartDisplayVisitor());
	}
}
