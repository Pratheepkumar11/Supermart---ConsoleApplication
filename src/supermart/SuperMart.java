/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermart;

import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.io.DataInputStream;

/**
 *
 * @author PRATHEEP KUMAR
 */
public class SuperMart {
    
    
    
    Connection con = null;
    PreparedStatement pst=null;
   
    ResultSet rs;
    public static Connection ConnectDB(){

        

        System.out.println("Connecting database...");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket","root","");
            return connection;
      //connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }        
    }


    public static void insert(String f, String t, String u){
        String pname ;
        String price;
        int qunty=Integer.parseInt(u);
        pname=f;
        price=t;
        
        

        Connection conn=ConnectDB();
        try{
           String query = "insert into product(`Pname`, `Price`, `Quantity`)values ('"+pname+"', '"+price+"', '"+qunty+"')";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);


      // execute the preparedstatement
      preparedStmt.execute();
      
      System.out.println("data added");

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex); 
        }
    }
    
    
     public static void Delete(int d){
        int pid;
         pid=d;
        

        Connection conn=ConnectDB();
        try{
           String query = "DELETE FROM `product` WHERE pid = '" + pid+ "'" ;

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);


      // execute the preparedstatement
      preparedStmt.execute();

      System.out.println("data deleted");
      JOptionPane.showMessageDialog(null, "Data deleted");

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex); 
        }
    }
     
     public final  void Viewall(){
         try{
            String query;
            query="SELECT * FROM `product` ORDER BY Pname ASC";
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket","root","");
            pst= con.prepareStatement(query);
            rs=pst.executeQuery();
            
            while(rs.next()){
           System.out.println("Product Id:"+rs.getString("pid")+"\nProduct Name:"+rs.getString("pname")+"\nProduct Price:"+rs.getString("Price")+"\nProduct Quality:"+rs.getString("Quantity"));
       }
            
            }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex);
            
        }   
     }
     
      public final  void Viewone(int d){
          int pid;
         pid=d;
          try{
            String query;
             query="SELECT * FROM `product` WHERE pid = '" + pid+ "'";
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket","root","");
            pst= con.prepareStatement(query);
            rs=pst.executeQuery();
            
            while(rs.next()){
           System.out.println("Product Id:"+rs.getString("pid")+"\nProduct Name:"+rs.getString("pname")+"\nProduct Price:"+rs.getString("Price")+"\nProduct Quality:"+rs.getString("Quantity"));
       }
            
            }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex);
            
        }   
          
      }
     public static void main(String args[]) {
         SuperMart ss = new SuperMart();
        //ConnectDB();
         
         
         DataInputStream sc=new DataInputStream(System.in);
         
         
         
        Scanner s = new Scanner(System.in);
         System.out.println("choose command");
         System.out.println("1. Insert Information");
         System.out.println("2. Delete Information");
          System.out.println("3. View All Information");
          System.out.println("4. View One Information");
         int o=s.nextInt();
         
         
         
    
    switch(o)
    {
      case 1:
         try{
        
         String f = sc.readLine();
         String t = sc.readLine();
         String u= sc.readLine();
         insert(f,t,u);
         
         }catch(Exception e){
			System.out.println(e);  
		}
      
        break;

    case 2:
        System.out.println("Enter Product Id");
         int  d=s.nextInt();
         Delete(d);
       
        break;

    case 3:
        ss.Viewall();
       
        break;

    case 4:
        System.out.println("Enter Product Id");
        int  x=s.nextInt();
        ss.Viewone(x);
       
        break;
    default:
        
    }
     }
}
    
    

