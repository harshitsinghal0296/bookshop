package bookpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Uptbook
 */
@WebServlet("/Uptbook")
public class Uptbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int size,userId;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uptbook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String html="<html><head><style type=\"text/css\">"+
	             "body{"+
	             "font-family: \"Lucida Console\";"+
	             "text-align: center;"+
	             "color:white;"+
	             "} td{ text-align: center;"+
	             "}</style></head>"+
	             "<body bgcolor=teal>"+
	             "<h1 style=\"color:white;\">Book Shoppe</h1>";
	    
		 int j=1,i=0,n;	
         Cookie c[]=request.getCookies();
			
         if(c!=null)
		 {
			userId=Integer.parseInt(c[0].getValue());
			Hashtable <Integer,Integer> k=Kart.karts.get(userId);
			
			
			  html=html+"<br><h3>YOUR KART<span style=\"padding-left: 250px;color: white;\">"+
					    "<a href=\"bookpkg\" style=\"color: white;\">Go Back</a></span></h3><table>";
				   
		    size=k.size();
		    int arr[]=new int[size];
		 
		    for(i=0;i<size;i++)
		    {
			 try
			 {
		      j=i+1;
			  arr[i]=Integer.parseInt(request.getParameter("qnt"+j));  
			 }
			 catch(NumberFormatException ne)
			 {
				 
			 }
			  
//			 html=html+"<h1>"+arr[i]+"</h1>";  
		    }

	        i=0; 	 
			Enumeration <Integer> keys=k.keys();
			while(keys.hasMoreElements())
			  {
				  int bkid=(int)keys.nextElement();
			 	  BookDet bd=(BookDet)BookShopData.books.get(bkid);
                  String s="booksimages/"+ bd.itemid + ".jpg";
			 	
	  		      
			 	  if((arr[i])<(k.get(bkid)))
			 	  {
			 	   n=(k.get(bkid)-arr[i]);	  
			 	   k.put(bkid, arr[i]);

			 	   bd.qoh=bd.qoh+n;
				   	
				   BookShopData.books.put(bkid, bd);
				   
				   html=html+"<tr><td><img src=\""+s+"\" width=\"100\" height=\"100\""+
						    "style=\"border:1px solid white;\"></td>"+
						    "<td style=\"text-align:left;\">Description : "+bd.desc+"<br>"+
						    "Author : "+bd.author+"<br>price : "+bd.price+
						    "<br><form action=\"updatekart\" method=\"Get\">Quantity : "+
						    k.get(bkid)+"</td></tr>";
		
                   			 	  			 	  
			 	  }
			 	  else if(arr[i]==(k.get(bkid)))
			 	  {
			 		  i++;
			 		 
			 		  
			 		 html=html+"<tr><td><img src=\""+s+"\" width=\"100\" height=\"100\""+
							    "style=\"border:1px solid white;\"></td>"+
							    "<td style=\"text-align:left;\">Description : "+bd.desc+"<br>"+
							    "Author : "+bd.author+"<br>price : "+bd.price+
							    "<br><form action=\"updatekart\" method=\"Get\">Quantity : "+
							    k.get(bkid)+"</td></tr>";
			 		 continue;
			 	  }
			 	  else
			 	  {
			 		n=arr[i]-(k.get(bkid));  
			 		if(n<=(bd.qoh))
			 		{
			 		 bd.qoh=bd.qoh-n;
					 BookShopData.books.put(bkid, bd);
                     k.put(bkid, arr[i]);
                     html=html+"<tr><td><img src=\""+s+"\" width=\"100\" height=\"100\""+
							    "style=\"border:1px solid white;\"></td>"+
							    "<td style=\"text-align:left;\">Description : "+bd.desc+"<br>"+
							    "Author : "+bd.author+"<br>price : "+bd.price+
							    "<br><form action=\"updatekart\" method=\"Get\">Quantity : "+
							    k.get(bkid)+"</td></tr>";
			 		
			 		}
			 		else
			 		{
			 			i++;
			 			 html=html+"<tr><td><img src=\""+s+"\" width=\"100\" height=\"100\""+
								    "style=\"border:1px solid white;\"></td>"+
								    "<td style=\"text-align:left;\">Description : "+bd.desc+"<br>"+
								    "Author : "+bd.author+"<br>price : "+bd.price+
								    "<br><form action=\"updatekart\" method=\"Get\">Quantity : "+
								    k.get(bkid)+"<br><b style=\"color:red;\">Required quantity not available"+
								    "<br>avilable quantity : "+bd.qoh+"<b></td></tr>";
				 	 		
			 			continue;
			 		}
			 	
			 	  }	
		    
		       i++;
		    
			  }
		
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
