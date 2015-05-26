package com.asiainfo.baas.dao;

import java.util.List;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.contact.*;
/**
 * party role服务接口
 * @author huangyq3
 *
 */
public interface PartyRoleDao {
/**
 * 插入party role联系方式
 * @param partyRoleContact
 * @return
 */
	PartyRole insertPartyRoleContact(ContactMedium partyRoleContact);
/**
 * 建立一个partyrole
 * @param parameter
 * @return
 */
	boolean createOnePartyRole(PartyRole parameter);

	/**
	 * 插入party role联系方式
	 * @param partyRoleContact
	 * @return
	 */
		List<PartyRole> searchPartyRolesByPartyId(String partyId);
}
