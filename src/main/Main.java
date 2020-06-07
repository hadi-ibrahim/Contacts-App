package main;



import java.io.IOException;

import org.hibernate.Session;

import controller.CliController;
import database.HibernateUtil;
/**
* The main class to initiate the program
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Main {

	public static void main (String args[]) throws IOException {
			
		HibernateUtil connection = new HibernateUtil();
		Session session = connection.getSessionFactory().openSession();
		CliController.startChatbot(session); 
		connection.shutdown();
	}
	
}

 