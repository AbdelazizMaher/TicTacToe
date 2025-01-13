/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOControllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author eman_
 */
public class RecordController {
    
    static String player1;
    static String player2;
   
    
   
    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return now.format(formatter);
    }
    
    
    
}
