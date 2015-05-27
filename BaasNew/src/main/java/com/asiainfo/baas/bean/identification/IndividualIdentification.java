package com.asiainfo.baas.bean.identification;

import com.asiainfo.baas.bean.common.*;

/**
 * Individual Identification represents our registration of information used as proof of identity by an Individual
 */
public abstract class IndividualIdentification extends PartyIdentification {

	public Individual _individual;
	
	public String IndividualType;

	public Individual get_individual() {
		return _individual;
	}

	public void set_individual(Individual _individual) {
		this._individual = _individual;
	}

	public String getIndividualType() {
		return IndividualType;
	}

	public void setIndividualType(String individualType) {
		IndividualType = individualType;
	}
	
	
}
