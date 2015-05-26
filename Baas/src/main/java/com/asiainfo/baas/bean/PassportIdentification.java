package com.asiainfo.baas.bean;

/**
 * Passport information used to Identify an Individual
 */
public class PassportIdentification extends IndividualIdentification {

	/**
	 * The country issuing the passport.
	 */
	public string issuingCountry;
	/**
	 * The unique identifier per passport issuer
	 */
	public string passportNr;
	/**
	 * The type of Passport. e.g. Personal, Consular
	 */
	public string passportType;

}