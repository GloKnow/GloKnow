package wad.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Post extends AbstractResource {

    @ManyToOne
    private Person author;

    @Column(name = "POST_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    private String title;
    private String content;

    public Post() {
        this.date = new Date();
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
