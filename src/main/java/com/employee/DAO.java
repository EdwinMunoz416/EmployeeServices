package com.employee;

import java.util.List;

public interface DAO<T>{
    public List<T> getAll();
    public List<T> get(String s);
    public void update(T t);
    public void delete(T t);
    public void save(T t);
}
