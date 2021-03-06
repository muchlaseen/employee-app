/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author muchlas
 */
public class Person implements Serializable {

    private static int count = 0;
    
    private int id;
    private String name;
    private String age;
    private StatusCategory statusCategory;
    private DepartmentCategory deptCategory;
    private String taxId;
    private Gender gender;

    public Person(String name, String age, StatusCategory statusCategory,
            DepartmentCategory deptCategory, String taxId,
            boolean usCitizen, Gender gender) {
        this.name = name;
        this.age = age;
        this.statusCategory = statusCategory;
        this.deptCategory = deptCategory;
        this.taxId = taxId;
        this.gender = gender;
        
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public StatusCategory getStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(StatusCategory statusCategory) {
        this.statusCategory = statusCategory;
    }

    public DepartmentCategory getDeptCategory() {
        return deptCategory;
    }

    public void setDeptCategory(DepartmentCategory deptCategory) {
        this.deptCategory = deptCategory;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
