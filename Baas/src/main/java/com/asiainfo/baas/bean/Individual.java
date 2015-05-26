package com.asiainfo.baas.bean;

import java.util.*;

/**
 * Represents a single human being (a man, woman or child).
 * The individual could be a customer, an employee or any other person that the organization needs to store information about. An Individual is a type of Party.
 */
public class Individual extends Party {

	public List<IndividualIdentification> _individualIdentification;
	public IndividualName _individualName;
	public List<LanguageAbility> _languageAbility;
	/**
	 * Birth date and death date.
	 */
	public TimePeriod aliveDuring;
	/**
	 * Notes:  Used for legal requirements (special billing, special products, special contact media, such as Braille). This should be modeled as a separate entity but is shown as an attribute for brevity.
	 */
	public string disabilities;
	/**
	 * A socially constructed role that implies behaviours, activities, and attributes.
	 */
	public string gender;
	/**
	 * Permitted Values: married, never married, divorced, widowed
	 */
	public string maritalStatus;
	/**
	 * Note:
	 * Pointer to a country object
	 */
	public string nationality;
	/**
	 * Note:
	 * Pointer to a Location object
	 * City, town name etc.
	 */
	public string placeOfBirth;
	/**
	 * Note:
	 * Probably only used for employees, but is really role independent
	 * This should be modeled as a separate entity but is shown as an attribute for brevity
	 */
	public string skills;

}