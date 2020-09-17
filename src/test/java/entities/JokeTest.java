package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Nikolaj Larsen
 */
public class JokeTest {
    
    private Joke joke;
    private final String JOKE = "Dark humour is like food, not everyone gets it";
    private final String REFERENCE = "Joseph Stalin";
    private final String TYPE = "Mørk Humor";
    
    public JokeTest(){
    }
    
    @BeforeEach
    public void setUp(){
        joke = new Joke(JOKE, REFERENCE, TYPE);
    }
    
    @Test
    public void testGetJoke(){
        //Arrange
        String expected = JOKE;
        
        //Act
        String actual = joke.getJoke();
        
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetJoke(){
        //Arrange
        String expected = "Hvorfor leger man aldrig gemmeleg i sverige... Hvem fanden gider at finde en svensker";
        
        //Act
        joke.setJoke(expected);
        String actual = joke.getJoke();
        
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetReference(){
        //Arrange
        String expected = REFERENCE;
        
        //Act
        String actual = joke.getReference();
        
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetReference(){
        //Arrange
        String expected = "Sven fra baren";
        
        //Act
        joke.setReference(expected);
        String actual = joke.getReference();
        
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetType(){
        //Arrange
        String expected = TYPE;
        
        //Act
        String actual = joke.getType();
        
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSetType(){
        //Arrange
        String expected = "svensker humor";
        
        //Act
        joke.setType(expected);
        String actual = joke.getType();
        
        //Assert
        assertEquals(expected, actual);
    }
}
