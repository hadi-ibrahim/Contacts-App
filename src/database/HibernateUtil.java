package database;

import java.io.File;
import java.util.logging.Level;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.slf4j.event.Level;


/**
* Class to setup connection to database and store the session
* @author  Hadi Ibrahim
* @version 1.0
*/
public class HibernateUtil {

		private SessionFactory sessionFactory ;

		// constructor will create a session and store it in a variable
	    public HibernateUtil() {
	    	
	    	System.out.println("Initializing DB connection...");
	    	
	    	// Disable console logging
	    	java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	    	
	    	   try {
	    			StandardServiceRegistry standardRegistry = 
	    		       new StandardServiceRegistryBuilder().configure(new File("hibernate.cfg.xml")).build();
	    			Metadata metaData = 
	    		        new MetadataSources(standardRegistry).getMetadataBuilder().build();
	    			sessionFactory = metaData.getSessionFactoryBuilder().build();
	    			System.out.println("Done!");
//	    			this.sessionFactory;
	    		   } catch (Throwable th) {
	    			System.err.println("Initial SessionFactory creation failed" + th);
	    			throw new ExceptionInInitializerError(th);
	    		  }
	      }
	    
	    public SessionFactory getSessionFactory() {
	    	return sessionFactory;
	    }
	    
	    public void shutdown() {
	        // Close caches and connection pools
	        getSessionFactory().close();
	    }

}
