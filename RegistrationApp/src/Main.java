import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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
                data.databaseCreate();
            }

            st.execute("USE BillPayment;");
        }catch (Exception e){
            System.out.println("Something went wrong in this "+e);
        }

        if(data.checkTableConumer()){
            data.createTableConsumer();
        }

        if(data.checkTableBill()){
            data.createTableBill();
        }

        try {

            System.out.println("Enter 1 to proceed.");
            int a = SC.nextInt();

            while (a != -1) {
                System.out.println("Enter 1 to create a new Customer Profile.");
                System.out.println("Press 2 to search a specific customer.");
                System.out.println("Press 3 to delete a CustomerProfile");
                System.out.println("Enter 4 to go to Bill Payment Menu");
                int b = SC.nextInt();
                switch (b) {
                    case 1:
                        System.out.println("Creating CustomerProfile");
                        System.out.println("Enter the ConsumerNo(Press enter after writing)");
                        p = SC.nextLine();
                        z = SC.nextLine();
                        System.out.println("Enter the Name(Press enter after writing)");
                        k = SC.nextLine();
                        System.out.println("Enter the Address(Press enter after writing)");
                        c = SC.nextLine();
                        System.out.println("Enter the Pin(Press enter after writing)");
                        x = SC.nextLine();
                        System.out.println("Enter the PhoneNo(Press enter after writing)");
                        o = SC.nextLine();
                        System.out.println(z);
                        System.out.println(k);
                        System.out.println(c);
                        System.out.println(x);
                        System.out.println(o);
                        String sql = "INSERT INTO CustomerInfo(ConsumerNo, name, Address, Pin, PhoneNo, DATE ) VALUES ("+ "\""+ z + "\""+", " +"\""+ k +"\""+", "+"\""+c+"\""+", "+"\""+ x +"\""+", "+"\""+ o +"\""+", NOW());";
                        System.out.println(sql);
                        st.executeUpdate(sql);
                        System.out.println("Consumer added");
                        break;
                    case 3:
                        System.out.println("Enter the consumer no. of the reminder you want to delete");
                        z = SC.nextLine();
                        k = SC.nextLine();
                        sql = "DELETE FROM ConsumerInfo WHERE No = " + k + ";";
                        st.executeUpdate(sql);
                        System.out.println("Reminder Deleted");
                        break;

                    case 2:

                        System.out.println("Enter 1 to search Customer by consumerNo., Enter 2 to search Customer by Customer name");
                        l= SC.nextInt();

                        switch (l) {

                            case 1:
                                System.out.println("Enter the consumerNo");
                                o = SC.nextLine();
                                l = SC.nextInt();
                                sql = "SELECT * FROM CustomerInfo WHERE ConsumerNo = " + l + ";";
                                rs = st.executeQuery(sql);
                                data.queryDisplayConsumer(rs);
                                break;

                            case 2:

                                System.out.println("Enter the name of the person");
                                j = SC.nextLine();
                                h = SC.nextLine();
                                sql = "SELECT * FROM ConsumerInfo WHERE Name = '" + h + "';";
                                rs = st.executeQuery(sql);
                                data.queryDisplayConsumer(rs);
                                break;

                        }

                    case 4:
                        System.out.println("Enter 1 to create Bill Payment log");
                        System.out.println("Enter 2 to Search Bills by BillNo.");
                        System.out.println("Enter 3 to Search Bills by ConsumerNo.");
                        l=SC.nextInt();
                        switch (1){
                            case 1:

                                System.out.println("Enter Bill no(Press enter after writing)");
                                k = SC.nextLine();
                                z = SC.nextLine();
                                System.out.println("Enter Bill Type must be one of these: Commercial, Instance, Industry(Press enter after writing)");
                                x = SC.nextLine();
                                System.out.println("Enter the ConsumerNo(Press enter after writing)");
                                p = SC.nextLine();
                                System.out.println("Enter the amount(Press enter after writing)");
                                o = SC.nextLine();

                                sql = "INSERT INTO Bill VALUES ("+ "\""+ z + "\""+", " +"\""+ x +"\""+", "+"\""+p+"\""+", "+"\""+ o +"\""+", NOW());";
                                System.out.println(sql);
                                st.executeUpdate(sql);
                                System.out.println("Bill added");
                                break;

                            case 2:
                                System.out.println("Enter Bill no.");
                                o = SC.nextLine();
                                l = SC.nextInt();
                                sql = "SELECT * FROM CustomerInfo WHERE ConsumerNo = " + l + ";";
                                rs = st.executeQuery(sql);
                                data.queryDisplayConsumer(rs);
                                break;

                            case 3:
                                System.out.println("Enter Consumer no.");
                                o = SC.nextLine();
                                l = SC.nextInt();
                                sql = "SELECT * FROM Bill WHERE ConsumerNo = " + l + ";";
                                rs = st.executeQuery(sql);
                                data.queryDisplayBill(rs);
                                break;
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
