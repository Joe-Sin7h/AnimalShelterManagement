import java.sql.*;

public class Mysql {
    

    Connection con;
    Statement smt;
    private String USER;
    private String PASSWORD;
    ResultSet result;

    public static void main(String args[]) throws SQLException{
        Mysql mysql = new Mysql();
        ResultSet rs = mysql.fetchData();
        
        rs.next();
        System.out.println(rs.getString(2));

    }

    Mysql(){
        USER = "developer";
        PASSWORD = "developer";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shelter", USER, PASSWORD);
            smt=con.createStatement();  

        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }

    public void insertData(Animal a){
        String query = String.format("""
                                    INSERT INTO animalDetails(name, species, age, weight, isinjured, vaccinated)
                                    values('%s', '%s', %d, %d, '%s', '%s')""",
                                    a.name, a.species, a.age, a.weight, a.injured, a.vaccinated);
        
        try{
            smt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }                            

    }

    public ResultSet fetchData(){
        String table = "SELECT * from animaldetails";
        
        try{
            result =  smt.executeQuery(table);
            // return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return result;
        

        

        

    }
    



    // public void close(){
    //     con.close();
    // }
    
}
