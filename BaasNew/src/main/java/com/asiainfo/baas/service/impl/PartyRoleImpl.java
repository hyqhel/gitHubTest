package com.asiainfo.baas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.baas.service.*;
import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.dao.PartyInfoDao;
import com.asiainfo.baas.dao.PartyRoleDao;
@Service
public class PartyRoleImpl implements PartyRoleService {
	@Autowired
	PartyRoleDao partyRoleDao;
	@Autowired
	PartyInfoDao partyInfoDao;
	/**
	 * ����partyrole
	 * @return
	 */
	public PartyRole addOnePartyRole(PartyRole partyRole,
			PartyRole partyRelateRole) {
		throw new UnsupportedOperationException();
	}
}
