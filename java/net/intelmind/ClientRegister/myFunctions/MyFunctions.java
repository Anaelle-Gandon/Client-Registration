package net.intelmind.ClientRegister.myFunctions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyFunctions {

	public MyFunctions() {
		// TODO Auto-generated constructor stub
	}
	
	public static String dateTimeToString(LocalDateTime dateCreation) {
		
		//LocalDateTime dCreation = LocalDateTime.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss"); 
		
		String dCreation = dateCreation.format(formatterDate);
		
		return dCreation;
	}
	
	public static String duration(String date) {
		String duration = null;
		
		int year = Integer.parseInt(date.substring(0, 4));
		int mon = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		
		String now = MyFunctions.dateTimeToString(LocalDateTime.now());	
		
		int yearNow = Integer.parseInt(now.substring(0, 4));
		int monNow = Integer.parseInt(now.substring(5, 7));
		int dayNow = Integer.parseInt(now.substring(8, 10));
		
		int difYear = yearNow - year;
		int difMon = monNow - mon;
		int difDay = dayNow - day;
		
		if(difYear != 0) {
			duration = difYear + " year(s) ago";
		} else if (difMon != 0) {
			duration = difMon + " month(s) ago";
		} else if (difDay != 0) {
			duration = difDay + " day(s) ago";
		} else {
			duration = "today";
		}
				
		return duration;
	}
			

}
