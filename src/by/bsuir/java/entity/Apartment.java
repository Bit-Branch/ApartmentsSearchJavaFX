package by.bsuir.java.entity;

import java.io.Serializable;

public class Apartment implements Serializable {
    static final long SerialVersionUID = -4862926644813433707L;
    private String address;
    private double cost;

    public Apartment() {
    }

    public Apartment(String address, double cost) {
        this.address = address;
        this.cost = cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        return address != null ? address.equals(apartment.address) : apartment.address == null &&
                apartment.cost == cost;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = address != null ? address.hashCode() : 0;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return address + " --- " + cost;
    }
}
