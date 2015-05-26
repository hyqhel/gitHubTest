package com.asiainfo.baas.bean.contact;

/**
 * The eMail address to use when contacting a given PartyRole. An eMailContact is a type of ContactMedium.
 */
public class EmailContact extends ContactMedium {

	/**
	 * A full e-mail address in standard format.
	 * 
	 * Notes:
	 * e.g. fred.bloggs@thing.com
	 */
	public String eMailAddress;

}