package com.asiainfo.baas.bean.partyname;

import java.util.*;
import com.asiainfo.baas.bean.common.*;

/**
 * A word, term, or phrase by which an individual is known and distinguished from other individuals.A name is an informal way of identifying an object [Fowler]This entity allows for international naming variations. An IndividualName is a type of PartyName.
 */
public class IndividualName extends PartyName {

	public List<Individual> _individual;
	/**
	 * A name that describes someone's aristocratic position, such as Baron, Graf, Earl, and so forth.
	 */
	public String aristocraticTitle;
	/**
	 * An abbreviation or word that pertains to the generation in a family, such Sr, Jr.
	 */
	public String familyGeneration;
	/**
	 * Notes:
	 * e.g. Van den, Von etc.
	 */
	public String familyNamePrefix;
	/**
	 * Contains the non-chosen or inherited name. Also known as a person's last name in the Western context. Notes: Also known as surname Multiple fields may be entered with a delimiter in-between
	 */
	public String familyNames;
	/**
	 * Contains, in one string, a fully formatted name with all of its pieces in their proper place. This includes all of the necessary punctuation. This de-normalized form of the name cannot be easily parsed.
	 * 
	 * Note:
	 * if NULL, then derive from the other fields using name policy
	 */
	public String formattedName;
	/**
	 * Contains the Salutation,e.g. Mr., Mrs., Hon., Dr.,Major, etc
	 * 
	 * Note:
	 * Also known as person title
	 * Also includes. Miss, Ms,
	 */
	public String formOfAddress;
	/**
	 * An abbreviation or word that pertains to the generation in a family history, such as Sr., Jr., III (the third), and so forth.
	 */
	public String generation;
	/**
	 * Note: Also known as Christian name, chosen name, first name Multiple fields may be entered with a delimiter in-between or stored in a collection
	 */
	public String givenNames;
	/**
	 * Contains, in one string, a fully formatted name with all of its pieces in their proper place. This includes all of the necessary punctuation
	 * 
	 * Note:
	 * if NULL, then derive from the other fields using name policy
	 */
	public String legalName;
	/**
	 * Middle name(s) or initial(s)
	 * Note:
	 * Multiple fields may be entered with a delimiter in-between or stored in a collection
	 */
	public String middleNames;
	/**
	 * Contains the chosen name by which the person prefers to be addressed. Note: This name may be a name other than a given name, such as a nickname
	 */
	public String preferredGivenName;
	/**
	 * Contains the letters used to describe academic or other type qualifications held by a person and/or the distinctions conferred upon them. e.g. PhD, MD, CPA, MCSD, etc
	 * 
	 * Note:
	 * also known as orders, decorations, honors, awards and distinctions
	 */
	public String qualifications;

}