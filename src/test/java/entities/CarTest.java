package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mathias Nielsen
 */
public class CarTest {
    
    private Car car;
    private final int YEAR = 2000;
    private final String MAKE = "Skoda";
    private final String MODEL = "Superb";
    private final double PRICE = 10000.0;
    private final String LICENSEPLATE = "AB12345";
    private final String OWNER = "TestMand";
    
    public CarTest() {
    }
    
    @BeforeEach
    public void setUp() {
        car = new Car(YEAR, MAKE, MODEL, PRICE, LICENSEPLATE, OWNER);
    }

    @Test
    public void testGetYear() {
        int expected = YEAR;
        
        int actual = car.getYear();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSetYear() {
        int expected = 2020;
        
        car.setYear(expected);
        int actual = car.getYear();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMake() {
        String expected = MAKE;
        
        String actual = car.getMake();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSetMake() {
        String expected = "Audi";
        
        car.setMake(expected);
        String actual = car.getMake();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetModel() {
        String expected = MODEL;
        
        String actual = car.getModel();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSetModel() {
        String expected = "Q8";
        
        car.setModel(expected);
        String actual = car.getModel();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPrice() {
        double expected = PRICE;
        
        double actual = car.getPrice();
        
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testSetPrice() {
        double expected = 20000.0;
        
        car.setPrice(expected);
        double actual = car.getPrice();
        
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testGetLicensePlate() {
        String expected = LICENSEPLATE;
        
        String actual = car.getLicensePlate();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLicensePlate() {
        String expected = "BC98765";
        
        car.setLicensePlate(expected);
        String actual = car.getLicensePlate();
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetOwner() {
        String expected = OWNER;
        
        String actual = car.getOwner();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetOwner() {
        String expected = "TestKvinde";
        
        car.setOwner(expected);
        String actual = car.getOwner();
        
        assertEquals(expected, actual);
    }
    
}
