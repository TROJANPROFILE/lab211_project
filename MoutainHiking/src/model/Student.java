/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * 
 *
 * @author CAU_TU
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tutionFee;

    public Student(String id, String name, String phone, String email, String mountainCode, double tuitionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tutionFee = tuitionFee;
    }

    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTuitionFee() {
        return tutionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tutionFee = tuitionFee;
    }

    @Override
    public String toString() {
        return String.format("| %-12s| %-19s| %-13s| %-14s| %-12s|", id, name, phone, mountainCode, tutionFee);
    }

}
