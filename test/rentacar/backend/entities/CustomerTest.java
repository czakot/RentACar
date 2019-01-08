/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dc7ydt
 */
public class CustomerTest {
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validate method, of class Customer.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Customer instance = new Customer();
        instance.setIdCustomer(1);
        instance.setName("A Könyvelő");
        instance.setAddress("Mobile Home");
        instance.setPhone("nincs");
        Boolean expResult = false;
        Boolean result = instance.validate();
        assertEquals(expResult, result);
        
        instance.setPhone("---");
        expResult = true;
        result = instance.validate();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
    }

}
