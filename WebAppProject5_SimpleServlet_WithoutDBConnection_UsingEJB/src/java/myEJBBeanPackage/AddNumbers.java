/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myEJBBeanPackage;

import javax.ejb.Stateless;

/**
 *
 * @author Sambit
 */
@Stateless
public class AddNumbers {

    private int firstNum;
    private int secondNum;
    private int addResult;
    
    /*
    Add getter-setter for all the attributes
    */
    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }
    
    public int getAddResult() {
        return addResult;
    }

    public void setAddResult(int addResult) {
        this.addResult = addResult;
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void addNumbers(){
        
        addResult = firstNum+secondNum;
    }

    
}
