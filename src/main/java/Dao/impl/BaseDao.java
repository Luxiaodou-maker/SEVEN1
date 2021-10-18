package Dao.impl;

import Dao.IBaseDao;
import Util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDao<T> implements IBaseDao<T> {
    private Class<T> clz;

    public  BaseDao(){
        ParameterizedType parametclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parametclass.getActualTypeArguments();
        clz = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public void save(Object s) {
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(s);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void update(Object s) {
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(s);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void delete(Serializable Id) {
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        T t= manager.find(clz,Id);
        manager.remove(t);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public T getOne(Serializable Id) {
        EntityManager manager = JPAUtil.getEntityManager();
        T t = manager.find(clz, Id);
        manager.close();
        return t;
    }

    @Override
    public List getAll() {
        EntityManager manager = JPAUtil.getEntityManager();
        String sql = "select p from "+ clz.getName() +" as p";
        Query query = manager.createQuery(sql);
        return query.getResultList();
    }
}
