package com.asiainfo.baas.bean.common;

import com.asiainfo.baas.bean.identification.*;
import java.util.*;
import com.asiainfo.baas.bean.partyname.*;

/**
 * A group of people identified by shared interests or purpose. Examples include business, department, enterprise.Because of the complex nature of many businesses, both organizations and organization units are represented by the same business entity in this model. An Organization is a type of Party
 */
public class Organization extends Partys {

	/**
	 * Organization Identification represents our registration of information used as proof of identity by an Organization
	 */
	public List<OrganizationIdentification> _organizationIdentification;
	public OrganizationName _organizationName;
	public List<PartyIdentification> _partyIdentification;
	/**
	 * Incorporation date to disincorporation date.
	 */
	public String existsDuring;
	/**
	 * Permitted Values: TRUE or FALSE
	 */
	public boolean isLegalEntity;
	/**
	 * The type of organization Notes: Club, Society, ��
	 */
	public String type;
	
	public List<OrganizationIdentification> get_organizationIdentification() {
		return _organizationIdentification;
	}
	public void set_organizationIdentification(
			List<OrganizationIdentification> _organizationIdentification) {
		this._organizationIdentification = _organizationIdentification;
	}
	public OrganizationName get_organizationName() {
		return _organizationName;
	}
	public void set_organizationName(OrganizationName _organizationName) {
		this._organizationName = _organizationName;
	}
	public List<PartyIdentification> get_partyIdentification() {
		return _partyIdentification;
	}
	public void set_partyIdentification(
			List<PartyIdentification> _partyIdentification) {
		this._partyIdentification = _partyIdentification;
	}
	public String getExistsDuring() {
		return existsDuring;
	}
	public void setExistsDuring(String existsDuring) {
		this.existsDuring = existsDuring;
	}
	public boolean isLegalEntity() {
		return isLegalEntity;
	}
	public void setLegalEntity(boolean isLegalEntity) {
		this.isLegalEntity = isLegalEntity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
