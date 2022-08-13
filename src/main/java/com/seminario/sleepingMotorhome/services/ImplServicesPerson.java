package com.seminario.sleepingMotorhome.services;

import java.util.List;

public abstract class ImplServicesPerson <T> {

    public abstract Iterable<T> getAll();
    public abstract T get(T t);
    public abstract void save(T t);
    public abstract void update(T t);
    public abstract T delete(T t);
    public abstract boolean exist(T t);

}
