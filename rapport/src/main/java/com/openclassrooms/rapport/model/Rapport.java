package com.openclassrooms.rapport.model;



import java.io.Serializable;


public class Rapport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lastName;
    private String firstName;
    private String sex;
    private long age;

    private String status;

    public Rapport() {
    }

    public Rapport(String lastName, String firstName, String sex, long age, String status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.age = age;
        this.status = status;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setAssessment(String status) {
        this.status = status;
    }

}
