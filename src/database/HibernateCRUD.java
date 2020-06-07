package database;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import controller.InputMapper;
import data.Contact;
import data.Contacts;

/**
* Class to perform CRUD operations on the entities in the database.
* @author  Hadi Ibrahim
* @version 1.0
*/
public class HibernateCRUD {

//	==============================     FILTER SELECT OF DATA	 	==============================	//		

	//TODO implement builder design pattern to solve unchecked cast, silence error until then
	@SuppressWarnings("unchecked")
	   /**
	   * this method searches for contacts and filters based on specified criteria
	   * @param attributeValuePair this is a map to build a predicate based on the attributes and corresponding pairs
	   * @param session this is the session that is connected to the database
	   * @return List of contacts that matched the search criteria 
	   */
	public static List<Contact>	searchBasedOnCriteria (Map<String,String> attributeValuePair,Session session) {
		try {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		Predicate predicate= builder.and();
		CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
		Root<Contact> root = query.from(Contact.class);
    // each entry represents a filter to apply 
		for(Entry<String,String> pair : attributeValuePair.entrySet()) {
			String[] key = pair.getKey().split(" ");
			// filtering an attribute of Contact
			if (key.length ==1)
				predicate= builder.and(predicate,builder.like(root.get(pair.getKey()),"%" +  pair.getValue() + "%" )); 
			// filtering an entity associated to Contact
			else {
				// map Class type from string 
				Class<? extends IDbEntity> type =InputMapper.stringToClass.get(key[0]);
				Subquery<Object> subquery=(Subquery<Object>) query.subquery(type);
				// unchecked warning here
				Root<?> subRoot = subquery.from(InputMapper.stringToClass.get(key[0]));

				// generate the conditions of filters and link them with AND operator
				predicate = builder.and(predicate,
						builder.in(root.get(key[0])).value(
								subquery.select((Expression<Object>) subRoot)
								.where(builder.like(subRoot.get(key[1] ), "%" +  pair.getValue() + "%" ))));
			}
		}
		// SELECT * FROM Contact WHERE (conditions)
		query.select(root).where(builder.and(predicate));
		// create query and execute, then return result
		Query<Contact> queryResult = session.createQuery(query);
		List<Contact> results = queryResult.getResultList();
		return results;
		} catch( IllegalArgumentException e) {
			System.out.println("Invalid arguments");
			return null;
		}
}
	
//	=========================================================================================	//		

//	==============================     BATCH INSERT OF DATA	 	==============================	//		
	   /**
	   *  this method inserts a batch of contacts into the database
	   * @param contacts the contacts to insert into the database
	   * @param session this is the session that is connected to the database
	   */
	public static void batchInsertContacts (Contacts contacts,Session session) {
		for(Contact contact : contacts.getContact()) {
			createEntity(contact,session);
		}
	}
	
//	=========================================================================================	//		
	
//	===========================     LEGACY SELECT ENTITY ID	 	============================	//		

//	public static long selectIdFromDb(IDbEntity entity ,Session session) {
//		
//		// generate the HQL template using the class's own requirements
//      	String hql = "SELECT id " + entity.conditionsToEvaluateInSQLExpressionsToGetPrimaryKey();
//      	Query<?> query = session.createQuery(hql);
//      	
//      	// will be used to fill the placeholder in the HQL String 
//      	int counter =0 ;
//      	
//      	for (Supplier<?>getter : entity.attributesQueryParametersSupplier()) {
//      		Object param = getter.get();
//      		
//      		// check if parameter is an Entity
//      		if (param instanceof IDbEntity){
//      			
//      			// recursively call it to get the ID
//      			long duplicateCheck= selectIdFromDb((IDbEntity) param, session);
//      			
//      			// return if the child parameter is not a duplicate, meaning the parent is not either
//      			if(duplicateCheck==-1) 
//      				return -1;
//      			else
//      				// add the id of the child to the corresponding placeholder
//          			query.setParameter("param" + String.valueOf(counter), duplicateCheck);
//      		}
//      		// add value to corresponding placeholder
//      		else query.setParameter("param" + String.valueOf(counter),param);
//      		counter++;
//      	}
//      	
//      	// result of the query
//      	List<?> results =query.list();
//
//      	// not found, return -1
//      	if (results.isEmpty()) {
//      		return -1;
//      	}
//      	
//      	// return id of entity
//      	return (long) results.get(0);
//
//	}
	
//	=========================================================================================	//		
	
//	==================================     SELECT QUERY		================================	//	
	   /**
	   *  this method executes a HQL query
	   * @param hql the HQL query as a string 
	   * @param session this is the session that is connected to the database
	   * @return List of results of the query
	   */
	public static List<?> executeQuery( String hql, Session session ) {
		Query<?> query = session.createQuery(hql);
		// Case sensitive form of the above restriction.
      	List<?> results =query.list();
		return results;
		
	}

//	=========================================================================================	//		

//	=================================     CREATE ENTITY 	=================================	//	

	   /**
	   *  this method creates a new record in the database
	   * @param entity the record to insert
	   * @param session this is the session that is connected to the database
	   * @return returns the entity if it was added, null otherwise
	   */
	public static IDbEntity createEntity(IDbEntity entity,Session session) {							
		try {
			session.beginTransaction();																	
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			System.out.println("RECORD ADDED");
			return entity;
			
		} catch(ConstraintViolationException e ) {
			session.getTransaction().rollback();
			System.out.println("CONSTRAINT VIOLATED");
			return null;
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("ERROR, RECORD NOT ADDED");
			return null;
		}
	}
	
//	=========================================================================================	//		
	
//	=================================     SELECT ENTITY 	=================================	//		
	   /**
	   *  this method reads an entity from the database by its id 
	   * @param id this is the id of the entity in the database
	   * @param type this is the Class type of the entity 
	   * @param session this is the session that is connected to the database
	   * @return an entity object, or null if not found
	   */
	public static IDbEntity readEntity(Long id ,Class<? extends IDbEntity> type,Session session) {
		session.beginTransaction();
		IDbEntity entity =(IDbEntity) session.get(type, id);
		session.getTransaction().commit();
		return entity;
	}
	
//	=========================================================================================	//		
	
//	=================================     UPDATE ENTITY 	=================================	//		
	   /**
	   *  this method updates the record that has the specified id with a new entity
	   * @param id this is the id of the entity to replace in the database
	   * @param entity this is the updated record
	   * @param session this is the session that is connected to the database
	   */
	public static void updateEntity(long id,IDbEntity entity,  Session session) {
		
		try {
			session.beginTransaction();
			entity.setId(id);
			
			session.merge(entity);
			session.getTransaction().commit();
			System.out.println("RECORD UPDATED");
			
		} catch (PersistenceException e) {
			System.out.println("CONSTRAINT VIOLATED");
			session.getTransaction().rollback();
			
		}catch (Exception e) {
			System.out.println("ERROR, VALUE NOT UPDATED");
			session.getTransaction().rollback();
			
		}
		
	}

//	=========================================================================================	//	
	
//	=================================     DELETE ENTITY 	=================================	//		

	   /**
	   *  this method deletes the record of the specified entity
	   * @param entity this is the record to delete
	   * @param session this is the session that is connected to the database
	   */
	public static void deleteEntity(IDbEntity entity,Session session) {
		try {
			session.beginTransaction();
			if(session.contains(entity)) {
				session.remove(entity);
				System.out.println("RECORD DELETED");

			}
			session.getTransaction().commit();
		} catch(Exception e ) {
			System.out.println("ERROR, RECORD NOT DELETED");
		}
	}
		
	
//	=========================================================================================	//	
	
//	===============================     DELETE ENTITY BY ID 	=============================	//		
	   /**
	   *  this method deletes the record of the specified entity
	   * @param Id this is the id of the entity to delete in the database
	   * @param type this is the Class type of the entity 
	   * @param session this is the session that is connected to the database
	   */
	public static void deleteEntity(long Id, Class <? extends IDbEntity> type,Session session) {
		try {
			IDbEntity entity = readEntity(Id, type,session);
			if(entity!=null) {
				session.beginTransaction();
				System.out.println("RECORD DELETED");
				session.remove(entity);
				session.getTransaction().commit();
			}
			else System.out.println("RECORD NOT FOUND");
		} catch(Exception e ) {
			System.out.println("ERROR, RECORD NOT DELETED");
		}
	}
	
//	=========================================================================================	//		
	
}