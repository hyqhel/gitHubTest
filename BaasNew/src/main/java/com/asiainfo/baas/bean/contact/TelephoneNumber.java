package com.asiainfo.baas.bean.contact;

/**
 * A number and type of number assigned to a particular telephone and used in making connections to it.
 */
public class TelephoneNumber extends ContactMedium {

	/**
	 * A number assigned to a particular telephone and used in making connections to it.
	 */
	public String number;
	/**
	 * The kind of telephone number, such as mobile, home, office.
	 */
	public String type;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
