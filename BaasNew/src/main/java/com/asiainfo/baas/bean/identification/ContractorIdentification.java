package com.asiainfo.baas.bean.identification;

/**
 * A document used to identify a contractor.
 */
public class ContractorIdentification extends IndividualIdentification {

	/**
	 * A number assigned to a contractor used to identify it.
	 */
	public String contractorNr;

	public String getContractorNr() {
		return contractorNr;
	}

	public void setContractorNr(String contractorNr) {
		this.contractorNr = contractorNr;
	}
	
	
}
