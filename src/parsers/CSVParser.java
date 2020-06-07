package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import data.Address;
import data.Contact;
import data.Contacts;
import data.Location;
import data.Organization;
import data.Phone;
import data.Website;

/**
* class to parse data from a csv file
* @author  Hadi Ibrahim
* @version 1.0
*/
public class CSVParser implements IParser {
	
	   /**
	   * this method reads the information of a contact from a csv file
	   * @param path  This is the path to the csv file
	   * @return Contacts the contacts wrapper class of the set of all the contacts
	   */
	public Contacts readData(String path) {
		FileReader fileReader;
		Set<Contact> contacts = new HashSet<Contact>();
		try {
			fileReader = new FileReader (path);
			BufferedReader reader = new BufferedReader (fileReader);
			String line ="";
			// first line contains column names
			line= reader.readLine(); 	
			while((line=reader.readLine())!=null) {
				
				String [] recordData = line.split(",(?=[^ ])");
				//match "," if the next character is not a space
				String name = recordData[0];
				String familyName = recordData[1];
				String birthday = recordData[2];
				String gender= recordData[3];
				Location location = new Location (Double.parseDouble(recordData[4]), Double.parseDouble(recordData[5])); //TODO edit location to longitude and lattitude 
				String occupation = recordData[6];
				String notes = recordData[7];
				String group = recordData[8];
				String email = recordData[9];
				//			String phoneOneType= recordData[9];
				//			String phoneOneNumber = recordData[10];
				Phone phoneOne = new Phone(recordData[10],recordData[11]);
				//			String phoneTwoType = recordData[11];
				//			String phoneTwoNumber = recordData[12];
				Phone phoneTwo = new Phone(recordData[12],recordData[13]);
				//			String addressType = recordData[13];
				//			String addressStreet = recordData[14];
				//			String addressCity = recordData[15];
				//			String addressRegion = recordData[16];			
				//			String addressCountry = recordData[17];
				Address address = new Address(recordData[14],recordData[15],recordData[16],recordData[17],recordData[18]);
				//			String organizationType = recordData[18];
				//			String organizationName = recordData[19];
				//			String organizationLocation = recordData[20];
				//			String organizationJobDescripton = recordData[21];
				// TODO edit locations here too 
				Organization organization = new Organization(recordData[19], recordData[20],new Location(Double.parseDouble(recordData[21]),Double.parseDouble(recordData[22])),recordData[23]);
				//			String websiteType recordData[23];
				//			String websiteUrl = recordData[24];
				Website website ;
				try {
					website =new Website(recordData[24],recordData[25]);
				}catch(ArrayIndexOutOfBoundsException e) {
					website = null ;
				}
				contacts.add(new Contact(name,familyName,birthday,gender,location,
						occupation,notes,group,email,phoneOne,phoneTwo, address,organization,website));
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return new Contacts(contacts);
	}

}

//==========================   	write appropriate xml file to read from 	========================== //
//FileWriter writer = new FileWriter ("OOP_MOCK_DATA_212.csv");
//String csv="name,familyName,birthday,gender,locationLongitude,locationLatitude,occupation,notes,group,email,phoneOneType,phoneOneNumber,"
//		+ "phoneTwoType,phoneTwoNumber,addressType,addressStreet,addressCity,addressRegion,addressCountry," + 
//		"organizationType,organizationName,organisationLocationLongitude,organizationLocationLatitude,"+ 
//		"organizationJobDescription,websiteType,websiteUrl\n";
//for (Contact contact : contacts.getContact()) {
//	if(contact.getWebsite()==null) {
//		csv+=contact.getName() + "," + contact.getFamilyName() + "," + contact.getBirthday() +"," + contact.getGender() + "," + contact.getLocation().getLongitude() + "," + contact.getLocation().getLatitude() +
//				"," + contact.getOccupation() + ",\"" + contact.getNotes() + "\"," + contact.getGender() + "," + contact.getEmail() + "," + contact.getPhoneOne().getType() +"," + contact.getPhoneOne().getNumber() + ",\"" + 
//				contact.getPhoneTwo().getType() +"," + contact.getPhoneTwo().getNumber() + "," + contact.getAddress().getType() + "," + contact.getAddress().getStreet() + "," + contact.getAddress().getCity() + "," +
//				contact.getAddress().getRegion() + "\"," + contact.getAddress().getCountry() + "," + contact.getOrganization().getType() + "," + contact.getOrganization().getName() + "," +
//				contact.getOrganization().getLocation().getLongitude() + "," + contact.getOrganization().getLocation().getLatitude() + "," + contact.getOrganization().getJobDescription() + "," 
//				 + "," +"\n";
//	}
//	else {
//	csv+=contact.getName() + "," + contact.getFamilyName() + "," + contact.getBirthday() +"," + contact.getGender() + "," + contact.getLocation().getLongitude() + "," + contact.getLocation().getLatitude() +
//			"," + contact.getOccupation() + ",\"" + contact.getNotes() + "\"," + contact.getGender() + "," + contact.getEmail() + "," + contact.getPhoneOne().getType() +"," + contact.getPhoneOne().getNumber() + ",\"" + 
//			contact.getPhoneTwo().getType() +"," + contact.getPhoneTwo().getNumber() + "," + contact.getAddress().getType() + "," + contact.getAddress().getStreet() + "," + contact.getAddress().getCity() + "," +
//			contact.getAddress().getRegion() + "\"," + contact.getAddress().getCountry() + "," + contact.getOrganization().getType() + "," + contact.getOrganization().getName() + "," +
//			contact.getOrganization().getLocation().getLongitude() + "," + contact.getOrganization().getLocation().getLatitude() + "," + contact.getOrganization().getJobDescription() + "," +
//			contact.getWebsite().getType() + "," + contact.getWebsite().getUrl()+"\n";
//	}
//}
//writer.write(csv);

