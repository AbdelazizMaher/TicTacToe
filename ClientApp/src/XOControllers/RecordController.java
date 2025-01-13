/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eman_
 */
public class RecordController {
    
    static String player1;
    static String player2;
    static FileOutputStream fos;
    static DataOutputStream dos;
   
      public static void createFile(){
        String fileName = player1+"_"+player2+"_"+getCurrentTime();
        try{     
            fos = new FileOutputStream("../ClientApp/src/Record/" + fileName);        
            dos = new DataOutputStream(fos);
        } catch(IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   
    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return now.format(formatter);
    }
    
    
    
}
