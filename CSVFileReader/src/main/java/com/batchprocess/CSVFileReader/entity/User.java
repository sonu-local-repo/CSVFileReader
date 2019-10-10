package com.batchprocess.CSVFileReader.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String Name;
    private String dept;

    public User() {
    }

    public User(Integer id, String name, String dept) {
        Id = id;
        Name = name;
        this.dept = dept;
    }



    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
