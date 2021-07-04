package it.polito.oop.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;



public class Vaccines {

    public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();
    private Map<String,Person> insanlar=new HashMap<>(); 
    private Map<String,Hub> hubs=new HashMap<>();
    private Map<AgeInterval,List<Person>> interval=new HashMap<>();
    private List<AgeInterval> certainIntervalList=new ArrayList<>();
    private List<Integer> hourz=new ArrayList<>();
    
    private BiConsumer<Integer,String> bicons=null;
    // R1
    /**
     * Add a new person to the vaccination system.
     *
     * Persons are uniquely identified by SSN (italian "codice fiscale")
     *
     * @param first first name
     * @param last last name
     * @param ssn italian "codice fiscale"
     * @param y birth year
     * @return {@code false} if ssn is duplicate,
     */
    public boolean addPerson(String firstName, String lastName, String ssn, int year) {
        if(insanlar.containsKey(ssn)) 	return false;
        
    	
        Person p=new Person(firstName, lastName,ssn, year);
    	insanlar.put(ssn, p);
    	return true;
    }

    /**
     * Count the number of people added to the system
     *
     * @return person count
     */
    public int countPeople() {
        return insanlar.size();
    }

    /**
     * Retrieves information about a person.
     * Information is formatted as ssn, last name, and first name
     * separate by {@code ','} (comma).
     *
     * @param ssn "codice fiscale" of person searched
     * @return info about the person
     */
    public String getPerson(String ssn) {
      
    	return insanlar.get(ssn).toString();
    }

    /**
     * Retrieves of a person given their SSN (codice fiscale).
     *
     * @param ssn "codice fiscale" of person searched
     * @return age of person (in years)
     */
    public int getAge(String ssn) {
    	  return CURRENT_YEAR - insanlar.get(ssn).getBirthyear();
    }

    /**
     * Define the age intervals by providing the breaks between intervals.
     * The first interval always start at 0 (non included in the breaks)
     * and the last interval goes until infinity (not included in the breaks).
     * All intervals are closed on the lower boundary and open at the upper one.
     * <p>
     * For instance {@code setAgeIntervals(40,50,60)}
     * defines four intervals {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
     *
     * @param brk the array of breaks
     */
    public void setAgeIntervals(int... brk) { 
    	
    	
    	
    	
    	
    	
    	certainIntervalList.add(new AgeInterval(0,brk[0])); 
    	for(int i=0;i<brk.length;i++)
    {  if(i==brk.length-1) {
    	certainIntervalList.add(new AgeInterval(brk[i],10000));
    	continue;
    	}
      else
    	certainIntervalList.add(new AgeInterval(brk[i],brk[i+1]));}
    	//Prof, JCF is too bad idea for this for loop...
       for(AgeInterval i:certainIntervalList) {
    		interval.put(i, insanlar.values().stream().filter(e->i.ifFales(e.getAge())).collect(toList()));
    		insanlar.values().stream().filter(e->i.ifFales(e.getAge())).forEach(e->e.SetAgeInterval(i));
       }
    	
    	
    }

    /**
     * Retrieves the labels of the age intervals defined.
     *
     * Interval labels are formatted as {@code "[0,10)"},
     * if the upper limit is infinity {@code '+'} is used
     * instead of the number.
     *
     * @return labels of the age intervals
     */
    public Collection<String> getAgeIntervals() {
     return	 certainIntervalList.stream().map(AgeInterval::toString).collect(toList());
   	
    	
       
    }

    /**
     * Retrieves people in the given interval.
     *
     * The age of the person is computed by subtracting
     * the birth year from current year.
     *
     * @param intv age interval label
     * @return collection of SSN of person in the age interval
     */
    public Collection<String> getInInterval(String inter) {
   	 return interval.entrySet().stream().filter(e->e.getKey().toString().equals(inter)).flatMap(e->e.getValue().stream()).map(Person::getSsn)
    			 .collect(toList());	
          }

    // R2
    /**
     * Define a vaccination hub
     *
     * @param name name of the hub
     * @throws VaccineException in case of duplicate name
     */
    public void defineHub(String name) throws VaccineException {
       if(hubs.containsKey(name))   throw new VaccineException();
       Hub hub= new Hub(name);
       hubs.put(name, hub);
    
    }

    
    /**
     * Retrieves hub names
     *
     * @return hub names
     */
    public Collection<String> getHubs() {
    	return hubs.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Define the staffing of a hub in terms of
     * doctors, nurses and other personnel.
     *
     * @param name name of the hub
     * @param countDoctors number of doctors
     * @param nNurses number of nurses
     * @param other number of other personnel
     * @throws VaccineException in case of undefined hub, or any number of personnel not greater than 0.
     */
    public void setStaff(String name, int nDoctors, int nNurses, int other) throws VaccineException {
    	if(!hubs.containsKey(name) || nDoctors<=0 || nNurses<=0 ||other<=0  ) throw new VaccineException();
    	hubs.get(name).setnDoctors(nDoctors);
    	hubs.get(name).setnNurses(nNurses);
    	hubs.get(name).setOther(other);
    }

    /**
     * Estimates the hourly vaccination capacity of a hub
     *
     * The capacity is computed as the minimum among
     * 10*number_doctor, 12*number_nurses, 20*number_other
     *
     * @param hubName name of the hub
     * @return hourly vaccination capacity
     * @throws VaccineException in case of undefined or hub without staff
     */
    public int estimateHourlyCapacity(String hub) throws VaccineException {
    	if(!hubs.containsKey(hub) || hubs.get(hub).nDoctors==0) throw new VaccineException();
    	return Math.min(hubs.get(hub).nDoctors*10,Math.min(hubs.get(hub).nNurses*12,hubs.get(hub).other*20));
    }

    // R3
    /**
     * Load people information stored in CSV format.
     *
     * The header must start with {@code "SSN,LAST,FIRST"}.
     * All lines must have at least three elements.
     *
     * In case of error in a person line the line is skipped.
     *
     * @param people {@code Reader} for the CSV content
     * @return number of correctly added people
     * @throws IOException in case of IO error
     * @throws VaccineException in case of error in the header
     */
    public long loadPeople(Reader people) throws IOException, VaccineException {
	long n=0;
    	String line ; 
        // Hint:
        BufferedReader br = new BufferedReader(people);
        int sayim=1;
        try {
        	line=br.readLine();
        	
        }
        catch(IOException exp) {
        	throw exp;
        }
        if(n==0) {
        if(!line.contains("SSN,LAST,FIRST,YEAR")) {
        	if(bicons!=null) bicons.accept(sayim, line);
        	throw new VaccineException("SSN,LAST,FIRST,YEAR not correct..");
        }
        n++;
        }
        Map<String, Person> peoplegetsvaccine = new HashMap<>();
        while(((line = br.readLine()) !=null)) {
            String [] p= line.split(",");
            sayim++;
            if(insanlar.containsKey(p[0]) || p.length != 4) {
              if(bicons!=null) {bicons.accept(sayim, line);}
            }
            if(!insanlar.containsKey(p[0]) && p.length == 4) {
                int year = Integer.parseInt(p[3]);
                addPerson(p[2], p[1], p[0], year); 
                if(!peoplegetsvaccine.containsKey(p[0]))
                	peoplegetsvaccine.put(p[0], insanlar.get(p[0]));
                n++;   
            }
        }
        br.close();
        return n;
}

    // R4
    /**
     * Define the amount of working hours for the days of the week.
     *
     * Exactly 7 elements are expected, where the first one correspond to Monday.
     *
     * @param hours workings hours for the 7 days.
     * @throws VaccineException if there are not exactly 7 elements or if the sum of all hours is less than 0 ore greater than 24*7.
     */
    public void setHours(int... hours) throws VaccineException {
     if(hours.length!=7 )
       throw new VaccineException();
     for(int i:hours)
     { if(i>12)
    	 throw new VaccineException();
       else
    	   hourz.add(i);
     
     }
     	setHoursHubs();
    }
    
    
    public void setHoursHubs() {
    	for(Hub hub: hubs.values()) {
       	 hub.SetHours(hourz);
        }
    }
    
    

    /**
     * Returns the list of standard time slots for all the days of the week.
     *
     * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and
     * they cover the number of working hours defined through method {@link #setHours}.
     * <p>
     * Times are formatted as {@code "09:00"} with both minuts and hours on two
     * digits filled with leading 0.
     * <p>
     * Returns a list with 7 elements, each with the time slots of the corresponding day of the week.
     *
     * @return the list hours for each day of the week
     */
    public List<List<String>> getHours() {
    	  List<List<String>> result =new ArrayList<>();
          int DAY_START_HOUR =9;
          for(int i:hourz) {
       	   List<String> showhours= new ArrayList<>();
       	   result.add(showhours);
       	   int saat=DAY_START_HOUR;
       	   while(saat<i+DAY_START_HOUR) {
       		   int deqiqem=0;
       		   while(deqiqem<60) {
       	
       			   String showhour = String.format("%02d", saat) +":" +  String.format("%02d", deqiqem);
       			   showhours.add(showhour);
       			   deqiqem=deqiqem+15;
       		   }
       		   saat++;
       	   }
          }
         return result;
    	
    	
    	
    }

    /**
     * Compute the available vaccination slots for a given hub on a given day of the week
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public int getDailyAvailable(String hub, int d) {
    	   int capacity;
    		try {
    			capacity = estimateHourlyCapacity(hub);
    			return capacity*hubs.get(hub).getSlot().get(d);
    		} catch (VaccineException e) {
    			
    			e.printStackTrace();
    		}
       return 0;
    }

    /**
     * Compute the available vaccination slots for each hub and for each day of the week
     * <p>
     * The method returns a map that associates the hub names (keys) to the lists
     * of number of available hours for the 7 days.
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public Map<String, List<Integer>> getAvailable() {
       Map<String, List<Integer>> result =new HashMap<>();
       for(Hub hub:hubs.values()) 
        	result.put(hub.getName(), hub.getDailyAvailability());
        
        return result;
    }

    /**
     * Computes the general allocation plan a hub on a given day.
     * Starting with the oldest age intervals 40%
     * of available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is the list of SSNs (codice fiscale) of the
     * persons allocated to that day
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     *
     * @param hubName name of the hub
     * @param d day of week index (0 = Monday)
     * @return the list of daily allocations
     */
    public List<String> allocate(String hubName, int d) {
    	int dailyavailablepeople=getDailyAvailable(hubName,d);
    	List<String> res=new ArrayList<>();
    	List<Person> result=new ArrayList<>();
        List<AgeInterval> i=new ArrayList<>();
        i= certainIntervalList.stream().sorted(comparing((AgeInterval e)->e.getMin(),reverseOrder())).collect(toList());
        for(AgeInterval c:i) {
    	List<Person> bezinsanlar=insanlar.values().stream().filter((Person p)->!p.ifAllocated()).filter(p->c.ifFales(p.getAge())).limit((int)(dailyavailablepeople*0.4)).collect(toList());
    	bezinsanlar.forEach(Person::isAllocated);
    	dailyavailablepeople-=bezinsanlar.size();
    	for(Person j:bezinsanlar) {
    		res.add(j.getSsn());
    	}  		 }
    	if(dailyavailablepeople>0) 
    		 for(AgeInterval c:i) {
    		    	List<Person> temp=insanlar.values().stream().filter((Person p)->!p.ifAllocated()).filter(p->c.ifFales(p.getAge())).limit((int)(dailyavailablepeople)).collect(toList());
    		    	for (Person t:temp) t.isAllocated();
    		    	dailyavailablepeople=dailyavailablepeople -temp.size();
    		    	for (Person t:temp) res.add(t.getSsn());
    		    	 }hubs.get(hubName).setDailyAllocate(d,result);
    							
    	return res;
    }

    /**
     * Removes all people from allocation lists and
     * clears their allocation status
     */
    public void clearAllocation() {
    	for(Person insan:insanlar.values()) 
    	 insan.setFalse();
    }

    /**
     * Computes the general allocation plan for the week.
     * For every day, starting with the oldest age intervals
     * 40% available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is a list with 7 elements, one
     * for every day of the week, each element is a map that
     * links the name of each hub to the list of SSNs (codice fiscale)
     * of the persons allocated to that day in that hub
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     * but the same invocation (after {@link #clearAllocation}) must return the same
     * allocation.
     *
     * @return the list of daily allocations
     */
    public List<Map<String, List<String>>> weekAllocate() {
    	List<Integer> haftaningunleri=new ArrayList<>();
    	List<Map<String, List<String>>> result=new ArrayList<>();
    	for(int j=0;j<7;j++) haftaningunleri.add(j);
    	for(int i:haftaningunleri) result. add(hubs.values().stream() .collect(toMap(h->h.getName(),h ->allocate(h.getName(),i))));
    	return result;
    }

    // R5
    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system
     *
     * @return proportion of allocated people
     */
    public double propAllocated() {
    	double numberofpeople= Double.valueOf(countPeople());
    	double allocatedpeople=0;
    	for(Person insan:insanlar.values()) {
    		if(insan.ifAllocated())
    		{
    			allocatedpeople++;
    		}
    	}
    	
   return allocatedpeople/numberofpeople;  
    	
    	
    }

    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system, divided by age interval.
     * <p>
     * The map associates the age interval label
     * to the proportion of allocates people in that interval
     *
     * @return proportion of allocated people by age interval
     */
    public Map<String, Double> propAllocatedAge() {
    	  	Map<String,Double> res=new HashMap<>();
    		double numberofpeople=insanlar.size();
    		double counter;
    	  	for(AgeInterval interval: certainIntervalList) {
    	  		Collection<String> ssns =new ArrayList<>();
    	  		counter=0;
    	  		ssns= getInInterval(interval.toString());
    	  		for(String s: ssns) {
    	  		if(insanlar.get(s).ifAllocated()) {
    	  				counter++;
    	  			}
    	  		}
    	  		res.put(interval.toString(), (counter/numberofpeople));
    	  	}
    	  	return res;
    }

    /**
     * Retrieves the distribution of allocated persons
     * among the different age intervals.
     * <p>
     * For each age intervals the map reports the
     * proportion of allocated persons in the corresponding
     * interval w.r.t the total number of allocated persons
     *
     * @return
     */
    public Map<String, Double> distributionAllocated() {
    	Map<String,Double> result=new HashMap<>();
    	//i tried to do it but jcf is bad idea here too
	  	insanlar.values().stream().filter((Person p)->p.ifAllocated()).collect(groupingBy((Person p)->p.getIntervalofPerson().toString(),counting()))
	  	.forEach((key,value)->{ result.put(key, (double)(value/(double)insanlar.values().stream().filter((Person p)->p.ifAllocated()).count()));});  			
	return result;    }

    // R6
    /**
     * Defines a listener for the file loading method.
     * The {@ accept()} method of the listener is called
     * passing the line number and the offending line.
     * <p>
     * Lines start at 1 with the header line.
     *
     * @param lst the listener for load errors
     */
    public void setLoadListener(BiConsumer<Integer, String> lst) {
    	// listener initializer
    	bicons=lst;
    }
}