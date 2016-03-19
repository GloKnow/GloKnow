
package com.example;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class GoNowUser extends AbstractPersistable<Long> {
    
    @Id
    private String firstName;
    private String lastName;
    private String phone;
    
    @Temporal(javax.persistence.TemporalType.DATE)
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
    
    
    
}
