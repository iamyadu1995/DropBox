package gol;

import java.sql.*;

public class DBManager 
{//SINGLETON
    private static DBManager ref = null;
    private Connection conn;
    
    private DBManager() 
    {
        try
        {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String uid = "system";
            String pass = "manager";
                    
            DriverManager.registerDriver( new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(url, uid, pass);
        }
        catch(Exception ex)
        {
            System.out.println("Err in DBManager() : " + ex);
            ref = null;
        }
    }
    
    public boolean addUser(String uname, String pass)
    {
        try
        {
            String sql = "insert into golUSER values('"+uname+"','"+pass+"')";
            Statement stmt = conn.createStatement();
            
           // pstmt.setString(1, uname);
            //pstmt.setString(2, pass);
    
            //execute
            stmt.executeUpdate(sql);
            
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("Err in addUser : " + ex);
            return false;
        }
    }
    
    
    public Member authenticateUser(String uname, String password)
    {
        try
        {
            //form the sql and prepare the statement
            String sql = "select userName from golUSER where userName = '"+uname+"' and password = '"+password+"'";
            Statement stmt = conn.createStatement();
            
            //substitute data
           // pstmt.setString(1, uname);
           // pstmt.setString(2, password);
            
            //execute the sql
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {//credentials matched, credit data fetched
                Member p = new Member();
                p.setUname(uname);
                
                rs.close();
                return p;
            }
            rs.close();
            return null;
        }
        catch(Exception ex)
        {
            System.out.println("Err : " + ex);
            return null;
        }
    }
    
    public boolean isUserNameAvailable(String uname)
    {
        try
        {   
            System.out.println("original ="+uname);
            String sql = "select * from goluser where lower(userName) = lower('"+uname+"')";
            Statement st = conn.createStatement();
//PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, uname);
            
            try (ResultSet rs = st.executeQuery(sql)) {
                
                if(rs.next()== true)
                {//data fetched i.e. user exists in table hence not avilable
                    
                    rs.close();
                    return false;
                }
            }
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("Err : " + ex);
            return false;
        }
    }
    public static DBManager getInstance()
    {
        if(ref==null)
        {
            ref = new DBManager();
        }
        return ref;
    }
}
