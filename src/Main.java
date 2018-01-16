/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        try {
            String userName = "mydatabase"; // username for connection
            String passWord = "mydatabase"; // insert password here
            String url = "jdbc:mysql://localhost/mydatabase"; // url to database
            // load mysql driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Database connection established");
        } catch (SQLException e) {
            System.err.println("SQLException" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.err.println("Class Not Found Exception:" + cE.toString());
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("[OUTPUT FROM SELECT]");
        String query = "SELECT * FROM id";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int s = rs.getInt("idteams");
                String n = rs.getString("name");
                System.out.println(s + "   " + n);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO teams (name) "
                    + "VALUES ('Nejmeh')");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("Closing Connection");
        try {
            conn.close();
        } catch (SQLException me) {
            System.out.println("SQLException:" + me.toString());
        }
    }
}
