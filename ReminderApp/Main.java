import java.sql.*;
import java.util.Scanner;

class DatabaseCreation {

    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public DatabaseCreation(Connection conn, Statement st){

        this.conn = conn;
        this.st = st;

    }

    public void DatabaseCreate() {

        try {
            String sql = "CREATE DATABASE notes;";
            st.executeUpdate(sql);
            System.out.println("Created Database");
        } catch (Exception e) {
            System.out.println("Something went wrong in DatabaseCreate \n " + e.getMessage());
        }
    }

    public boolean checkDatabase() {

        try {
            String sql = "SHOW DATABASES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("notes" == rs.getString(1)) {
                    System.out.println("Database already exists");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in checkDatabase \n " + e.getMessage());
        }
        return true;
    }

    public boolean CheckTable() {
        try {
            String sql = "SHOW TABLES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("reminderapp" == rs.getString(1)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in CheckTable \n " + e.getMessage());
        }
        return true;
    }

    public void CreateTable() {


        try {
            st = conn.createStatement();
            String sql = "USE notes;";
            st.executeUpdate(sql);
            sql = "CREATE TABLE reminder( no INT NOT NULL PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100), note TEXT, date DATE);";
            st.executeUpdate(sql);
            System.out.println("Table Created Successfully");


        }catch (Exception e) {
            System.out.println("Something went wrong in CreateTable  \n" + e.getMessage());
        }

    }

    public void queryDisplay(ResultSet rs){
       try {


           while (rs.next()) {
               System.out.print(rs.getInt(1));
               System.out.print(" ");
               System.out.print(rs.getString(2));
               System.out.print(" ");
               System.out.print(rs.getString(3));
               System.out.print(" ");
               System.out.print(rs.getString(4));
               System.out.println(" ");
           }

       }catch (Exception e){
           System.out.println("Something went wrong in queryDisplay \n "+ e);
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

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "ultrablast2018");
            System.out.println("Connected Database Successfully...\n\n");
            st = conn.createStatement();
        }catch (Exception e){
           conn = null;
           st = null;
           System.out.println("Something went wrong \n" +e );
        }

        DatabaseCreation data = new DatabaseCreation(conn, st);

        try {

            if (data.checkDatabase()) {
                data.DatabaseCreate();
            }

            st.execute("USE notes;");
        }catch (Exception e){
            System.out.println("Something went wrong in this "+e);
        }

        if(data.CheckTable()){
         data.CreateTable();
        }

        try {

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
                                data.queryDisplay(rs);
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
                                        data.queryDisplay(rs);
                                        break;

                                    case 2:

                                        System.out.println("Enter the note of the reminder");
                                        j = SC.nextLine();
                                        h = SC.nextLine();
                                        sql = "SELECT * FROM reminder WHERE note = '"+h+"';";
                                        rs = st.executeQuery(sql);
                                        data.queryDisplay(rs);
                                        break;

                                    case 3:

                                        System.out.println("Enter the dates, between which you want to find the reminders.");
                                        String s = SC.nextLine();
                                        String n = SC.nextLine();
                                        String t = SC.nextLine();
                                        sql = "SELECT * FROM reminder WHERE date BETWEEN '"+n+"' AND '"+t+"';";
                                        System.out.println(sql);
                                        rs = st.executeQuery(sql);
                                        data.queryDisplay(rs);

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




    }}
