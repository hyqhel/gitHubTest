package com.asiainfo.baas.bean.language;

import com.asiainfo.baas.bean.partyname.*;
import java.util.*;
import com.asiainfo.baas.bean.identification.*;
import com.asiainfo.baas.bean.*;

/**
 * Represents a spoken and/or written language.
 */
public class Language {

	public List<PartyName> _language;
	public List<PartyIdentification> _partyIdentification;
	
	public String languageId;
	/**
	 * The alphabet name use for the language Note: ISO standard ?
	 */
	public String alphabetName;
	/**
	 * A list of the dialects of the language Note: ISO standard ?
	 */
	public String dialectNames;
	public List<PartyName> get_language() {
		return _language;
	}
	public void set_language(List<PartyName> _language) {
		this._language = _language;
	}
	public List<PartyIdentification> get_partyIdentification() {
		return _partyIdentification;
	}
	public void set_partyIdentification(
			List<PartyIdentification> _partyIdentification) {
		this._partyIdentification = _partyIdentification;
	}
	public String getAlphabetName() {
		return alphabetName;
	}
	public void setAlphabetName(String alphabetName) {
		this.alphabetName = alphabetName;
	}
	public String getDialectNames() {
		return dialectNames;
	}
	public void setDialectNames(String dialectNames) {
		this.dialectNames = dialectNames;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	
	
}
