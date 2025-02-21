/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CAU_TU
 */
public class StatisticalInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    public String mountainCode;
    public int numOfStudent;
    public double totalCost;
    public int NumOfRegistration;

    public StatisticalInfo() {

    }

    public StatisticalInfo(String mountainCode, List<Student> students, double tuitionFee) {
        this.mountainCode = mountainCode;
        this.numOfStudent = students.size();
        this.totalCost = tuitionFee;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumOfRegistration() {
        return NumOfRegistration;
    }

    public void setNumOfRegistration(int NumOfRegistration) {
        this.NumOfRegistration = NumOfRegistration;
    }

    @Override
    public String toString() {
        return String.format("| %-11s| %-24d| %-12s|", mountainCode, numOfStudent, totalCost);
    }

}
