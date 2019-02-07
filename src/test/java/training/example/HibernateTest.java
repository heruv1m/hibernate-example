package training.example;

import java.util.Collections;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import training.Student;

public class HibernateTest {
    private static final Logger log = LoggerFactory.getLogger(HibernateTest.class);

    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:./db/repository");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        Configuration configuration = new Configuration();
        configuration.setProperties(settings);

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(StudentGroup.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    @Test
    public void testSave() {
        String name = "Your name";
        Student student = new Student()
                .setFirstName(name);

        StudentGroup studentGroup = new StudentGroup()
                .setName("December-group")
                .setStudents(Collections.singletonList(student));
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(student);
        session.save(studentGroup);

        session.getTransaction().commit();

        Student studentResult = session.get(Student.class, student.getId());
        log.info("Hello {}", studentResult.getFirstName());
        log.info("Hello {}; students: {}", studentGroup.getName(), studentGroup.getStudents());
        session.close();
    }
}
