package it.polito.oop.vaccination;

public class AgeInterval {
	private int max;
	private int min;
	public AgeInterval(int i, int j) {
		min=i;
	    max = j;
	}
	public boolean ifFales(int i)
	{ if(min<=i && max>i )	return true;
	
	 return false;
	}
	public int getMin() {
	return min;
}
	
	 public String inter(){
			return "[" +  min +","+ max + "]";
		}
	
  public String toString() {
	 if(max>120)
		 return  "["+min+",+)"; 

	   return  "["+min+","+max+")"; 
  }
}
