package wad.profiles;

import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import wad.domain.Person;
import wad.repository.ActivityRepository;
import wad.repository.PersonRepository;
import wad.repository.PostRepository;

@Configuration
@Profile("prod")
public class ProdProfile {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ActivityRepository activityRepository;

    @PostConstruct
    public void init() {
        Person admin = new Person();
        admin.setFirstname("A");
        admin.setSurname("Dmin");
        admin.setBirthday("Today");
        admin.setEmail("asdasdasd@asd.asd");
        admin.setPhone("040-toolazy");
        admin.setUsername("kljhasklyhf934quqfyqlifliyvja");
        admin.setPassword("kljhasklyhf934quqfyqlifliyvja");

        admin = personRepository.save(admin);
    }
    

}
