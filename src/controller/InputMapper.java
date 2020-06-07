package controller;

import java.util.HashMap;
import java.util.Map;

import data.Address;

import data.Location;
import data.Organization;
import data.Phone;
import data.Website;
import database.IDbEntity;

/**
* class to contain maps to map input to functions
* @author  Hadi Ibrahim
* @version 1.0
*/
public class InputMapper {
	
	// add important keywords here for more questions, then add case in the corresponding switch
	   /**
	    * maps keywords from input to defined keywords
	   */
	
	public static Map <String,String> filterInput = new HashMap <String,String> (){

		private static final long serialVersionUID = 1L;

	{
		put("update","update");
		put("edit","update");
		put("show", "select");
		put("select","select");
		put("get", "select");
		put("search", "select");
		put("find", "select");
		put("delete", "delete");
		put("remove", "delete");		
		put("add", "create");
		put("create",  "create");
		put("contacts", "contacts");
		put("file", "file");

	}
	};
	
	// add all attribute names here, and link their aliases too
	   /**
	    * maps all attributes from input to the attribute names in program 
	   */
	public static Map <String,String> attributeNames = new HashMap <String,String> (){

		private static final long serialVersionUID = 1L;

	{
		put("name","name");
		put("family","familyName");
		put("email", "email");
		put("occupation","occupation");
		put("country", "country");
		put("number", "number");
		put("type", "type");
	}
	};
	
	// map entity name as string to entity class
	   /**
	    * map entity name as string to entity class
	   */
	public static Map <String,Class<? extends IDbEntity>> stringToClass = new HashMap <String,Class<? extends IDbEntity>> (){

		private static final long serialVersionUID = 3L;

		{
			put("phoneOne", Phone.class);
			put("phoneTwo", Phone.class);
			put("organization", Organization.class);
			put("address", Address.class);
			put("location", Location.class);
			put("website", Website.class);
			put("phone", Phone.class);
		}
	};
	
	// map String from input to entity name in program
	   /**
	   * map String from input to entity name in program
	   */
	public static Map <String,String> stringToClassName = new HashMap <String,String> (){

		private static final long serialVersionUID = 3L;

		{
			put("phone1", "phoneOne");
			put("phone2", "phoneTwo");
			put("organization", "organization");
			put("address", "address");
			put("location", "location");
			put("website", "website");
			put("phone", "phoneOne");
			put("web", "website");

		}
	};
	
}