package com.asiainfo.baas.bean.identification;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.language.*;

/**
 * Party Identification represents our registration of information used as proof of identity by a Party
 */
public abstract class PartyIdentification {

	public String partyIdentificationId;
	public Organization _issuer;
	public Language _language;
	
	public String identifiEnumId;
	
	
	public String getIdentifiEnumId() {
		return identifiEnumId;
	}
	public void setIdentifiEnumId(String identifiEnumId) {
		this.identifiEnumId = identifiEnumId;
	}
	/**
	 * The date that the identification was produced / printed
	 */
	public String issueDate;
	/**
	 * A scan of the identification paper Note: May be used to get a copy of the photo to help in identification
	 */
	public String scan;
	/**
	 * The time period that the Party Identification is valid for Note: The validitity start date may not necessarily equal the issue date (e.g. credit cards are only valid some time after thay are issued) Permitted Values: Refer Time Period class
	 */
	public String validFor;
	public Organization get_issuer() {
		return _issuer;
	}
	public void set_issuer(Organization _issuer) {
		this._issuer = _issuer;
	}
	public Language get_language() {
		return _language;
	}
	public void set_language(Language _language) {
		this._language = _language;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getScan() {
		return scan;
	}
	public void setScan(String scan) {
		this.scan = scan;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getPartyIdentificationId() {
		return partyIdentificationId;
	}
	public void setPartyIdentificationId(String partyIdentificationId) {
		this.partyIdentificationId = partyIdentificationId;
	}
	
}
