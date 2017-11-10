package bookpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewBooks
 */
@WebServlet("/ViewBooks")
public class ViewBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int userId;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBooks() {
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
		int i=0;
		String html="<head><style type=\"text/css\">"+
	             "body{"+
	             "font-family: \"Lucida Console\";"+
	             "text-align: center;"+
	             "color:white;"+
	             "} td{ text-align: center;"+
	             "}</style></head>"+
	             "<body bgcolor=teal>"+
	             "<h1 style=\"color:white;\">Book Shoppe</h1>";
	    
		Cookie c[]=request.getCookies();
		
		if(c!=null)
		{
			userId=Integer.parseInt(c[0].getValue());
			Hashtable <Integer,Integer> k=Kart.karts.get(userId);
			
			    html=html+"<br><h3>YOUR KART<span style=\"padding-left: 250px;color: white;\">"+
			    "<a href=\"bookpkg\" style=\"color: white;\">Go Back</a></span></h3></td><table>";
		//	    "<a href=\"Uptbook?userId="+userId+"\">"+"<input type=\"submit\" value=\"Update\">"
			    
			 
			
			Enumeration <Integer> keys=k.keys();
			while(keys.hasMoreElements())
			  {
				  int bkid=(int)keys.nextElement();
			 	  BookDet bd=(BookDet)BookShopData.books.get(bkid);
				  ++i;	
			 	  String s="booksimages/"+ bd.itemid + ".jpg";
			 	 
			 	  
			      html=html+"<tr><td><img src=\""+s+"\" width=\"100\" height=\"100\""+
						    "style=\"border:1px solid white;\"></td>"+
						    "<td style=\"text-align:left;\">Description : "+bd.desc+"<br>"+
						    "Author : "+bd.author+"<br>price : "+bd.price+
						    "<br>Quantity :"+
						    "<form action=\"Uptbook\" method=\"Get\">"+
						    "<input type=\"text\" value=\""+k.get(bkid)+"\" name=\"qnt"+i+"\">"+"<br><br><br></td>";
						     
			 
			  }
			html=html+"<td style=\"text-align:center;\"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><input type=\"submit\" value=\"Update\">"+"</td></form></tr>";
			
			 html=html+"</table></body></html>";
		}
		else
		{
			html=html+"<br><h3>You have no items in kart<br>"+
	                  "<br><a href=\"bookpkg\"  style=\"color: white;\">"+
					  "START SHOPPING</a></h3></body></html>";
		}
		
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
