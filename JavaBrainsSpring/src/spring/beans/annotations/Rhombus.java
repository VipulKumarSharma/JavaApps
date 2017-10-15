package spring.beans.annotations;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import spring.beans.Coordinates;
import spring.interfaces.Shape;

@Controller
public class Rhombus implements Shape {

	private Coordinates center;
	public Coordinates getCenter() {
		return center;
	}
	@Resource
	public void setCenter(Coordinates center) {
		this.center = center;
	}
	
	/*	To access getMessage() of MessageSorce, you have to inject messageSource here
	 * 	You can inject by using type (i.e. @Autowired)
	 */
	@Autowired
	MessageSource messageSource;
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void draw() {
		System.out.println("\n"+this.messageSource.getMessage("label.drawRhombus", null, "Drawing Anonymous Shape", Locale.ENGLISH));
		System.out.println(this.messageSource.getMessage("label.rhombusCenter", new Object[] {center.getX(), center.getY()}, "Drawing Anonymous Shape", Locale.JAPANESE));
	}

}
