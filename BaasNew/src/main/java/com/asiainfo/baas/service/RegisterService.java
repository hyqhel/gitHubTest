package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * 注册用户接口服务
 */
public interface RegisterService {

	/**
	 * 注册用户
	 * @param party
	 */
	Partys registerUser(Partys party);

	/**
	 * 添加party
	 * @param party
	 */
	Partys addPartyInfo(Partys party);

	/**
	 * 添加partyname
	 * @param partyName
	 */
	PartyName addPartyNameInfo(PartyName partyName);

	/**
	 * 添加认证信息
	 * @param identifitionInfo
	 */
	PartyIdentification addIdentificationInfo(PartyIdentification identifitionInfo);

}