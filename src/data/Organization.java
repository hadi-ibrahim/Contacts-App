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
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

import com.sun.xml.txw2.annotation.XmlElement;

import database.IDbEntity;

@XmlElement
@Entity
@Table(name = "ORGANIZATION")//, uniqueConstraints = 
//        @UniqueConstraint(columnNames={"ORGANIZATION_TYPE", "ORGANIZATION_NAME", "LOCATION",
//        		"ORGANIZATION_JOB"})
//)
/**
* container class for all organization attributes
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Organization implements Serializable ,IDbEntity {
	

	private static final long serialVersionUID = -8212406003812302532L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORGANIZATION_ID", unique = true, nullable = true)
    private long id;
    
	@Column(name = "ORGANIZATION_TYPE", unique = false, nullable = true, length = 50)
    private String type;
    
	@Column(name = "ORGANIZATION_NAME", unique = false, nullable = true, length = 100)
    private String name;

    @OneToOne(cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name="LOCATION",referencedColumnName="LOCATION_ID")	
	private Location location;

	@Column(name = "ORGANIZATION_JOB", unique = false, nullable = true, length = 100)
    private String jobDescription;
	
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "organization", orphanRemoval = true, fetch=FetchType.LAZY)
	private Set<Contact> contacts;
	
	public Organization(String type, String name, Location location, String jobDescription) {
		this.type = type;
		this.name = name;
		this.location = location;
		this.jobDescription = jobDescription;
	}
	
	public Organization(String type, String name, double longitude, double latitude, String jobDescription) {
		this.type = type;
		this.name = name;
		this.location = new Location(longitude,latitude);
		this.jobDescription = jobDescription;
	}
	
	public Organization() {};

	public Set<Contact> getContacts() {
		return contacts;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
