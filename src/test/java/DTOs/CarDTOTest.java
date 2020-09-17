package DTOs;

import entities.Car;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nicklas Nielsen
 */
public class CarDTOTest {

    private Car car;
    private CarDTO carDTO;

    public CarDTOTest() {
    }

    @BeforeEach
    public void setUp() {
        car = new Car(2006, "Skoda", "Fabia", 29900, "AB12345", "Mads Madsen");
        car.setId((long) 1);
        carDTO = new CarDTO(car);
    }

    @Test
    public void TestGetId() {
        // Arrange
        long expected = car.getId();

        // Act
        long actual = carDTO.getId();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestSetId() {
        // Arrange
        long expected = 123;

        // Act
        carDTO.setId(expected);
        long actual = carDTO.getId();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetYear() {
        // Arrange
        int expected = car.getYear();

        // Act
        int actual = carDTO.getYear();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestSetYear() {
        // Arrange
        int expected = 1234;

        // Act
        carDTO.setYear(expected);
        int actual = carDTO.getYear();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetPrice() {
        // Arrange
        double expected = car.getPrice();

        // Act
        double actual = carDTO.getPrice();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestSetPrice() {
        // Arrange
        double expected = 9999.95;

        // Act
        carDTO.setPrice(expected);
        double actual = carDTO.getPrice();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetMake() {
        // Arrange
        String expected = car.getMake();

        // Act
        String actual = carDTO.getMake();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestSetMake() {
        // Arrange
        String expected = "Audi";

        // Act
        carDTO.setMake(expected);
        String actual = carDTO.getMake();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetModel() {
        // Arrange
        String expected = car.getModel();

        // Act
        String actual = carDTO.getModel();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void TestSetModel() {
        // Arrange
        String expected = "Batmobile";

        // Act
        carDTO.setModel(expected);
        String actual = carDTO.getModel();

        // Assert
        assertEquals(expected, actual);
    }

}
