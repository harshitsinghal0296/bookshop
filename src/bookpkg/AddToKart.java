package bookpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddToKart
 */
@WebServlet("/AddToKart")
public class AddToKart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int userId;
	static int kartid=101;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToKart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int bkid=Integer.parseInt(request.getParameter("bkid"));
		 int qoh=Integer.parseInt(request.getParameter("qoh"));
		 int qnt=Integer.parseInt(request.getParameter("qnt"));
		
		 BookDet bd=(BookDet)BookShopData.books.get(bkid);
		 String s="booksimages/"+ bd.itemid + ".jpg";
		
		 Cookie c[]=request.getCookies();
		 if(qnt<=qoh)
		 { 
		   if(c!=null)
		   {
			    int set=0;
				userId=Integer.parseInt(c[0].getValue());
				Hashtable <Integer,Integer> k=Kart.karts.get(userId);
				
				Enumeration <Integer> kz=k.keys();
				  while(kz.hasMoreElements())
				  {
				   int key=(int)kz.nextElement();
				   if(key==bkid)
				   {
					   set=1;
					   break;
				   }
				  } 
				  if(set==1)
				  {
					  int i=k.get(bkid);
					  i+=qnt;
					  k.put(bkid,i);
				  }
				  else
				  {
					  k.put(bkid, qnt);
				  }
				  
				  bd.qoh=bd.qoh-qnt;
				
				  BookShopData.books.put(bkid, bd);
					
				  
			
		   }
		   else
		   {
			   userId=kartid;
			   Cookie cn=new Cookie("userid",userId+"");
			   cn.setMaxAge(60*60);
			   response.addCookie(cn);
			   
			   kartid++;
			   Hashtable <Integer,Integer> ht=new Hashtable <Integer,Integer>();
			   ht.put(bkid, qnt);
			   Kart.karts.put(userId, ht);
			   BookShopData.books.put(bkid,new BookDet(
                      bd.itemid,
                      bd.desc,
                      bd.author,
                      bd.price,
                      (bd.qoh-qnt),
                      bd.discount));
		   }
		}		
			
		
		String html="<html><head><style type=\"text/css\">"+
	             "body{"+
	             "font-family: \"Lucida Console\";"+
	             "text-align: center;"+
	             "color:white;"+
	             "} td{ text-align: center;"+
	             "}</style></head>"+
	             "<body bgcolor=teal>"+
	             "<h1 style=\"color:white\">Book Shoppe</h1>"+
	             "<br><table><tr><td> <img src=\""+s+"\" width=\"151\""+
			     "style=\"border:1px solid white;\"></td><td>&nbsp;</td>"+
	             "<td style=\"text-align: left;\"><b>Author Name : "+
			     bd.author+"<br>Description  :"+bd.desc;
		 
		 if(qoh<qnt)
		 {
			 html=html+"<br>Required Quantity Not Available  ";
		 }
		 else
		 {
			 html=html+"<br>Quantity : "+qnt+
					 "<br>Price :"+((bd.price-(bd.price*bd.discount)/100)*qnt);
					 ;
			;
		 }
		 
		 html=html+"<form action=\"bookpkg\" method=\"Get\">"+
		           "<input type=\"submit\" value=\"OK\"></form></td></tr></body></html>";  
		   
		   PrintWriter pw=response.getWriter();
		   pw.println(html);
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
