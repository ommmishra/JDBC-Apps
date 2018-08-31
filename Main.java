

import java.sql.*;
import java.util.Scanner;

class DatabaseCreation {

    public void DatabaseCreate() {
        Connection conn;
        Statement st;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "ultrablast2018");
            st = conn.createStatement();
            String sql = "CREATE DATABASE notes;";
            st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Something went wrong 1 " + e.getMessage());
        }
    }

    public boolean checkDatabase() {
        Connection conn;
        Statement st;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "ultrablast2018");
            st = conn.createStatement();
            String sql = "SHOW DATABASES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("notes" == rs.getString(1)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong 2 " + e.getMessage());
        }
        return true;
    }

    public boolean CheckTable() {
        Connection conn;
        Statement st;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes", "root", "ultrablast2018");
            st = conn.createStatement();
            String sql = "SHOW TABLES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("reminderapp" == rs.getString(1)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong 3 " + e.getMessage());
        }
        return true;
    }

    public void CreateTable() {
        Connection conn;
        Statement st;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes", "root", "ultrablast2018");
            st = conn.createStatement();
            String sql = "USE notes;";
            st.executeUpdate(sql);
            sql = "CREATE TABLE reminder( no INT NOT NULL PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100), note TEXT, date DATE);";
            st.executeUpdate(sql);
            System.out.println("Table Created Successfully");


        }catch (Exception e) {
            System.out.println("Something went wrong 3 " + e.getMessage());
        }

    }

}


class Main {


    public static void main(String[] args) {
        Scanner SC= new Scanner(System.in);
        String c,x,z,k,o,p,j,h;
        int l;
        Connection conn;
        Statement st;
        ResultSet rs;


        DatabaseCreation data = new DatabaseCreation();
        if(data.checkDatabase()) {
            data.DatabaseCreate();
        }

        if(data.CheckTable()){
            data.CreateTable();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes", "root", "ultrablast2018");
            System.out.println("Connected Database Successfully...\n\n");
            st = conn.createStatement();
            System.out.println("Enter 1 to proceed.");
            int a = SC.nextInt();

            while (a != -1) {
                System.out.println("Enter 1 to make a reminder.");
                System.out.println("Press 2 to delete a reminder.");
                System.out.println("Press 3 to Display current reminders.");
                int b = SC.nextInt();
                switch (b) {
                    case 1:
                        System.out.println("Enter the reminder No, note, reminder.(Press enter after writing each attribute)");
                        z = SC.nextLine();
                        k = SC.nextLine();
                        c = SC.nextLine();
                        x = SC.nextLine();
                        String sql = "INSERT INTO reminder VALUES ( " + k + ", '" + c + "','" + x + "', NOW());";
                        System.out.println(sql);
                        st.executeUpdate(sql);
                        System.out.println("Reminder added");
                        break;
                    case 2:
                        System.out.println("Enter the index no. of the reminder you want to delete");
                        z = SC.nextLine();
                        k = SC.nextLine();
                        sql = "DELETE FROM reminder WHERE No = " + k + ";";
                        st.executeUpdate(sql);
                        System.out.println("Reminder Deleted");
                        break;

                    case 3:
                        System.out.println("Enter 1 to display all the reminders or else enter 2 ro display others.");
                        o = SC.nextLine();
                        l = SC.nextInt();
                        switch (l){
                            case 1:
                                sql = "SELECT * FROM reminder";
                                rs = st.executeQuery(sql);

                                while (rs.next()){
                                    System.out.print(rs.getInt(1));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(2));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(3));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(4));
                                    System.out.println(" ");
                                }
                                break;
                            case 2:
                                System.out.println("Enter 1 to search reminder by No., Enter 2 to search reminder by reminder title, Enter 3 to search by date");
                                l= SC.nextInt();
                                switch (l){
                                    case 1:
                                        System.out.println("Enter the No. of reminder");
                                        o = SC.nextLine();
                                        l = SC.nextInt();
                                        sql = "SELECT * FROM reminder WHERE No = "+l+";";
                                        rs = st.executeQuery(sql);
                                        while (rs.next()){
                                            System.out.print(rs.getInt(1));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(2));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(3));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(4));
                                            System.out.println(" ");
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Enter the note of the reminder");
                                        j = SC.nextLine();
                                         h = SC.nextLine();
                                        sql = "SELECT * FROM reminder WHERE note = '"+h+"';";
                                        rs = st.executeQuery(sql);
                                        while (rs.next()){
                                            System.out.print(rs.getInt(1));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(2));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(3));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(4));
                                            System.out.println(" ");
                                        }
                                            break;

                                    case 3:
                                        System.out.println("Enter the dates, between which you want to find the reminders.");
                                         String s = SC.nextLine();
                                         String n = SC.nextLine();
                                         String t = SC.nextLine();
                                        sql = "SELECT * FROM reminder WHERE date BETWEEN '"+n+"' AND '"+t+"';";
                                        System.out.println(sql);
                                        rs = st.executeQuery(sql);
                                        while (rs.next()){
                                            System.out.print(rs.getInt(1));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(2));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(3));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(4));
                                            System.out.println(" ");
                                        }
                                        break;

                                }
                        }




                }
                System.out.println("If you wish to continue enter 1 else enter -1 ");
                a = SC.nextInt();
            }

        }

        catch(Exception e){
            System.out.println("Something went wrong 4 "+ e.getMessage());

        }




    }
}
