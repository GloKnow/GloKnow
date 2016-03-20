package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class Activity extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Person creator;

    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> attendees;

    @Column(unique = true)
    private String name;
    private String time;
    private String location;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdated;
    
    private Long imageId;

    public Activity() {
        this.lastUpdated = new Date();
    }

    /**
     * @return the attendees
     */
    public List<Person> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees the attendees to set
     */
    public void setAttendees(List<Person> attendees) {
        this.attendees = attendees;
    }
    
    /**
     * @param attendee the attendee to add 
     */
    public void addAttendee(Person attendee) {
        this.attendees.add(attendee);
    }

    /**
     * @return the creator
     */
    public Person getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Person creator) {
        this.creator = creator;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the imageId
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * @param imageId the imageId to set
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

}
