package com.asiainfo.baas.bean.common;

import java.util.*;

/**
 * Represents an  individual, organization or organization unit.Party is an abstract concept that should be used in places where the business says something can be an organization , organization unit or an individual
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

}