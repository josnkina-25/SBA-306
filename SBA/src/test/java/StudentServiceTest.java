import models.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import utils.HibernateUtil;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest {
    private Session session;
    private Transaction tx;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }
    @Test
    void testCreateStudent() {
        Student student = new Student("josnkina@gmail.com", "Josias", "root", new HashSet<>());
        session.persist(student);
        Student student1 = session.get(Student.class, "josnkina@gmail.com");
        assertNotNull(student1);
        assertEquals("Josias", student1.getName());
    }
    @AfterAll
    void tearDown() {
        tx.rollback();
        session.close();
    }

}
