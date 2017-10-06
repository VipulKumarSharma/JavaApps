package spring.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean {

	public Student() {
		System.out.println("\nStudent Object has been created");
	}
	
	private int 	rollNum;
	private String 	name;
	private String 	address;
	
	public int getRollNum() {
		return rollNum;
	}
	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public void getDetails() {
		System.out.println("\nStudent details :: ");
		
		try {
			System.out.println("Roll Number : "+rollNum);
			System.out.println("Name : "+name);
			System.out.println("Address : "+address);
			
		} catch (Exception e) {
			System.out.println("Error getting Rectangle co-ordinates.");
		}
	}
	
	

	
	private HashMap<String, Integer> marks;
	
	public HashMap<String, Integer> getMarks() {
		return marks;
	}
	public void setMarks(HashMap<String, Integer> marks) {
		this.marks = marks;
	}
	
	public void getSubjectWiseMarks() {
		System.out.println("\nSubject wise marks :: <"+getMarks().getClass().getSimpleName()+">");
		
		try {
			Set<Entry<String, Integer>> entrySet = getMarks().entrySet();
			Iterator<Entry<String, Integer>> itr = entrySet.iterator();
		 
		    while(itr.hasNext()) {
		       Entry<String, Integer> mapEntry = (Entry<String, Integer>) itr.next();
		       System.out.println(mapEntry.getKey() + " : "+mapEntry.getValue());
		   }
			
		} catch (Exception e) {
			System.out.println("Error getting Student's subject wise marks.");
		}
	}
	
	
	
	/*  InitializingBean Interface : afterPropertiesSet() will be called after the bean is initialized 
	 *  It is called after Class constructor.
	 *  It is a Spring specific method.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("\nInitializingBean Interface CallBack Method afterPropertiesSet() : Student Bean has been initialized. ");
		
	}
	
	/*  DisposableBean Interface : destroy() will be called before the bean is destroyed
	 *  It is a Spring specific method.
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("\nDisposableBean Interface CallBack Method destroy() : Student Bean will be destroyed. ");
		
	}
	
	
	
	/*  It is a custom method.
	 *  It will be called after the bean is initialized
	 *  It is called after Class constructor.
	 *  It is called using bean definition in XML file (i.e. init-method="").
	 *  Is is called after Spring's afterPropertiesSet() method.
	 */
	public void StudentInit() {
		System.out.println("StudentInit() : Student Object has been initialized");
	}
	
	/*  It is a custom method.
	 *  It will be called before the bean is destroyed
	 *  It is called using bean definition in XML file (i.e. destroy-method="").
	 *  Is is called after Spring's destroy() method.
	 */
	public void StudentDestroy() {
		System.out.println("StudentDestroy() : Student Object will be destroyed");
	}
	
	
}
