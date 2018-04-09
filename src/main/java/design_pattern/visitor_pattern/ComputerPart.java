package design_pattern.visitor_pattern;

public interface ComputerPart {

	public void accept(ComputerPartVisitor computerPartVisitor);
}
