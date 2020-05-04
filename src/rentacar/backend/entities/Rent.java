/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.entities;

import java.time.LocalDate;

/**
 *
 * @author czakot
 */
public class Rent {

    private Integer idRent;
    private int idCustomer;
    private String numberPlate;
    private LocalDate beginningDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;
    private Integer dailyRentalFee;
    private Integer paidFee;

    public Rent() {
    }

    public Rent(Integer idRent) {
        this.idRent = idRent;
    }

    public Rent(Integer idRent, int idCustomer, String numberPlate, LocalDate beginningDate, 
                LocalDate expectedReturnDate, LocalDate returnDate, Integer dailyRentalFee, Integer paidFee) {
        this.idRent = idRent;
        this.idCustomer = idCustomer;
        this.numberPlate = numberPlate;
        this.beginningDate = beginningDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.dailyRentalFee = dailyRentalFee;
        this.paidFee = paidFee;
    }

    public Integer getIdRent() {
        return idRent;
    }

    public void setIdRent(Integer idRent) {
        this.idRent = idRent;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getDailyRentalFee() {
        return dailyRentalFee;
    }

    public void setDailyRentalFee(Integer dailyRentalFee) {
        this.dailyRentalFee = dailyRentalFee;
    }

    public Integer getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(Integer paidFee) {
        this.paidFee = paidFee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRent != null ? idRent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rent)) {
            return false;
        }
        Rent other = (Rent) object;
        if ((this.idRent == null && other.idRent != null) || (this.idRent != null && !this.idRent.equals(other.idRent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rent[idRent=(" + idRent + ", " + numberPlate + ", " + idCustomer + "]";
    }
    
}
