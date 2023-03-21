package org.generation.italy.legion.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(generator = "address_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "address_generator", sequenceName = "address_sequence", allocationSize = 1)
    @Column(name= "id_address")
    private long id;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;
    private String city;
    private String country;

    public Address(){}
    public Address(long id, String street, int houseNumber, String city, String country) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return String.format("%s %d, %s, %s",
                street, houseNumber, city, country);
    }
}
