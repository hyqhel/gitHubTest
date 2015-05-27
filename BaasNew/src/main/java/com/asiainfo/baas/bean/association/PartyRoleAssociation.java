package com.asiainfo.baas.bean.association;

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
}
