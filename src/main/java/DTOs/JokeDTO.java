package DTOs;

import entities.Joke;
import java.util.Objects;

/**
 *
 * @author Nikolaj Larsen
 */
public class JokeDTO {

    private String joke;
    private String reference;
    private String type;

    public JokeDTO(Joke j) {
        joke = j.getJoke();
        reference = j.getReference();
        type = j.getType();
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final JokeDTO other = (JokeDTO) obj;
        if (!Objects.equals(this.joke, other.joke)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

}
