package spring.implementations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {

	/*  BeanPostProcessor tells Spring that there is some processing spring needs to do after initializing the bean.
	 *  Spring executes that code after initializing each & every bean.
	 */
	
	/*  It is called after initializing each & every bean.
	 *  It is called before InitializingBean Interface afterPropertiesSet()
	 *  It is called after Class constructor, BeanNameAware Interface setBeanName & ApplicationContextAware Interface setApplicationContext()
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("\n["+beanName+"] BeanPostProcessor : postProcessBeforeInitialization() : before initializing bean");
		return bean;
	}
	
	/*  It is called before destroying each & every bean.
	 *  It is called before DisposableBean Interface destroy()
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("\n["+beanName+"] BeanPostProcessor : postProcessAfterInitialization() : after initializing bean");
		return bean;
	}

}
