package com.asiainfo.baas.bean.common;

import java.util.*;
import com.asiainfo.baas.bean.association.*;
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

}