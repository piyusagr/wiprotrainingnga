package com.company.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
public class HibernateUtil {
	
	
	private static final SessionFactory sessionFactory = build();
	
	

	private static SessionFactory build() {
	    try {
	    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	    	configuration.addAnnotatedClass(com.company.model.Student.class);
	    	StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
	    	    .applySettings(configuration.getProperties());
	    	SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());

	        return sessionFactory;
	    } catch (Exception ex) {
	        throw new RuntimeException("Initial SessionFactory creation failed." + ex);
	    }
	}

	 
	 public static SessionFactory getSessionFactory()
	 {
		 return sessionFactory;
	 }

	 

	 public static void   close()
	 {
		  sessionFactory.close();
		 	 }
}