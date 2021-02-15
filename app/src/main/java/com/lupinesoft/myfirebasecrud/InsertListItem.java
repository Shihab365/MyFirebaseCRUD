package com.lupinesoft.myfirebasecrud;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class InsertListItem {

    public String name;
    public String id;
    public String dept;
    public String cgpa;

    public InsertListItem(){}

    public InsertListItem(String name, String id, String dept, String cgpa) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}

