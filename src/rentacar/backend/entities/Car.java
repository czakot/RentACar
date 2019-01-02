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
public class Car extends BareCar {

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNumberPlate() != null ? getNumberPlate().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.getNumberPlate() == null && other.getNumberPlate() != null) || 
            (this.getNumberPlate() != null && !this.getNumberPlate().equals(other.getNumberPlate()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car[numberPlate=" + getNumberPlate() + "]";
    }
}
