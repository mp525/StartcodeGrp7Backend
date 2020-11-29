/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author matti
 */
public class CarFacadeIT {
    private static CarFacade facade;
    private static EntityManagerFactory emf;

    private Car c1;
    private Car c2;
    private Car c3;
    public CarFacadeIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade=CarFacade.getFacadeExample(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
         EntityManager em = emf.createEntityManager();
        try {
            c1=new Car (new CarDTO("BMW","11","Bono",2000));
            c2=new Car (new CarDTO("HEUNDAI","21","Belgien",2000));
            c3=new Car (new CarDTO("UNO","22","Danmark",2000));
            
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();

            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

//    /**
//     * Test of getFacadeExample method, of class CarFacade.
//     */

//
//    /**
//     * Test of addCar method, of class CarFacade.
//     */
    @Test
    public void testAddCar() {
        System.out.println("addCar");
        CarDTO dto = new CarDTO(c1);
        dto.setMake("her er jeg bro");
        CarFacade instance = new CarFacade();
        instance.addCar(dto);
        // TODO review the generated test code and remove the default call to fail.
        List<CarDTO> result = instance.getAllCars();
         assertThat(result, everyItem(hasProperty("make")));
        assertThat(result, hasItems( // or contains or containsInAnyOrder 
                Matchers.<CarDTO>hasProperty("make", is("11")),
                Matchers.<CarDTO>hasProperty("make", is("21")),
                Matchers.<CarDTO>hasProperty("make", is("22")),
                Matchers.<CarDTO>hasProperty("make", is("her er jeg bro"))
        )
        );
    }
//
//    /**
//     * Test of findCar method, of class CarFacade.
//     */
    @Test
    public void testFindCar() {
        System.out.println("findCar");
        
        CarFacade instance = new CarFacade();
        
        CarDTO result = instance.findCar(c1.getId());
        assertEquals(new CarDTO(c1), result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of getAllCars method, of class CarFacade.
//     */
    @Test
    public void testGetAllCars() {
        System.out.println("getAllCars");
        CarFacade instance = new CarFacade();
        List<CarDTO> result = instance.getAllCars();
          assertThat(result, everyItem(hasProperty("make")));
        assertThat(result, hasItems( // or contains or containsInAnyOrder 
                Matchers.<CarDTO>hasProperty("make", is("11")),
                Matchers.<CarDTO>hasProperty("make", is("21")),
                Matchers.<CarDTO>hasProperty("make", is("22"))
        )
        );

    }
//
//    /**
//     * Test of deleteCar method, of class CarFacade.
//     */
    @Test
    public void testDeleteCar() throws Exception {
        CarFacade instance = new CarFacade();
        instance.deleteCar(c1.getId());
        List<CarDTO> result = instance.getAllCars();
        assertTrue(result.size()==2);
    }
//
//    /**
//     * Test of updateCar method, of class CarFacade.
//     */
    @Test
    public void testUpdateCar() throws Exception {
        System.out.println("updateCar");
        CarFacade instance = new CarFacade();
        CarDTO expResult = new CarDTO("we","wu","wa",2000);
        expResult.setId(c1.getId());
        instance.updateCar(expResult, c1.getId());
        
        CarDTO result1 = instance.findCar(c1.getId());
        assertEquals(expResult, result1);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
}
