package com.asiainfo.baas.bean.common;

import com.asiainfo.baas.bean.identification.*;
import java.util.*;
import com.asiainfo.baas.bean.partyname.*;
import com.asiainfo.baas.bean.*;

/**
 * Represents a single human being (a man, woman or child).
 * The individual could be a customer, an employee or any other person that the organization needs to store information about. An Individual is a type of Party.
 */
public class Individual extends Partys {

	public List<IndividualIdentification> _individualIdentification;
	public IndividualName _individualName;
	/**
	 * Birth date and death date.
	 */
	public String aliveDuring;
	/**
	 * Notes:  Used for legal requirements (special billing, special products, special contact media, such as Braille). This should be modeled as a separate entity but is shown as an attribute for brevity.
	 */
	public String disabilities;
	/**
	 * A socially constructed role that implies behaviours, activities, and attributes.
	 */
	public String gender;
	/**
	 * Permitted Values: married, never married, divorced, widowed
	 */
	public String maritalStatus;
	/**
	 * Note: Pointer to a country object
	 */
	public String nationality;
	/**
	 * Note: Pointer to a Location object City, town name etc.
	 */
	public String placeOfBirth;
	/**
	 * Note: Probably only used for employees, but is really role independent This should be modeled as a separate entity but is shown as an attribute for brevity
	 */
	public String skills;
	
	public List<IndividualIdentification> get_individualIdentification() {
		return _individualIdentification;
	}
	public void set_individualIdentification(
			List<IndividualIdentification> _individualIdentification) {
		this._individualIdentification = _individualIdentification;
	}
	public IndividualName get_individualName() {
		return _individualName;
	}
	public void set_individualName(IndividualName _individualName) {
		this._individualName = _individualName;
	}
	public String getAliveDuring() {
		return aliveDuring;
	}
	public void setAliveDuring(String aliveDuring) {
		this.aliveDuring = aliveDuring;
	}
	public String getDisabilities() {
		return disabilities;
	}
	public void setDisabilities(String disabilities) {
		this.disabilities = disabilities;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
}
