package spring.events;

import org.springframework.context.ApplicationEvent;

/*	This is our custom event class
 */
public class DrawEvent extends ApplicationEvent {

	public DrawEvent(Object source) {
		super(source);
	}

	/*	We'll create our own custom message.
	 */	
	@Override
	public String toString() {
		return "!!!!! DrawEvent occured in JavaBrainsSpring App !!!!!";
	}
}
