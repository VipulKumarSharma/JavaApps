package spring.testapp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import spring.beans.Rectangle;
import spring.beans.Rhombus;
import spring.beans.Student;
import spring.beans.Triangle;

public class DrawingApp {

	/*  To implement unimplemented methods : [ALT + SHIFT + S + V] 
	 */
	
	@SuppressWarnings({ "resource", "deprecation" })
	public static void main(String[] args) {
		
		/*  Simple object creation [POJO]
		 */
		
		Triangle triangle 						= new Triangle();
		triangle.draw();
		
		/* "spring-factory.xml" is present in main working directory */
		BeanFactory factory 					= new XmlBeanFactory(new FileSystemResource("spring-factory.xml"));
		Triangle triangleFactory				= (Triangle) factory.getBean("triangle");
		triangleFactory.draw();
		
		
		
		/* 1.  "spring-context.xml" is present in "src" directory
		 * 2.  Spring provides instance of the object & control it's whole life cycle.
		 * 3.  ApplicationContext is BeanFactory with additional functionality, which gets the blueprint of the object & creates bean.
		 * 4.  ApplicationContext is a container which reads configuration file & creates SpringBeans (which are defined in the file) in the container.
		 * 5.  While calling getBean() container hands over the already initialized bean.
		 * 6.  There are 2 bean scopes : Singleton & Prototype.
		 * 7.  By default all beans in Spring are Singleton (i.e. one instance per Spring Container).
		 * 8.  For Prototype scope, a new bean will be created with each request and reference (i.e. ref="").
		 * 9.  In Prototype scope, Spring wait for getBean() for initialization of Bean.
		 * 10. Web-Aware context bean scopes - Spring ties & very well with webApps (i.e. Servlet API's), therefore it is capable of knowing when there is new request/session. 
		 * 	   So you can tie the bean scopes with requests & sessions.
		 * 11. Request scope - new bean per Servlet request.
		 * 12. Session scope - new bean per Session.   
		 * 13. Global Session scope  - new bean per global HTTP session (portlet context).
		 * 14. There can be multipe containers running inside JVM.
		 * 
		 */
		
		ApplicationContext context 				= new ClassPathXmlApplicationContext("spring-context.xml");
		
		Triangle triangleSetterInjObj 			= (Triangle) context.getBean("triangleSetterInjection");
		triangleSetterInjObj.draw();
		
		Triangle triangleConstructorInjObj 		= (Triangle) context.getBean("triangleConstructorInjection");
		triangleConstructorInjObj.draw();
		
		Triangle triangleConstructorInjNoObj	= (Triangle) context.getBean("triangleConstructorInjectionNoArgType");
		triangleConstructorInjNoObj.draw();
		
		Triangle triangleConstructorInjStrObj	= (Triangle) context.getBean("triangleConstructorInjectionStrArgType");
		triangleConstructorInjStrObj.draw();
		
		Triangle triangleConstructorInjIntObj	= (Triangle) context.getBean("triangleConstructorInjectionIntArgType");
		triangleConstructorInjIntObj.draw();
		
		Triangle triangleConstructorInjArgIndex	= (Triangle) context.getBean("triangleConstructorInjectionArgIndex");
		triangleConstructorInjArgIndex.draw();
		
		Rectangle rectangleObjRef				= (Rectangle) context.getBean("rectangleObjRef");
		rectangleObjRef.draw();
		
		Rectangle rectangleInnerBeans			= (Rectangle) context.getBean("rectangleInnerBeans-name");
		rectangleInnerBeans.draw();
		
		Rectangle rectangleInnerBeansAlias		= (Rectangle) context.getBean("rectangleInnerBeans-alias");
		rectangleInnerBeansAlias.draw();
		
		Rectangle rectangleIdRef				= (Rectangle) context.getBean("rectangleIdRef-name");
		rectangleIdRef.draw();
		
		Rectangle rectangleCollection			= (Rectangle) context.getBean("rectangleCollection");
		rectangleCollection.drawByCollection();
		
		Rectangle rectangleAutowire				= (Rectangle) context.getBean("rectangleAutowire");
		rectangleAutowire.draw();
		
		Triangle triangleScope					= (Triangle) context.getBean("triangleScope");
		triangleScope.draw();
		
		
		
		/*  If you want an instance of other class inside a different class, then we will require context object there.
		 *  For this requirement we have to implement ApplicationContextAware Interface & add setApplicationContext() in our class.
		 *  EventPublisherAware, BeanClassLoaderAware, BeanNameAware Interfaces are some examples. 
		 */
		
		Rhombus rhombusAware					= (Rhombus) context.getBean("rhombusAware");
		rhombusAware.draw();
		
		Rectangle rectangleParent				= (Rectangle) context.getBean("rectangleParent");
		rectangleParent.draw();
		
		Rectangle rectangleChild1				= (Rectangle) context.getBean("rectangleChild1");
		rectangleChild1.draw();
		rectangleChild1.drawByCollection();
		
		Rectangle rectangleChild2				= (Rectangle) context.getBean("rectangleChild2");
		rectangleChild2.draw();
		rectangleChild2.drawByCollection();
		
		Rectangle rectangleChild3				= (Rectangle) context.getBean("rectangleChild3");
		rectangleChild3.draw();
		rectangleChild3.drawByCollection();
		
		
		
		/*  AbstractApplicationContext is used for Desktop(JavaSE) apps, where we have to close the container on app shutdown.
		 *  It is of no use in WebApps, because we can't close the container, which is managing our webApp. 
		 * 	registerShutdownHook() destroys all the beans in the container.
		 */
		
		AbstractApplicationContext abstContext 	= new ClassPathXmlApplicationContext("spring-context.xml");
		abstContext.registerShutdownHook();
		Student student							= (Student) abstContext.getBean("student");
		student.getDetails();
		student.getSubjectWiseMarks();
		
		
		
		/*  It uses PropertyPlaceholderConfigurer to substitute values from specified file to bean 
		 * 	PropertyPlaceholderConfigurer runs before BeanFactoryPostProcessor.
		 *  BeanFactoryPostProcessor will do the value substitution for us.
		 */
		
		Rectangle rectanglePlaceholder			= (Rectangle) context.getBean("rectanglePlaceholder");
		rectanglePlaceholder.draw();
		
		
		/*  Coding to Interfaces
		 *  DrawingApp doesn't now what it is drawing.
		 *  It is only getting a bean which Implements Shape Interface & calling Shape Interface method.
		 *  Later on if you want to add Pentagon shape, then you only need to create Bean which implements Shape &  add entry to the xml.
		 *  Or, create a shape definition in XML & change only the class (i.e. modify bean definition).
		 *  Main advantage - remove the dependency of knowing the type of object.
		 *  Business services, DAO Layer uses this concept.
		 *  Doing DI always use interfaces, so that if you change the implementation, 
		 *  you don't have to change the actual class which depends on the Interface.
		 *  You just plug in the new implementation & change the wiring. So that the class can use new implementation.
		 */
		String shapeToDrawId					= "circle";
		Shape shape								= (Shape) context.getBean(shapeToDrawId);
		shape.draw();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
