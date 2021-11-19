import java.sql.*;

public class Mysql {
    

    Connection con;
    Statement smt;
    private String USER;
    private String PASSWORD;


    public static void main(String args[]) throws SQLException{
        Mysql mysql = new Mysql();

    }

    Mysql(){
        USER = "developer";
        PASSWORD = "developer";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shelter", USER, PASSWORD);

        } catch (Exception e) {
            
            e.printStackTrace();
        }

        try {
            smt=con.createStatement();  
            ResultSet rs=smt.executeQuery("select * from demo");
            rs.next();
            System.out.println(rs.getString(1));

        } catch (SQLException e) {
            
            e.printStackTrace();
        }


    }

    // public void close(){
    //     con.close();
    // }
    
}
