package DTOs;

import entities.Joke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Nikolaj Larsen
 */
public class JokeDTOTest {
    
    private Joke joke;
    private JokeDTO jokeDTO;
    
    public JokeDTOTest() {
    }
    
    @BeforeEach
    public void setUp(){
        joke = new Joke("JokeTest", "ReferenceTest", "TypeTest");
        jokeDTO = new JokeDTO(joke);
    }
    
    @Test
    public void testGetJoke() {
        // Arrange
        String expected = joke.getJoke();

        // Act
        String actual = jokeDTO.getJoke();

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetJoke() {
        // Arrange
        String expected = "JokeTest2";

        // Act
        jokeDTO.setJoke(expected);
        String actual = jokeDTO.getJoke();

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetReference() {
        // Arrange
        String expected = joke.getReference();

        // Act
        String actual = jokeDTO.getReference();

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetReference() {
        // Arrange
        String expected = "ReferenceTest2";

        // Act
        jokeDTO.setReference(expected);
        String actual = jokeDTO.getReference();

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetType() {
        // Arrange
        String expected = joke.getType();

        // Act
        String actual = jokeDTO.getType();

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetType() {
        // Arrange
        String expected = "TypeTest2";

        // Act
        jokeDTO.setType(expected);
        String actual = jokeDTO.getType();

        // Assert
        assertEquals(expected, actual);
    }
}
