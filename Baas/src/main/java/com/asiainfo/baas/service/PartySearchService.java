package com.asiainfo.baas.service;

import com.asiainfo.baas.bean.common.*;
/**
 * 查询party信息服务类
 * @author huangyq3
 *
 */
public interface PartySearchService {
/**
 * 查询party
 * @param party
 * @return
 */
	Partys searchParty(Partys party);
}
