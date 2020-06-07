package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import data.Address;
import data.Contact;
import data.Location;
import data.Organization;
import data.Phone;
import data.Website;
import parsers.CSVParser;
import parsers.IParser;
import parsers.JSONParser;
import parsers.XMLParser;
/**
* class to contain helper methods 
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Operations {
	   /**
	   * this method prints a list of resutls
	   * @param results  This is the list of objects to print
	   */
	public static void printResults(List<?> results) {
		
		if (results != null) {
			System.out.println("Contacts found: " + results.size());
			results.forEach(result -> System.out.println(result));
		}
		else System.out.println("No contacts found.");
		
	}
	
	   /**
	   * this method determines which parser to choose based on the format
	   * @param path  This is the path to the file
	   * @return a corresponding parser to the file, null if not supported
	   */
	public static IParser determineParserFromFile( String path) {
		String [] pathDetails = path.split("\\.");
		String format = pathDetails[pathDetails.length-1];
		
		if (format.equals("xml"))
			return new XMLParser();
		else if (format.equals("json"))
			return new JSONParser();
		else if (format.equals("csv"))
			return new CSVParser();
			else System.out.println("Unsupported format");
				return null;
	}
	
	   /**
	   * this method reads the information of a contact from user input
	   * @throws IOException
	   * @return Contact the contact object filled with specified information
	   */
	public static Contact fillContact() throws IOException {
		try {
		InputStreamReader reader=new InputStreamReader(System.in);  
        BufferedReader bufferedReader=new BufferedReader(reader);  

		System.out.print("Enter contact name: ");
		String name = bufferedReader.readLine();
		System.out.print("Enter contact family name: ");
		String familyName = bufferedReader.readLine();
		System.out.print("Enter contact birthday: ");
		String birthday = bufferedReader.readLine();
		System.out.print("Enter contact gender: ");
		String gender= bufferedReader.readLine();
		System.out.print("Enter contact location longitude: ");
		
		// fill location
		String locationLongitude= bufferedReader.readLine();
		System.out.print("Enter contact location latitude: ");
		String locationLatitude= bufferedReader.readLine();
		Location location= null;
		if (!locationLongitude.isBlank() && locationLatitude.isBlank())
			location = new Location (Double.parseDouble(locationLongitude),Double.parseDouble(locationLatitude));
		
		System.out.print("Enter contact occupation: ");
		String occupation = bufferedReader.readLine();
		System.out.print("Enter contact notes: ");
		String notes = bufferedReader.readLine();
		System.out.print("Enter contact group: ");
		String group = bufferedReader.readLine();
		System.out.print("Enter contact email: ");
		String email = bufferedReader.readLine();
		
		// fill phone 1
		System.out.print("Enter contact phone 1 type: ");
		String phoneOneType= bufferedReader.readLine();
		System.out.print("Enter contact phone 1 number: ");
		String phoneOneNumber = bufferedReader.readLine();
		Phone phoneOne = new Phone(phoneOneType, phoneOneNumber);
		
		// fill phone 2
		System.out.print("Enter contact phone 2 type: ");
		String phoneTwoType = bufferedReader.readLine();
		System.out.print("Enter contact phone 2 number: ");
		String phoneTwoNumber = bufferedReader.readLine();
		Phone phoneTwo = new Phone(phoneTwoType, phoneTwoNumber);
		
		// fill address
		System.out.print("Enter contact address type: ");
		String addressType = bufferedReader.readLine();
		System.out.print("Enter contact address street: ");
		String addressStreet = bufferedReader.readLine();
		System.out.print("Enter contact address city: ");
		String addressCity = bufferedReader.readLine();
		System.out.print("Enter contact address region: ");
		String addressRegion = bufferedReader.readLine();			
		System.out.print("Enter contact address country: ");
		String addressCountry = bufferedReader.readLine();
		Address address = new Address(addressType,addressStreet,addressCity,addressRegion,addressCountry);
				
		// fill organization
		System.out.print("Enter contact organization type: ");
		String organizationType = bufferedReader.readLine();
		System.out.print("Enter contact organization name: ");
		String organizationName = bufferedReader.readLine();
		System.out.print("Enter contact organization location longitude: ");
		String organizationLocationLongitude = bufferedReader.readLine();
		System.out.print("Enter contact organization location latitude: ");
		String organizationLocationLatitude = bufferedReader.readLine();
		System.out.print("Enter contact organization job description: ");
		String organizationJobDescription = bufferedReader.readLine();
		Location orgLocation = null;
			if(!organizationLocationLongitude.isBlank() && organizationLocationLatitude.isBlank())
				orgLocation = new Location (Double.parseDouble(organizationLocationLongitude), Double.parseDouble(organizationLocationLatitude));
			Organization organization =new Organization(organizationType,organizationName,orgLocation,organizationJobDescription);

		// fill web
		System.out.print("Enter contact website type: ");
		String websiteType =bufferedReader.readLine();
		System.out.print("Enter contact website url: ");
		String websiteUrl = bufferedReader.readLine();
		Website website = null;
		if (!websiteUrl.isBlank())
			website = new Website(websiteType,websiteUrl);

		return new Contact(name,familyName,birthday,gender,location,
				occupation,notes,group,email,phoneOne,phoneTwo, address,organization,website);
		}catch (IOException e ) {
			System.out.println("Error reading input.");
			return null;
		}catch(Exception e) {
			System.out.println("Invalid value entered.");
			return null;
		}

		
	}
}
