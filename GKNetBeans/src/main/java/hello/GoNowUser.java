package hello;

import java.util.Date;
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
    private String phone;
    private Date birthday;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected GoNowUser() {}

    public GoNowUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

