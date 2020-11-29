/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.CarDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author matti
 */
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")

@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String make;
    private String countryMadeIn;
    private int year;

    public Car() {
    }

    public Car(CarDTO c) {
        this.id = c.getId();
        this.model = c.getModel();
        this.make=c.getMake();
        this.countryMadeIn = c.getCountryMadeIn();
        this.year = c.getYear();
    }
    public List<CarDTO> toDTO(List<Car>list1){
        ArrayList<CarDTO>list=new ArrayList();
        for(Car c1 : list1) {
            list.add(new CarDTO(c1));
        }
        return list;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
