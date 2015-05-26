package com.asiainfo.baas.bean;

import java.util.*;

/**
 * Represents a spoken and/or written language.
 */
public class Language {

	public List<PartyName> _language;
	public List<PartyIdentification> _partyIdentification;
	public List<LanguageAbility> _languageAbility;
	/**
	 * The alphabet name use for the language
	 * 
	 * Note:
	 * ISO standard ?
	 */
	public string alphabetName;
	/**
	 * A list of the dialects of the language
	 * 
	 * Note:
	 * ISO standard ?
	 */
	public string dialectNames;

}