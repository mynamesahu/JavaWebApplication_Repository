/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
//import java.sql.Date;
//import java.util.Date;
//import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sambit
 */

/**
 *
 * This class is a EJB entity bean which persists its data to the Database table "customer" 
 * by implementing 'Serializable' interface.
 * 
 * All the attributes of the "Customer" entity class map 
 * to the relevant fields of the "customer" table of the relational database
 * => ORM (Object Relational Mapping)
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustId", query = "SELECT c FROM Customer c WHERE c.custId = :custId"),
    @NamedQuery(name = "Customer.findByCustName", query = "SELECT c FROM Customer c WHERE c.custName = :custName"),
    @NamedQuery(name = "Customer.findByCustAddress", query = "SELECT c FROM Customer c WHERE c.custAddress = :custAddress"),
    @NamedQuery(name = "Customer.findByCustEmail", query = "SELECT c FROM Customer c WHERE c.custEmail = :custEmail"),
    @NamedQuery(name = "Customer.findByCustDOB", query = "SELECT c FROM Customer c WHERE c.custDOB = :custDOB"),
    @NamedQuery(name = "Customer.findByCustDOR", query = "SELECT c FROM Customer c WHERE c.custDOR = :custDOR")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cust_id")
    private Integer custId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cust_name")
    private String custName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cust_address")
    private String custAddress;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cust_email")
    private String custEmail;
    
    @Basic(optional = true)
    @Column(name = "cust_DateOfBirth")
    private String custDOB;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cust_DateOfRegistration")
    private String custDOR;

    public Customer() {
    }

    public Customer(Integer custId) {
        this.custId = custId;
    }

    public Customer(Integer custId, String custName, String custAddress, String custEmail, String custDOB, String custDOR) {
        this.custId = custId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custEmail = custEmail;
        this.custDOB = custDOB;
        this.custDOR = custDOR;
    }

   

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    
    public String getCustDOB() {
        return custDOB;
    }

    public void setCustDOB(String custDOB) {
        this.custDOB = custDOB;
    }

    public String getCustDOR() {
        return custDOR;
    }

    public void setCustDOR(String custDOR) {
        this.custDOR = custDOR;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Customer[ custId=" + custId + " ]";
    }

    
    
    
}
