package DTOs;

import entities.Member;
import java.util.Objects;

/**
 *
 * @author Nicklas Nielsen
 */
public class MemberDTO {

    private String fullname;
    private String studentID;
    private String github;

    public MemberDTO(Member member) {
        studentID = member.getStudentID();
        github = member.getGithub();

        if (member.getMiddlename() == null || member.getMiddlename().isEmpty()) {
            fullname = String.format("%s %s", member.getFirstname(), member.getLastname());
        } else {
            fullname = String.format("%s %s %s", member.getFirstname(), member.getMiddlename(), member.getLastname());
        }
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.fullname);
        hash = 43 * hash + Objects.hashCode(this.studentID);
        hash = 43 * hash + Objects.hashCode(this.github);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemberDTO other = (MemberDTO) obj;
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.studentID, other.studentID)) {
            return false;
        }
        if (!Objects.equals(this.github, other.github)) {
            return false;
        }
        return true;
    }

}
