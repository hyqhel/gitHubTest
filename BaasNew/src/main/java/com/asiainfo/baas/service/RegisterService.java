package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * ע���û��ӿڷ���
 */
public interface RegisterService {

	/**
	 * ע���û�
	 * @param party
	 */
	Partys registerUser(Partys party);

	/**
	 * ���party
	 * @param party
	 */
	Partys addPartyInfo(Partys party);

	/**
	 * ���partyname
	 * @param partyName
	 */
	PartyName addPartyNameInfo(PartyName partyName);

	/**
	 * �����֤��Ϣ
	 * @param identifitionInfo
	 */
	PartyIdentification addIdentificationInfo(PartyIdentification identifitionInfo);

}