package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import data.Contact;
import data.Contacts;
import database.HibernateCRUD;
import parsers.IParser;
/**
* class to make a chatbot
* @author  Hadi Ibrahim
* @version 1.0
*/
public class CliController {
	   /**
	   *  this method reads input from user and calls the appropriate functions
	   *  based on it, the user has to type "exit" to break out.
	   *  @throws IOException
	   *  @param session this is the session that is connected to the database
	   */
	public static void startChatbot (Session session) throws IOException {
		InputStreamReader reader=new InputStreamReader(System.in);  
        BufferedReader bufferedReader=new BufferedReader(reader);  

		String command ="" ;
		ArrayList <String> commandLine;
		List<?> results ;
		System.out.println("Hello! I am chatbot. How may I help you today?" );
		
		while (true) {
			System.out.println("Enter your command : ");
			Set<String> keywords= new HashSet <String>();
			Map<String,String> entitiesAndValues= new HashMap<String,String>();
			command = bufferedReader.readLine().toLowerCase();
			commandLine = new ArrayList<String> (Arrays.asList(command.split("\\s")));
			
			if (command.equals("exit"))	{		//========================================> only exit out of the CLI
				System.out.println("Thank you for coming. Bye!");
				break;
			}
			
	
			for(int i = 0 ; i< commandLine.size(); i++) {
				String word = commandLine.get(i);
				
				// follows the format : <Class> <attribute> <value>
				if(InputMapper.stringToClassName.containsKey(word)) {
					try {
						String attribute = commandLine.get(i + 1);
						if(InputMapper.attributeNames.containsKey(attribute)) {
							String value = commandLine.get(i+2);
							entitiesAndValues.put(InputMapper.stringToClassName.get(word) + " " +InputMapper.attributeNames.get(attribute), value);
						}
					} catch (IndexOutOfBoundsException e) {}
				}	
				
				// follows the formal : <!Class> <attribute> <value>
				if(InputMapper.attributeNames.containsKey(word)) {
					try {
						if(!InputMapper.stringToClassName.containsKey(commandLine.get(i-1))) {
						String value = commandLine.get(i+1);
						entitiesAndValues.put(InputMapper.attributeNames.get(word), value);
						}
					
					// to make sure it doesn't appear elsewhere 
					} catch (IndexOutOfBoundsException e) {}
				}
				
				//map important keywords to a their meaning
				if(InputMapper.filterInput.containsKey(word))
					keywords.add(InputMapper.filterInput.get(word));
			}	

			// if no country was specified, break out and try again
			if(entitiesAndValues.isEmpty() && keywords.isEmpty()) {
				System.out.println("Ambigious command. Try again");     
			}
			
			else {
				
				//============================= SELECT QUERY =============================//
				if(keywords.contains("select")) {					
					if (keywords.contains("contacts")) {
						results=HibernateCRUD.executeQuery("FROM Contact", session);
						Operations.printResults(results);
					}
					else if (!entitiesAndValues.isEmpty()){
						results=HibernateCRUD.searchBasedOnCriteria (entitiesAndValues,session) ;
						Operations.printResults(results);
					}
					else System.out.println("Specify search criteria.");
				}

				//========================================================================//
				
				//============================ UPDATE QUERY ==============================//		
				else if(keywords.contains("update") ) {
					System.out.println("Enter the ID of the contact to edit");
					long id = Long.parseLong(bufferedReader.readLine());
					System.out.println("Fill the following contact information");
					Contact contact = Operations.fillContact();
					HibernateCRUD.updateEntity(id, contact, session);
					
				//========================================================================//
					
				//============================ CREATE QUERY ==============================//		
					}
				else if(keywords.contains("create")) {
					System.out.println("Fill the following contact information");
					Contact contact = Operations.fillContact();
					if (contact != null)
						HibernateCRUD.createEntity(contact, session);
					}
				//========================================================================//
				
				//============================ DELETE QUERY ==============================//	
				else if(keywords.contains("delete")) {
					System.out.println("Enter the ID of the contact to delete");
					long id = Long.parseLong(bufferedReader.readLine());
					HibernateCRUD.deleteEntity(id, Contact.class, session);
					}
				
				//========================================================================//
				
				//========================== FILE BATCH INSERT ===========================//		
				else if(keywords.contains("file")) {
					System.out.println("Enter the path to the file you want to load data from: ");
					String path = bufferedReader.readLine();
					IParser parser = Operations.determineParserFromFile(path);
					Contacts contacts=null;
					if(parser!= null) {
						contacts =parser.readData(path);
						HibernateCRUD.batchInsertContacts(contacts, session);
					}
					else System.out.println("File not found");
				}
				//========================================================================//

				else System.out.println("Ambigous command. Try again.");
			}
		}
	}
}
