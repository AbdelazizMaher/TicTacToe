/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eman_
 */
public class RecordController {

    static String player1;
    static String player1Shape;

    static String player2;
    static String player2Shape;

    static FileOutputStream fos;
    static DataOutputStream dos;

    public static void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void createFile(String folderName) {  //"offline" or "onlnine" //you can write-> "online/username" to create a folder for each user 
        String folderPath = "../ClientApp/src/Record/" + folderName + "/";
        String fileName = player1 + "#" + player1Shape + "_" + player2 + "#" + player2Shape + "_" + getCurrentTime();

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
            String move = "move" + "#" + row.toString() + "#" + col.toString() + "#" + shape + "\n";
            dos.writeUTF(move);
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveLine(Double startX, Double startY, Double endX, Double endY) {
        try {
            String line = "line" + "#" + startX.toString() + "#" + startY.toString() + "#" + endX.toString() + "#" + endY.toString();
            dos.writeUTF(line);
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Vector<String> readFile(String filePath) {
        Vector<String> movesRecord = new Vector<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream dis = new DataInputStream(fis);

            while (dis.available() > 0) {
                movesRecord.add(dis.readUTF());
            }

            fis.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movesRecord;
    }

    public static Vector<String> getFileNamesFromDirectory(String directoryPath) {
        Vector<String> fileNames = new Vector<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        return fileNames;
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

    public static void setPlayersShapes(String player1Shape, String player2Shape) {
        RecordController.player1Shape = player1Shape;
        RecordController.player2Shape = player2Shape;
    }

    public static void setPlayersName(String player1, String player2) {
        RecordController.player1 = player1;
        RecordController.player2 = player2;
    }

    public static String getPlayer1() {
        return player1;
    }

    public static String getPlayer2() {
        return player2;
    }

}
