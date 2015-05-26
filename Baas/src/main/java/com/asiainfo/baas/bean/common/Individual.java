package com.asiainfo.baas.bean.common;

import java.util.*;
import com.asiainfo.baas.bean.identification.*;
import com.asiainfo.baas.bean.partyname.*;

/**
 * Represents a single human being (a man, woman or child).
 * The individual could be a customer, an employee or any other person that the organization needs to store information about. An Individual is a type of Party.
 */
public class Individual extends Partys {

	public List<IndividualIdentification> _individualIdentification;
	public IndividualName _individualName;
	/**
	 * Birth date and death date.
	 */
	public String aliveDuring;
	/**
	 * Notes: ?Used for legal requirements (special billing, special products, special contact media, such as Braille). This should be modeled as a separate entity but is shown as an attribute for brevity.
	 */
	public String disabilities;
	/**
	 * A socially constructed role that implies behaviours, activities, and attributes.
	 */
	public String gender;
	/**
	 * Permitted Values: married, never married, divorced, widowed
	 */
	public String maritalStatus;
	/**
	 * Note:
	 * Pointer to a country object
	 */
	public String nationality;
	/**
	 * Note:
	 * Pointer to a Location object
	 * City, town name etc.
	 */
	public String placeOfBirth;
	/**
	 * Note:
	 * Probably only used for employees, but is really role independent
	 * This should be modeled as a separate entity but is shown as an attribute for brevity
	 */
	public String skills;

}