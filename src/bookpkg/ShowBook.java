package bookpkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowBook
 */
@WebServlet("/ShowBook")
public class ShowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBook() {         
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 String html="<html>";
		
		 int bkid=Integer.parseInt(request.getParameter("bkid"));
		 BookDet bd=(BookDet)BookShopData.books.get(bkid);
		 
		 String s="booksimages/"+ bd.itemid + ".jpg";
		  
		 html=html+"<head><style type=\"text/css\">"+
	             "body{"+
	             "font-family: \"Lucida Console\";"+
	             "text-align: center;"+
	             "color:white;"+
	             "} td{ text-align: center;"+
	             "}</style></head>"+
	             "<body bgcolor=teal>"+
	             "<h1 style=\"color:white\">Book Shoppe</h1>"+
	             "<br><table><tr><td><img src=\""+s+"\" width=\"151\""+
			     "style=\"border:1px solid white;\"></td>"+
	             "<td>&nbsp;&nbsp;</td><td style=\"text-align:left;\">Desc :"+bd.desc+"<br/>Price: "+bd.price+"<br/>Author: "+bd.author+"<br/>Discount: "+bd.discount+"%";		
		 
		  if(bd.qoh>0)
		  {
			  html=html+"</br>Available: "+bd.qoh;
			  
		  }
		  else
		  {
			 html=html+"Sold Out !!"; 
		  }
		  
		  html=html+"<br><br><form action=\"AddToKart\" method=\"Get\">"+
		             "<input type=\"text\" name=\"qnt\">"+
		             "<input type=\"submit\" value=\"Add To Cart\">"+
				      "<input type=\"hidden\" value=\""+bkid+"\" name=\"bkid\">"+
				      "<input type=\"hidden\" value=\""+bd.qoh+"\" name=\"qoh\">"+
			     "&nbsp;&nbsp;<a href=\"bookpkg\" style=\"color:white;\">Go Back</a></form></td></tr>";		;
		 
		  html=html+"</body></html>";
		   
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
