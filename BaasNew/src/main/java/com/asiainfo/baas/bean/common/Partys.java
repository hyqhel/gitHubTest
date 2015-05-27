package com.asiainfo.baas.bean.common;

import java.util.*;

/**
 * Represents an  individual, organization or organization unit.Party is an abstract concept that should be used in places where the business says something can be an organization , organization unit or an individual
 */
public abstract class Partys {

	public List<PartyRole> _partyRole;
	/**
	 * Unique identifier for Party
	 */
	public String partyId;
	/**
	 * The time period that the Party is valid for
	 */
	public String validFor;
	public String party_type;
	public String party_pwd;
	
	
	public List<PartyRole> get_partyRole() {
		return _partyRole;
	}
	public void set_partyRole(List<PartyRole> _partyRole) {
		this._partyRole = _partyRole;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getParty_type() {
		return party_type;
	}
	public void setParty_type(String party_type) {
		this.party_type = party_type;
	}
	public String getParty_pwd() {
		return party_pwd;
	}
	public void setParty_pwd(String party_pwd) {
		this.party_pwd = party_pwd;
	}
	
	
}
