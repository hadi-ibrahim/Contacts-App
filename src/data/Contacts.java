package data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
* Container class for the set of all contacts
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Contacts implements Serializable{

	private static final long serialVersionUID = 3593742082792904571L;

	private Set<Contact>contacts = new HashSet <Contact>();

	public Contacts () {};
	
	public Contacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@XmlElement
	public Set<Contact> getContact() {
		return contacts;
	}

}
