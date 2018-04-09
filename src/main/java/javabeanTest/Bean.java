package javabeanTest;

public class Bean {
	private String function;
	private String useWay;
	
	private String name;
	
	private Integer age;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getUseWay() {
		return useWay;
	}
	public void setUseWay(String useWay) {
		this.useWay = useWay;
	}
	@Override
	public String toString() {
		return "Bean [function=" + function + ", useWay=" + useWay + ", name=" + name + "]";
	}
	
	
	
}
