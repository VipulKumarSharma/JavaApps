package spring.beans.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import spring.beans.Coordinates;
import spring.testapp.Shape;

public class Triangle implements Shape {

	private Coordinates center;

	public Coordinates getCenter() {
		return center;
	}

	/*  If we don't specify the name="" it will look for bean which has same name as member variable.
	 */	
	@Resource(name="centerResource")
	public void setCenter(Coordinates center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println("\nDrawing Triangle");
		System.out.println("Triangle Center coordinates are ("+center.getX()+","+center.getY()+")");
	}
	
	@PostConstruct
	public void initializeBean() {
		System.out.println("Triangle Annotation bean has been initialized");
	}
	
	@PreDestroy
	public void destroyBean() {
		System.out.println("Triangle Annotation bean will be destroyed");
	}
}
