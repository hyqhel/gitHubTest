package com.asiainfo.baas.bean.common;

import java.util.List;

import com.asiainfo.baas.bean.association.PartyRoleAssociation;
import com.asiainfo.baas.bean.contact.ContactMedium;
import com.asiainfo.baas.bean.association.*;
import java.util.*;
import com.asiainfo.baas.bean.contact.*;

/**
 * The part played by a party in a given context with any characteristics, such as expected pattern of behavior, attributes, and/or associations that it entails.PartyRole is an abstract concept that should be used in places where the business refers to a Party playing a Role
 */
public class PartyRole {

	public Partys _party;
	public List<PartyRoleAssociation> _partyRoleAssociation;
	public List<ContactMedium> _contactMedium;
	public List<PartyRoleAssociation> _partyRoleAssociation2;
	/**
	 * Unique identifier for PartyRoles
	 */
	public String partyRoleId;
	/**
	 * Used to track the lifecycle status, e.g. existing, prospective or former customers.
	 */
	public String status;
	/**
	 * The time period that the PartyRole is valid for
	 */
	public String validFor;
	/**
	 * A word, term, or phrase by which the PartyRole is known and distinguished from other PartyRoles.
	 */
	public String name;
	
	public Partys get_party() {
		return _party;
	}
	public void set_party(Partys _party) {
		this._party = _party;
	}
	public List<PartyRoleAssociation> get_partyRoleAssociation() {
		return _partyRoleAssociation;
	}
	public void set_partyRoleAssociation(
			List<PartyRoleAssociation> _partyRoleAssociation) {
		this._partyRoleAssociation = _partyRoleAssociation;
	}
	public List<ContactMedium> get_contactMedium() {
		return _contactMedium;
	}
	public void set_contactMedium(List<ContactMedium> _contactMedium) {
		this._contactMedium = _contactMedium;
	}
	public List<PartyRoleAssociation> get_partyRoleAssociation2() {
		return _partyRoleAssociation2;
	}
	public void set_partyRoleAssociation2(
			List<PartyRoleAssociation> _partyRoleAssociation2) {
		this._partyRoleAssociation2 = _partyRoleAssociation2;
	}
	public String getPartyRoleId() {
		return partyRoleId;
	}
	public void setPartyRoleId(String partyRoleId) {
		this.partyRoleId = partyRoleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
