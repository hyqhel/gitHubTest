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
	public Partys insertParty(Partys party);
	/**
	 * 添加个人party
	 */
	public Partys insertIndividualParty(Partys individualparty);
	/**
	 * 添加集团party
	 */
	public Partys insertOrganizationParty(Partys organizasparty);
	
	/**
	 * 添加partyname
	 */
	public PartyName addPartyName(PartyName partyName);
	
	/**
	 * 添加个人partyname
	 */
	public PartyName addIndividualPartyNameInfo(PartyName individualpartyName);
	/**
	 * 添加集团partyname
	 */
	public PartyName addOrganizationPartyNameInfo(PartyName organizaspartyName);

	/**
	 * 添加认证信息
	 */
	public PartyIdentification addPartyIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * 添加个人认证信息
	 */
	public PartyIdentification addIndividualIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * 添加集团认证信息
	 */
	public PartyIdentification addOrganizationIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * 查询party
	 */
	public Partys searchParty(Partys party);
	/**
	 * 查询partyName
	 */
	public Partys searchPartyNameByPartyId(String partyId);
	/**
	 * 查询party认证信息
	 */
	public Partys searchPartyIdentificationByPartyId(String partyId);
	/**
	 * 查询party语言信息
	 */
	public Partys searchPartyLanguageByPartyId(String partyId);
}