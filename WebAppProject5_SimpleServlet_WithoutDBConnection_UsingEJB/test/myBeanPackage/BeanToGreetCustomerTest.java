/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myBeanPackage;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sambit
 */
public class BeanToGreetCustomerTest {
    
    public BeanToGreetCustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class BeanToGreetCustomer.
     */
    @Test
    public void testSayHello() throws Exception {
        System.out.println("sayHello");
        
        String customerName = "Sambit"; // Assign a test data "Sambit" to the variable 'customerName' that is actually passed as a parameter to the method 'sayHello()' in MyTestBean class
        
        /** To invoke the test method you have to first instantiate the embeddable EJB container **************************************/
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        BeanToGreetCustomer instance = (BeanToGreetCustomer)container.getContext().lookup("java:global/classes/BeanToGreetCustomer");
        /******************************************************************************************************************************/
        
        String actualResult = instance.sayHello(customerName);  // the sayHello() method returns this value (for the above test data) 
        String expResult = "Hello Sambit";                      // what value, you expect the sayHello() method to return (for the above test data)
        
        assertEquals(expResult, actualResult);                  // Compare the expected result with the actual result --> if it is equal, then the test is passed.
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
