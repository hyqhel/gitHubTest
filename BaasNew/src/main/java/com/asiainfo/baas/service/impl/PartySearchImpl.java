package com.asiainfo.baas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.baas.service.*;
import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.dao.PartyInfoDao;
import com.asiainfo.baas.dao.PartyRoleDao;
@Service
public class PartySearchImpl implements PartySearchService {
	@Autowired
	PartyInfoDao partyInfoDao;
	/**
	 * ��ѯparty
	 * @return
	 */
	public Partys searchParty(java.lang.String partyId) {
		throw new UnsupportedOperationException();
	}
}
