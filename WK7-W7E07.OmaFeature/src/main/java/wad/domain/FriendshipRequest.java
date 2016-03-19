package wad.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class FriendshipRequest extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Person source;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person target;
    private Status status;

    public FriendshipRequest() {
        this.status = Status.REQUESTED;
    }

    public Person getSource() {
        return source;
    }

    public void setSource(Person source) {
        this.source = source;
    }

    public Person getTarget() {
        return target;
    }

    public void setTarget(Person target) {
        this.target = target;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {

        REQUESTED, ACCEPTED, DECLINED;
    }
}
