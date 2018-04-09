package design_pattern.visitor_pattern;

public class Computer implements ComputerPart {

	ComputerPart[] parts;

	public Computer() {
		super();
		this.parts = new ComputerPart[] { new Mouse(), new Monitor(), new Keyboard() };

	}

	@Override
	public void accept(ComputerPartVisitor computerPartVisitor) {
		for (ComputerPart computerPart : parts) {
			computerPart.accept(computerPartVisitor);
		}
		computerPartVisitor.visit(this);

	}

}
