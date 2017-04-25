/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sambit
 */

/* 
This class- "customeFacade.java" is a  EJB 'stateless' session bean that 
    - implements the business logic 
    - and operates on the database table (entity bean - "Customer.java") 
      using the functionalities of "EntityManager" component of JPA 
      through inheritance from the class - "AbstractFacade.java"

      The servlet (or client) can instantiate the this EJB session bean later 
      to avail services / functionalities of the EJB 
*/

@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    
    
    /*
    Inject the Persistence Unit from the Persistence Provider ( Java Persistence API)
    */
    @PersistenceContext(unitName = "WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction_PU")
    
    
    
    /*Instantiate the "EntityManager" component of the Persistence Provider to access its functionalities.
      EntityManager manages the entites (reads the ORM meta data for an entity 
      and performs persistence operations on the database)
    */
    private EntityManager em;   //Instantiate the "EntityManager" component
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
}
