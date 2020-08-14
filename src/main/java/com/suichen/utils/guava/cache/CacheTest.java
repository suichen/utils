package com.suichen.utils.guava.cache;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class Employee {
    String name;
    String dept;
    String emplD;

    public Employee(String name, String dept, String empID){
        this.name = name;
        this.dept = dept;
        this.emplD = empID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getEmplD() {
        return emplD;
    }
    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Employee.class)
                .add("Name", name)
                .add("Department", dept)
                .add("Emp Id", emplD).toString();
    }
}

public class CacheTest {
    private static Employee getFromDatabase(String empId){
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String, Employee> database = new HashMap();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for" + empId);
        return database.get(empId);
    }
    public static void main(String[] args) {
        LoadingCache<String, Employee> employeeLoadingCache =
                                            CacheBuilder.newBuilder()
                                                        .maximumSize(100).expireAfterAccess(10, TimeUnit.SECONDS)
                                                        .build(new CacheLoader<String, Employee>() {
                                                            @Override
                                                            public Employee load(String s) throws Exception {
                                                                return null;
                                                            }
                                                        });


    }
}
