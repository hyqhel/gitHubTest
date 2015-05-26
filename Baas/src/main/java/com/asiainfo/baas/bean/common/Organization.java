package com.asiainfo.baas.bean.common;

import java.util.*;
import com.asiainfo.baas.bean.identification.*;
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
	 * Permitted Values:
	 * TRUE or FALSE
	 */
	public boolean isLegalEntity;
	/**
	 * The type of organization
	 * 
	 * Notes:
	 * Club, Society, бн
	 */
	public String type;

}