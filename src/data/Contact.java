package data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;


import com.sun.xml.txw2.annotation.XmlElement;

import database.IDbEntity;

@XmlElement
@Entity
@Table(name = "CONTACT")//,uniqueConstraints = 
//        @UniqueConstraint(columnNames={ "EMAIL" , "PHONE1_ID", "PHONE2_ID"})
//        )
/**
*Container class for all contact information 
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Contact implements Serializable, IDbEntity{


	private static final long serialVersionUID = 6427949817145513488L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID", unique = true, nullable = false)
	private long id;
	
    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 32)
	private String name;

	@Column(name = "LAST_NAME", unique = false, nullable = true, length = 32)
	private String familyName;
    
	@Column(name = "BIRTHDAY", unique = false, nullable = true, length = 32)
	private String birthday;
    
	@Column(name = "GENDER", unique = false, nullable = true, length = 50)
	private String gender;
	
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="LOCATION",referencedColumnName="LOCATION_ID")	
    private Location location; 
    
	@Column(name = "OCCUPATION", unique = false, nullable = true, length = 100)
	private String occupation;
    
	@Column(name = "NOTES", unique = false, nullable = true, length = 1000)
	private String notes;
    
	@Column(name = "GROUPNAME", unique = false, nullable = true, length = 100)
	private String group;
    
    @Column(name = "EMAIL", unique = true, nullable = true, length = 100)
	private String email;
     
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="PHONE1_ID",referencedColumnName="PHONE_ID")
    private Phone phoneOne;
	
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="PHONE2_ID",referencedColumnName="PHONE_ID")
	private Phone phoneTwo;
	
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ADDRESS",referencedColumnName="ADDRESS_ID",nullable = true)
	private Address address;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ORGANIZATION",referencedColumnName="ORGANIZATION_ID")	
    private Organization organization;
	
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="WEBSITE",referencedColumnName="WEBSITE_ID")	
    private Website website ;
	
	public Contact() {};
	public Contact(String name, String familyName, String birthday, String gender, double locationLongitude, double locationLatitude, String occupation,
			String notes, String group, String email, String phoneOneType,String phoneOneNumber ,String phoneTwoType,String phoneTwoNumber,
			String addressType, String addressStreet, String addressCity, String addressRegion, String addressCountry,
			String organizationType, String organizationName, double organisationLocationLongitude,double organizationLocationLatitude,
			String organizationJobDescription, String websiteType, String websiteUrl) {

		this.name = name;
		this.familyName = familyName;
		this.birthday = birthday;
		this.gender = gender;
		this.location = new Location(locationLongitude, locationLatitude);
		this.occupation = occupation;
		this.notes = notes;
		this.group = group;
		this.email = email;
		this.phoneOne = new Phone(phoneOneType, phoneOneNumber);
		this.phoneTwo = new Phone(phoneTwoType, phoneTwoNumber);

		this.address = new Address(addressType, addressStreet, addressCity, addressRegion, addressCountry);
		this.organization = new Organization(organizationType, organizationName, new Location( organisationLocationLongitude,organizationLocationLatitude),
				organizationJobDescription);
		this.website = new Website (websiteType, websiteUrl);
	}

	public Contact(String name, String familyName, String birthday, String gender, Location location, String occupation,
			String notes, String group, String email, Phone phoneOne, Phone phoneTwo, Address address,
			Organization organization, Website website) {

		this.name = name;
		this.familyName = familyName;
		this.birthday = birthday;
		this.gender = gender;
		this.location = location;
		this.occupation = occupation;
		this.notes = notes;
		this.group = group;
		this.email = email;
		this.phoneOne = phoneOne;
		this.phoneTwo = phoneTwo;
		this.address = address;
		this.organization = organization;
		this.website = website;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@XmlElement
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@XmlElement
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@XmlElement
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@XmlElement
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@XmlElement
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@XmlElement
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public Phone getPhoneOne() {
		return phoneOne;
	}

	public void setPhoneOne(Phone phoneOne) {
		this.phoneOne = phoneOne;
	}

	@XmlElement
	public Phone getPhoneTwo() {
		return phoneTwo;
	}

	public void setPhoneTwo(Phone phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	@XmlElement
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@XmlElement
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@XmlElement
	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}
	
	@Override
	public String toString() {
		if (website!=null)
		return "ID: " + id + "\nName: " + name + "\nFamily Name: " + familyName + "\nE-mail: " + email + "\nPhone 1: "+ phoneOne + "\nPhone 2: " +
				phoneTwo + "\nOccupation: " + occupation + "\nOrganization: " + organization + "\nWebsite: " +website;
		else 		
			return "ID: " + id + "\nName: " + name + "\nFamily Name: " + familyName + "\nE-mail: " + email + "\nPhone 1: "+ phoneOne + "\nPhone 2: " +
			phoneTwo + "\nOccupation: " + occupation + "\nOrganization: " + organization ;
	}
	
}
