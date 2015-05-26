package com.asiainfo.baas.bean.language;

import java.util.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * Represents a spoken and/or written language.
 */
public class Language {

	public List<PartyName> _language;
	public List<PartyIdentification> _partyIdentification;
	/**
	 * The alphabet name use for the language
	 * 
	 * Note:
	 * ISO standard ?
	 */
	public String alphabetName;
	/**
	 * A list of the dialects of the language
	 * 
	 * Note:
	 * ISO standard ?
	 */
	public String dialectNames;

}