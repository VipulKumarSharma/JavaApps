package service;

import aspect.AspectClass;
import model.Circle;

public class ShapeServiceProxy extends ShapeService {

	public Circle getCircle() {
		
		/* Here you can actually make a call to the advice of AspectClass */
		new AspectClass().customAdviceMethod();
		
		return super.getCircle();
	}
}
