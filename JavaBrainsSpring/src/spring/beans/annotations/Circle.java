package spring.beans.annotations;

import org.springframework.beans.factory.annotation.Required;

import spring.beans.Coordinates;
import spring.testapp.Shape;

public class Circle implements Shape {

	private Coordinates center;
	
	public Coordinates getCenter() {
		return center;
	}
	
	@Required
	public void setCenter(Coordinates center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("\nDrawing Circle");
		System.out.println("Circle Center coordinates are ("+center.getX()+","+center.getY()+")");
	}

}
