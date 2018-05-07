package springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.Shape;

public class AOPApp {

	public static void main(String[] args) {

		/*	AOP is not just a feature that Spring provides, it's actually a model of programming.
		 * 	In Functional Programming, you break down the problem into smaller units (or functions, when the last function ends, that's when the program ends.)
		 * 	When the program becomes complex, then no of functions & dependencies between them increases.
		 * 	For some situations you would get a better design if you use Object Oriented Programming.
		 * 	In OOP you would think of single individual entities as objects, & you would write objects that mirrors different entities in your problem space.
		 * 	Each object contains member variables & methods.
		 * 	You have encapsulated entities & you can design more complex code, because you have a cleaner design & separation of concerns.
		 * 	But not all of the tasks you want to do, can be classified into objects.
		 * 	Shape.class automatically casts the returned bean to Shape Object.  
		 */
		ApplicationContext appContext 	= new ClassPathXmlApplicationContext("spring.xml");
		Shape shapeService				= appContext.getBean("shapeService", Shape.class);
		//System.out.println(shapeService.getCircle().getName());
		//System.out.println(shapeService.getTriangle().getName());
		
		//shapeService.getCircle().setName("Dummy Circle");
		
		shapeService.getCircle().setNameAndReturn("Perfect Circle");
		
	}

}
