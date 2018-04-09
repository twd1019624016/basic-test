package jmx;

import digester.Test01;

public class Car implements CarMBean {

	private String color = "car";

	/* (non-Javadoc)
	 * @see jmx.CarMBean#getColor()
	 */
	@Override
	public String getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see jmx.CarMBean#setColor(java.lang.String)
	 */
	@Override
	public void setColor(String color) {
		this.color = color;
	}
	
	/* (non-Javadoc)
	 * @see jmx.CarMBean#drive()
	 */
	@Override
	public void drive(){
		System.out.println("baby you can driver my car");
	}
	public static void main(String[] args) {

		ClassLoader classLoader = Car.class.getClassLoader();
		
		while(true) {
			System.err.println(classLoader.getClass().getSimpleName());
			classLoader = classLoader.getParent();
		}
	}
}
