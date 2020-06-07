package parsers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import data.Contacts;

/**
* class to parse data from an XML file
* @author  Hadi Ibrahim
* @version 1.0
*/
public class XMLParser implements IParser{
	
	   /**
	   * this method reads the information of a contact from a XML file
	   * @param path  This is the path to the XML file
	   * @return Contacts the contacts wrapper class of the set of all the contacts
	   */
	public Contacts readData (String path) {
		
		
		File xmlFile = new File(path);
		 
		JAXBContext jaxbContext;
		try
		{
		    jaxbContext = JAXBContext.newInstance(Contacts.class);              
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    Contacts contacts =  (Contacts) jaxbUnmarshaller.unmarshal(xmlFile);
		     
		    return contacts;

		}
		catch (JAXBException e) 
		{
			System.out.println("exception");
		    e.printStackTrace();
		}
		return null;
	}

}

//================== write appropriate xml file to read from ================== //
//try {
//    // create an instance of `JAXBContext`
//    JAXBContext context = JAXBContext.newInstance(Contacts[].class);
//
//    // create an instance of `Marshaller`
//    Marshaller marshaller = context.createMarshaller();
//
//    // enable pretty-print XML output
//    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//
//    // convert `Books` object to XML file
//    marshaller.marshal(contacts, new File("test3.xml"));
//
//    // print XML to console
////    marshaller.marshal(books, System.out);
//
//} catch (JAXBException ex) {
//    ex.printStackTrace();
//}
//