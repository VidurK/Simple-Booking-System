/**
 * @(#)CustomerRecord.java
 *
 *
 * @Eric Newcombe 
 * @version 1.00 2013/6/4
 */

//package TrippCreekCampground;

import java.io.*;

public class CustomerRecord {
	
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String province;
	public String postalCode;
	public String licencePlate;
	public String emailAddress;
	public String notes;
	public int trailerSize;
	
    public CustomerRecord(String firstName, String lastName, String address, String city, String province, String postalCode, String licencePlate, String emailAddress, int trailerSize, String notes) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.province = province;
    	this.postalCode = postalCode;
    	this.licencePlate = licencePlate;
    	this.emailAddress = emailAddress;
    	this.trailerSize = trailerSize;
    	this.notes = notes;
    }
    
    public CustomerRecord(){
    	firstName = "";
    	lastName = "";
    	address = "";
    	city = "";
    	province = "";
    	postalCode = "";
    	licencePlate = "";
    	emailAddress = "";
    	trailerSize = 0;
    	notes = "";
    }
    
    public static void printRecords(CustomerRecord[] c,int numberOfCampers) throws IOException{
    	//Prints a sorted array of customer records to the "customers.dat" file
    	PrintWriter fileOut = new PrintWriter (new FileWriter("data\\customers.dat"));
        CustomerRecord[] b = new CustomerRecord[numberOfCampers];
        for (int i=0;i<numberOfCampers;i++) {
            b[i]=c[i];
        }
    	//b = mergeSort(b);
    	fileOut.println(numberOfCampers);
    	for(int i = 0; i < numberOfCampers; i++){
    		fileOut.println(b[i].firstName+","+b[i].lastName+","+b[i].address+","+b[i].city+","+b[i].province+","+b[i].postalCode+","+b[i].licencePlate+","+b[i].emailAddress+","+b[i].trailerSize+","+b[i].notes);
    	}
    	fileOut.close();
    }
    public static CustomerRecord[] sortCampers(CustomerRecord[] b, int numberOfCampers) {
        CustomerRecord[] c = new CustomerRecord[numberOfCampers];
        for (int i=0;i<numberOfCampers;i++) {
            b[i]=c[i];
        }
    	c = mergeSort(c);
        return c;
    }
    
    public String getInformation(){
    	String customerInfo = firstName+","+lastName+","+address+","+city+","+province+","+postalCode+","+licencePlate+","+emailAddress+","+trailerSize+","+notes;
    	return customerInfo;
    }
    public static CustomerRecord[] mergeSort(CustomerRecord[] array){
        //merge sorts an array of Customer Records
        if(array.length<=1){
                return array;
        }
        int middle = array.length / 2;

        CustomerRecord[] left = new CustomerRecord[middle];
        CustomerRecord[] right = new CustomerRecord[array.length-middle];

        System.arraycopy(array,0,left,0,middle);
        System.arraycopy(array,middle,right,0,array.length-middle);
        left=mergeSort(left);
        right=mergeSort(right);
        return merge(left,right);
   }
   
    public static CustomerRecord[] merge( CustomerRecord[] left, CustomerRecord[] right ) {
	/*  This method takes two sorted arrays of Customer Records, left and right,
	 *  then merges them into one sorted array that is returned.*/ 
        // Initialize the result array.
        CustomerRecord[ ] result = new CustomerRecord[ left.length + right.length ];

        // Initialize the array indexes.
        int i = 0; // left-index
        int j = 0; // right-index
        int k = 0; // result-index

        // Merge the results by comparing each element.
        while( i < left.length && j < right.length ) {
                if((left[i].lastName).compareTo(right[ j ].lastName )>0){
                        result[ k ] = right[ j ];
                        j++; // Next right-index
                } else {
                        result[ k ] = left[ i ];
                        i++; // Next left-index
                }
                k++; // Next result-index
        }
        // Append whatever is left from the left array into the result array.
        while( i < left.length ) {
                result[ k ] = left[ i ];
                i++; // left-index
                k++; // result-index
        }
        // Append whatever is left from the right array into the result array.
        while( j < right.length ) {
                result[ k ] = right[ j ];
                j++; // right-index
                k++; // result-index
        }
        return result;
    }
}