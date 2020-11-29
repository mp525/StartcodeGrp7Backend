/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author matti
 */
public class CarFacade {
    private static EntityManagerFactory emf=EMF_Creator.createEntityManagerFactory();
    private static CarFacade instance;

    public CarFacade() {
    }

    
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void addCar(CarDTO dto){
        CarFacade c = new CarFacade();
          Car car = new Car(dto);
        EntityManager em = c.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    public CarDTO findCar(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Car car = em.find(Car.class,id);
            return new CarDTO(car);
        }finally {
            em.close();
        }}
    public List<CarDTO> getAllCars(){
        Car c = new Car();
        EntityManager em = emf.createEntityManager();
        try{
        TypedQuery<Car>q=em.createQuery("SELECT c from Car c",Car.class);
        return c.toDTO(q.getResultList());
        }finally {
            em.close();}
    }
    
    public CarDTO deleteCar(int id) throws Exception{
        EntityManager em = emf.createEntityManager();
        Car c = em.find(Car.class,id);
        if(c!=null){
           em.getTransaction().begin();
           em.remove(c);
           em.getTransaction().commit();
           return new CarDTO(c);
        }else{
            throw new Exception("{\"message\": \"Could not delete, provided id does not exist\"}");
        }
        


    }
    
    
public CarDTO updateCar(CarDTO c1,int id) throws Exception{
        EntityManager em = emf.createEntityManager();
        Car c2 = new Car(c1);
        Car c = em.find(Car.class,id);
        em.getTransaction().begin();
        em.merge(c);
        c.setMake(c2.getMake());
        c.setModel(c2.getModel());
        c.setYear(c2.getYear());
        c.setCountryMadeIn(c2.getCountryMadeIn());
        em.getTransaction().commit();
        return new CarDTO(c);
}
    public static void main(String[] args) throws Exception {
       // private String model; private String countryMadeIn; private int year;
       EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        CarFacade c = new CarFacade();
        CarDTO dto = new CarDTO("Bobs","2020","Denmark",2029);
        
        //c.addCar(dto);
        System.out.println(c.findCar(4));
        c.updateCar(dto,4);
        System.out.println(c.findCar(4));
        System.out.println(c.getAllCars());

    }
}
