import java.sql.*;

class DatabaseCreation {

    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public DatabaseCreation(Connection conn, Statement st){

        this.conn = conn;
        this.st = st;

    }

    public void databaseCreate() {

        try {
            String sql = "CREATE DATABASE BillPayment;";
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
                if ("BillPayment" == rs.getString(1)) {
                    System.out.println("Database already exists");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in checkDatabase \n " + e.getMessage());
        }
        return true;
    }

    public boolean checkTableConumer() {
        try {
            String sql = "SHOW TABLES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("CustomerInfo" == rs.getString(1)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in CheckTable \n " + e.getMessage());
        }
        return true;
    }

    public boolean checkTableBill() {
        try {
            String sql = "SHOW TABLES;";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if ("Bill" == rs.getString(1)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in CheckTable \n " + e.getMessage());
        }
        return true;
    }


    public void createTableConsumer() {


        try {
            st = conn.createStatement();
            String sql = "USE BillPayment;";
            st.executeUpdate(sql);
            sql = "CREATE TABLE CustomerInfo( ConsumerNo INT NOT NULL PRIMARY KEY , Name VARCHAR(100), Address TEXT, Pin INT, PhoneNo INT, Date DATE, paidDate DATE);";
            st.executeUpdate(sql);
            System.out.println("Table Created Successfully");


        }catch (Exception e) {
            System.out.println("Something went wrong in CreateTable  \n" + e.getMessage());
        }

    }

    public void createTableBill(){
        try {
            st = conn.createStatement();
            String sql = "USE BillPayment;";
            st.executeUpdate(sql);
            sql = "CREATE TABLE Bill( BillNo INT NOT NULL PRIMARY KEY , TypeofBill VARCHAR(100), ConsumerNo INT, amount INT, paidDate DATE);";
            st.executeUpdate(sql);
            System.out.println("Table Created Successfully");


        }catch (Exception e) {
            System.out.println("Something went wrong in CreateTable  \n" + e.getMessage());
        }

    }



    public void queryDisplayConsumer(ResultSet rs){
        try {


            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.print(rs.getString(3));
                System.out.print(" ");
                System.out.print(rs.getString(4));
                System.out.print(" ");
                System.out.print(rs.getString(5));
                System.out.print(" ");
                System.out.print(rs.getString(6));
                System.out.print(" ");
                System.out.print(rs.getString(7));
                System.out.print(" ");
                System.out.println(" ");

            }

        }catch (Exception e){
            System.out.println("Something went wrong in queryDisplay \n "+ e);
        }

    }

    public void queryDisplayBill(ResultSet rs){
        try {


            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.print(rs.getString(3));
                System.out.print(" ");
                System.out.print(rs.getString(4));
                System.out.print(" ");
                System.out.print(rs.getString(5));
                System.out.print(" ");
                System.out.println(" ");

            }

        }catch (Exception e){
            System.out.println("Something went wrong in queryDisplay \n "+ e);
        }

    }

}

