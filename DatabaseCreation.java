import java.sql.*;

class DatabaseCreation {
        public static void main(String[]args){
            Connection conn;
            Statement st;
            ResultSet rs;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "ultrablast2018");
                System.out.println("Connected Database Successfully...\n\n");
                st = conn.createStatement();
                String sql = "CREATE DATABASE notes;";
                System.out.println("Database Created Successfully");
                st.executeUpdate(sql);
                sql= "USE notes;";
                st.executeUpdate(sql);
                sql = "CREATE TABLE reminder( no INT NOT NULL PRIMARY KEY AUTO_INCREMENT, title VARCHAR(100), note TEXT, date DATE);";
                st.executeUpdate(sql);
                System.out.println("Table Created Successfully");
            }



                catch (Exception e){
                System.out.println("Something went wrong "+e.getMessage());
            }






        }

}
