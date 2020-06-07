package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.txw2.annotation.XmlElement;

import database.IDbEntity;

@XmlElement
@Entity
@Table(name = "PHONE")//, uniqueConstraints = 
//        @UniqueConstraint(columnNames={"PHONE_TYPE", "PHONE_NUMBER"})
//)
/**
* container class for all phone attributes
* @author  Hadi Ibrahim
* @version 1.0
*/
public class Phone implements Serializable ,IDbEntity{
    
	private static final long serialVersionUID = -6039285908921321099L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID", unique = true, nullable = false)
	public long id;
	
	@Column(name = "PHONE_TYPE", unique = false, nullable = true, length = 20)
	private String type;
    
	@Column(name = "PHONE_NUMBER", unique = true, nullable = true, length = 32)
    private String number;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
	 private Contact contact;
	 
	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}
	
	public Phone() {};
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlTransient
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "type: " + type + ", number: " + number ;
	}
	
}
