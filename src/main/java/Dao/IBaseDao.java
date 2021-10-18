package Dao;

import java.util.List;
import java.io.Serializable;

public interface IBaseDao<T> {
    void save(T s);
    void update(T s);
    void delete(Serializable Id);
    T getOne(Serializable Id);
    List<T> getAll();
   // boolean Login(String username,String password);
}
