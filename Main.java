import java.awt.desktop.SystemSleepEvent;
import java.io.StringBufferInputStream;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner SC= new Scanner(System.in);
        String c,x,z,k,o,p,j,h;
        int l;
        Connection conn;
        Statement st;
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reminder", "root", "ultrablast2018");
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
                        System.out.println("Enter the reminder No, note followed by your reminder, its time and date.(Press enter after writing each attribute)");
                        System.out.println("Enter the date in the format DD/MM/YY and the time in the format HH:MM");
                        z = SC.nextLine();
                        k = SC.nextLine();
                        c = SC.nextLine();
                        x = SC.nextLine();
                        o = SC.nextLine();
                        p = SC.nextLine();
                        String sql = "INSERT INTO reminderapp VALUES ( " + k + ", '" + c + "','" + x + "', '" + o + "','" + p + "' );";
                        st.executeUpdate(sql);
                        System.out.println("Reminder added");
                        break;
                    case 2:
                        System.out.println("Enter the index no. of the reminder you want to delete");
                        z = SC.nextLine();
                        k = SC.nextLine();
                        sql = "DELETE FROM reminderapp WHERE No = " + k + ";";
                        st.executeUpdate(sql);
                        System.out.println("Reminder Deleted");
                        break;

                    case 3:
                        System.out.println("Enter 1 to display all the reminders or else enter 2 ro display others.");
                        o = SC.nextLine();
                        l = SC.nextInt();
                        switch (l){
                            case 1:
                                sql = "SELECT * FROM reminderapp";
                                rs = st.executeQuery(sql);
//                                display();
                                while (rs.next()){
                                    System.out.print(rs.getInt(1));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(2));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(3));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(4));
                                    System.out.print(" ");
                                    System.out.print(rs.getString(5));
                                    System.out.println(" ");
                                }
                                break;
                            case 2:
                                System.out.println("Enter 1 to search reminder by No., Enter 2 to search reminder by reminder note, Enter 3 to search by date");
                                l= SC.nextInt();
                                switch (l){
                                    case 1:
                                        System.out.println("Enter the No. of reminder");
                                        o = SC.nextLine();
                                        l = SC.nextInt();
                                        sql = "SELECT * FROM reminderapp WHERE No = "+l+";";
                                        rs = st.executeQuery(sql);
                                        while (rs.next()){
                                            System.out.print(rs.getInt(1));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(2));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(3));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(4));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(5));
                                            System.out.println(" ");
                                        }
                                        break;

                                    case 2:
                                        System.out.println("Enter the note of the reminder");
                                        j = SC.nextLine();
                                         h = SC.nextLine();
                                        sql = "SELECT * FROM reminderapp WHERE note = '"+h+"';";
                                        rs = st.executeQuery(sql);
                                        while (rs.next()){
                                            System.out.print(rs.getInt(1));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(2));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(3));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(4));
                                            System.out.print(" ");
                                            System.out.print(rs.getString(5));
                                            System.out.println(" ");
                                        }
                                            break;

                                    case 3:
                                        System.out.println("Enter the dates, between which you want to find the reminders.");
                                         String s = SC.nextLine();
                                         String n = SC.nextLine();
                                         String t = SC.nextLine();
                                        sql = "SELECT * FROM reminderapp WHERE date BETWEEN '"+n+"' AND '"+t+"';";
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
                                            System.out.print(" ");
                                            System.out.print(rs.getString(5));
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
            System.out.println("Something went wrong "+ e.getMessage());

        }




    }
}
