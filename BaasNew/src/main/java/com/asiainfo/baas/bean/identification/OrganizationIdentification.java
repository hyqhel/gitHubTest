package com.asiainfo.baas.bean.identification;

import com.asiainfo.baas.bean.common.*;

/**
 * Organization Identification represents our registration of information used as proof of identity by an Organization
 */
public abstract class OrganizationIdentification extends PartyIdentification {

	public Organization _organization;

	public Organization get_organization() {
		return _organization;
	}

	public void set_organization(Organization _organization) {
		this._organization = _organization;
	}
	
	
}
