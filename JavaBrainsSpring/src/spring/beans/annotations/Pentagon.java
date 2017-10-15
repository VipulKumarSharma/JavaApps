package spring.beans.annotations;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import spring.events.DrawEvent;
import spring.interfaces.Shape;

@Component
public class Pentagon implements Shape, ApplicationEventPublisherAware {

	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public void draw() {
		DrawEvent drawEvent = new DrawEvent(this);
		eventPublisher.publishEvent(drawEvent);
	}


	/*	The parameter eventPublisher is actually ApplicationContext which implements ApplicationEventPublisher.
	 * 	We are actually calling the publishEvent() of ApplicationContext.
	 * 	ApplicationEventPublisher is implemented as an Interface in Spring, you don't have to worry about core object.
	 */ 
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
}
