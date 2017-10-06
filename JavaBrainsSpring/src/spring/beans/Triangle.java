package spring.beans;

public class Triangle {
	public Triangle() {}
	
	
	public void draw(){
		System.out.println("\n"+getType()+" : triangle drawn of height : "+getHeight());
	}
	
	
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public Triangle(String type) {
		this.type = type;
	}
	
	
	private int height;

	public int getHeight() {
		return height;
	}
	
	public Triangle(String type, int height) {
		this.type 	= type;
		this.height = height;
	}
	
	
	public Triangle(int height) {
		this.height = height;
	}
	
}
