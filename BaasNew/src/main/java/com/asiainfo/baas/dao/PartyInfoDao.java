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
	public Partys insertParty(Partys party);
	/**
	 * ��Ӹ���party
	 */
	public Partys insertIndividualParty(Partys individualparty);
	/**
	 * ��Ӽ���party
	 */
	public Partys insertOrganizationParty(Partys organizasparty);
	
	/**
	 * ���partyname
	 */
	public PartyName addPartyName(PartyName partyName);
	
	/**
	 * ��Ӹ���partyname
	 */
	public PartyName addIndividualPartyNameInfo(PartyName individualpartyName);
	/**
	 * ��Ӽ���partyname
	 */
	public PartyName addOrganizationPartyNameInfo(PartyName organizaspartyName);

	/**
	 * �����֤��Ϣ
	 */
	public PartyIdentification addPartyIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * ��Ӹ�����֤��Ϣ
	 */
	public PartyIdentification addIndividualIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * ��Ӽ�����֤��Ϣ
	 */
	public PartyIdentification addOrganizationIdentificationInfo(PartyIdentification identifitionInfo);
	
	/**
	 * ��ѯparty
	 */
	public Partys searchParty(Partys party);
	/**
	 * ��ѯpartyName
	 */
	public Partys searchPartyNameByPartyId(String partyId);
	/**
	 * ��ѯparty��֤��Ϣ
	 */
	public Partys searchPartyIdentificationByPartyId(String partyId);
	/**
	 * ��ѯparty������Ϣ
	 */
	public Partys searchPartyLanguageByPartyId(String partyId);
}