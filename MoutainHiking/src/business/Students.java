/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;
import tools.Acceptable;

/**
 *
 * @author CAU_TU
 */
public final class Students extends ArrayList<Student> {

    private final String pathFile;
    private boolean saved;
    private final String HEADER_TABLE = "---------------------------------------------------------------------------------\n"
            + "| Student ID  | Name               | Phone        | Peak Code     | Fee         |\n"
            + "---------------------------------------------------------------------------------\n";
    private final String FOOTER_TABLE = "---------------------------------------------------------------------------------\n";
//    private Map<String, Student> studentRecords = new HashMap<>();

    public Students() throws IOException {
        super();
        this.pathFile = "./registrations.dat";
        this.saved = true;
        Path path = Paths.get(this.pathFile);
        if (Files.exists(path)) {
            readFromFile();
        } else {
            // File does not exist, create a new one or handle the exception
            Files.createFile(path);
            System.out.println("File created successfully!");
        }
    }

    public boolean isSaved() {
        return saved;
    }

    //case 1
    public void addNew(Student x) {
        //kiem tra xem ma hoc sinh da ton tai chua. TU THEM
        if (searchById(x.getId()) != null) {
            System.out.println("Student ID " + x.getId() + " already exists!\n");
            return;
        }

        if (this.add(x)) //if phong truong hop bo nho khong them duoc
        {
            this.saved = false;
        }
    }

    //case 2
    public void update(Student newStd) {

        //xac dinh muc tieu can cap nhat
        Student oldStd = searchById(newStd.getId());
        //tien hanh cap nhat cac field theo yeu cau
//        if(newStd.getName().length()>0)
//            oldStd.setName(newStd.getName());
        //or
        oldStd.setName(newStd.getName().length() > 0 ? newStd.getName() : oldStd.getName());
        oldStd.setPhone(newStd.getPhone().length() > 0 ? newStd.getPhone() : oldStd.getPhone());
        oldStd.setEmail(newStd.getEmail().length() > 0 ? newStd.getEmail() : oldStd.getEmail());
        oldStd.setMountainCode(newStd.getMountainCode().length() > 0 ? newStd.getMountainCode() : oldStd.getMountainCode());
        // Kiểm tra và cập nhật chi phí
        oldStd.setTuitionFee(
                (Acceptable.isValid(oldStd.getPhone(), Acceptable.VNPT_VALID)
                || Acceptable.isValid(oldStd.getPhone(), Acceptable.VIETTEL_VALID) ? 0.65 : 1) * 6000000);
        System.out.println("Student ID " + newStd.getId() + " has been updated succesfully!\n");
        this.saved = false;
    }

    //case 3 display 
    public void showAll() {
        showAll(this);
    }

    public void showAll(List<Student> li) {
        //neu danh sach trong thi display msg
        if (li == null || li.isEmpty()) {
            System.out.println("No students have registered yet.\n");
        } else {
            System.out.println(HEADER_TABLE);
            for (Student i : li) {
                System.out.println(i);
            }
            System.out.println(FOOTER_TABLE);
        }
    }

    //case 4 - delete
    public boolean delete(String id){
        boolean kq = false;
        Student k = searchById(id);
        if (k != null) {
            this.remove(k);
            kq = true;
            saveToFile();
        } else {
            System.out.println("không có nên không thể xóa");
        }
        return kq;
    }
    
    public Student searchById(String id){
        Student res = null;
        for (Student i : this) {
            if (i.getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return res;
    }
    
    public void displayStudentdetails(Student student){
        DecimalFormat a = new DecimalFormat("#,###");
        if (a!=null) {
            System.out.println("Student Detail: ");
            System.out.println("----------------------------------------------");
            System.out.println("Student ID: " + student.getId().toUpperCase());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Phone Number: " + student.getPhone());
            System.out.println("Mountain: " + student.getMountainCode());
            System.out.println("Fee: " + a.format(student.getTuitionFee()));
            System.out.println("----------------------------------------------");
        }
    }

    //case 5
    public void searchByName(String name) {
        List<Student> moi = new ArrayList<>();
        //loc va tim de dua nhung student thoa man sang 1 ds moi
        for (Student i : this) {
            if (i.getName().toLowerCase().endsWith(name.toLowerCase())) {
                moi.add(i);
            }
        }
        //show ra
        if (moi.size() > 0) {
            showAll(moi);
        } else {
            System.out.println("No one matches the search criteria!");
        }
    }

    //case 6
    public List<Student> filtersCampusCode(String campusCode){
        String cam = campusCode.length() >=2 ? campusCode.substring(0,2).toLowerCase() : campusCode.toLowerCase();
        List<Student> ls = new ArrayList<>();
        for (Student i : this) {
            if (i.getId().toLowerCase().startsWith(cam.toLowerCase())) {
                ls.add(i);
            }
        }
        return ls;
    }
    
    public void showCampus(String campusCope){
        List<Student> kql = filtersCampusCode(campusCope);
        if (kql.isEmpty()) {
            System.out.println("không có học sinh bạn tìm trong danh sách");
        } else {
            showAll(kql);
        }
    }
    
    //case 7
    /**
     * thống kê số lượng sinh viên và học phí
     */
    public void statisticalizeByMountainPeak() {
        Map<String, Integer> studentCount = new HashMap<>();
        Map<String, Double> totalFee = new HashMap<>();

        for (Student student : this) {
            String mountainCode = student.getMountainCode();
            double fee = student.getTuitionFee();

            studentCount.put(mountainCode, studentCount.getOrDefault(mountainCode, 0) + 1);
            totalFee.put(mountainCode, totalFee.getOrDefault(mountainCode, 0.0) + fee);
        }

        // System.out.println("==== Statistics by Mountain Peak ====");
        System.out.printf("|%-15s| %-15s| %-15s|\n", "Peak Name", "Number of Participants", "Total Cost");
        for (String mountainCode : studentCount.keySet()) {
            System.out.printf("|%-15s| %-15d| %-15.2f|\n",
                    mountainCode, studentCount.get(mountainCode), totalFee.get(mountainCode));
        }
    }

    public void readFromFile() throws IOException {
        Path path = Paths.get(pathFile);
        File file = path.toFile();
        if (file.length() == 0) {
            System.out.println("File trống: " + pathFile);
            return;
        }
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Object obj = ois.readObject();
                if (obj instanceof Student) {
                    this.add((Student) obj);
                } else {
                    System.err.println("Dữ liệu trong file không phải kiểu Student!");
                }
            }
            System.out.println("Đọc file thành công!");

        } catch (FileNotFoundException ex) {
            System.err.println("Không tìm thấy file: " + ex.getMessage());
//            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Lỗi khi đọc file: " + ex.getMessage());
//            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            System.err.println("Lớp Student không tồn tại: " + ex.getMessage());
//            ex.printStackTrace();
        }
    }

    //case 8 & 9
    public void saveToFile() {
        FileOutputStream fos = null;
        try {
            //b1
            File f = new File(pathFile);
            //b2
            fos = new FileOutputStream(f);
            //b3
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //b4
            for (Student i : this) {
                oos.writeObject(i);
            }
            //b5
            this.saved = true;
//            System.out.println("Registration data has been successfully saved to `registrations.dat`.\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
