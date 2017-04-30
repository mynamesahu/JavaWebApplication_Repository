
package myEJBBeanPackage;

import javax.ejb.Stateless;

/*This is a EJB Bean to be invoked by the servlet "ServletInvokingEJBBean"  */
@Stateless
public class GreetCustomer {
    
    

    /*Add business logic below. 
      Right-click in editor > Insert Code > Add Business Method
    */
    
    public String sayHello(String customerName) {
        
         if (customerName.equalsIgnoreCase("Sambit")){
            return "Hello to Myself";
        }
        else{
            return  "Hello "+customerName;
        }
    }
    
    
}
