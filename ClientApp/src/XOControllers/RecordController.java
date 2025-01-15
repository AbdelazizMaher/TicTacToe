/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import java.io.DataOutputStream;
import java.io.File;
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

    public static void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        } 
    }

    public static void createFile(String folderName) {
        String folderPath = "../ClientApp/src/Record/" + folderName + "/";
        String fileName = player1 + "_" + player2 + "_" + getCurrentTime();

        createFolderIfNotExists(folderPath);
        try {
            fos = new FileOutputStream(folderPath + fileName);
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

    public static void saveLine(Double startX, Double startY, Double endX, Double endY) {
        try {
            String line = startX.toString() + "#" + startY.toString() + "#" + endX.toString() + "#" + endY.toString();
            dos.writeUTF(line);
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeRecordConection() {
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
