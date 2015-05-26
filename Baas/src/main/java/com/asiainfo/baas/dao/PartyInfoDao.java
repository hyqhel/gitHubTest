package com.asiainfo.baas.dao;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * party dao��ӿ�
 */
public interface PartyInfoDao {
	/**
	 * ���party
	 */
	Partys insertParty(Partys party);
	/**
	 * ��Ӹ���party
	 */
	Partys insertIndividualParty(Partys individualparty);
	/**
	 * ��Ӽ���party
	 */
	Partys insertOrganizationParty(Partys organizasparty);
	
	/**
	 * ���partyname
	 */
	PartyName addPartyName(PartyName partyName);
	
	/**
	 * ��Ӹ���partyname
	 */
	PartyName addIndividualPartyNameInfo(PartyName individualpartyName);
	/**
	 * ��Ӽ���partyname
	 */
	PartyName addOrganizationPartyNameInfo(PartyName organizaspartyName);

	/**
	 * �����֤��Ϣ
	 */
	PartyIdentification addIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * ��ѯparty
	 */
	Partys searchParty(Partys party);
	/**
	 * ��ѯpartyName
	 */
	Partys searchPartyNameByPartyId(String partyId);
	/**
	 * ��ѯparty��֤��Ϣ
	 */
	Partys searchPartyIdentificationByPartyId(String partyId);
	/**
	 * ��ѯparty������Ϣ
	 */
	Partys searchPartyLanguageByPartyId(String partyId);
}