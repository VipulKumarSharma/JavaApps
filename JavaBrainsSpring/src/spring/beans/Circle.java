package spring.beans;

import spring.interfaces.Shape;

public class Circle implements Shape {

	private Coordinates center;
	
	public Coordinates getCenter() {
		return center;
	}

	public void setCenter(Coordinates center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("\nDrawing Circle");
		System.out.println("Circle Center coordinates are ("+center.getX()+","+center.getY()+")");
	}

}
