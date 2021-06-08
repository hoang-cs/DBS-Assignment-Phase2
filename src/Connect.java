import java.sql.*;



public class Connect {
    public Connection con;
    private static String DB_URL = "jdbc:sqlserver://localhost:1434;databaseName=AIESEC";
    public static String user_Name = "sa";
    public static String pass = "123456";

    public Connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(DB_URL,user_Name,pass);
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
};