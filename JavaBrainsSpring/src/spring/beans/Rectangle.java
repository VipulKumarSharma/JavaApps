package spring.beans;

import java.util.List;

public class Rectangle {
	
	public Rectangle() {
		System.out.println("\nRectangle class non-parameterized constructor.");
	}
	
	private Coordinates point_A;
	private Coordinates point_B;
	private Coordinates point_C;
	private Coordinates point_D;
	
	public Coordinates getPoint_A() {
		return point_A;
	}
	public void setPoint_A(Coordinates point_A) {
		this.point_A = point_A;
	}
	public Coordinates getPoint_B() {
		return point_B;
	}
	public void setPoint_B(Coordinates point_B) {
		this.point_B = point_B;
	}
	public Coordinates getPoint_C() {
		return point_C;
	}
	public void setPoint_C(Coordinates point_C) {
		this.point_C = point_C;
	}
	public Coordinates getPoint_D() {
		return point_D;
	}
	public void setPoint_D(Coordinates point_D) {
		this.point_D = point_D;
	}
	
	public void draw() {
		try {
			System.out.println("\nPoint A - ("+getPoint_A().getX()+", "+getPoint_A().getY()+")");
			System.out.println("Point B - ("+getPoint_B().getX()+", "+getPoint_B().getY()+")");
			System.out.println("Point C - ("+getPoint_C().getX()+", "+getPoint_C().getY()+")");
			System.out.println("Point D - ("+getPoint_D().getX()+", "+getPoint_D().getY()+")");
			
		} catch (Exception e) {
			System.out.println("Error getting Rectangle co-ordinates.");
		}
	}
	
	
	private List<Coordinates> points;

	public List<Coordinates> getPoints() {
		return points;
	}
	public void setPoints(List<Coordinates> points) {
		this.points = points;
	}
	
	public void drawByCollection() {
		System.out.println("\nPoints drawn by :: <"+points.getClass().getSimpleName()+">");
		for(Coordinates point : points) {
			System.out.println("Point - ("+point.getX()+", "+point.getY()+")");
		}
	}
}
