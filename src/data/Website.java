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

import com.sun.xml.txw2.annotation.XmlElement;

import database.IDbEntity;

@XmlElement
@Entity
@Table(name = "WEBSITE")

/**
* container class for all website attributes
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Website implements Serializable, IDbEntity {
    

	private static final long serialVersionUID = -672998856281041347L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEBSITE_ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "WEBSITE_TYPE", unique = false, nullable = true, length = 10)
	private String type;
    
	@Column(name = "WEBSITE_URL", unique = true, nullable = true, length = 200)
    private String url;
	
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "website", orphanRemoval = true, fetch= FetchType.LAZY)
    private Set<Contact> contacts;
	
	public Website(String type, String url) {
		super();
		this.type = type;
		this.url = url;
	}

	public Website() {};
	
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
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
