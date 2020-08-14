package com.suichen.utils.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

public class TableTest {
    public static void main(String[] args) {
        Table<String, String, String> employeeTable = HashBasedTable.create();
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");

        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        Map<String, String> ibmEmployees = employeeTable.row("IBM");

        System.out.println("List of IBM Employees");
        for (Map.Entry<String, String> entry:ibmEmployees.entrySet()) {
            System.out.println("Emp Id: "+entry.getKey()+", Name: "+entry.getValue());
        }

        Set<String> employers = employeeTable.rowKeySet();
        System.out.println("Employers: ");
        for (String employer:employers) {
            System.out.print(employer+" ");
        }
        System.out.println();

        Map<String, String> employersMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry:employersMap.entrySet()) {
            System.out.println("Employer: "+entry.getKey()+", Name: "+entry.getValue());
        }
    }
}
