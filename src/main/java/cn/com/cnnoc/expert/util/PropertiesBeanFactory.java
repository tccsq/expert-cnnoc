package cn.com.cnnoc.expert.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesBeanFactory implements BeanFactory {
	Map beans = new HashMap();
	
	public PropertiesBeanFactory() {
		this("bean.properties");
	}
	
	public PropertiesBeanFactory(String configLocation) {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(configLocation));

			for (Iterator iterator = prop.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = (String) entry.getKey();
				String className = (String) entry.getValue();

				Class clz = Class.forName(className);
				Object bean = clz.newInstance();
				beans.put(key, bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public Object getBean(String name) {
		return beans.get(name);
	}

}
