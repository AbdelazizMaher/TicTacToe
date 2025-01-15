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
    static FileOutputStream fos;
    static DataOutputStream dos;

    public static void createFile(String player1,String player2) {
        String fileName = player1 + "_" + player2 + "_" + getCurrentTime();
        try {
            fos = new FileOutputStream("../ClientApp/src/Record/" + fileName);
            dos = new DataOutputStream(fos);
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveMove(Integer row, Integer col, String shape) {
        try {
            String move = row.toString() + "#" + col.toString() + "#" + shape + "\n";
            dos.writeUTF(move);
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveLine(Double startPoint1, Double endPoint1, Double startPoint2, Double endPoint2) {
        try {
                String line = startPoint1.toString() + "#" + endPoint1.toString() + "#" + startPoint2.toString() + "#" + endPoint2.toString();
                dos.writeUTF(line);  
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeRecordConection(){
        try {
            fos.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return now.format(formatter);
    }

}
