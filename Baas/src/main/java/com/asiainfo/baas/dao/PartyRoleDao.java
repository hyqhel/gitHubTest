package com.asiainfo.baas.dao;

import java.util.List;

import com.asiainfo.baas.bean.common.*;
import com.asiainfo.baas.bean.contact.*;
/**
 * party role����ӿ�
 * @author huangyq3
 *
 */
public interface PartyRoleDao {
/**
 * ����party role��ϵ��ʽ
 * @param partyRoleContact
 * @return
 */
	PartyRole insertPartyRoleContact(ContactMedium partyRoleContact);
/**
 * ����һ��partyrole
 * @param parameter
 * @return
 */
	boolean createOnePartyRole(PartyRole parameter);

	/**
	 * ����party role��ϵ��ʽ
	 * @param partyRoleContact
	 * @return
	 */
		List<PartyRole> searchPartyRolesByPartyId(String partyId);
}
