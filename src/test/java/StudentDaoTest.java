import Dao.impl.StudenDao;
import Entity.Student;
import org.junit.*;

public class StudentDaoTest {
    StudenDao studenDao=new StudenDao();
@Test
    public void create(){
    Student student=new Student();
    student.setId("1");
    student.setName("小白");
    student.setPassword(9999);
    studenDao.save(student);
}
  @Test
    public void testLogin(){
        System.out.println(studenDao.Login("小明",1234));
    }
}
