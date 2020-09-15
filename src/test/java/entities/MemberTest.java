package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Nicklas Nielsen
 */
public class MemberTest {

    private Member member;
    private final String FIRSTNAME = "Nicklas";
    private final String MIDDLENAME = "Alexander";
    private final String LASTNAME = "Nielsen";
    private final String STUDENT_ID = "cph-nn161";
    private final String GITHUB = "nicklasanielsen";

    public MemberTest() {
    }

    @BeforeEach
    public void setUp() {
        member = new Member(FIRSTNAME, MIDDLENAME, LASTNAME, STUDENT_ID, GITHUB);
    }

    @Test
    public void testGetFirstname() {
        // Arrange
        String expected = FIRSTNAME;

        // Act
        String actual = member.getFirstname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetFirstname() {
        // Arrange
        String expected = "TestName";

        // Act
        member.setFirstname(expected);
        String actual = member.getFirstname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMiddlename() {
        // Arrange
        String expected = MIDDLENAME;

        // Act
        String actual = member.getMiddlename();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetMiddlename() {
        // Arrange
        String expected = "TestName";

        // Act
        member.setMiddlename(expected);
        String actual = member.getMiddlename();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLastname() {
        // Arrange
        String expected = LASTNAME;

        // Act
        String actual = member.getLastname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLastname() {
        // Arrange
        String expected = "TestName";

        // Act
        member.setLastname(expected);
        String actual = member.getLastname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStudentID() {
        // Arrange
        String expected = STUDENT_ID;

        // Act
        String actual = member.getStudentID();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStudentID() {
        // Arrange
        String expected = "TestStudentId";

        // Act
        member.setStudentID(expected);
        String actual = member.getStudentID();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetGithub() {
        // Arrange
        String expected = GITHUB;

        // Act
        String actual = member.getGithub();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetGithub() {
        // Arrange
        String expected = "TestGithub";

        // Act
        member.setGithub(expected);
        String actual = member.getGithub();

        // Assert
        assertEquals(expected, actual);
    }

}
