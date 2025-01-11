/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DataModels.UserDataModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author Abdel
 */
public class DataAccessLayer {

    private static Connection connection;
    private static ResultSet rs;

    static {
        try {
            DriverManager.registerDriver(new ClientDriver());
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/ServerDB", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean addUser(UserDataModel user) {
        boolean finalResult = false;

        PreparedStatement statment;
        try {
            statment = connection.prepareStatement("INSERT INTO USERS "
                    + "(USERNAME, PASSWORD, SCORE) VALUES (?, ?, ?)",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statment.setString(1, user.getUsername());
            statment.setString(2, user.getPassword());
            statment.setInt(3, user.getScore());

            int result = statment.executeUpdate();
            if (result > 0) {
                finalResult = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return finalResult;
        }

        return finalResult;
    }

    public static UserDataModel getUser(String username) {
        PreparedStatement statment;

        try {
            statment = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? ",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statment.setString(1, username);

            rs = statment.executeQuery();
            if (rs.next()) {
                return new UserDataModel(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("SCORE"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public static int getNumberOfUsers() {
        PreparedStatement statment;
        try {
            statment = connection.prepareStatement("SELECT COUNT(*) AS TOTAL FROM USERS");
            rs = statment.executeQuery();
            if (rs.next()) {
                return rs.getInt("TOTAL");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return -1;
    }

    public static boolean updateUserScore(String username) {
        PreparedStatement statment;
        try {
            statment = connection.prepareStatement("UPDATE USERS SET SCORE = SCORE + 5 WHERE USERNAME = ?");
            statment.setString(1, username);
            int rowsUpdated = statment.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    public static int getUserScore(String username) {
        PreparedStatement statment;
        try {
            statment = connection.prepareStatement("SELECT SCORE FROM USERS WHERE USERNAME = ?");
            statment.setString(1, username);
            rs = statment.executeQuery();
            if (rs.next()) {
                return rs.getInt("SCORE");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return -1;
    }

}
