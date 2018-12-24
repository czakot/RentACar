/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.entities;

/**
 *
 * @author czakot
 */
public class Customer {

    private int idCustomer;
    private String name;
    private String address;
    private String phone;

    public Customer() {
    }

    public Customer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Customer(int idCustomer, String name) {
        this.idCustomer = idCustomer;
        this.name = name;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        return idCustomer;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if (this.idCustomer != other.idCustomer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rentacar.backend.entities.Customer[ idCustomer=" + idCustomer + " ]";
    }
    
}
