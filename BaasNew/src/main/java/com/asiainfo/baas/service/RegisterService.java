package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.identification.*;

/**
 * 注册用户接口服务
 */
public interface RegisterService {

	/**
	 * 注册个人用户
	 * @param party
	 */
	public Partys registerUserIndividual(Individual individual) throws Exception;

	/**
	 * 添加个人party
	 * @param party
	 */
	public Partys addIndividualPartyInfo(Individual individual) throws Exception;

	/**
	 * 添加个人partyname
	 * @param partyName
	 */
	public PartyName addIndividualPartyNameInfo(IndividualName individualName) throws Exception;

	/**
	 * 添加个人认证信息
	 * @param identifitionInfo
	 */
	public PartyIdentification addIndividualIdentificationInfo(IndividualIdentification individualIdentifitionInfo) throws Exception;

}