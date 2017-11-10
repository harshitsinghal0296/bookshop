package bookpkg;

public class BookDet {
	
	int itemid,price,qoh,discount;
	 String desc,author;
	   
	 BookDet(int itemid,String desc,String author,int price,int qoh,int discount)
	 {
	  this.itemid=itemid;
	  this.desc=desc;
	  this.author=author;
	  this.price=price;
	  this.qoh=qoh;
	  this.discount=discount;
	 }
}
