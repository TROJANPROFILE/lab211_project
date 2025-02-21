/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mountain;
/**
 *
 * @author CAU_TU
 */
public final class Mountains extends ArrayList<Mountain> {
    
    private String pathFile;

    //default constructor
    public Mountains() {
        super();
        this.pathFile ="./src/MountainList.csv";
        // this.pathFile = "./MountainList.csv";
        readFromFile();
    }

    public Mountain get(String code) {
        Mountain x = null;
        for (Mountain i : this) {
            if (i.getMountainCode().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return x;
    }

    public boolean isValidMountainCode(String code) {
        
        return get(code) != null;
    }

    public void readFromFile(){
        
        FileReader fr = null;
        try {
            //b1
            File f = new File(pathFile);
            //b2
            fr = new FileReader(f);
            //b3
            BufferedReader br = new BufferedReader(fr);
            
            String tam = br.readLine();
            while((tam = br.readLine())!=null){
                Mountain x = dataToObject(tam);
                if(x!= null)
                this.add(x);
            }   //dong
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Mountains.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Mountain dataToObject(String tam) {
        Mountain z = null;
        try {
            String[] temp = tam.split(",");
            z = new Mountain(temp[0], temp[1], temp[2], temp[3]);
        } catch (Exception e) {
        }
        return z;
    }

}
