/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import model.StatisticalInfo;
import java.util.HashMap;
import java.util.List;
import model.Student;

/**
 *
 * @author CAU_TU
 */
public class Statistics extends HashMap<String, StatisticalInfo> {

    private final String HEADER_TABLE = "|-----------------------------------------------------------------|\n"
                                      + "|     Peak Name     |   Number of Participants   |   Total Cost   |\n"
                                      + "|-----------------------------------------------------------------|\n";
    private final String FOOTER_TABLE = "|-----------------------------------------------------------------|";

    /**
     * DEFAULT CONsTRUCTOR
     */
    public Statistics() {
        super();
    }

    /**
     * Constructor with student list to update Statistical Infomation
     *
     * @param l
     */
    public Statistics(List<Student> l) {
        super();
        statisticalize(l);
    }

    /**
     *
     * @param i
     */
    private void statisticalize(List<Student> l) {
        for (Student i : l) {
            if (this.containsKey(i.getMountainCode())) {
                StatisticalInfo x = this.get(i.getMountainCode());
                x.setNumOfRegistration(x.getNumOfRegistration() + 1);
                x.setTotalCost(x.getTotalCost() + i.getTuitionFee());
            } else {
                StatisticalInfo z = new StatisticalInfo(i.getMountainCode(), l, i.getTuitionFee());
                this.put(i.getMountainCode(), z);
            }
        }
    }
    /**
     * 
     */
    public void show(){
        System.out.println(HEADER_TABLE);
        for (StatisticalInfo i: this.values()) {
            System.out.println(i);
        }
        System.out.println(FOOTER_TABLE);
    }
}
