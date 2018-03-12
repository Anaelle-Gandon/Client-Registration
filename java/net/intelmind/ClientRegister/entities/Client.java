package net.intelmind.ClientRegister.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Client implements Serializable {
	@Id @GeneratedValue
	private Integer id;
	
	private String name;
	private String phoneNum;
	private String dateCreation;
		
	public Client() {
		
		/*LocalDateTime dCreation = LocalDateTime.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss"); 
		
		this.dateCreation = dCreation.format(formatterDate);*/
	}	
		

	public Client(String name, String phoneNum, String dateCreation) {
		super();
		
		this.name = name;
		this.phoneNum = phoneNum;
		this.dateCreation = dateCreation;
		
		/*LocalDateTime dCreation = LocalDateTime.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss"); 
		
		this.dateCreation = dCreation.format(formatterDate);*/
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		
		builder.append(", name=");
		builder.append(name);
		builder.append(", phoneNum=");
		builder.append(phoneNum);
		builder.append("]");
		return builder.toString();
	}

	
	
}
