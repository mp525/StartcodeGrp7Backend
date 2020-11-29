/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Car;
import java.util.Objects;

/**
 *
 * @author matti
 */
public class CarDTO {
    private int id;
    private String model;
    private String make;

    private String countryMadeIn;
    private int year;

    public CarDTO() {
    }

    public CarDTO(String model,String make, String countryMadeIn, int year) {
        
        this.model = model;
        this.make= make;
        this.countryMadeIn = countryMadeIn;
        this.year = year;
    }
    
    public CarDTO(Car c) {
        this.id=c.getId();
        this.model = c.getModel();
        this.make=c.getMake();
        this.countryMadeIn = c.getCountryMadeIn();
        this.year = c.getYear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountryMadeIn() {
        return countryMadeIn;
    }

    public void setCountryMadeIn(String countryMadeIn) {
        this.countryMadeIn = countryMadeIn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.model);
        hash = 97 * hash + Objects.hashCode(this.make);
        hash = 97 * hash + Objects.hashCode(this.countryMadeIn);
        hash = 97 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarDTO other = (CarDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.countryMadeIn, other.countryMadeIn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", model=" + model + ", make=" + make + ", countryMadeIn=" + countryMadeIn + ", year=" + year + '}';
    }
    
    
}
