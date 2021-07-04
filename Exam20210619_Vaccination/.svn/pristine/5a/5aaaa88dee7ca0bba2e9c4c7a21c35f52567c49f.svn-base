package it.polito.oop.vaccination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Hub {
	int nDoctors;
	int nNurses;
	int capacity;
	int other;
	private String name;
	
	private List<Integer> slot=new ArrayList<>();


	private List<Integer> DailyAvailability=new ArrayList<>();
	private HashMap<Integer,List<Person>> WeekAllocate=new HashMap<>();
	public Hub(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void SetHours(List<Integer> hourSet) {
		
		 this.slot=hourSet;
	}
	void setCapacity() {
		this.capacity= Math.min(this.getnDoctors()*10,Math.min(this.getnNurses()*12,this.getOther()*20));
	}
	List<Integer> getSlot() {
		return slot;
	}
	void setSlot(List<Integer> slot) {
		this.slot = slot;
	}
	
	public List<Integer> getDailyAvailability()
	
	{
		setCapacity();
		for(int i:slot)
		DailyAvailability.add(i*capacity);
	
	 return DailyAvailability;
	}


	public void setDailyAllocate(int d, List<Person> result) {
		// TODO Auto-generated method stub
		WeekAllocate.put(d,result);
		
	}
	int getnDoctors() {
		return nDoctors;
	}

	void setnDoctors(int nDoctors) {
		this.nDoctors = nDoctors;
	}

	int getnNurses() {
		return nNurses;
	}

	void setnNurses(int nNurses) {
		this.nNurses = nNurses;
	}

	int getOther() {
		return other;
	}

	void setOther(int other) {
		this.other = other;
	}
	
   
}
