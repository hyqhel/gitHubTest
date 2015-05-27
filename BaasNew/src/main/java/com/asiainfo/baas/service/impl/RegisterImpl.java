package com.asiainfo.baas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.baas.bean.common.Partys;
import com.asiainfo.baas.bean.identification.PartyIdentification;
import com.asiainfo.baas.bean.partyname.PartyName;
import com.asiainfo.baas.dao.PartyInfoDao;
import com.asiainfo.baas.dao.PartyRoleDao;
import com.asiainfo.baas.service.RegisterService;
@Service
public class RegisterImpl implements RegisterService {
	@Autowired
	PartyRoleDao partyRoleDao;
	@Autowired
	PartyInfoDao partyInfoDao;
	/**
	 * ע���û�
	 */
	public Partys registerUser(Partys party) {
		throw new UnsupportedOperationException();
	}

	/**
	 * ���party
	 */
	public Partys addPartyInfo(Partys party) {
		throw new UnsupportedOperationException();
	}

	/**
	 * ���partyname
	 */
	public PartyName addPartyNameInfo(PartyName partyName) {
		throw new UnsupportedOperationException();
	}

	/**
	 * �����֤��Ϣ
	 */
	public PartyIdentification addIdentificationInfo(
			PartyIdentification identifitionInfo) {
		throw new UnsupportedOperationException();
	}
}
