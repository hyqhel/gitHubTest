package com.asiainfo.baas.bean.identification;

/**
 * A portable document, typically a plasticized card with digitally-embedded information, that someone is required or encouraged to carry as a means of confirming their identity.
 */
public class NationalIdentityCardIdentification extends
		IndividualIdentification {

	/**
	 * A number assigned to a NationalIdentityCard used to identify it.
	 */
	public String cardNr;

	public String getCardNr() {
		return cardNr;
	}

	public void setCardNr(String cardNr) {
		this.cardNr = cardNr;
	}
	
	
}