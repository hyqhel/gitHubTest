package com.asiainfo.baas.bean.contact;

import com.asiainfo.baas.bean.common.*;
import java.util.*;

/**
 * A means by which communication may be established with a party (individual or organization).Contact Medium is an abstract concept that should be subclassed as required.
 */
public abstract class ContactMedium {

	public List<PartyRole> _partyRole;
	/**
	 * The time period that the Contact Medium is valid for.
	 */
	public String validFor;
}
