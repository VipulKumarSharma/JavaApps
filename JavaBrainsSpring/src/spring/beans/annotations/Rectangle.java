package spring.beans.annotations;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import spring.beans.Coordinates;
import spring.testapp.Shape;

@Component
public class Rectangle implements Shape {

	private Coordinates center;
	
	public Coordinates getCenter() {
		return center;
	}

	@Resource
	public void setCenter(Coordinates center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("\nDrawing Rectangle");
		System.out.println("Rectangle Center coordinates are ("+center.getX()+","+center.getY()+")");
	}
}
