/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatcher;

import business.Students;
import java.io.IOException;
import java.util.List;
import model.Student;
import tools.Inputter;

/**
 *
 * @author CAU_TU
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Students ls = new Students();
        Inputter ndl = new Inputter();
        int choice = 0;
        do {
            choice = ndl.getInt("1. New Registration \n"
                    + "2. Update Registration Information\n"
                    + "3. Display Registered List\n"
                    + "4. Delete Registration Information\n"
                    + "5. Search Participants by Name\n"
                    + "6. Filter Data by Campus\n"
                    + "7. Statistics of Registration Numbers by Location\n"
                    + "8. Save Data to File\n"
                    + "9. Exit the Program\n");
            switch (choice) {
                case 1:
                    //--- Nhập dữ liệu
                    Student x = ndl.inputStudentInfo(false, "Input Data: ");
                    //--- Nhập vào danh sách
                    ls.addNew(x);
                    break;
                case 2:
                    //--- Nhập dữ liệu
                    Student z = ndl.inputStudentInfo(true);
                    //--- Gọi update
                    ls.update(z);
                    break;
                case 3:
                    ls.showAll();
                    break;
                case 4:
                    Students k = new Students();
                    String idDelete = ndl.getString("Enter student's ID: ");
//                    k.displayStudentDetails(k.searchById(idDelete));
                    k.displayStudentdetails(k.searchById(idDelete));
                    String confirmation= ndl.getString("Are you sure to delete [y/n]: ");
                    if ("y".equalsIgnoreCase(confirmation)) {
                        if (ls.delete(idDelete)) {
                            System.out.println("The registation has been successfully deleted");
                        } else {
                            System.out.println("This student has not registered yet.");
                        }
                    } else {
                        System.out.println("The registation is not deleted.");
                    }
                    break;
                case 5:
                    String name = ndl.getString("Enter student's name: ");
                    ls.searchByName(name);
                    break;
                case 6:
                    String campusPrefix = ndl.getString("Enter campus code: ");
                    List<Student> kql = ls.filtersCampusCode(campusPrefix);
                    ls.showAll(kql);
                    break;
                case 7:
                    // Hiển thị thống kê theo ngọn núi (Mountain Peak)
                    System.out.println("\n===== STATISTICS BY MOUNTAIN PEAK =====");
                    ls.statisticalizeByMountainPeak();
                    break;
                case 8:
                    if (!ls.isSaved()) {
                        ls.saveToFile();
                        System.out.println("Registration data has been successfully saved to `registrations.dat`.\n");
                    }
                    break;
                case 9:
                    if (!ls.isSaved()) {
                        String hn = ndl.getString("Bạn có muốn lưu không? [Y:yes / N:no]");//chọn 0 hoặc 1 để lưu
                        if ("Y".equalsIgnoreCase(hn)) {
                            ls.saveToFile();
                        }
                    }
                    System.out.println("Good Bye, see you again ...");
                    break;
                default:
//                    throw new AssertionError();
                    System.out.println("This function is not available.");
            }
        } while (choice != 9);
        //--- Test tinh huong nhap ma sinh vien

    }

}
