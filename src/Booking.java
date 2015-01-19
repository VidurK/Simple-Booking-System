/**
 * @(#)Bookings.java
 *
 *
 * @author 
 * @version 1.00 2013/6/4
 */


import java.io.*;
import java.util.*;

public class Booking {
	int campsiteNumber,numberOfPeople;
	String arrivalDate,departDate;
	CustomerRecord customer;
    public Booking(int campsiteNumber, CustomerRecord customer, int numberOfPeople, String arrivalDate, String departDate) {
    	//Creates a record for a booking on specified dates
    	this.campsiteNumber = campsiteNumber;
    	this.customer = customer;
    	this.numberOfPeople = numberOfPeople;
    	this.arrivalDate = arrivalDate;
    	this.departDate = departDate;
    }
    public Booking(){
    	//Creates a blank booking record
    	campsiteNumber = 0;
    	customer = new CustomerRecord();
    	numberOfPeople = 0;
    	arrivalDate = "0000/00/00";
    	departDate = "0000/00/00";
    }
    public static Booking[] addBooking(Booking[] b,Booking newBooking,int i){
    	Booking[] blah;
    	if(b != null){
                blah = new Booking[b.length+1];
    		System.arraycopy(b,0,blah,0,b.length-1);
    		blah[i]=newBooking;
    		sortBooking(blah,i);
                
        }
    	else{
    		blah = new Booking[1];
                blah[0]=newBooking;
        }
        return blah;
    }
        
    
	public static void printBooking(Booking[][] b,int startSite, int numSites, String fileName, int invoiceNumber)throws IOException{
		//Purpose of this method is to print the booking data to a filename specified in the program calling it
    	PrintWriter fileOut = new PrintWriter (new FileWriter("data\\"+fileName));
    	fileOut.println(invoiceNumber);
        for(int i = 0; i < numSites; i++){
    		int entries = 0;
    		fileOut.println(i+startSite);
                for(int k = 0; k < b[i].length; k++){
    			//Determines number of bookings for the site based on the number of non null entries
    			if(b[i][k]!=null)
    				entries++;
    		}
    		fileOut.println(entries);
    		if(b[i]!=null)
    			//If the site has bookings in it, they are sorted
    			b[i] = sortBooking(b[i],entries);
    		for(int j = 0; j < b[i].length; j++){
    			if(b[i][j]!=null)
    			//Checks if the booking entry at the position is null or not, if it is not prints it to the file
    			fileOut.println(b[i][j].campsiteNumber+","+(b[i][j].customer).getInformation()+","+b[i][j].numberOfPeople+","+b[i][j].arrivalDate+","+b[i][j].departDate);
    		}
    	}
    	fileOut.close();
    }
    
    public static Booking[] sortBooking(Booking[] b,int bookings){
    	//sorts the array
		for(int currentPos=1;currentPos<bookings;currentPos++){
			int i=0;
			if(b[i]!=null){
				while((b[currentPos].arrivalDate).compareTo(b[i].arrivalDate)>0){
					i++;         
				}
				shiftRight(i,currentPos,b);
			}
		}
		return b;
    }
    
    public static void shiftRight(int shiftTo,int shiftFrom,Booking[] a){
        //shifts all values to the right after placing a value in its correct position in an array
		Booking temp=a[shiftFrom];
		for(int i=shiftFrom;i>shiftTo;i--){
			a[i]=a[i-1];
		}
		a[shiftTo]=temp;
    }
}