package hello;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GoNowUser extends AbstractPersistable<Long> {

    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //private long id;
    private String firstName;
    private String lastName;

    protected GoNowUser() {}

    public GoNowUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "GoNowUser[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}

