package com.asiainfo.baas.bean;

import java.util.*;

/**
 * Represents an  individual, organization or organization unit.Party is an abstract concept that should be used in places where the business says something can be an organization , organization unit or an individual
 */
public abstract class Party {

	public List<PartyRole> _partyRole;
	/**
	 * Unique identifier for Party
	 */
	public string partyId;
	/**
	 * The time period that the Party is valid for
	 */
	public TimePeriod validFor;

}