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
@Entity()
@Table(name = "LOCATION")//, uniqueConstraints = 
//        @UniqueConstraint(columnNames={"LOCATION_LONGITUDE", "LOCATION_LATITUDE"})
//)
/**
* container class for all location attributes
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Location implements Serializable,IDbEntity {
    

	private static final long serialVersionUID = 6458169939340247664L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
	private long id;
	
	@Column(name = "LOCATION_LONGITUDE", unique = false, nullable = true, length = 100,updatable=true)
	private double longitude;
    
	@Column(name = "LOCATION_LATITUDE", unique = false, nullable = true, length = 100,updatable=true)
    private double latitude;

	@OneToMany(cascade = CascadeType.ALL,
            mappedBy = "location",fetch= FetchType.LAZY)
	private Set<Contact> contacts;
	
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "location", fetch= FetchType.LAZY)
	private Set<Organization> organizations;
	
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Location() {};
	
	public double getLongitude() {
		return longitude;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	
}
