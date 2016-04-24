/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wad;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import wad.controller.ActivityController;
import wad.domain.Activity;
import wad.domain.Person;
import wad.repository.ActivityRepository;
import wad.repository.PersonRepository;
import wad.service.PersonService;

//TODO: Figure out how to get this running using a separate test profile
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration(defaultRollback=true)
public class ActivityControllerTest {
    
    @Mock
    private PersonService personServiceMock;
    

    @InjectMocks
    @Autowired
    ActivityController activityController;
    
    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    public ActivityControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    @Transactional
    public void creatorCanJoin()
    {
        Activity football = activityRepository.findByName("Football");
        Person jackr = personRepository.findByUsername("jackr");
        canJoin(jackr,football);
    }
    
    @Test
    @Transactional
    public void othersCanJoin()
    {
        Activity football = activityRepository.findByName("Football");
        Person jackb = personRepository.findByUsername("jackb");
        canJoin(jackb,football);
    }
    
    @Test
    @Transactional
    public void testTansactionGetsRolledBack()
    {
        Activity football = activityRepository.findByName("Football");
        int attendees = football.getAttendeeCount();
        assertEquals(0,attendees);
    }
    
    public void canJoin(Person person, Activity activity)
    {


        int attendeesOld = activity.getAttendeeCount();
        join(person, activity);
        int attendees = activity.getAttendeeCount();
        assertEquals(attendeesOld+1,attendees);
    }
    
    public void cannotJoin(Person person, Activity activity)
    {
        int attendeesOld = activity.getAttendeeCount();
        join(person, activity);
        int attendees = activity.getAttendeeCount();
        assertEquals(attendeesOld,attendees);
    }

    private void join(Person person, Activity activity) {
        //Mock behavior
        when(personServiceMock.getAuthenticatedPerson()).thenReturn(person);
        
        Long activityID = activity.getId();
        activityController.joinActivity(activityID);
    }
}
