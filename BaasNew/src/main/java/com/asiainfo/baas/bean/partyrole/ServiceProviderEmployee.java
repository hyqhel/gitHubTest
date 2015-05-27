package com.asiainfo.baas.bean.partyrole;

import com.asiainfo.baas.bean.common.*;

/**
 * A Service ProviderEmployee is an Individual who is employed by the Service Provider. This is modeled as an Individual playing the role of ServiceProviderEmployee . A ServiceProviderEmployee is a type of PartyRole.
 */
public class ServiceProviderEmployee extends PartyRole {

	/**
	 * Permitted Values: employed, suspended, resource-rebalanced
	 */
	public String employmentStatus;
	/**
	 * A unique identification number for an employee.
	 */
	public String employeeNr;
	/**
	 * The current fixed regular payment, typically paid on a monthly or biweekly basis but often expressed as an annual sum, made by an employer to an employee.
	 */
	public String currentSalary;
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getEmployeeNr() {
		return employeeNr;
	}
	public void setEmployeeNr(String employeeNr) {
		this.employeeNr = employeeNr;
	}
	public String getCurrentSalary() {
		return currentSalary;
	}
	public void setCurrentSalary(String currentSalary) {
		this.currentSalary = currentSalary;
	}
	
	
}
