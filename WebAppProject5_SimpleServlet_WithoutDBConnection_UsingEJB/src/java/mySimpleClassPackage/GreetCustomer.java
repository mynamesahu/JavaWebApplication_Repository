
package mySimpleClassPackage;

/*This is a simple Java class to be invoked by the servlet "ServletInvokingSimpleJavaClass"  */
public class GreetCustomer {
    
    
    public String sayHello(String customerName){
        
        return "Hello "+customerName;
        
    }
    
}
