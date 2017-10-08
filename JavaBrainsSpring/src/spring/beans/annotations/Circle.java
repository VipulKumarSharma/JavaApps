package spring.beans.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import spring.beans.Coordinates;
import spring.testapp.Shape;

public class Circle implements Shape {

	private Coordinates center;
	
	public Coordinates getCenter() {
		return center;
	}
	
	@Required @Autowired 
	@Qualifier("circleRelatedBean")
	public void setCenter(Coordinates center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("\nDrawing Circle");
		System.out.println("Circle Center coordinates are ("+center.getX()+","+center.getY()+")");
	}

}
