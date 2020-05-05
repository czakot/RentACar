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
    private String phoneNumber;
    
    private Boolean valid;
    private String validationMessage;
    public static final String VALID_CHARS_FOR_PHONE_NUMBER = "0123456789 +-()#";

    public Customer() {
        valid = false;
        validationMessage = "Üres Ügyfél\n";
    }

    public Boolean isValid() {
        return valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }
    
    public Boolean validate() {
        valid = true;
        validationMessage = "";
        
        validateIdCustomer();
        validateName();
        validateAddress();
        validatePhoneNumber();
        if (valid) {
            validationMessage = null;
        }
        return valid;
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
        return phoneNumber;
    }

    public void setPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return idCustomer + " - " + name;
    }

    private void validateIdCustomer() {
        if (idCustomer < 0) {
            valid = false;
            validationMessage += "Ügyfélazonosító szám = " + idCustomer + ". Pozitív egész kell legyen (meg nem adása esetén nulla) !\n";
        }
    }

    private void validateName() {
        if (name.trim().length() < 3) {
            valid = false;
            validationMessage += "Név: a trimmelt név tetszőleges, de legalább 3 karakter hosszú kell legyen!\n";
        }
    }

    private void validateAddress() {
    }

    private void validatePhoneNumber() {
                Boolean error = false;
                for (int i = 0; i < phoneNumber.length() && !error; i++) {
                    error = (VALID_CHARS_FOR_PHONE_NUMBER.indexOf(phoneNumber.charAt(i)) < 0);
                }
                if (error) {
                    valid = false;
                    validationMessage += "Telefonszám csak számjegyeket és a \"" + 
                                         VALID_CHARS_FOR_PHONE_NUMBER.substring(10) + "\" karaktereket tartalmazhatja";
                }
    }
}
