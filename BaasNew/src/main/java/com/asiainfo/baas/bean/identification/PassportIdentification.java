package com.asiainfo.baas.bean.identification;

/**
 * Passport information used to Identify an Individual
 */
public class PassportIdentification extends IndividualIdentification {

	/**
	 * The country issuing the passport.
	 */
	public String issuingCountry;
	/**
	 * The unique identifier per passport issuer
	 */
	public String passportNr;
	/**
	 * The type of Passport. e.g. Personal, Consular
	 */
	public String passportType;
}
