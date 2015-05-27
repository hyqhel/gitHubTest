package com.asiainfo.baas.bean.identification;

import com.asiainfo.baas.bean.common.*;

/**
 * Individual Identification represents our registration of information used as proof of identity by an Individual
 */
public abstract class IndividualIdentification extends PartyIdentification {

	public Individual _individual;
	
	public String IndividualType;
}
