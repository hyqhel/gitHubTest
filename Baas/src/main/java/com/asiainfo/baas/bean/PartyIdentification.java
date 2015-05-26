package com.asiainfo.baas.bean;

/**
 * Party Identification represents our registration of information used as proof of identity by a Party
 */
public abstract class PartyIdentification {

	public Organization _issuer;
	public Language _language;
	/**
	 * The date that the identification was produced / printed
	 */
	public Date issueDate;
	/**
	 * A scan of the identification paper
	 * 
	 * Note:
	 * May be used to get a copy of the photo to help in identification
	 */
	public string scan;
	/**
	 * The time period that the Party Identification is valid for
	 * Note:
	 * The validitity start date may not necessarily equal the issue date (e.g. credit cards are only valid some time after thay are issued)
	 * 
	 * Permitted Values:
	 * Refer Time Period class
	 */
	public TimePeriod validFor;

}