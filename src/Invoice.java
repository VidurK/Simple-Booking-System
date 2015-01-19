

/**
 * @(#)Invoice.java
 *
 *
 * @author 
 * @version 1.00 2013/6/12
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class Invoice {
        
        double weekdayRate; 
        double weekendRate;
        double HST;
	Date arrival,departure,year;
	String arrivalOutput,departureOutput;
	Booking b;
	int prevInvoice;
    public Invoice(Booking b, int previousInvoiceNumber) throws ParseException,IOException {
    	BufferedReader prices = new BufferedReader(new FileReader("data\\prices.dat"));
        weekdayRate = Double.parseDouble(prices.readLine());
        weekendRate = Double.parseDouble(prices.readLine());
        HST = Double.parseDouble(prices.readLine());
      	arrival = new SimpleDateFormat("yyyy/MM/dd").parse(b.arrivalDate);
      	departure = new SimpleDateFormat("yyyy/MM/dd").parse(b.departDate);
      	SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
      	String arrivalInput = date.format(arrival);
      	String departureInput = date.format(departure);
      	arrivalOutput = arrivalInput.substring(0,10);
      	departureOutput = departureInput.substring(0,10);
      	this.b = b;
      	prevInvoice = previousInvoiceNumber;
    	
    }
    public static void changeRate(double weekday, double weekend, double HST) throws IOException{
        PrintWriter fileOut = new PrintWriter(new FileWriter("data\\prices.dat"));
        fileOut.println(weekday);
        fileOut.println(weekend);
        fileOut.println(HST);
        fileOut.close();
    }
    public void printInvoice() throws IOException{
    	SimpleDateFormat y = new SimpleDateFormat("yy");
      	year = arrival;
      	String yr = y.format(year);
      	String invoiceNumber = fileSpacing(yr,Integer.toString(prevInvoice));
    	PrintWriter fileOut = new PrintWriter(new FileWriter("data\\invoices\\"+invoiceNumber+".dat"));
    	
    	String info[] = calculateDays().split(",");
    	int weekDays = Integer.parseInt(info[0]);
    	int weekendDays = Integer.parseInt(info[1]);
    	int daysStayed = Integer.parseInt(info[2]);
    	
    	double subTotal = weekDays*weekdayRate + weekendDays * weekendRate;
    	double tax = subTotal * HST;
    	
    	fileOut.println("Tripp Creek Camp"+spacing()+"Invoice No "+invoiceNumber);
    	fileOut.println(b.customer.firstName+" "+b.customer.lastName);
    	fileOut.println(arrivalOutput+" - "+departureOutput);
    	fileOut.println();
    	fileOut.println("Days Stayed:  "+daysStayed);
    	fileOut.println("Week Days:    " + weekDays);
    	fileOut.println("Weekend Days: " + weekendDays);
    	fileOut.println("Sub-Total:  " + subTotal);
    	fileOut.println("Tax:        " + tax);
    	fileOut.println("Total Cost: " + (subTotal+tax));
    	
    	fileOut.close();
    }
    public static String spacing(){
    	String spaces="";
    	for(int i = 0; i < 28; i ++){
    		spaces+=" ";
    	}
    	return spaces;
    }
    public static String fileSpacing(String yr,String prev){
    	String fdas=yr;
    	if(prev.length()<=4){
	    	for ( int i = 0; i < 4-prev.length();i++){
	    		fdas+="0";
	    	}
    	}
    	fdas+=prev;
    	return fdas;
    }
    public String calculateDays(){
    	String info = null;
        int weekDays=0,weekendDays=0,totalDays=0;
    	Calendar day = Calendar.getInstance();
	day.setTime(arrival);
    	Calendar finish = Calendar.getInstance();
    	finish.setTime(departure);
    	while((day.equals(finish))!=true){
            if(day.getTime().getDay()==0||day.getTime().getDay()>=5){
                weekendDays++;
            }
            else{
                weekDays++;
            }
            totalDays++;
            day.add(Calendar.DAY_OF_MONTH, 1);
    	}
        info = weekDays+","+weekendDays+","+totalDays;
    	return info;
    }
}