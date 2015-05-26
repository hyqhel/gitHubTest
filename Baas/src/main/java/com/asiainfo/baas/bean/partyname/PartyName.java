package com.asiainfo.baas.bean.partyname;

import com.asiainfo.baas.bean.language.*;

/**
 * A word, term, or phrase by which a party (individual or organization) is known and distinguished from other parties.A name is an informal way of identifying an object [Fowler].PartyName is an abstract concept that should be used in places where the business refers to an organization name, organization unit name or individual name
 */
public abstract class PartyName {

	public Language _partyName;
	/**
	 * The time period that the PartyName is applicable.
	 */
	public String validFor;

}