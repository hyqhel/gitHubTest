package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
/**
 * party role servcie
 * @author huangyq3
 *
 */
public interface PartyRoleService {
/**
 * ����partyrole
 * @param partyRole
 * @return
 */
	PartyRole addOnePartyRole(PartyRole partyRole,PartyRole partyRelateRole);
}
