package spring.implementations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {

	/*  BeanFactoryPostProcessor have postProcessBeanFactory() which run before bean factory is initialized.
	 *  BeanFactoryPostProcessor methods will be called before BeanPostProcessor methods.
	 */
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("\nBean Factory will be initialized...");
		
	}

}
