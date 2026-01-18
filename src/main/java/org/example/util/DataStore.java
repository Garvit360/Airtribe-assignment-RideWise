package org.example.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStore<T> {
    private HashMap<String,T> dataMap;
    private ArrayList<T> datalist;

    public DataStore() {
         this.dataMap = new HashMap<>();
         this.datalist = new ArrayList<>();
    }

    public void add(String id,T item) {
        dataMap.put(id,item);
        datalist.add(item);
    }

    public T findById(String id) {
        return dataMap.get(id);
    }

    public void update(String id,T item) {
        dataMap.put(id,item);
        for (int i=0;i<datalist.size();) {
            datalist.set(i,item);
            break;
        }
    }

    public void delete(String id) {
        T item = dataMap.remove(id);
        datalist.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(datalist);
    }

    public int size() {
        return datalist.size();
    }

    public boolean contains(String id) {
        return dataMap.containsKey(id);
    }
}
