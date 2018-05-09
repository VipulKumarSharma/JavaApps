package service;

public class FactoryService {
	
	public Object getBean(String beanType) {
		/* Proxy Class 
		 * Instead of returning an instance of ShapeService, return instance of ShapeServiceProxy 
		 */
		
		if("shapeService".equals(beanType)) {
			return new ShapeServiceProxy();
		
		} else if("circle".equals(beanType)) {
			return new Shape();
		
		} else if("triangle".equals(beanType)) {
			return new Shape();
		}
		
		return null;
	}

}
