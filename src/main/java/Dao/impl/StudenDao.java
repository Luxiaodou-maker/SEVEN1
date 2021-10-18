package Dao.impl;
import Dao.IBaseDao;
import Dao.IStudentDao;
import Entity.Student;
import Util.JPAUtil;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class StudenDao extends BaseDao <Student> implements IStudentDao {

    @Override
    public boolean Login(String name, int password) {
        EntityManager manager = JPAUtil.getEntityManager();
        String sql = "select s from Student s where s.name=:name and s.password=:password ";
        Query query = manager.createQuery(sql);
        query.setParameter("name",name);
        query.setParameter("password",password);
        List list = query.getResultList();
        manager.close();
        return list.size()>=1;
    }
}
