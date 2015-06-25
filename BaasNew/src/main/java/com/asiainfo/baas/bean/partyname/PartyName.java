package com.asiainfo.baas.bean.partyname;

import com.asiainfo.baas.bean.language.*;

/**
 * A word, term, or phrase by which a party (individual or organization) is known and distinguished from other parties.A name is an informal way of identifying an object [Fowler].PartyName is an abstract concept that should be used in places where the business refers to an organization name, organization unit name or individual name
 */
public abstract class PartyName {

	public String partyNameId;
	
	public Language _partyName;
	/**
	 * The time period that the PartyName is applicable.
	 */
	public String validFor;
	public Language get_partyName() {
		return _partyName;
	}
	public void set_partyName(Language _partyName) {
		this._partyName = _partyName;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getPartyNameId() {
		return partyNameId;
	}
	public void setPartyNameId(String partyNameId) {
		this.partyNameId = partyNameId;
	}
	
}