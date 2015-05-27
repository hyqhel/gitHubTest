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
	public String getIssuingCountry() {
		return issuingCountry;
	}
	public void setIssuingCountry(String issuingCountry) {
		this.issuingCountry = issuingCountry;
	}
	public String getPassportNr() {
		return passportNr;
	}
	public void setPassportNr(String passportNr) {
		this.passportNr = passportNr;
	}
	public String getPassportType() {
		return passportType;
	}
	public void setPassportType(String passportType) {
		this.passportType = passportType;
	}
	
	
}
