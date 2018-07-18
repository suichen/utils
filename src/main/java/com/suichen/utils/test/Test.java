package com.suichen.utils.test;

import com.suichen.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        Person p = new Person();
        p.setAge(25);
        p.setName("zhangsan");

        people.add(p);

        Person p1 = new Person();
        p1.setAge(26);
        p1.setName("lisi");

        people.add(p1);

        Person p2 = new Person();
        p2.setAge(23);
        p2.setName("wangwu");
        people.add(p2);

        Person p3 = new Person();
        p3.setAge(24);
        p3.setName("haha");
        people.add(p3);

        Map<Integer, Person> ans = BeanUtils.mapByKey("age", people);

        System.out.println();
    }
}
