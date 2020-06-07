package data;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

import com.sun.xml.txw2.annotation.XmlElement;

import database.IDbEntity;
@XmlElement
@Entity
@Table(name = "ADDRESS")//, uniqueConstraints = 
//        @UniqueConstraint(columnNames={"ADDRESS_TYPE", "ADDRESS_STREET",
//        		"ADDRESS_CITY","ADDRESS_REGION","ADDRESS_COUNTRY"}))

/**
* This class is a container for address attributes
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Address implements Serializable,IDbEntity{
	

	private static final long serialVersionUID = -2561772990708378984L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", unique = true, nullable = false)
	private long id;

	@Column(name = "ADDRESS_TYPE", unique = false, nullable = true, length = 32)
	private String type;
    
	@Column(name = "ADDRESS_STREET", unique = false, nullable = true, length = 100)
	private String street;
    
	@Column(name = "ADDRESS_CITY", unique = false, nullable = true, length = 32)
	private String city;
    
	@Column(name = "ADDRESS_REGION", unique = false, nullable = true, length = 32)
    private String region;
	
	@Column(name = "ADDRESS_COUNTRY", unique = false, nullable = true, length = 32)
    private String country;
	
	
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "address", orphanRemoval = true, fetch=FetchType.LAZY)
	private Set<Contact> contacts;
	
	public Address(String type, String street, String city, String region, String country) {
		super();
		this.type = type;
		this.street = street;
		this.city = city;
		this.region = region;
		this.country = country;
	}
	
	public Address() {};

	public long getId() {
		return this.id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return type + ", " + street + ", " + city + ", " + region + ", "
				+ country;
	}
	
}
