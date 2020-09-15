package DTOs;

import entities.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Nicklas Nielsen
 */
public class MemberDTOTest {

    private Member member;
    private MemberDTO memberDTO;

    public MemberDTOTest() {
    }

    @BeforeEach
    public void setUp() {
        member = new Member("FirstTest", "MiddleTest", "LastTest", "TEST-ID", "GitHub Test");
        memberDTO = new MemberDTO(member);
    }

    @Test
    public void testGetFullname_with_middlename() {
        // Arrange
        String expected = String.format("%s %s %s", member.getFirstname(), member.getMiddlename(), member.getLastname());

        // Act
        String actual = memberDTO.getFullname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFullname_without_middlename_null() {
        // Arrange
        member = new Member("FirstTest", null, "LastTest", "TEST-ID", "GitHub Test");
        memberDTO = new MemberDTO(member);
        String expected = String.format("%s %s", member.getFirstname(), member.getLastname());

        // Act
        String actual = memberDTO.getFullname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFullname_without_middlename_empty() {
        // Arrange
        member = new Member("FirstTest", "", "LastTest", "TEST-ID", "GitHub Test");
        memberDTO = new MemberDTO(member);
        String expected = String.format("%s %s", member.getFirstname(), member.getLastname());

        // Act
        String actual = memberDTO.getFullname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetFullname() {
        // Arrange
        String expected = "testFullName";

        // Act
        memberDTO.setFullname(expected);
        String actual = memberDTO.getFullname();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStudentID() {
        // Arrange
        String expected = member.getStudentID();

        // Act
        String actual = memberDTO.getStudentID();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStudentID() {
        // Arrange
        String expected = "VALID TEST ID";

        // Act
        memberDTO.setStudentID(expected);
        String actual = memberDTO.getStudentID();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetGithub() {
        // Arrange
        String expected = member.getGithub();

        // Act
        String actual = memberDTO.getGithub();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetGithub() {
        // Arrange
        String expected = "VALID GITHUB USERNAME";

        // Act
        memberDTO.setGithub(expected);
        String actual = memberDTO.getGithub();

        // Assert
        assertEquals(expected, actual);
    }

}
