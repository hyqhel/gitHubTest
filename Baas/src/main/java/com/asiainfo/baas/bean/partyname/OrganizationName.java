package com.asiainfo.baas.bean.partyname;

import java.util.*;
import com.asiainfo.baas.bean.common.*;

/**
 * A word, term, or phrase by which an organization is known and distinguished from other organizations.A name is an informal way of identifying an object [Fowler]An OrganizationName is a type of PartyName.
 */
public class OrganizationName extends PartyName {

	public List<Organization> _organization;
	/**
	 * Co., Inc., Ltd., Pty Ltd. , Plc., Gmbh
	 */
	public String nameType;
	/**
	 * The name that the organization (unit) trades under
	 */
	public String tradingName;

}