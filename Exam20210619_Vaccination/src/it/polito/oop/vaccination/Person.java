package it.polito.oop.vaccination;


public class Person {

	private String first;
	private String last;
	private String ssn;
	private int birthyear;
	public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();
	private boolean ifallocated=false;
	private AgeInterval i;
	int age;
	
	
	
	public Person(String first, String last, String ssn, int birthyear) {
	
		this.first=first;
		this.last=last;
		this.ssn=ssn;
		this.birthyear=birthyear;
		age=CURRENT_YEAR-birthyear;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getSsn() {
		return ssn;
	}

	public int getBirthyear() {
		return birthyear;
	}
	 int getAge() {
		return age;
	}
   public boolean ifAllocated() {
	   return ifallocated;
   }
   public void isAllocated() {
	   ifallocated=true;
   }
   public void setFalse() {
	   ifallocated=false;
   }
   public void SetAgeInterval(AgeInterval i)
   { this.i=i;
	   
   }

   public AgeInterval getIntervalofPerson() {
	return i;
}
   @Override
	public String toString() {
		return ssn +","+ first+ "," + last;
	};
}
