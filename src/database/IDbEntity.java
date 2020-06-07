package database;

import java.io.Serializable;
/**
* all classes that are stored in the database must implement this interface to perform CRUD operations
* @author  Hadi Ibrahim
* @version 1.0
*/
public interface IDbEntity extends Serializable{
	
	void setId(long id);
	long getId();

}
