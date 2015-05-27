package com.asiainfo.baas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.baas.bean.common.Individual;
import com.asiainfo.baas.bean.common.Partys;
import com.asiainfo.baas.bean.identification.IndividualIdentification;
import com.asiainfo.baas.bean.identification.PartyIdentification;
import com.asiainfo.baas.bean.partyname.IndividualName;
import com.asiainfo.baas.bean.partyname.PartyName;
import com.asiainfo.baas.dao.PartyInfoDao;
import com.asiainfo.baas.dao.PartyRoleDao;
import com.asiainfo.baas.service.RegisterService;
import com.asiainfo.baas.util.Index;

@Service
public class RegisterImpl implements RegisterService {
	
	@Autowired
	PartyRoleDao partyRoleDao;
	@Autowired
	PartyInfoDao partyInfoDao;
	
	/**
	 * 注册个人用户
	 */
	@Transactional
	public Partys registerUserIndividual(Individual individual) throws Exception{
		// TODO 注册party用户   
		//添加partyname 
		//添加identification 
		//添加partyrole信息
		
		//保存个人用户party信息
		individual = (Individual)addIndividualPartyInfo(individual);
		
		//保存个人用户partyName信息
		IndividualName individualName = individual.get_individualName();
		List<Individual> listIndividual = new ArrayList<Individual>();
		listIndividual.add(individual);
		individualName.set_individual(listIndividual);
		addIndividualPartyNameInfo(individualName);
		
		//保存个人用户partyIndividual信息
		List<IndividualIdentification> individualIdentify = individual.get_individualIdentification();
		if(null!=individualIdentify && individualIdentify.size()>0){
			for (int i = 0; i < individualIdentify.size(); i++) {
				IndividualIdentification individualIdentifitionInfo = individualIdentify.get(i);
				individualIdentifitionInfo.set_individual(individual);
				addIndividualIdentificationInfo(individualIdentifitionInfo);
			}
		}
		
		return individual;
	}

	/**
	 * 添加个人party
	 */
	public Partys addIndividualPartyInfo(Individual individual) throws Exception{
		
		String indexId = Index.CreateNoncestr();
		individual.setPartyId(indexId);
		partyInfoDao.insertParty(individual);
		partyInfoDao.insertIndividualParty(individual);
		
		return individual;
	}

	/**
	 * 添加个人partyname
	 */
	public PartyName addIndividualPartyNameInfo(IndividualName individualName) throws Exception{
		
		String indexId = Index.CreateNoncestr();
		individualName.setPartyNameId(indexId);
		partyInfoDao.addPartyName(individualName);
		partyInfoDao.addIndividualPartyNameInfo(individualName);
		
		return individualName;
	}

	/**
	 * 添加个人认证信息
	 */
	public PartyIdentification addIndividualIdentificationInfo(IndividualIdentification individualIdentifitionInfo) throws Exception{
		
		String indexId = Index.CreateNoncestr();
		individualIdentifitionInfo.setPartyIdentificationId(indexId);
		partyInfoDao.addPartyIdentificationInfo(individualIdentifitionInfo);
		partyInfoDao.addIndividualIdentificationInfo(individualIdentifitionInfo);
		
		return individualIdentifitionInfo;
	}
	
}
