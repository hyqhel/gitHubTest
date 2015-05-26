package com.asiainfo.baas.dao;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * party dao层接口
 */
public interface PartyInfoDao {
	/**
	 * 添加party
	 */
	Partys insertParty(Partys party);
	/**
	 * 添加个人party
	 */
	Partys insertIndividualParty(Partys individualparty);
	/**
	 * 添加集团party
	 */
	Partys insertOrganizationParty(Partys organizasparty);
	
	/**
	 * 添加partyname
	 */
	PartyName addPartyName(PartyName partyName);
	
	/**
	 * 添加个人partyname
	 */
	PartyName addIndividualPartyNameInfo(PartyName individualpartyName);
	/**
	 * 添加集团partyname
	 */
	PartyName addOrganizationPartyNameInfo(PartyName organizaspartyName);

	/**
	 * 添加认证信息
	 */
	PartyIdentification addIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * 查询party
	 */
	Partys searchParty(Partys party);
	/**
	 * 查询partyName
	 */
	Partys searchPartyNameByPartyId(String partyId);
	/**
	 * 查询party认证信息
	 */
	Partys searchPartyIdentificationByPartyId(String partyId);
	/**
	 * 查询party语言信息
	 */
	Partys searchPartyLanguageByPartyId(String partyId);
}