package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Nikolaj Larsen
 */


@Entity
@NamedQueries({
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE from Joke"),
@NamedQuery(name = "Joke.getAll", query = "SELECT j FROM Joke j"),})
@NamedQuery(name = "Joke.getById", query = "SELECT j FROM Joke j WHERE j.id = :id")
//@NamedQuery(name = "Joke.getRandom", query = "")
public class Joke implements Serializable {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="joke", nullable = false)
    private String joke;
    @Column(name="reference", nullable = true)
    private String reference;
    @Column(name="type", nullable = false)
    private String type;

    public Joke(String joke, String reference, String type) {
        this.joke = joke;
        this.reference = reference;
        this.type = type;
    }
    
    public Joke(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int hash = 3;
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
        final Joke other = (Joke) obj;
        if (!Objects.equals(this.joke, other.joke)) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
