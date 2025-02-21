/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import business.Mountains;
import business.Students;
import java.io.IOException;
import java.util.Scanner;
import model.Student;

/**
 *
 * @author CAU_TU
 */
public class Inputter {

    private final Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return this.ndl.nextLine();
    }

    public String getString() {
        return this.ndl.nextLine();
    }

    public int getInt(String mess) {
        int kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.INTEGER_VALID)) {
            kq = Integer.parseInt(tam);
        }
        return kq;
    }

    /**
     * @param mess
     * @return
     */
    public double getDouble(String mess) {
        double kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.DOUBLE_VALID)) {
            kq = Double.parseDouble(tam);
        }
        return kq;
    }

    /**
     *
     * @param mess
     * @param pattern
     * @param isUpdate
     * @return
     */
    public String inputAndLoop(String mess, String pattern, boolean isUpdate) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more && !isUpdate) {
                System.out.println("Data is invalid !. Re-Enter ...");
            }
        } while (more && !isUpdate);
        return result;
    }

    /**
     *
     * @param isUpdate
     * @param mess
     * @return
     */
    public Student inputStudentInfo(boolean isUpdate, String... mess) throws IOException {
        if (mess.length > 0) {
            System.out.println(mess[0]);
        }

        Student x = new Student();
        Students s = new Students();
        if (!isUpdate) {
            do {
                x.setId(inputAndLoop("Student ID: ", Acceptable.STU_ID_VALID, isUpdate));
                if (s.searchById(x.getId()) != null) {
                    System.out.println("Student ID " + x.getId() + " already exists!\n");
                } else {
                    break;
                }
            } while (true);
        } else if (isUpdate) {  //update
            do {
                x.setId(inputAndLoop("Student ID to update: ", Acceptable.STU_ID_VALID, isUpdate));
                if (s.searchById(x.getId()) == null) {
                    System.out.println("This student has not registered yet\n");
                } else {
                    break;
                }
            } while (true);

        }

        x.setName(inputAndLoop("Name [2-20 characters]: ", Acceptable.NAME_VALID, isUpdate));
        x.setPhone(inputAndLoop("Phone [10 digit]: ", Acceptable.PHONE_VALID, isUpdate));
        x.setEmail(inputAndLoop("Email address: ", Acceptable.EMAIL_VALID, isUpdate));
        //định dạng mountain peak code
        String codePattern = "\\d{2}";
        Mountains ml = new Mountains();

        Scanner sc = new Scanner(System.in);
        System.out.print("Mountain peak [Ex: 1,2,3,...]:");
        int code;
        do {
            code = sc.nextInt();
            if (code >= 14 || code < 0) {
                System.out.println("Invalid code. Try again");
            }

        } while (code >= 14 || code < 0);

        x.setMountainCode("MT" + code);
        System.out.println("Success to register");
        
        x.setTuitionFee((Acceptable.isValid(x.getPhone(), Acceptable.VNPT_VALID)
                || Acceptable.isValid(x.getPhone(), Acceptable.VIETTEL_VALID))
                ? 0.65 * 6000000 : 6000000);

        return x;
    }

}
