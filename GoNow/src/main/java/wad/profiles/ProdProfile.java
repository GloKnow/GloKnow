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
    
    @Bean
    public PlatformTransactionManager transactionManager() throws URISyntaxException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaDialect(new HibernateJpaDialect());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName("production");
        factory.setPackagesToScan("wad.domain");
        factory.setDataSource(dataSource());

        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}