package com.asiainfo.baas.bean.association;

import com.asiainfo.baas.bean.common.PartyRole;
import com.asiainfo.baas.bean.common.*;

/**
 * Allows PartyRoles to be associated.aaaaaaaaa
 */
public class PartyRoleAssociation {

	public PartyRole _partyRole;
	/**
	 * A categorization of the association, such as organizational, household, and so forth.
	 */
	public String associationType;
	/**
	 * The time period that the PartyRoleAssociation is valid for.
	 */
	public String validFor;
	/**
	 * The condition of the association, such as active, inactive, planned.
	 */
	public String status;
	
	public PartyRole get_partyRole() {
		return _partyRole;
	}
	public void set_partyRole(PartyRole _partyRole) {
		this._partyRole = _partyRole;
	}
	public String getAssociationType() {
		return associationType;
	}
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}
	public String getValidFor() {
		return validFor;
	}
	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
