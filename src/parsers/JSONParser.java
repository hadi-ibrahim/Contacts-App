package parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.Contact;
import data.Contacts;

/**
* class to parse data from a JSON file
* @author  Hadi Ibrahim
* @version 1.0
*/
public class JSONParser implements IParser{
	   /**
	   * this method reads the information of a contact from a JSON file
	   * @param path  This is the path to the JSON file
	   * @return Contacts the contacts wrapper class of the set of all the contacts
	   */
	public Contacts readData(String path) {
		
		Set<Contact> contacts = new HashSet<Contact> ();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Reader reader;
		try {
			reader = new FileReader(path);
			Contact [] contactsFromJson = gson.fromJson(reader, Contact[].class);
			Collections.addAll(contacts, contactsFromJson);
			return new Contacts(contacts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}

//================== write appropriate json file to read from ================== //
//List<Contact> list = Arrays.asList(contactsFromJson);  
//Reader reader = new FileReader("OOP_MOCK_DATA_DETAILED.json");
//AtomicContactDummyClass[] list = new Gson().fromJson(reader, AtomicContactDummyClass[].class);
//Contact temp;
//ArrayList<Contact> contactsJson =new ArrayList<Contact>();
//for (AtomicContactDummyClass example : list) {
//	temp= new Contact(example.name,example.familyName,example.birthday,example.gender,example.locationLongitude,example.locationLatitude,example.occupation,example.notes,
//						example.group,example.email,example.phoneOneType,example.phoneOneNumber,example.phoneTwoType,example.phoneTwoNumber,
//						example.addressType,example.addressStreet,example.addressCity,example.addressRegion,example.addressCountry,example.organizationType,example.organizationName,example.organizationLocationLongitude,example.organizationLocationLatitude,
//						example.organizationJobDescription,example.websiteType,example.websiteUrl);
//	contactsJson.add(temp);
//}
//Gson gson = new GsonBuilder().setPrettyPrinting().create();
//Contact[] contactsList = new Contact [contactList.size()];
//contactsList = contactList.toArray(contactsList);
//String json = gson.toJson(contactsList);
//System.out.println(json);
//FileWriter writer = new FileWriter ("OOP_MOCK_DATA_212.json");
//writer.write(json);
//}