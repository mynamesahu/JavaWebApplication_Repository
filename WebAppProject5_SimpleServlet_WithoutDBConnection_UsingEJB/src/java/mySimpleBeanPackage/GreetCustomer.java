
package mySimpleBeanPackage;

/*This is a simple Bean to be invoked by the servlet "ServletInvokingSimpleBean"   */
public class GreetCustomer {
    
    private int custID;
    private String custName;
    
    public GreetCustomer(){
        // empty
    }

    /*Add getter-setter method for all the attributes here. 
      Right-click in editor > Insert Code > getter-setter 
    */
    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    /*Add business logic below. 
      Right-click in editor > Insert Code > Add Business Method
    */
    public String sayHello(String custName){
        
        if (custName.equalsIgnoreCase("Sambit")){
            return "Hello to Myself";
        }
        else{
            return  "Hello "+custName;
        }
        
    }


}
