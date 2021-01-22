/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Home;

/**
 *
 * @author saurabh
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author RohanShweta
 */
public class DatabaseConnectivity {
    Connection con1;
    PreparedStatement pst;
    ResultSet rs;
    
    HashMap<String,String> getLoginDetails(String usermailid)
    {
    HashMap<String,String> loginMap=new  HashMap<String,String>();
    // System.out.println(userid);
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms?user=root&password=9404743178&useSSL=false");
    
    if(con1 == null)
      {
        throw new NullPointerException ("Connection object is not initialised properly");
      }
    pst=con1.prepareStatement("select EmailId, Password from Accounts where User_EmailID=?;");
    pst.setString(1, usermailid);
    System.out.println("Query is"+pst.toString());
    rs=pst.executeQuery();
    while(rs.next())
    {
        loginMap.put("email",rs.getString("User_EmailID"));
        loginMap.put("password",rs.getString("User_password"));
        loginMap.put("role",String.valueOf(rs.getInt("User_Role")));
        loginMap.put("User_ID",String.valueOf(rs.getInt("User_ID")));
        loginMap.put("User_Name",rs.getString("User_name"));
        System.out.println("Succefully map filled");
    }
    rs.close();
    pst.close();
    con1.close();
    }catch(Exception e)
    {
    System.out.println(e);
    }
    return loginMap;
    }
    
}
