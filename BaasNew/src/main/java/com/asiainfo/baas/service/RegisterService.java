package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * ע���û��ӿڷ���
 */
public interface RegisterService {

	/**
	 * ע������û�
	 * @param party
	 */
	public Partys registerUserIndividual(Individual individual) throws Exception;

	/**
	 * ��Ӹ���party
	 * @param party
	 */
	public Partys addIndividualPartyInfo(Individual individual) throws Exception;

	/**
	 * ��Ӹ���partyname
	 * @param partyName
	 */
	public PartyName addIndividualPartyNameInfo(IndividualName individualName) throws Exception;

	/**
	 * ��Ӹ�����֤��Ϣ
	 * @param identifitionInfo
	 */
	public PartyIdentification addIndividualIdentificationInfo(IndividualIdentification individualIdentifitionInfo) throws Exception;

}