package com.asiainfo.baas.bean.identification;

/**
 * A medium, such as a card or document, used to identify an employee.
 */
public class EmployeeIdentification extends IndividualIdentification {

	/**
	 * A number assigned to a employee used to identify the individual.
	 */
	public String employeeNr;
	/**
	 * A picture or likeness obtained by photography.
	 */
	public String photo;
	public String getEmployeeNr() {
		return employeeNr;
	}
	public void setEmployeeNr(String employeeNr) {
		this.employeeNr = employeeNr;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
