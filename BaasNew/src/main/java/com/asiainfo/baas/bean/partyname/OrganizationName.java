package com.asiainfo.baas.bean.partyname;

import com.asiainfo.baas.bean.common.*;
import java.util.*;

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
	public List<Organization> get_organization() {
		return _organization;
	}
	public void set_organization(List<Organization> _organization) {
		this._organization = _organization;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getTradingName() {
		return tradingName;
	}
	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}
	
	
}
