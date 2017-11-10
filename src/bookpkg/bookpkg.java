package bookpkg;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.crypto.provider.RSACipher;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * Servlet implementation class bookpkg
 */
@WebServlet("/bookpkg")
public class bookpkg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private java.sql.PreparedStatement pstGetBooks;  
    private java.sql.PreparedStatement pstUptBooks;  
    
    static int kartId=101;
    int userId=0;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookpkg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
		
           Class.forName("com.mysql.jdbc.Driver");
		   Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingkart","root","");
		   pstGetBooks=cn.prepareStatement("select * from items");
		   pstUptBooks=cn.prepareStatement("update items set goh=? where itemid=?");
	
		   ResultSet rs=pstGetBooks.executeQuery();
		   
		   while(rs.next())
		   {
			   BookShopData.books.put(rs.getInt("itemid"),new BookDet(
	                   rs.getInt("itemid"),
	                   rs.getString("desc"),
	                   rs.getString("author"),
	                   rs.getInt("price"),
	                   rs.getInt("qoh"),
	                   rs.getInt("discount")
	                   )); 
		   }
		
		
		}
		
        catch(ClassNotFoundException e)
		{
			System.err.println("\ndriver not found..\n");
		}
		catch(SQLException e)
		{
			System.err.println("\nsql alert - "+e.getMessage()+"\n");	
		}	
	
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   String html="<html>";
		   int size=0;
		   
		   Cookie c[]=request.getCookies();
		   if(c!=null)
		   {
				userId=Integer.parseInt(c[0].getValue());
				
				Hashtable <Integer,Integer> k=Kart.karts.get(userId);
				
				size=k.size();
		   }
			   html=html+"<head><style type=\"text/css\">"+
			             "body{"+
			             "font-family: \"Lucida Console\";"+
			             "text-align: center;"+
			             "color:white;"+
			             "} td{ text-align: center;"+
			             "}</style></head>"+
			             "<body bgcolor=teal>"+
			             "<h1 style=\"color:white;\">Book Shoppe"+
			             "<span style=\"padding-left: 100px;\">"+
			             "<a href=\"ViewBooks\"><img src=\"booksimages/cart.png\""+
			             " width=50px; height=40px;></a> </span>"+size+"</h1>"+
			             
			             "<div style=\"clear:both;\"></div><table> <tr>";
			   int count=0;
			   Enumeration <Integer> kz=BookShopData.books.keys();
			   
			   while(kz.hasMoreElements())
			   {
				   int key=(int)kz.nextElement();
				   BookDet bd=BookShopData.books.get(key);
				   
				   String s="booksimages/"+ bd.itemid + ".jpg";
				   html=html+  " <td>"+
				               "&nbsp; <a href=\"ShowBook?bkid="+bd.itemid+"\">"+
				                "<img src=\""+s+"\" width=\"151\""+
						        "style=\"border:1px solid white;\"></a> &nbsp;"+
				               " <br><h3>price :"+bd.price + "</h3><br></td>";
				   count++;
				   if(count==5)
				   {
					  html=html+"</tr><tr>";
					  count=0;
				   }	 
			   }
			   
			   System.out.println("\n");
			   html=html+"</body></html>";
			    
			   
			   PrintWriter pw=response.getWriter();
			   pw.println(html);
		   
		}
	public void destroy()
	{
		System.out.print("hello");
		
	try
	{
	  for(int i=0;i<12;i++)
	  {
		 BookDet bd=(BookDet)BookShopData.books.get(i);
		 pstUptBooks.setInt(1,bd.qoh);
		 pstUptBooks.setInt(2,bd.itemid);
		 pstUptBooks.executeUpdate();
		 
	  }
	 }
	   catch(SQLException e)
	   {
		   System.err.println("\nsql alert [doget] - "+e.getMessage()+"\n");	
	   }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
